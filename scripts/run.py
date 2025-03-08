import os
import subprocess

# Get the parent directory of the current script location
CURRENT_DIR = os.getcwd()
PARENT_DIR = os.path.dirname(CURRENT_DIR)

# Define paths
MODULE_PATH = os.path.join(PARENT_DIR, "libs", "javafx-sdk-23", "lib")
BIN_DIR = os.path.join(PARENT_DIR, "bin")

# Main Java class (fully qualified class name, not path)
java_class = "WordCounterJFX.mainapp.MainApp"

# Construct the java command
java_command = [
    "java",
    "--module-path", MODULE_PATH,
    "--add-modules", "javafx.controls,javafx.graphics",
    "-cp", BIN_DIR, java_class
]

# Run the java command
try:
    subprocess.run(java_command, check=True)
except subprocess.CalledProcessError as e:
    print("Error during compilation:", e)