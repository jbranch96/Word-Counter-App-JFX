@echo off
for %%i in ("%CD%") do set "PD=%%~dpi"
set "PD=%PD:~0,-1%"
set "MODULE_PATH=%PD%\libs\javafx-sdk-23\lib"
set "BIN_DIR=%PD%\bin"
set "SRC_DIR=%PD%\src\WordCounterJFX"

javac --module-path "%MODULE_PATH%" --add-modules javafx.controls,javafx.graphics -d "%BIN_DIR%"^
 "%SRC_DIR%\mainapp\MainApp.java"^
 "%SRC_DIR%\view\AppGUI.java"^
 "%SRC_DIR%\controller\ClearButtonController.java"^
 "%SRC_DIR%\controller\DownloadButtonController.java"^
 "%SRC_DIR%\controller\UploadButtonController.java"^
 "%SRC_DIR%\controller\FindButtonController.java"^
 "%SRC_DIR%\controller\FindReplaceButtonController.java"^
 "%SRC_DIR%\controller\HelpButtonController.java"^
 "%SRC_DIR%\controller\UndoButtonController.java"^
 "%SRC_DIR%\controller\RedoButtonController.java"^
 "%SRC_DIR%\model\WordAndCharCounter.java"^
 "%SRC_DIR%\model\UndoAndRedo.java"^
 "%SRC_DIR%\utils\GetIconPath.java"