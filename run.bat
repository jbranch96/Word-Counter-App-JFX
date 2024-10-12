@echo off
set "MODULE_PATH=%CD%\libs\javafx-sdk-23\lib"
java --module-path "%MODULE_PATH%" --add-modules javafx.controls,javafx.graphics -cp "bin" WordCounterJFX.mainapp.MainApp