## Purpose

Short, actionable guidance for AI coding agents working on this repository (small Java project for planning tourist routes).

## Quick facts

- Language: Java (plain Java SE, no build system present)
- Entry point: `Main.java` (calls `GestorArchivos.leerLugares("dataExterna.txt")`)
- Data file: `dataExterna.txt` in the repo root — parsed by `GestorArchivos`
- Key domain classes: `Lugar`, `Turista`, `EntidadTuristica`, `Conexion`, `PlanificadorRutas`, `GestorArchivos`

## What the code does (big picture)

- `GestorArchivos` parses `dataExterna.txt` into a `List<Lugar>` using `Files.readAllLines` and a simple key:value template. Look for markers like `[Lugar]` to begin a new record.
- `Lugar` represents a tourism site and extends the simple base `EntidadTuristica`. It stores comma-separated lists (interests, open days, direct connections, travel times).
- `Conexion` is a lightweight pair (origin, destination) with an integer travel time.
- `PlanificadorRutas` contains routing helpers (some methods are incomplete/stubs) — main responsibilities: check connectivity, filter by open days, and produce lists of connected nodes.
- `Main` currently demonstrates loading data and is a safe place to add small integration examples.

## File / API patterns to reference (concrete examples)

- File parsing pattern: `GestorArchivos.leerLugares(String ruta)`
  - It reads lines, skips empty lines and lines starting with `#`, splits lines on `:` into key/value pairs, and groups records by `[Lugar]` markers.
  - When adding or changing file fields, update `crearLugarDesdeMapa(Map<String,String>)` to read the new keys and convert them into the `Lugar` constructor arguments.

- List fields in the file are comma-separated and trimmed. `crearLugarDesdeMapa` uses `Arrays.asList(...).replaceAll(String::trim)` to normalize values.

- `Lugar` constructor and getters:
  - constructor: `new Lugar(nombre, intereses, diasA, conexionesD, tiempo_viaje)`
  - getters used in code: `getNombre()`, `getConexiones()`, `getIntereses()`, `getDiasAbierto()`

- `Conexion` usage:
  - fields: `lugarOrigen`, `lugarDestino`, `tiempo_viaje`
  - accessors: `getLugarOrigen()`, `getLugarDestino()`, `getTiempoViaje()`

## Conventions and gotchas discovered

- The project uses Spanish identifiers and keys (e.g., `dias_abierto`, `tiempo_viaje`, `conexiones`) — search for these exact strings when matching data fields.
- Data parsing expects lowercase or exact keys; follow the existing keys when editing `dataExterna.txt` or `GestorArchivos`.
- Many fields use package-private visibility (no explicit `private` for `nombre` in `EntidadTuristica`), so modifications should respect package scope or add accessors if changing visibility.
- Several methods in `PlanificadorRutas` are incomplete (stubs). These are obvious, high-value tasks: implement `listaConectadas`, complete `planificarRuta`, and ensure `conexcionExistente` logic (note: currently referenced but not present).

## How to run locally (Windows PowerShell)

Run a quick compile and run in the project root (works for small changes and smoke tests):

```powershell
javac *.java; java Main
```

There is no build script or test harness in the repo. For more advanced development, add a build tool (Maven/Gradle) and JUnit for tests.

## Suggested low-risk tasks for an AI to take next (explicit, concrete)

1. Implement missing pieces in `PlanificadorRutas`:
   - Implement `listaConectadas(Conexion[])` (or refactor signature to use `List<Conexion>`), and ensure `conexionExistente` is defined.
   - Add sensible null checks and use `getConexiones()` from `Lugar` when checking connections.
2. Harden `GestorArchivos` parsing:
   - Add defensive checks when `datos.get(...)` returns null.
   - Normalize empty fields to empty lists to avoid NPEs in `Lugar`.
3. Add a small smoke integration test: a minimal `Main`-based test that loads `dataExterna.txt` and asserts `lista.size() > 0`.

## Where to look when changing behavior

- `GestorArchivos.java` — file format parsing and normalization
- `Lugar.java` and `EntidadTuristica.java` — domain model and accessors
- `PlanificadorRutas.java` — routing logic and incomplete algorithms
- `dataExterna.txt` — canonical data format (use it as the source of truth when editing parsing logic)

## If you are an AI agent: do this first

1. Run the project locally (compile + run Main) to observe console output.
2. Open `dataExterna.txt` to see concrete input examples before editing parsing code.
3. Implement a single small method (e.g., `PlanificadorRutas.listaConectadas`) and run the Main smoke test.

---

If anything in these notes is unclear or you'd like me to include a sample `dataExterna.txt` block and concrete code snippets for the main missing methods, tell me which area to expand and I will update this file.
