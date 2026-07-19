# Introduction
This specification captures the ideas from 4diac-ide GitHub Discussion #2655 ("Ideas around printout") and turns them into an implementation plan for improving print/printout and related editor capabilities (coordinate origin, multi-page FB networks, frames, cross-page connection labels). It is a work-in-progress; scope and key behavioral decisions are being clarified with the user.

## 1. Purpose & Scope
Intended audience: 4diac-ide contributors/committers.
Goal: Define an implementation plan for the printout-related improvements proposed in discussion #2655.
Boundaries/assumptions: TBD pending scope decision (see open questions). Related to bug issue #2637 ("Printout not working").

## 2. Definitions
- FB: Function Block (IEC 61499)
- FB Network: diagram of FBs and their connections
- Coordinate Origin: visible (0;0) reference point shown in the editor
- Page: a sheet within an FB Network (analogous to Subapp FB Network pages)
- Frame: a sized border/title-block per IEC 61082-1, placed at (0,0) on a page
- Label: a cross-page connection marker referencing target page/column/row
- Printout: the printed or exported representation of the network

## 3. Requirements, Constraints & Guidelines
- **REQ-001**: Editor shall show a visible Coordinate Origin (cross at 0;0).
- **REQ-002**: FBs shall be placeable by drag-and-drop AND by entering coordinates in the Properties view.
- **REQ-003**: An FB Network shall support at least 1 page and may have unlimited pages.
- **REQ-004**: A "Frames" library shall contain multiple frames; each page may have 0 or 1 frame, always at coordinate (0,0).
- **REQ-005**: A frame shall have IEC 61082-1 columns and rows; the frames library shall be user-editable (incl. custom logo/docfield).
- **REQ-006**: Connections (event/data/adapter) crossing pages shall use 2 labels referencing page/column/row.
- **REQ-007**: The print dialog shall support page-limit scaling (e.g., A3 frame on A4 paper with page-limit=1 scales to 0.73).
- **REQ-008**: Scaling shall be the only mechanism to resize FBs; FBs have a fixed real-world size (0.1 inch pin-to-pin).
- **CON-001**: Must integrate with the existing GEF-based editor (org.eclipse.fordiac.ide.gef) and the existing PrintPreview (org.eclipse.fordiac.ide.gef.print.PrintPreview).
- **GUD-001**: Follow IEC 61082-1 for frame columns/rows and IEC 61499 for FB semantics.
- OPEN: which REQ items are in scope for the first plan (see questions).

## 4. Interfaces & Data Contracts
TBD after code exploration (editor classes DiagramEditor / DiagramEditorWithFlyoutPalette, PrintPreview dialog, and the FB-network model that would need to carry pages/frames/coordinates).

## 5. Acceptance Criteria
TBD.

## 6. Test Automation Strategy
TBD (JUnit for model changes; SWTBot for editor/dialog interaction).

## 7. Rationale & Context
The discussion references Autodesk Fusion360 Electronics for the coordinate-origin cross and frame concept, and IEC 61082-1 for frame columns/rows. The motivation is to produce standards-compliant, printable documentation of FB networks. Issue #2637 reports that printout is currently broken, making the print path the natural entry point.

## 8. Dependencies & External Integrations
- **EXT-001**: IEC 61082-1 standard (frame columns/rows).
- **EXT-002**: Existing PrintPreview implementation (org.eclipse.fordiac.ide.gef.print).
- **EXT-003**: GitHub issue #2637 (printout bug) — likely foundational.

## 9. Examples & Edge Cases
- A3 frame on A4 paper with page-limit=1 -> scale factor 0.73 (A3 scaled down to A4).
- Grid spacing: 0.1 inch = FB pin-to-pin distance; a finer 0.05 inch grid for connection lines while keeping FB inputs at 0.1 inch.

## 10. Validation Criteria
TBD.

## 11. Related Specifications / Further Reading
- Discussion #2655 (source of this spec)
- Issue #2637 (printout not working)
- IEC 61082-1 (electrical documentation frames)
- IEC 61499 (FB / FB network semantics)
