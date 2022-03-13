# VScript

VScript ist eine Programmiersprache, die ich im Zuge meiner 
vorwissenschaftlichen Arbeit für die Schule entwickelt habe.

## Verwendung

Für das Ausführen des Interpreters ist Java (mindestens Version 16) auf dem Computer erforderlich.

Schritte für die Verwendung:
- Installation von [Java](https://adoptium.net), wenn noch nicht vorhanden
- [Aktuelles Release](https://github.com/MaxiHuHe04/VScript/releases/latest) von VScript herunterladen
- Terminal, cmd oder PowerShell öffnen
- REPL: `java -jar "C:\Pfad\zu\VScript.jar"`
- Datei: `java -jar "C:\Pfad\zu\VScript.jar" "C:\Pfad\zur\Skriptdatei.vs"`

## Syntax
Die Syntax von VScript ist an JavaScript angelehnt.

### Variablen

```js
var variablenname = wert;
```

### Typen

Es gibt folgende primitive Datentypen: *string*, *number*, *boolean*, *array* und *null*.

#### string

Strings werden als Text mit doppelten Anführungszeichen geschrieben.
Anführungszeichen innerhalb von Zeichenketten können mit Backslashes escaped werden.
```js
var begruessung = "Hallo, " + name + "!";
```

#### number

Zahlen sind Ganzzahlen oder Dezimalzahlen mit beliebiger Länge.
Als Dezimaltrennzeichen wird ein Punkt verwendet.
```js
var pi = 3.14159265358979;
```

#### boolean

`true` oder `false`

#### array

```js
var arr = ["test", 1, 2.5];
```

Auf die Elemente kann mit einem mit 0 beginnenden Index in eckigen Klammern zugegriffen werden:
```js
println(arr[2]);  // 2.5
```

#### null

`null` ist der Nullwert und deutet auf das Fehlen eines richtigen Wertes hin.

### Kontrollstrukturen

#### if-Bedingung:
```js
if (a) {
    ...;
} else if (b) {
    ...;
} else {
    ...;
}
```

#### while-Schleife:
```js
while (bedingung) {
    ...;
}
```

break:
```js
while (true) {
    ...;
    
    if (!bedingung) {
        break;
    }
    
    ...;
}

```


### Funktionen
```swift
func name(param1, param2) {
    ...;
}
```


### Operatoren

| Rang | Operator                  | Beschreibung                                                                   |
|------|---------------------------|--------------------------------------------------------------------------------|
| 1    | `()` `[]`                 | Funktionsaufruf, Arrayzugriff                                                  |
| 2    | `!` `-`                   | Negation, unäres Minus                                                         |
| 3    | `*` `/` `%`               | Multiplikation, Division, Modulo (Divisionsrest)                               |
| 4    | `+` `-`                   | Addition und Konkatenation (Zusammenfügen von Strings und Arrays), Subtraktion |
| 5    | `<` `>` `<=` `>=`         | Vergleiche: kleiner, größer, kleiner/gleich, größer/gleich                     |
| 6    | `==` `!=`                 | Gleichheit: gleich, nicht gleich                                               |
| 7    | `&&`                      | Und                                                                            |
| 8    | <code>&#124;&#124;</code> | Oder                                                                           |
| 9    | `=`                       | Zuweisung                                                                      |

### Built-ins

| Funktion  | Parameter | Beschreibung                                                        |
|-----------|-----------|---------------------------------------------------------------------|
| `print`   | value     | Gibt einen Wert ohne Zeilenumbruch in die Konsole aus               |
| `println` | value     | Gibt einen Wert mit anschließendem Zeilenumbruch in die Konsole aus |

### Kommentare

```js
// Zeilenkommentar

/*
Blockkommentar
über
mehrere
Zeilen
 */
```