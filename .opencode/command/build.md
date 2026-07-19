---
description: Build the 4diac IDE with Maven/Tycho from the repository root and report the result.
---

Run a full build of the 4diac IDE from the repository root using Maven with Tycho.

Execute: `mvn clean verify` $ARGUMENTS

Then report:
- whether the build succeeded or failed
- the location of produced product packages (under `plugins/org.eclipse.fordiac.ide.product/target`)
- any compilation errors or test failures, with the relevant module names

Notes:
- Requires Java 21+.
- The first build downloads Eclipse p2 artifacts from download.eclipse.org and can take several minutes.
- To skip tests, append `-DskipTests`.
- To build a single module, cd into its folder (e.g. `plugins/org.eclipse.fordiac.ide`) and run `mvn verify`.
- Do not edit generated sources under `src-gen/` or `xtend-gen/`.
