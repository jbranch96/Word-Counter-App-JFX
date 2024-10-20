package WordCounterJFX.controller;

import WordCounterJFX.view.AppGUI;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DownloadButtonController {

    private AppGUI appGUI;
    private Button downloadButton;
    private TextArea textArea;

    public DownloadButtonController(AppGUI appGUI) { 
        this.appGUI = appGUI; 
        this.downloadButton = this.appGUI.getDownloadButton();
        this.textArea = this.appGUI.getTextArea();
    }
    
    public void setupButtonActions() {
        if(this.downloadButton != null && this.textArea != null) {
            this.downloadButton.setOnAction(e -> { saveToFile(); }); // Add action lister to the button, listener downloads the text from the textArea object to a .txt file
        } else { return; }
    }

    private void saveToFile() {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save To File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        // Open the save dialog
        Stage stage = (Stage) this.downloadButton.getScene().getWindow(); // Get the current window
        File file = fileChooser.showSaveDialog(stage);

        // If the user selects a file, write the content to it
        if (file != null) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(textArea.getText());  // Write the text from the TextArea to the file
                System.out.println("File saved: " + file.getAbsolutePath());
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}