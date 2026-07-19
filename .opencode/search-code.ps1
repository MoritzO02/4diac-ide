<#
.SYNOPSIS
    Windows PowerShell equivalent of the Unix grep search for finding code.
    The original command failed on Windows PowerShell 5.1 because:
      - "&&" is not a valid statement separator in PowerShell 5.1 (use ";" or a script)
      - the path "/Users/morit/..." is a macOS path, not a Windows path
    Original (broken on Windows):
      cd /Users/morit/Desktop/repos/4diac-ide && grep -r -i "print" --include="*.xml" --include="*.java" --include="*.xtend" . 2>/dev/null | head -200

.USAGE
    powershell -ExecutionPolicy Bypass -File .opencode\search-code.ps1
    powershell -ExecutionPolicy Bypass -File .opencode\search-code.ps1 -Term "print" -First 200
    powershell -ExecutionPolicy Bypass -File .opencode\search-code.ps1 -Term "PrintHandler" -Include @("*.java","*.xtend")
#>
param(
    [string]$Term    = 'print',
    [int]   $First   = 200,
    [string]$Repo    = 'C:\Users\morit\Desktop\repos\4diac-ide',
    [string[]]$Include = @('*.xml', '*.java', '*.xtend')
)

# SilentlyContinue == the "2>/dev/null" part (ignore access/permission errors)
$ErrorActionPreference = 'SilentlyContinue'

Get-ChildItem -Path $Repo -Recurse -Include $Include -File |
    Select-String -Pattern $Term -CaseSensitive:$false |
    Select-Object -First $First |
    ForEach-Object { "$($_.Path):$($_.LineNumber): $($_.Line)" }
