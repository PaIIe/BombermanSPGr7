<!DOCTYPE html>
<html class="" lang="en">
<head prefix="og: http://ogp.me/ns#">
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="object" property="og:type">
<meta content="GitLab" property="og:site_name">
<meta content="protocol.md · master · Group5Productions / bomberman-protocol" property="og:title">
<meta content="GitLab.com" property="og:description">

<div class="file-content wiki">
<h1 dir="auto">&#x000A;<a id="user-content-grundlagen-des-protokolls" class="anchor" href="#grundlagen-des-protokolls" aria-hidden="true"></a>Grundlagen des Protokolls</h1>&#x000A;&#x000A;<p dir="auto">Sämtliche Kommunikation zwischen den Servern und den Clients wird über TCP/IP abgewickelt.</p>&#x000A;&#x000A;<p dir="auto">Auf welchen Ports die Server Verbindungen akzeptieren, ist den Spezifikationen der Server zu entnehmen.</p>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-format-für-nachrichten" class="anchor" href="#format-f%C3%BCr-nachrichten" aria-hidden="true"></a>Format für Nachrichten</h2>&#x000A;&#x000A;<p dir="auto"><strong>Nachrichten</strong> sind das elementare Grundelement, auf dem jede weiter spielespezifische Kommunikation aufbaut. Nachrichten werden als String codiertes JSON-Objekt über das Netzwerk übertragen. Dieser String hat folgendes Format:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight plaintext" lang="plaintext" v-pre="true"><code>[Länge des JSON-Strings, Basis 10, inklusive abschließendes Newline][JSON-String][Newline als 0x0A]&#x000A;</code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight plaintext" lang="plaintext" v-pre="true"><code>17{"Hallo":"Welt"}\n&#x000A;</code></pre>&#x000A;&#x000A;<p dir="auto">Der JSON-String <code>{"Hallo":"Welt"}</code> hat eine Länge von 16 Bytes, und das Newline-Zeichen wird als 1 Byte übertragen, daher wird die Summe 17 vor den String geschrieben. Das Newline-Zeichen wurde in obiger Repräsentation über sein Escape <code>\n</code> dargestellt; auf Netzwerkebene muss das Byte <code>0x0A</code> übertragen werden.</p>&#x000A;&#x000A;<p dir="auto">JSON-Strings werden grundsätzlich in <strong>UTF-8</strong> codiert. Treten Nicht-ASCII-Zeichen im String auf, so ist jedes Zeichen in so vielen Bytes zu zählen, wie für die Darstellung des Zeichens in UTF-8 benötigt werden. Beispiel:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight plaintext" lang="plaintext" v-pre="true"><code>31{"universität":"TU Chemnitz"}\n&#x000A;</code></pre>&#x000A;&#x000A;<p dir="auto">Obwohl sich im JSON-String (inklusive Newline) nur 30 Zeichen befinden, sind es dennoch 31 Bytes, da das kleine Ä in UTF-8 mit den Zeichen <code>0xC3</code> und <code>0xA4</code>, also 2 Bytes, codiert ist.</p>&#x000A;&#x000A;<p dir="auto">Um die Übertragungsgröße zu reduzieren, ist es empfohlen, unnötigen Whitespace im JSON-String auszulassen, es ist aber nicht unbedingt notwendig. Unabhängig davon dürfen implementierende Programme Nachrichten, dessen Inhalt 64 KiB (= 65536 Byte) überschreitet, ohne weitere Begründung ablehnen.</p>&#x000A;&#x000A;<p dir="auto">Für die Beschreibungen im weiteren Teil dieser Seite sowie anderen Seiten dieser Spezifikation wird JSON in lesbarer Struktur und ohne vorangestellte Länge dargestellt, um das Lesen zu vereinfachen.</p>&#x000A;&#x000A;<p dir="auto">Warum dieses Format?</p>&#x000A;&#x000A;<ul dir="auto">&#x000A;<li>Das Wurzelelement in JSON muss ein Objekt sein.</li>&#x000A;<li>Es ist notwendig, zu wissen, wie groß ein JSON-String ist, da wir nicht wissen, wann er endet.</li>&#x000A;<li>Eine maximale Übertragungsgröße ist notwendig, um den Speicherverbrauch der implementierenden Programme kontrollieren zu können.</li>&#x000A;<li>Ein abschließendes Newline erlaubt das zeilenweise Einlesen des JSON-Strings, da es sich um ansonsten lesbaren (ASCII-)Text handelt. JSON-Strings ohne Whitespace werden so in eine einzelne Zeile eingelesen.</li>&#x000A;</ul>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-kommandos-und-antworten" class="anchor" href="#kommandos-und-antworten" aria-hidden="true"></a>Kommandos und Antworten</h2>&#x000A;&#x000A;<p dir="auto">Es werden zwei Arten von Nachrichten unterschieden: <strong>Kommandos</strong> werden von Client zu Server gesendet, um Daten abzufragen oder zu verändern. <strong>Antworten</strong> werden von Server zu Client als Reaktion auf ausgewählte Kommandos gesendet.</p>&#x000A;&#x000A;<p dir="auto">Beide Arten von Nachrichten übermitteln aber das gleiche Format:</p>&#x000A;&#x000A;<ul dir="auto">&#x000A;<li>Jedes JSON-Objekt enthält einen Schlüssel <code>"command"</code>, welches das zu übermittelnde Kommando bzw. die zu übermittelnde Antwort beschreibt.</li>&#x000A;<li>Kommandos tragen Namen der Form <code>"[Serverkürzel][eigentlicher Name]"</code>.</li>&#x000A;<li>Antworten tragen Namen der Form <code>"[Serverkürzel]R[eigentlicher Name]"</code>.</li>&#x000A;<li>Es sind zwei Kürzel definiert: <code>ms</code> für den Verwaltungsserver, <code>gs</code> für den Spieleserver.</li>&#x000A;<li>Namen der Kommandos werden in CamelCase und Englisch, möglichst in der Form "Infinitivwerb + Substantiv", geschrieben.</li>&#x000A;<li>Müssen zusätzliche Daten zu einem Kommando oder einer Antwort übermittelt werden, so gibt es einen Schlüssel <code>"content"</code>, der ein JSON-Objekt enthält, welches mindestens ein Schlüssel-Wertepaar besitzt.</li>&#x000A;<li>Ist es nicht notwendig, weitere Daten zu einem Kommando oder zu einer Antwort zu übermitteln, <em>so darf der Schlüssel <code>"content"</code> <strong>nicht</strong> existieren.</em>&#x000A;</li>&#x000A;</ul>&#x000A;&#x000A;<p dir="auto">Beispiel 1: Wir möchten den Verwaltungsserver anweisen, eine Bratpfanne zu erhitzen, bevor wir Eierkuchen zubereiten können (der Server antwortet uns nicht):</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msPreheatPan"</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel 2: Wir möchten den Spieleserver anweisen, 2 Packungen Mehl im Supermarkt einzukaufen (der Server antwortet uns nicht):</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"gsBuyIngredient"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"ingredient"</span><span class="p">:</span><span class="w"> </span><span class="s2">"flour"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"pieces"</span><span class="p">:</span><span class="w"> </span><span class="mi">2</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel 3: Wir möchten den Verwaltungsserver fragen, für wie viele Stück Eierkuchen unsere Zutaten ausreichen.</p>&#x000A;&#x000A;<p dir="auto">Der Client sendet:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msEstimateCrepeProduction"</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Der Server antwortet:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msREstimateCrepeProduction"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"pieces"</span><span class="p">:</span><span class="w"> </span><span class="mi">7</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>
</div>

</article>
</div>

</div>
</div>

</div>
</div>
</div>
</div>



</body>
</html>
