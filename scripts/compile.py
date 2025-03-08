import os
import subprocess

# Get the parent directory of the current script location
CURRENT_DIR = os.getcwd()
PARENT_DIR = os.path.dirname(CURRENT_DIR)

# Define paths
MODULE_PATH = os.path.join(PARENT_DIR, "libs", "javafx-sdk-23", "lib")
BIN_DIR = os.path.join(PARENT_DIR, "bin")
SRC_DIR = os.path.join(PARENT_DIR, "src", "WordCounterJFX")

# List of Java files to compile
java_files = [
    "mainapp/MainApp.java",
    "view/AppGUI.java",
    "controller/ClearButtonController.java",
    "controller/DownloadButtonController.java",
    "controller/UploadButtonController.java",
    "controller/FindButtonController.java",
    "controller/FindReplaceButtonController.java",
    "controller/HelpButtonController.java",
    "controller/UndoButtonController.java",
    "controller/RedoButtonController.java",
    "model/WordAndCharCounter.java",
    "model/UndoAndRedo.java",
    "utils/GetIconPath.java",
]

# Construct full paths for Java files
java_files_full_paths = [os.path.join(SRC_DIR, file) for file in java_files]

# Construct the javac command
javac_command = [
    "javac",
    "--module-path", MODULE_PATH,
    "--add-modules", "javafx.controls,javafx.graphics",
    "-d", BIN_DIR
] + java_files_full_paths

# Run the javac command
try:
    subprocess.run(javac_command, check=True)
    print("Compilation successful!")
except subprocess.CalledProcessError as e:
    print("Error during compilation:", e)