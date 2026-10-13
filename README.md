Sänka Skepp — Nätverksbaserat spel i Java

Gruppuppgift i kursen Avancerad Javaprogrammering: en självspelande version av Sänka Skepp där en server- och en klientinstans spelar mot varandra över nätverket enligt ett egendesignat kommunikationsprotokoll (meddelandeformat: [kod] shot [koordinat]).

Mitt ansvar: nätverkskommunikation (NetworkCommunications, server/klient-uppkoppling via sockets), trådsäkring med synchronized, samt delar av JavaFX-gränssnittet.

Tech stack: Java, JavaFX, sockets (TCP), multitrådning.

Betyg: Väl Godkänt. Läraren lyfte särskilt trådsäkringen, nätverkskommunikationen och JavaFX-implementationen.

Status: Projektet har ett känt, ännu olöst problem i kommunikationsloopen som förhindrar att en fullständig match spelas igenom — grundanslutningen, trådsäkringen och protokolldesignen fungerar, men turordningen i meddelandeutbytet behöver felsökas vidare.

Protokoll

All kommunikation sker via strängar i formatet [kod] shot [koordinat]:

i — klienten skjuter första skottet
h — föregående skott var en träff
m — föregående skott var en miss
s — föregående skott sänkte ett skepp
game over — alla skepp sänkta, spelet avslutat
