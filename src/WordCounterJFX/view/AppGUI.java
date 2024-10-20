package WordCounterJFX.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import WordCounterJFX.utils.GetIconPath;

/**
 * This class builds the GUI for the WordCounterJFX application.
 */

public class AppGUI {
    private String APP_VERSION = "1.1.0 b"; // major.minor.patch, a - test build, b - deploy build

    private Button buttonClear;
    private TextArea textArea;
    private Button buttonUndo, buttonRedo, buttonFind, buttonFindReplace;
    private Button buttonUpload, buttonDownload, buttonHelp;
    private Label labelVersionInfo, labelWordCount, labelCharCount;

    public void buildGUI(Stage primaryStage) {

        Image icon = new Image(GetIconPath.getIconPath()); // get application icon

        // Instatiate the TextArea (multi-line text box)
        this.textArea = new TextArea();
        this.textArea.setPromptText("Enter text here...");
        this.textArea.setWrapText(true); // Enable word wrapping

        // Instatiate the Buttons
        this.buttonClear = new Button("Clear");
        this.buttonUndo = new Button("Undo");
        this.buttonRedo = new Button("Redo");
        this.buttonFind = new Button("Find");
        this.buttonFindReplace = new Button("Find & Replace");
        this.buttonDownload = new Button("Download to File");
        this.buttonUpload = new Button("Upload from File");
        this.buttonHelp = new Button("Help");
        this.buttonHelp.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Font type, style, size
        this.buttonHelp.setStyle("-fx-text-fill: blue;");
        
        // Instatiate the Labels
        this.labelVersionInfo = new Label("Version: " + this.APP_VERSION);
        this.labelVersionInfo.setFont(Font.font("Arial", FontWeight.BOLD, 20));  
        this.labelWordCount = new Label("Words: ");
        this.labelWordCount.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.labelCharCount = new Label("Characters (no spaces): ");
        this.labelCharCount.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Create a GridPane to hold the GUI elements
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);  // Horizontal gap between elements
        gridPane.setVgap(10); // Vertical gap between elements

        // Add all of the objects to the pane
        gridPane.add(this.textArea, 0, 0); // col 0, row 0
        gridPane.add(this.buttonClear, 1, 0);
        gridPane.add(this.buttonUndo, 2, 0);
        gridPane.add(this.buttonRedo, 3, 0);
        gridPane.add(this.buttonFind, 4, 0);
        gridPane.add(this.buttonFindReplace, 5, 0);
        gridPane.add(this.buttonUpload, 7, 0);
        gridPane.add(this.buttonDownload, 9, 0);
        gridPane.add(this.labelVersionInfo, 0, 1);
        gridPane.add(this.labelWordCount, 1, 1);
        gridPane.add(new ImageView(icon), 0, 2);
        gridPane.add(this.labelCharCount, 5, 1);
        gridPane.add(this.buttonHelp, 9, 1);
        gridPane.setStyle("-fx-background-color: rgb(117, 148, 165);"); // Set widow background color
    
        Scene scene = new Scene(gridPane, 1150, 450); // Create a scene (width(px), len(px))

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("WordCounterJFX"); // Set the title of the stage (window)
        primaryStage.setScene(scene); // Set the scene for the stage
        primaryStage.show(); // Show the stage (window)
    }
    
    // Getter methods to access the GUI elements from outside the class
    public TextArea getTextArea() { return this.textArea; } 
    public Button getClearButton() { return this.buttonClear; }
    public Button getUndoButton() { return this.buttonUndo; } 
    public Button getRedoButton() { return this.buttonRedo; } 
    public Button getFindButton() { return this.buttonFind; } 
    public Button getFindReplaceButton() { return this.buttonFindReplace; } 
    public Button getUploadButton() { return this.buttonUpload; } 
    public Button getDownloadButton() { return this.buttonDownload; } 
    public Button getHelpButton() { return this.buttonHelp; } 
    public Label getVersionLabel() { return this.labelVersionInfo; }
    public Label getWordCount() { return this.labelWordCount; }
    public Label getCharCount() { return this.labelCharCount; }
}