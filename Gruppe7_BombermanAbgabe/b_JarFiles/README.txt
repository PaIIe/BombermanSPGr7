Bombermanprojekt - Gruppe 7 

		     -- README --
==========================================================

Vorab: Projekt ist nicht komplett funktionsfähig und es 
gibt zum Teil große Bugs.

----------------------------------------------------------

Es gibt zwei Client-Versionen: 
	-> Client.jar: nur die nackten Clients ohne Menü

	-> Client_Menue.jar: Client, wo unsere Menü-demo
	   eingebunden ist, Verbindung funktioniert nicht.

Start - Variante(1):
- Server.jar starten
- 2mal Client.jar starten

- es ist nur ein Bomberman(grün) steuerbar (vielleicht 
  etwas herumprobieren bis es klappt)
- irgendwann hängt sich dann der Client für den ersten
  Spieler auf (haben keine Lösung gefunden)
- mit 2. Client ist das Spiel aber spielbar
- wenn alle anderen Bomberman getötet wurde, wird Ranking
  angezeigt (dauert etwas)

ACHTUNG: Server beendet sich nicht; Prozess muss über Task-
	 Manager beendet werden!!!!

Start - Variante(2):
- Sever.jar starten
- Client_Menue.jar starten

- ist nur eine Demonstration des Menüs
- wenn der Server läuft: "Play" -> "Server 1", dann kann
  man seinen Namen eingeben und kommt in die Lobby
- wenn Sever nicht läuft: "Play" -> "Server 1", dann kommt
  eine Fehlermeldung "disconnected"

ACHTUNG: Server beendet sich nicht; Prozess muss über Task-
	 Manager beendet werden!!!!