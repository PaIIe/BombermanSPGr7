<!DOCTYPE html>
<html class="" lang="en">
<head prefix="og: http://ogp.me/ns#">
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="object" property="og:type">
<meta content="GitLab" property="og:site_name">
<meta content="management-server.md · master · Group5Productions / bomberman-protocol" property="og:title">
<meta content="GitLab.com" property="og:description">
<meta content="https://gitlab.com/assets/gitlab_logo-7ae504fe4f68fdebb3c2034e36621930cd36ea87924c11ff65dbcb8ed50dca58.png" property="og:image">
<meta content="https://gitlab.com/group5productions/bomberman-protocol/blob/master/management-server.md" property="og:url">
<meta content="summary" property="twitter:card">
<meta content="management-server.md · master · Group5Productions / bomberman-protocol" property="twitter:title">
<meta content="GitLab.com" property="twitter:description">
<meta content="https://gitlab.com/assets/gitlab_logo-7ae504fe4f68fdebb3c2034e36621930cd36ea87924c11ff65dbcb8ed50dca58.png" property="twitter:image">

<title>management-server.md · master · Group5Productions / bomberman-protocol · GitLab</title>
<meta content="GitLab.com" name="description">
<link rel="shortcut icon" type="image/x-icon" href="/assets/favicon-075eba76312e8421991a0c1f89a89ee81678bcde72319dd3e8047e2a47cd3a42.ico" />
<link rel="stylesheet" media="all" href="/assets/application-3850d331f06aaf20cf271289e74ddafc6dea83657cb9bd3975a09b33da9f73da.css" />
<link rel="stylesheet" media="print" href="/assets/print-87bb95ae825e1039facb71c62197dad696049012bb8cfeb76bb57c3a4aa865a6.css" />
<script src="/assets/webpack/runtime.5e07c212f03feb657e7a.bundle.js"></script>
<script src="/assets/webpack/common.35ff1a30b49ffd9c425f.bundle.js"></script>
<script src="/assets/webpack/main.d5663d863a68c66e2679.bundle.js"></script>

<meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="FwEMTemTNwOeQJw9rwKv0qskmvxF/PcLr/EfGMlLIVXIHqc9etXHkXJTLrcxwCWM71n71JtAz6mMFKnNiCGD+Q==" />
<meta content="origin-when-cross-origin" name="referrer">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
<meta content="#474D57" name="theme-color">
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-iphone-5a9cee0e8a51212e70b90c87c12f382c428870c0ff67d1eb034d884b78d2dae7.png" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-ipad-a6eec6aeb9da138e507593b464fdac213047e49d3093fc30e90d9a995df83ba3.png" sizes="76x76" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-iphone-retina-72e2aadf86513a56e050e7f0f2355deaa19cc17ed97bbe5147847f2748e5a3e3.png" sizes="120x120" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-ipad-retina-8ebe416f5313483d9c1bc772b5bbe03ecad52a54eba443e5215a22caed2a16a2.png" sizes="152x152" />
<link color="rgb(226, 67, 41)" href="/assets/logo-d36b5212042cebc89b96df4bf6ac24e43db316143e89926c0db839ff694d2de4.svg" rel="mask-icon">
<meta content="/assets/msapplication-tile-1196ec67452f618d39cdd85e2e3a542f76574c071051ae7effbfde01710eb17d.png" name="msapplication-TileImage">
<meta content="#30353E" name="msapplication-TileColor">


<!-- Piwik -->
<script>
  var _paq = _paq || [];
  _paq.push(['trackPageView']);
  _paq.push(['enableLinkTracking']);
  (function() {
    var u="//piwik.gitlab.com/";
    _paq.push(['setTrackerUrl', u+'piwik.php']);
    _paq.push(['setSiteId', 1]);
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
    g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
  })();
</script>
<noscript><p><img src="//piwik.gitlab.com/piwik.php?idsite=1" style="border:0;" alt="" /></p></noscript>
<!-- End Piwik Code -->


</head>

<body class="" data-group="" data-page="projects:blob:show" data-project="bomberman-protocol">
<script>
//<![CDATA[
window.gon={};gon.api_version="v3";gon.default_avatar_url="https:\/\/gitlab.com\/assets\/no_avatar-849f9c04a3a0d0cea2424ae97b27447dc64a7dbfae83c036c45b403392f0e8ba.png";gon.max_file_size=10;gon.asset_host=null;gon.relative_url_root="";gon.shortcuts_path="\/help\/shortcuts";gon.user_color_scheme="white";gon.katex_css_url="\/assets\/katex-e46cafe9c3fa73920a7c2c063ee8bb0613e0cf85fd96a3aea25f8419c4bfcfba.css";gon.katex_js_url="\/assets\/katex-04bcf56379fcda0ee7c7a63f71d0fc15ffd2e014d017cd9d51fd6554dfccf40a.js";
//]]>
</script>
<header class="navbar navbar-gitlab with-horizontal-nav">
<a class="sr-only gl-accessibility" href="#content-body" tabindex="1">Skip to content</a>
<div class="container-fluid">
<div class="header-content">
<div class="dropdown global-dropdown">
<button class="global-dropdown-toggle" data-toggle="dropdown" type="button">
<span class="sr-only">Toggle navigation</span>
<i class="fa fa-bars"></i>
</button>
<div class="dropdown-menu-nav global-dropdown-menu">
<ul>
<li class="home"><a title="Projects" href="/explore"><span>
Projects
</span>
</a></li><li class=""><a title="Groups" href="/explore/groups"><span>
Groups
</span>
</a></li><li class=""><a title="Snippets" href="/explore/snippets"><span>
Snippets
</span>
</a></li><li class=""><a title="Help" href="/help"><span>
Help
</span>
</a></li></ul>

</div>
</div>
<button class="navbar-toggle" type="button">
<span class="sr-only">Toggle navigation</span>
<i class="fa fa-ellipsis-v"></i>
</button>
<div class="navbar-collapse collapse">
<ul class="nav navbar-nav">
<li class="hidden-sm hidden-xs">
<div class="has-location-badge search search-form">
<form class="navbar-form" action="/search" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="&#x2713;" /><div class="search-input-container">
<div class="location-badge">This project</div>
<div class="search-input-wrap">
<div class="dropdown" data-url="/search/autocomplete">
<input type="search" name="search" id="search" placeholder="Search" class="search-input dropdown-menu-toggle no-outline js-search-dashboard-options" spellcheck="false" tabindex="1" autocomplete="off" data-toggle="dropdown" data-issues-path="https://gitlab.com/dashboard/issues" data-mr-path="https://gitlab.com/dashboard/merge_requests" />
<div class="dropdown-menu dropdown-select">
<div class="dropdown-content"><ul>
<li>
<a class="is-focused dropdown-menu-empty-link">
Loading...
</a>
</li>
</ul>
</div><div class="dropdown-loading"><i class="fa fa-spinner fa-spin"></i></div>
</div>
<i class="search-icon"></i>
<i class="clear-icon js-clear-input"></i>
</div>
</div>
</div>
<input type="hidden" name="group_id" id="group_id" class="js-search-group-options" />
<input type="hidden" name="project_id" id="search_project_id" value="2389918" class="js-search-project-options" data-project-path="bomberman-protocol" data-name="bomberman-protocol" data-issues-path="/group5productions/bomberman-protocol/issues" data-mr-path="/group5productions/bomberman-protocol/merge_requests" />
<input type="hidden" name="search_code" id="search_code" value="true" />
<input type="hidden" name="repository_ref" id="repository_ref" value="master" />

<div class="search-autocomplete-opts hide" data-autocomplete-path="/search/autocomplete" data-autocomplete-project-id="2389918" data-autocomplete-project-ref="master"></div>
</form></div>

</li>
<li class="visible-sm-inline-block visible-xs-inline-block">
<a title="Search" aria-label="Search" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/search"><i class="fa fa-search"></i>
</a></li>
<li>
<div>
<a class="btn btn-sign-in btn-success" href="/users/sign_in?redirect_to_referer=yes">Sign in</a>
</div>
</li>
</ul>
</div>
<div class="header-logo">
<a class="home" title="Dashboard" id="logo" href="/"><svg width="28" height="28" class="tanuki-logo" viewBox="0 0 36 36">
  <path class="tanuki-shape tanuki-left-ear" fill="#e24329" d="M2 14l9.38 9v-9l-4-12.28c-.205-.632-1.176-.632-1.38 0z"/>
  <path class="tanuki-shape tanuki-right-ear" fill="#e24329" d="M34 14l-9.38 9v-9l4-12.28c.205-.632 1.176-.632 1.38 0z"/>
  <path class="tanuki-shape tanuki-nose" fill="#e24329" d="M18,34.38 3,14 33,14 Z"/>
  <path class="tanuki-shape tanuki-left-eye" fill="#fc6d26" d="M18,34.38 11.38,14 2,14 6,25Z"/>
  <path class="tanuki-shape tanuki-right-eye" fill="#fc6d26" d="M18,34.38 24.62,14 34,14 30,25Z"/>
  <path class="tanuki-shape tanuki-left-cheek" fill="#fca326" d="M2 14L.1 20.16c-.18.565 0 1.2.5 1.56l17.42 12.66z"/>
  <path class="tanuki-shape tanuki-right-cheek" fill="#fca326" d="M34 14l1.9 6.16c.18.565 0 1.2-.5 1.56L18 34.38z"/>
</svg>

</a></div>
<h1 class="title"><span><a href="/group5productions">Group5Productions</a></span> / <a class="project-item-select-holder" href="/group5productions/bomberman-protocol">bomberman-protocol</a></h1>
<div class="js-dropdown-menu-projects">
<div class="dropdown-menu dropdown-select dropdown-menu-projects">
<div class="dropdown-title"><span>Go to a project</span><button class="dropdown-title-button dropdown-menu-close" aria-label="Close" type="button"><i class="fa fa-times dropdown-menu-close-icon"></i></button></div>
<div class="dropdown-input"><input type="search" id="" class="dropdown-input-field" placeholder="Search your projects" autocomplete="off" /><i class="fa fa-search dropdown-input-search"></i><i role="button" class="fa fa-times dropdown-input-clear js-dropdown-input-clear"></i></div>
<div class="dropdown-content"></div>
<div class="dropdown-loading"><i class="fa fa-spinner fa-spin"></i></div>
</div>
</div>

</div>
</div>
</header>

<script>
  var findFileURL = "/group5productions/bomberman-protocol/find_file/master";
</script>

<div class="page-with-sidebar">
<div class="layout-nav">
<div class="container-fluid">
<div class="scrolling-tabs-container">
<div class="fade-left">
<i class="fa fa-angle-left"></i>
</div>
<div class="fade-right">
<i class="fa fa-angle-right"></i>
</div>
<ul class="nav-links scrolling-tabs">
<li class="home"><a title="Project" class="shortcuts-project" href="/group5productions/bomberman-protocol"><span>
Project
</span>
</a></li><li class="active"><a title="Repository" class="shortcuts-tree" href="/group5productions/bomberman-protocol/tree/master"><span>
Repository
</span>
</a></li><li class=""><a title="Container Registry" class="shortcuts-container-registry" href="/group5productions/bomberman-protocol/container_registry"><span>
Registry
</span>
</a></li><li class=""><a title="Issues" class="shortcuts-issues" href="/group5productions/bomberman-protocol/issues"><span>
Issues
<span class="badge count issue_counter">0</span>
</span>
</a></li><li class=""><a title="Merge Requests" class="shortcuts-merge_requests" href="/group5productions/bomberman-protocol/merge_requests"><span>
Merge Requests
<span class="badge count merge_counter">0</span>
</span>
</a></li><li class=""><a title="Pipelines" class="shortcuts-pipelines" href="/group5productions/bomberman-protocol/pipelines"><span>
Pipelines
</span>
</a></li><li class=""><a title="Wiki" class="shortcuts-wiki" href="/group5productions/bomberman-protocol/wikis/home"><span>
Wiki
</span>
</a></li><li class=""><a title="Settings" class="shortcuts-tree" href="/group5productions/bomberman-protocol/settings/members"><span>
Settings
</span>
</a></li><li class="hidden">
<a title="Activity" class="shortcuts-project-activity" href="/group5productions/bomberman-protocol/activity"><span>
Activity
</span>
</a></li>
<li class="hidden">
<a title="Network" class="shortcuts-network" href="/group5productions/bomberman-protocol/network/master">Graph
</a></li>
<li class="hidden">
<a title="Charts" class="shortcuts-repository-charts" href="/group5productions/bomberman-protocol/graphs/master/charts">Charts
</a></li>
<li class="hidden">
<a class="shortcuts-new-issue" href="/group5productions/bomberman-protocol/issues/new">Create a new issue
</a></li>
<li class="hidden">
<a title="Jobs" class="shortcuts-builds" href="/group5productions/bomberman-protocol/builds">Jobs
</a></li>
<li class="hidden">
<a title="Commits" class="shortcuts-commits" href="/group5productions/bomberman-protocol/commits/master">Commits
</a></li>
<li class="hidden">
<a title="Issue Boards" class="shortcuts-issue-boards" href="/group5productions/bomberman-protocol/boards">Issue Boards</a>
</li>
</ul>
</div>

</div>
</div>
<div class="content-wrapper page-with-layout-nav">
<div class="scrolling-tabs-container sub-nav-scroll">
<div class="fade-left">
<i class="fa fa-angle-left"></i>
</div>
<div class="fade-right">
<i class="fa fa-angle-right"></i>
</div>

<div class="nav-links sub-nav scrolling-tabs">
<ul class="container-fluid container-limited">
<li class="active"><a href="/group5productions/bomberman-protocol/tree/master">Files
</a></li><li class=""><a href="/group5productions/bomberman-protocol/commits/master">Commits
</a></li><li class=""><a href="/group5productions/bomberman-protocol/branches">Branches
</a></li><li class=""><a href="/group5productions/bomberman-protocol/tags">Tags
</a></li><li class=""><a href="/group5productions/bomberman-protocol/graphs/master">Contributors
</a></li><li class=""><a href="/group5productions/bomberman-protocol/network/master">Graph
</a></li><li class=""><a href="/group5productions/bomberman-protocol/compare?from=master&amp;to=master">Compare
</a></li><li class=""><a href="/group5productions/bomberman-protocol/graphs/master/charts">Charts
</a></li><li class=""><a href="/group5productions/bomberman-protocol/path_locks">Locked Files
</a></li></ul>
</div>
</div>

<div class="alert-wrapper">


<div class="flash-container flash-container-page">
</div>


</div>
<div class=" ">
<div class="content" id="content-body">

<div class="container-fluid container-limited">

<div class="tree-holder" id="tree-holder">
<div class="nav-block">
<div class="tree-ref-holder">
<form class="project-refs-form" action="/group5productions/bomberman-protocol/refs/switch" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="destination" id="destination" value="blob" />
<input type="hidden" name="path" id="path" value="management-server.md" />
<div class="dropdown">
<button class="dropdown-menu-toggle js-project-refs-dropdown" type="button" data-toggle="dropdown" data-selected="master" data-ref="master" data-refs-url="/group5productions/bomberman-protocol/refs" data-field-name="ref" data-submit-form-on-click="true"><span class="dropdown-toggle-text ">master</span><i class="fa fa-chevron-down"></i></button>
<div class="dropdown-menu dropdown-menu-selectable">
<div class="dropdown-title"><span>Switch branch/tag</span><button class="dropdown-title-button dropdown-menu-close" aria-label="Close" type="button"><i class="fa fa-times dropdown-menu-close-icon"></i></button></div>
<div class="dropdown-input"><input type="search" id="" class="dropdown-input-field" placeholder="Search branches and tags" autocomplete="off" /><i class="fa fa-search dropdown-input-search"></i><i role="button" class="fa fa-times dropdown-input-clear js-dropdown-input-clear"></i></div>
<div class="dropdown-content"></div>
<div class="dropdown-loading"><i class="fa fa-spinner fa-spin"></i></div>
</div>
</div>
</form>
</div>
<ul class="breadcrumb repo-breadcrumb">
<li>
<a href="/group5productions/bomberman-protocol/tree/master">bomberman-protocol
</a></li>
<li>
<a href="/group5productions/bomberman-protocol/blob/master/management-server.md"><strong>
management-server.md
</strong>
</a>
</li>
</ul>
</div>
<ul class="blob-commit-info hidden-xs">
<li class="commit flex-list js-toggle-container" id="commit-a032a9a8">
<div class="avatar-cell hidden-xs">
<a href="/pokehax-co"><img class="avatar has-tooltip s36 hidden-xs" alt="Thomas Weber&#39;s avatar" title="Thomas Weber" data-container="body" src="https://secure.gravatar.com/avatar/b3f3643a34da39bdde39030c71875214?s=72&amp;d=identicon" /></a>
</div>
<div class="commit-detail">
<div class="commit-content">
<a class="commit-row-message item-title" href="/group5productions/bomberman-protocol/commit/a032a9a82611224b155e3f6178c9d181bfe26393">management-server: Ermögliche das Aktualisieren von Zustandsinformationen durch die Spieleserver</a>
<span class="commit-row-message visible-xs-inline">
&middot;
a032a9a8
</span>
<div class="commiter">
<a class="commit-author-link has-tooltip" title="pokehaxco@gmail.com" href="/pokehax-co">Thomas Weber</a>
committed
<time class="js-timeago" title="Mar 3, 2017 6:23pm" datetime="2017-03-03T18:23:52Z" data-toggle="tooltip" data-placement="top" data-container="body">Mar 03, 2017</time>
</div>
</div>
<div class="commit-actions flex-row hidden-xs">
<button class="btn btn-clipboard btn-transparent" data-toggle="tooltip" data-placement="bottom" data-container="body" data-clipboard-text="a032a9a82611224b155e3f6178c9d181bfe26393" data-title="Copy commit SHA to clipboard" type="button" title="Copy commit SHA to clipboard"><i aria-hidden="true" class="fa fa-clipboard"></i></button>
<a class="commit-short-id btn btn-transparent" href="/group5productions/bomberman-protocol/commit/a032a9a82611224b155e3f6178c9d181bfe26393">a032a9a8</a>

</div>
</div>
</li>

</ul>
<div class="blob-content-holder" id="blob-content-holder">
<article class="file-holder">
<div class="js-file-title file-title-flex-parent">
<div class="file-header-content">
<i class="fa fa-file-text-o fa-fw"></i>
<strong class="file-title-name">
management-server.md
</strong>
<small>
9.38 KB
</small>
</div>
<div class="file-actions hidden-xs">
<div class="btn-group" role="group"><a class="btn btn-sm" target="_blank" href="/group5productions/bomberman-protocol/raw/master/management-server.md">Raw</a><a class="btn btn-sm" href="/group5productions/bomberman-protocol/blame/master/management-server.md">Blame</a><a class="btn btn-sm" href="/group5productions/bomberman-protocol/commits/master/management-server.md">History</a><a class="btn btn-sm js-data-file-blob-permalink-url" href="/group5productions/bomberman-protocol/blob/a1b850c2012d37c447fb4635ec55bbe6a85c3308/management-server.md">Permalink</a></div>
<script>
  PathLocks.init(
    '/group5productions/bomberman-protocol/path_locks/toggle',
    'management-server.md'
  );
</script>

</div>
</div>
<div class="file-content wiki">
<h1 dir="auto">&#x000A;<a id="user-content-verwaltungsserver" class="anchor" href="#verwaltungsserver" aria-hidden="true"></a>Verwaltungsserver</h1>&#x000A;&#x000A;<p dir="auto">Über den Verwaltungsserver können Spieleserver gefunden werden, die momentan online sind (indem sie stets mit dem Verwaltungsserver in Verbindung bleiben) und neue Spieler akzeptieren (es findet keine Runde auf dem Spieleserver statt).</p>&#x000A;&#x000A;<p dir="auto">Der Verwaltungsserver hört stets auf folgendem Port: <strong>51963</strong>.</p>&#x000A;&#x000A;<p dir="auto">Folgende Kommandos und Antworten sind für den Verwaltungsserver definiert:</p>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-einen-spieleserver-registrieren" class="anchor" href="#einen-spieleserver-registrieren" aria-hidden="true"></a>Einen Spieleserver registrieren</h2>&#x000A;&#x000A;<p dir="auto"><strong>Bezeichner des Kommandos</strong>: <code>msRegisterGameServer</code></p>&#x000A;&#x000A;<p dir="auto"><strong>Verwendung durch</strong>: Spieleserver</p>&#x000A;&#x000A;<p dir="auto"><strong>Antwort</strong>: keine</p>&#x000A;&#x000A;<p dir="auto">Dieses Kommando registriert einen Spieleserver an einem Verwaltungsserver. Idealerweise registriert sich der Spieleserver selbst mit seinen Verbindungsdaten am Verwaltungsserver. Ein Spieleserver ist über einen <em>TCP-Port</em> sowie über eine <em>IPv4-Adresse</em> oder eine <em>IPv6-Adresse</em> oder einen <em>Hostnamen</em> erreichbar, wobei diese Informationen den Spielern versteckt werden dürfen. Den Spielern wird aber stets der Name, den der Serveradministrator dem Spieleserver zugewiesen hat, angezeigt.</p>&#x000A;&#x000A;<p dir="auto">Der TCP-Port kann beliebig mit 2 Ausnahmen gesetzt werden. Die Ausnahmen sind 0 (in TCP/IP als reserviert gekennzeichnet) und 51963 (da für den Verwaltungsserver verwendet). Es ist Abstand von Ports zu nehmen, die für bekannte Internetdienste verwendet werden, z.B. 21 (FTP), 80 (HTTP) oder 443 (HTTPS).</p>&#x000A;&#x000A;<p dir="auto">Sieht die Adresse des Spieleservers wie eine IP-Adresse (v4 oder v6) aus, ist aber keine gültige IP-Adresse, so wird das Kommando vom Server verworfen.</p>&#x000A;&#x000A;<p dir="auto">Pro Verbindung zum Verwaltungsserver kann nur ein einziger Spieleserver registriert werden. Ein existierender Spieleserver für eine Verbindung wird überschrieben.</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-zusätzlich-übermittelte-daten-content" class="anchor" href="#zus%C3%A4tzlich-%C3%BCbermittelte-daten-content" aria-hidden="true"></a>Zusätzlich übermittelte Daten (<code>content</code>)</h3>&#x000A;&#x000A;<ul dir="auto">&#x000A;<li>&#x000A;<code>serverName</code> (String): Ein beliebiger Name, der den Spielern zur Identifierung des Servers angezeigt wird. Er darf nicht leer sein.</li>&#x000A;<li>&#x000A;<code>serverAddress</code> (String): Adresse (IPv4, IPv6 oder Hostname), unter der der Spieleserver erreichbar ist.</li>&#x000A;<li>&#x000A;<code>serverPort</code> (Integer): Port zur Adresse, unter der Spieleserver erreichbar ist. Wertebereich: 1-65535, ohne 51963.</li>&#x000A;</ul>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-beispiele" class="anchor" href="#beispiele" aria-hidden="true"></a>Beispiele</h3>&#x000A;&#x000A;<p dir="auto">Beispiel 1: Wir möchten einen Spieleserver namens "Feuerland" registrieren, der unter einer IPv4-Adresse auf dem Port 20000 erreichbar ist:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msRegisterGameServer"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"serverName"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Feuerland"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"serverAddress"</span><span class="p">:</span><span class="w"> </span><span class="s2">"192.168.0.10"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"serverPort"</span><span class="p">:</span><span class="w"> </span><span class="mi">20000</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel 2: Wir möchten einen Spieleserver namens "Server von Bösewicht" registrieren, der unter einer IPv6-Adresse (lokales Präfix) auf dem Port 30000 erreichbar ist:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msRegisterGameServer"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"serverName"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Server von Bösewicht"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"serverAddress"</span><span class="p">:</span><span class="w"> </span><span class="s2">"fd40:9dc7:b528::1"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"serverPort"</span><span class="p">:</span><span class="w"> </span><span class="mi">30000</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel 3: Wir möchten einen Spieleserver namens "轰炸机人" registrieren, der unter einem Hostnamen auf dem Port 40000 erreichbar ist:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msRegisterGameServer"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"serverName"</span><span class="p">:</span><span class="w"> </span><span class="s2">"轰炸机人"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"serverAddress"</span><span class="p">:</span><span class="w"> </span><span class="s2">"bombergame.example.org"</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"serverPort"</span><span class="p">:</span><span class="w"> </span><span class="mi">40000</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-zustand-eines-spieleservers-aktualisieren" class="anchor" href="#zustand-eines-spieleservers-aktualisieren" aria-hidden="true"></a>Zustand eines Spieleservers aktualisieren</h2>&#x000A;&#x000A;<p dir="auto"><strong>Bezeichner des Kommandos</strong>: <code>msUpdateGameServerStats</code></p>&#x000A;&#x000A;<p dir="auto"><strong>Verwendung durch</strong>: Spieleserver</p>&#x000A;&#x000A;<p dir="auto"><strong>Antwort</strong>: keine</p>&#x000A;&#x000A;<p dir="auto">Dieses Kommando aktualisiert Informationen, die dem Verwaltungsserver über den momentanen Zustand des Spieleservers bekannt sind. Diese Informationen lauten:</p>&#x000A;&#x000A;<ul dir="auto">&#x000A;<li>Wie viele Spieler sind auf dem Server angemeldet?</li>&#x000A;<li>Wie viele Spieler sind in einer Runde zulässig?</li>&#x000A;<li>Ist die Lobby des Servers offen oder findet gerade eine Spielerunde statt?</li>&#x000A;<li>Welcher Spielmodus ist aktiv?</li>&#x000A;</ul>&#x000A;&#x000A;<p dir="auto">Die Informationen werden bei einer erneuten Anforderung durch die Clients an diese übertragen.</p>&#x000A;&#x000A;<p dir="auto">Der Spieleserver muss vorher am Verwaltungsserver angemeldet sein. Ansonsten wird dieses Kommando durch den Verwaltungsserver verworfen.</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-zusätzlich-übermittelte-daten-content-1" class="anchor" href="#zus%C3%A4tzlich-%C3%BCbermittelte-daten-content-1" aria-hidden="true"></a>Zusätzlich übermittelte Daten (<code>content</code>)</h3>&#x000A;&#x000A;<ul dir="auto">&#x000A;<li>&#x000A;<code>players</code> (Objekt):&#x000A;&#x000A;<ul>&#x000A;<li>&#x000A;<code>current</code> (Integer): Anzahl der momentan auf dem Server angemeldeten Spieler. Diese darf größer als die maximal zulässige Spielerzahl sein, um anzudeuten, dass weitere Spieler in der Warteschlange stehen.</li>&#x000A;<li>&#x000A;<code>max</code> (Integer): Anzahl der maximal in einer Runde zugelassenen Spieler. Diese beträgt 2, 3 oder 4.</li>&#x000A;</ul>&#x000A;</li>&#x000A;<li>&#x000A;<code>isLobbyOpen</code> (Boolean): Ist dieser Wert <code>true</code>, so ist die Lobby des Spieleservers offen. Damit können sich neue Spieler anmelden. Ansonsten ist dieser Wert <code>false</code>.</li>&#x000A;<li>&#x000A;<code>gameplayMode</code> (Integer): Dieser Wert ist 1, wenn der auf dem Spieleserver aktive Spielmodus "Standard A" ist, oder 2, wenn der aktive Spielmodus "Standard A+B" ist.</li>&#x000A;</ul>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-beispiel" class="anchor" href="#beispiel" aria-hidden="true"></a>Beispiel</h3>&#x000A;&#x000A;<p dir="auto">Beispiel: Auf dem Spieleserver sind 2 Spieler angemeldet. Für die nächste Runde sind 4 Spieler zugelassen, die Lobby ist momentan offen, und der Spielmodus ist "Standard A+B":</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msUpdateGameServerStats"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"players"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w"> </span><span class="s2">"current"</span><span class="p">:</span><span class="w"> </span><span class="mi">2</span><span class="p">,</span><span class="w"> </span><span class="s2">"max"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="w"> </span><span class="p">},</span><span class="w">&#x000A;        </span><span class="s2">"isLobbyOpen"</span><span class="p">:</span><span class="w"> </span><span class="kc">true</span><span class="p">,</span><span class="w">&#x000A;        </span><span class="s2">"gameplayMode"</span><span class="p">:</span><span class="w"> </span><span class="mi">2</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-einen-spieleserver-deregistrieren" class="anchor" href="#einen-spieleserver-deregistrieren" aria-hidden="true"></a>Einen Spieleserver deregistrieren</h2>&#x000A;&#x000A;<p dir="auto"><strong>Bezeichner des Kommandos</strong>: <code>msUnregisterGameServer</code></p>&#x000A;&#x000A;<p dir="auto"><strong>Verwendung durch</strong>: Spieleserver</p>&#x000A;&#x000A;<p dir="auto"><strong>Antwort</strong>: keine</p>&#x000A;&#x000A;<p dir="auto">Dieses Kommando deregistriert einen eventuell registrierten Spieleserver an einem Verwaltungsserver. Da nur ein Spieleserver pro Verbindung zulässig ist und der Verwaltungsserver jeder Verbindung einen Spieleserver zuweisen kann, muss der zu deregistrierende Spieleserver nicht übermittelt werden. (Damit ist es nicht möglich, fremde Spieleserver zu deregistrieren.)</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-zusätzlich-übermittelte-daten-content-2" class="anchor" href="#zus%C3%A4tzlich-%C3%BCbermittelte-daten-content-2" aria-hidden="true"></a>Zusätzlich übermittelte Daten (<code>content</code>)</h3>&#x000A;&#x000A;<p dir="auto">Keine.</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-beispiel-1" class="anchor" href="#beispiel-1" aria-hidden="true"></a>Beispiel</h3>&#x000A;&#x000A;<p dir="auto">Beispiel: Wir möchten unseren Spieleserver deregistrieren:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msUnregisterGameServer"</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-liste-registrierter-spieleserver-anfordern" class="anchor" href="#liste-registrierter-spieleserver-anfordern" aria-hidden="true"></a>Liste registrierter Spieleserver anfordern</h2>&#x000A;&#x000A;<p dir="auto"><strong>Bezeichner des Kommandos</strong>: <code>msQueryGameServers</code></p>&#x000A;&#x000A;<p dir="auto"><strong>Verwendung durch</strong>: Client</p>&#x000A;&#x000A;<p dir="auto"><strong>Antwort</strong>: <a href="#liste-registrierter-spieleserver">Liste registierter Spieleserver</a></p>&#x000A;&#x000A;<p dir="auto">Dieses Kommando weist den Verwaltungsserver an, eine Liste mit aktuell an ihm registrierten Spieleservern an den Sender zu übermitteln.</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-zusätzlich-übermittelte-daten-content-3" class="anchor" href="#zus%C3%A4tzlich-%C3%BCbermittelte-daten-content-3" aria-hidden="true"></a>Zusätzlich übermittelte Daten (<code>content</code>)</h3>&#x000A;&#x000A;<p dir="auto">Keine.</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-beispiel-2" class="anchor" href="#beispiel-2" aria-hidden="true"></a>Beispiel</h3>&#x000A;&#x000A;<p dir="auto">Beispiel: Wir möchten eine Liste von Spieleservern anfordern, die momentan am Verwaltungsserver registriert sind:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msQueryGameServers"</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Der Server sendet eine <a href="#liste-registrierter-spieleserver">Antwort</a>, dessen Format in den Beispielen abgebildet ist.</p>&#x000A;&#x000A;<h2 dir="auto">&#x000A;<a id="user-content-liste-registierter-spieleserver" class="anchor" href="#liste-registierter-spieleserver" aria-hidden="true"></a>Liste registierter Spieleserver</h2>&#x000A;&#x000A;<p dir="auto"><strong>Bezeichner der Antwort</strong>: <code>msRQueryGameServers</code></p>&#x000A;&#x000A;<p dir="auto"><strong>Verwendung durch</strong>: Client</p>&#x000A;&#x000A;<p dir="auto"><strong>Antwort auf</strong>: <a href="#liste-registrierter-spieleserver-anfordern">Liste registrierter Spieleserver anfordern</a></p>&#x000A;&#x000A;<p dir="auto">Der Verwaltungsserver sendet eine Liste von aktuell an ihm registrierten Spieleservern an den Sender, wenn er diese angefordert hat. Die Reihenfolge, in der die Server gelistet sind, ist nicht definiert.</p>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-zusätzlich-übermittelte-daten-content-4" class="anchor" href="#zus%C3%A4tzlich-%C3%BCbermittelte-daten-content-4" aria-hidden="true"></a>Zusätzlich übermittelte Daten (<code>content</code>)</h3>&#x000A;&#x000A;<ul dir="auto">&#x000A;<li>&#x000A;<code>servers</code> (Array): Liste von mindestens 0 Objekten, die am Verwaltungsserver registrierte Spieleserver enthält. Jedes Objekt hat folgende Form:&#x000A;&#x000A;<ul>&#x000A;<li>&#x000A;<code>name</code> (String): Name, der den Spielern zur Identifierung des Spieleservers angezeigt wird. Er muss identisch mit dem Namen sein, der dem Server durch das Kommando <a href="#einen-spieleserver-registrieren">Einen Spieleserver registrieren</a> zugewiesen wurde.</li>&#x000A;<li>&#x000A;<code>address</code> (String): IPv4-Adresse, IPv6-Adresse oder Hostname, unter dem der Spieleserver erreicht werden kann.</li>&#x000A;<li>&#x000A;<code>port</code> (Integer): Port, unter dem der Spieleserver an seiner Adresse erreicht werden kann. Wertebereich: 1-65535, ohne 51963.</li>&#x000A;<li>&#x000A;<code>players</code> (Objekt):</li>&#x000A;<li>&#x000A;<code>current</code> (Integer): Anzahl der momentan auf dem Server angemeldeten Spieler. Diese darf größer als die maximal zulässige Spielerzahl sein, um anzudeuten, dass weitere Spieler in der Warteschlange stehen.</li>&#x000A;<li>&#x000A;<code>max</code> (Integer): Anzahl der maximal in einer Runde zugelassenen Spieler. Diese beträgt 2, 3 oder 4.</li>&#x000A;<li>&#x000A;<code>isLobbyOpen</code> (Boolean): Ist dieser Wert <code>true</code>, so ist die Lobby des Spieleservers offen. Damit können sich neue Spieler anmelden. Ansonsten ist dieser Wert <code>false</code>.</li>&#x000A;<li>&#x000A;<code>gameplayMode</code> (Integer): Dieser Wert ist 1, wenn der auf dem Spieleserver aktive Spielmodus "Standard A" ist, oder 2, wenn der aktive Spielmodus "Standard A+B" ist.</li>&#x000A;</ul>&#x000A;</li>&#x000A;</ul>&#x000A;&#x000A;<h3 dir="auto">&#x000A;<a id="user-content-beispiele-1" class="anchor" href="#beispiele-1" aria-hidden="true"></a>Beispiele</h3>&#x000A;&#x000A;<p dir="auto">Beispiel 1: Am Verwaltungsserver sind keine Spieleserver registiert:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msRQueryGameServers"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"servers"</span><span class="p">:</span><span class="w"> </span><span class="p">[]</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel 2: Am Verwaltungsserver ist ein einziger Spieleserver registiert. An diesem ist ein Spieler angemeldet:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msRQueryGameServers"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"servers"</span><span class="p">:</span><span class="w"> </span><span class="p">[</span><span class="w">&#x000A;            </span><span class="p">{</span><span class="w"> </span><span class="s2">"name"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Feuerland"</span><span class="p">,</span><span class="w"> </span><span class="s2">"address"</span><span class="p">:</span><span class="w"> </span><span class="s2">"192.168.0.10"</span><span class="p">,</span><span class="w"> </span><span class="s2">"port"</span><span class="p">:</span><span class="w"> </span><span class="mi">20000</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"players"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w"> </span><span class="s2">"current"</span><span class="p">:</span><span class="w"> </span><span class="s2">"1"</span><span class="p">,</span><span class="w"> </span><span class="s2">"max"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="w"> </span><span class="p">},</span><span class="w"> </span><span class="s2">"isLobbyOpen"</span><span class="p">:</span><span class="w"> </span><span class="kc">true</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"gameplayMode"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="w"> </span><span class="p">}</span><span class="w">&#x000A;        </span><span class="p">]</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>&#x000A;&#x000A;<p dir="auto">Beispiel 2: Am Verwaltungsserver sind drei Spieleserver registiert:</p>&#x000A;&#x000A;<pre class="code highlight js-syntax-highlight json" lang="json" v-pre="true"><code><span class="p">{</span><span class="w">&#x000A;    </span><span class="s2">"command"</span><span class="p">:</span><span class="w"> </span><span class="s2">"msRQueryGameServers"</span><span class="p">,</span><span class="w">&#x000A;    </span><span class="s2">"content"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">&#x000A;        </span><span class="s2">"servers"</span><span class="p">:</span><span class="w"> </span><span class="p">[</span><span class="w">&#x000A;            </span><span class="p">{</span><span class="w"> </span><span class="s2">"name"</span><span class="p">:</span><span class="w"> </span><span class="s2">"轰炸机人"</span><span class="p">,</span><span class="w"> </span><span class="s2">"address"</span><span class="p">:</span><span class="w"> </span><span class="s2">"bombergame.example.org"</span><span class="p">,</span><span class="w"> </span><span class="s2">"port"</span><span class="p">:</span><span class="w"> </span><span class="mi">40000</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"players"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w"> </span><span class="s2">"current"</span><span class="p">:</span><span class="w"> </span><span class="s2">"22"</span><span class="p">,</span><span class="w"> </span><span class="s2">"max"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="w"> </span><span class="p">},</span><span class="w"> </span><span class="s2">"isLobbyOpen"</span><span class="p">:</span><span class="w"> </span><span class="kc">false</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"gameplayMode"</span><span class="p">:</span><span class="w"> </span><span class="mi">2</span><span class="w"> </span><span class="p">},</span><span class="w">&#x000A;            </span><span class="p">{</span><span class="w"> </span><span class="s2">"name"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Server von Bösewicht"</span><span class="p">,</span><span class="w"> </span><span class="s2">"address"</span><span class="p">:</span><span class="w"> </span><span class="s2">"fd40:9dc7:b528::1"</span><span class="p">,</span><span class="w"> </span><span class="s2">"port"</span><span class="p">:</span><span class="w"> </span><span class="mi">30000</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"players"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w"> </span><span class="s2">"current"</span><span class="p">:</span><span class="w"> </span><span class="s2">"3"</span><span class="p">,</span><span class="w"> </span><span class="s2">"max"</span><span class="p">:</span><span class="w"> </span><span class="mi">3</span><span class="w"> </span><span class="p">},</span><span class="w"> </span><span class="s2">"isLobbyOpen"</span><span class="p">:</span><span class="w"> </span><span class="kc">false</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"gameplayMode"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="w"> </span><span class="p">},</span><span class="w">&#x000A;            </span><span class="p">{</span><span class="w"> </span><span class="s2">"name"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Feuerland"</span><span class="p">,</span><span class="w"> </span><span class="s2">"address"</span><span class="p">:</span><span class="w"> </span><span class="s2">"192.168.0.10"</span><span class="p">,</span><span class="w"> </span><span class="s2">"port"</span><span class="p">:</span><span class="w"> </span><span class="mi">20000</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"players"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w"> </span><span class="s2">"current"</span><span class="p">:</span><span class="w"> </span><span class="s2">"1"</span><span class="p">,</span><span class="w"> </span><span class="s2">"max"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="w"> </span><span class="p">},</span><span class="w"> </span><span class="s2">"isLobbyOpen"</span><span class="p">:</span><span class="w"> </span><span class="kc">true</span><span class="p">,</span><span class="w">&#x000A;              </span><span class="s2">"gameplayMode"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="w"> </span><span class="p">}</span><span class="w">&#x000A;        </span><span class="p">]</span><span class="w">&#x000A;    </span><span class="p">}</span><span class="w">&#x000A;</span><span class="p">}</span><span class="w">&#x000A;</span></code></pre>
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

