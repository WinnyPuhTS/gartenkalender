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

### Lokaler Debug-Build

```bash
./gradlew assembleDebug
```

Das APK liegt danach unter `app/build/outputs/apk/debug/`.

### Lokaler Release-Build (signiert)

```bash
./gradlew assembleRelease \
  -Pandroid.injected.signing.store.file=/pfad/zum/keystore.jks \
  -Pandroid.injected.signing.store.password=STORE_PW \
  -Pandroid.injected.signing.key.alias=KEY_ALIAS \
  -Pandroid.injected.signing.key.password=KEY_PW
```

### Android App Bundle (AAB)

```bash
./gradlew bundleRelease
```

Das AAB liegt unter `app/build/outputs/bundle/release/`.

### Unit-Tests ausführen

```bash
./gradlew test
```

### Lint

```bash
./gradlew lint
```

---

## CI/CD (GitHub Actions)

### Workflows

| Workflow | Datei | Auslöser |
|----------|-------|---------|
| CI | `.github/workflows/ci.yml` | Pull Requests & Pushes auf `main` |
| Release | `.github/workflows/release.yml` | Pushes auf `main` & Tags `v*` |

### CI-Workflow

Bei jedem PR und Push auf `main` werden automatisch ausgeführt:

1. **Lint** – statische Code-Analyse
2. **Unit Tests** – alle Unit-Tests via `./gradlew test`
3. **Build** – Debug-APK (nur wenn Lint & Tests erfolgreich)

### Release-Workflow

Beim Erstellen eines Git-Tags (`v1.0.0`) oder bei einem Merge auf `main`:

1. Signiertes Release-APK & AAB werden gebaut
2. Bei einem Tag wird automatisch ein GitHub-Release erstellt (APK + AAB als Assets)
3. Bei einem Tag wird das AAB in den **internen Track** des Play Stores hochgeladen

### Required GitHub Secrets

Folgende Secrets müssen im Repository unter **Settings → Secrets and variables → Actions** hinterlegt werden:

| Secret | Beschreibung |
|--------|-------------|
| `KEYSTORE_BASE64` | Base64-kodierter Keystore: `base64 -w 0 release.jks` |
| `SIGNING_KEY_ALIAS` | Key-Alias im Keystore |
| `SIGNING_KEY_PASSWORD` | Passwort des Keys |
| `SIGNING_STORE_PASSWORD` | Passwort des Keystores |
| `PLAY_SERVICE_ACCOUNT_JSON` | JSON-Inhalt des Google Play Service Account (für Play Store Upload) |

> **Hinweis:** Der Play-Store-Upload (`upload-play-store`-Job) läuft nur bei Tags vom Typ `v*`. Ohne hinterlegte `PLAY_SERVICE_ACCOUNT_JSON` wird dieser Job übersprungen oder schlägt fehl.

Mindest-SDK: 24 (Android 7.0) · Target-SDK: 34

## Lizenz

MIT
