@echo off
for %%i in ("%CD%") do set "PD=%%~dpi"
set "PD=%PD:~0,-1%"
set "MODULE_PATH=%PD%\libs\javafx-sdk-23\lib"
set "BIN_DIR=%PD%\bin"
java --module-path "%MODULE_PATH%" --add-modules javafx.controls,javafx.graphics -cp "%BIN_DIR%" WordCounterJFX.mainapp.MainApp