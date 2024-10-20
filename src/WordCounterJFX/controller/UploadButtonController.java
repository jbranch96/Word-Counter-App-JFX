package WordCounterJFX.controller;

import WordCounterJFX.view.AppGUI;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class UploadButtonController {

    private AppGUI appGUI;
    private Button uploadButton;
    private TextArea textArea;

    public UploadButtonController(AppGUI appGUI) { 
        this.appGUI = appGUI; 
        this.uploadButton = this.appGUI.getUploadButton();
        this.textArea = this.appGUI.getTextArea();
    }
    
    public void setupButtonActions() {
        if(this.uploadButton != null || this.textArea != null) {
            this.uploadButton.setOnAction(e -> { readFromFile(); }); // Add action lister to the button, listener uploads text from a .txt file to the textArea object
        } else { return; }
    }

    private void readFromFile() {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Read from File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        // Open the save dialog
        Stage stage = (Stage) this.uploadButton.getScene().getWindow(); // Get the current window
        File file = fileChooser.showOpenDialog(stage);

        // If the user selects a file, read the content from it
        if (file != null) {
            try {
                this.textArea.setText(Files.readString(file.toPath()));  // Read the text from the file to the TextArea
                System.out.println("File read: " + file.getAbsolutePath());
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}