---
name: 4diac-project
description: Orientation for the 4diac IDE codebase (Eclipse/Tycho/Maven, Java + Xtend). Use when navigating, building, or modifying this repository.
---

# 4diac IDE Project Orientation

Eclipse 4diac IDE is an IEC 61499 engineering environment built on the Eclipse platform. This repo is a Maven/Tycho multi-module project.

## Layout
- `plugins/` — Eclipse plug-in modules (the bulk of the source). Each is an OSGi bundle with a `META-INF/MANIFEST.MF`, `plugin.xml`, and `build.properties`.
- `tests/` — test plug-ins (JUnit, sometimes SWTBot UI tests).
- `features/` — Eclipse feature definitions that group plug-ins.
- `releng/` — release engineering / product / target platform definitions.
- `plugins/org.eclipse.fordiac.ide.product/` — the runnable/product build; output lands in its `target/`.

## Languages
- Primarily Java 21.
- Some logic is written in Xtend (`.xtend`), which compiles to Java into `xtend-gen/`. Treat `xtend-gen/` as generated — never edit it; edit the `.xtend` source.
- `src-gen/` directories are also generated (e.g. from Xtext/EMF); never edit by hand.

## Build & test
- Build from repo root: `mvn clean verify` (or `mvn clean install`).
- Tycho pulls Eclipse dependencies from a p2 repository; first build needs network and is slow.
- Tests run during the `verify` phase. UI tests use SWTBot and need a display.

## Conventions
- License: EPL-2.0. New files need the standard Eclipse copyright header with `SPDX-License-Identifier: EPL-2.0` and a Contributors list.
- Contributions require a DCO sign-off: commit with `git commit -s` (adds `Signed-off-by:`).
- Follow the Eclipse 4diac contribution guide and code style; match surrounding code.
- See `CONTRIBUTING.md` and `README.md` for the full development workflow.
