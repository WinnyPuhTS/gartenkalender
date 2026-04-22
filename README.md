# Gartenkalender

Android offline-first Gartenkalender-App (Kotlin / Jetpack Compose / Room).

## Funktionen

- **Gartenkalender** – monatliche Aussaat- und Pflanzempfehlungen
- **Pflanzendatenbank** – offline eingebettete Daten für Gemüse, Kräuter und Blumen (15 Pflanzen im Startsatz)
- **Aufgaben-Manager** – eigene Gartentodos mit Notizen, kein Backend nötig

## Technologie-Stack

| Bereich | Technologie |
|---------|-------------|
| Sprache | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Datenbank | Room (SQLite) |
| DI | Hilt |
| Navigation | Navigation Compose |
| Serialisierung | kotlinx.serialization |

## Projektstruktur

```
app/src/main/java/com/gartenkalender/app/
├── data/
│   ├── database/   # Room DB, DAOs, Seed-Daten, DI-Modul
│   ├── model/      # Plant, GardenTodo Entity-Klassen
│   └── repository/ # PlantRepository, GardenTodoRepository
├── ui/
│   ├── screens/
│   │   ├── calendar/  # Monatskalender (Screen + ViewModel)
│   │   ├── plants/    # Pflanzendatenbank (Screen + ViewModel)
│   │   └── todos/     # Aufgabenverwaltung (Screen + ViewModel)
│   ├── theme/         # Material 3 Farben / Theme
│   └── GartenkalenderNavHost.kt
├── GartenkalenderApp.kt  (Hilt Application)
└── MainActivity.kt
```

## Build

```bash
./gradlew assembleDebug
```

Mindest-SDK: 24 (Android 7.0) · Target-SDK: 34

## Lizenz

MIT
