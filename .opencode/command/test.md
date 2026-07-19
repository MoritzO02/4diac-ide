---
description: Run the 4diac IDE test suites with Maven/Tycho and report failures.
---

Run the 4diac IDE tests from the repository root using Maven with Tycho.

Execute: `mvn clean verify` $ARGUMENTS

Tycho executes unit and UI tests during the `verify` phase. Report:
- overall pass/fail
- any failing test classes with their module (e.g. `tests/org.eclipse.fordiac.ide.test.ui`)
- stack traces or assertion failures for the failures

Notes:
- Requires Java 21+ and a display for UI (SWTBot) tests; headless UI tests may need a virtual display.
- To run a single test module, cd into `tests/<module>` and run `mvn verify`.
- Do not edit generated sources under `src-gen/` or `xtend-gen/`.
