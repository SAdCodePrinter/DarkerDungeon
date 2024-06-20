Das Spiel ist ein zweispieler Spiel, in welchem Monster bekämpft werden müssen. 
Sobald alle Monster getötet wurden, gelangt man in das nächste Level und muss weitere Monster bekämpfe. 

Jenachdem wie weit man gekommen ist, speichert sich eine Higscore Liste im Hintergrund, welcher Spieler welchen Score erreicht hat. 
Die Top 6 Spieler werden im Endscreen angezeit.

Um das Spiel zu starten, öffne die Main-Klasse im MainGui-Package und führe sie aus. 
Zunächst müssen zwei Namen für die Player eingegeben werden.
Danach gelangt man zum Start-Screen wo man aufgefordert wird die Taste "P" für Play zu drücken.

Schon geht es los und die beiden Spieler stehen im Dungeon, wo sie ein Monster angreift.
Bewegen kann sich der Spieler 1 mit den Tasten "W" "A" "S" "D" und zum Schlagen "R".
Spieler 2 nutzt die Tasten der ARROW_KEYS und zum Schlagen "O".

Beim Drücken der Taste "P" im Spielmodus, kommt ein Pause-Screen, wo ebenfalls die Kontroll- Tasten der Spieler zur Errinnerung zu sehen sind.

Wenn ein Spieler von einem Monster getroffen wurde, verringert sich die Lebensanzeige unten links bis sie auf 0 fäll.
Sobald dies passiert ist, kommt ein End-Screen, bei welchem die actuellen Scores der Player, als auch die gespeicherten Highscores erscheinen.

Um das Spiel neuzustarten, muss ein weiters mal "P" gedrückt werden.

Möchte man die Hitboxen der Entitäten, den Pfad den ein Troll berrechnet und die Schlag- Hitbox im Spiel anzeigen lassen, 
muss man im Start-Screen nachdem man das Spiel neugestartet hat, die taste "R" einmalig drücken. 

-------------------------------------------------------------------------------------------------------------------------

In einem weiteren Branche "Host-Implementierung" wurde eine Netzwerkverbindung per Localhost aufgebaut, über welche sich mehrere Spieler gleichzeitig anmelden können.

Um dies zu machen ist es erforderlich im Package "network", die Klasse HostRunner aufzurufen und dort die "main" methode auszuführen.

Der erste Spieler der das macht erhält in der Console eine Ausgabe der IP-Adresse, welche manuell von jedem weiteren Spieler 
in der Classe "Client" bei der Variable "SERVER_ADDRESS" anstatt dem String "localhost" dort eingegeben werden muss.

Dadurch wird nach jedem Tod ein String mit allen Highscores online gespeichert, und auf jedem Anwender Synchronisiert.

Leider ist dieser Branch nicht fertig geworden, weshalb die Speicherung und der Abruf der Daten noch nicht implementiert wurde.


-----
Projekt Von Philipp Bauer und Simon Adam
