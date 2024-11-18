<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="CSS/bootstrap-5.1.1-dist/js/bootstrap.min.js"></script>
    <link href="CSS/bootstrap-5.1.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <meta charset="ISO-8859-1">
    <title>Help</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Classroom</a>
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="navbar-item active"><a class="nav-link" href="index.jsp">Home page</a></li>
            <li class="navbar-item active"><a class="nav-link" href="chat.jsp">Chat</a></li>
            <li class="navbar-item active"><a class="nav-link" href="help.jsp">Help</a></li>
            <li class="navbar-item active"><a class="nav-link" href="ActionHandlers/LogoutHandler.jsp">Logout</a></li>
        </ul>
    </div>

</nav>
<div class="w-50 m-auto">
    <h2>Upustvo</h2>
    <p>Ideja ove aplikacije je da bude nalik aplikacijama Teams i Google Classroom.<br>
        Da bi aplikacija funkcionisala potrebno je da se pokrene mysql server i da se u fajlu Java
        Resources/Data/IProvider.java promeni url za konekciju sa bazom.<br>
        U fajlu JavaResources/Services/FilesManager potrebno je da se upise apsolutna adresa do foldera gde ce se
        skladistiti fajlovi i folderi Nakon uploada.<br>
        U fajlu WebContent/JS/Constants.js potrebno je da se upise adresa na kojoj se nalazi server. Obavezno dodati
        kosu crtu (/) na kraju<br>
        Potrebno je pokrenuti RMI servis koji se nalazi u JavaResources/RMI/RmiServer
        Da bi se koristila aplikacija potrebno je da korisnik bude prijavljen.<br>
        Nakon prijava korisnik se prosledjuje na stranu index.jsp gde vidi sve timove (predmete) u kojima se nalazi.
        Pored toga moze da napravi novi tim.<br>
        Klikom na zeljeni tim otvara se nova strana subjects.jsp gde korisnik vidi sve objave (postove) koje postoje za
        taj tim, i ima mogucnost da napravi novu objavu. Ako je korisnik
        kreator tog tima, on moze da dodaje nove korisnike u tim tako sto unese njihov mail.

        <br>
        Na stranici files.jsp korisnik vidi sve fajlove za izabrani tim. Kada se napravi tim, pravi se i folder gde se
        skladiste njegovi fajlovi. Svi korisnici imaju mogucnost da
        dodaju nove fajlove i nove foldere. <br>
        Korisnik ima mogucnost da menja lokaciju u fajlsistemu klikom na folder. Folder je obelezen plavom bojom.
        <br>Korisnik moze da preuzima fajlove klikom na dugme dovnload.
        <br>
        Na strani chat.jsp korisnik ima mogucnost da razmenjuje poruke sa ostalim korisnicima.
    </p>
</div>
</body>
</html>