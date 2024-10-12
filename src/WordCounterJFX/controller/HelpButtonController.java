package WordCounterJFX.controller;

import WordCounterJFX.utils.GetIconPath;
import WordCounterJFX.view.AppGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class HelpButtonController {

    private AppGUI appGUI;
    private Button helpButton;
    protected TextFlow textFlow = new TextFlow();

    public HelpButtonController(AppGUI appGUI) {
        this.appGUI = appGUI;
        this.helpButton = this.appGUI.getHelpButton();
        generateHelpDisplayContent();
    }

    public void setupButtonActions() {
        if(this.helpButton != null) {
            this.helpButton.setOnAction(e -> { openModalWindow(); }); // Add action lister to the button, listener launches model on click
        } else { return; }
    }

    protected void openModalWindow() {
        Stage modalStage = new Stage();
        modalStage.setTitle("Help Window");

        // Wrap TextFlow in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(this.textFlow);
        scrollPane.setFitToWidth(true); // Allow the ScrollPane to fit the width of the modal

        VBox modalLayout = new VBox();
        modalLayout.getChildren().addAll(scrollPane);
        modalLayout.setAlignment(Pos.CENTER); // center alignment of modal element(s)
        
        Scene modalScene = new Scene(modalLayout, 800, 300);
        Image icon = new Image(GetIconPath.getIconPath());

        modalStage.getIcons().add(icon);
        this.textFlow.setMinWidth(750); // Setting min width allows for text wrapping in the modal
        modalStage.setScene(modalScene);
        modalStage.show(); // Shows the modal, GUI isn't being blocked by this modal
    }

    protected void generateHelpDisplayContent() {
        Text textNode = new Text();
        List<Pair<String, String>> buttonFuncsAndDesc = new ArrayList<>();

        buttonFuncsAndDesc.add(new Pair<>("--Clear\n", "Clears the content currently in the GUI text area.\n\n"));
        buttonFuncsAndDesc.add(new Pair<>("--Undo\n", "Undoes the previous stored update to the GUI text area. Changes are polled for on a 5 second interval and are stored within the program for tracking. It is not always possible to undo every action.\n\n"));
        buttonFuncsAndDesc.add(new Pair<>("--Redo\n", "Redoes previously undone updates to the GUI text area. It is not always possible to redo every undone action.\n\n"));
        buttonFuncsAndDesc.add(new Pair<>("--Find\n", "Searches the content currently in the GUI text area for a phrase defined by the user. If the phrase is found it will be marked Red. The no. of matches will be updated to match the number of found occurences of the phrase.\n\n"));
        buttonFuncsAndDesc.add(new Pair<>("--Find & Replace\n", "See description for 'Find' functionality above. In addition, this allows replacing a found phrase with a user specified phrase. If no. of matches is greater than zero, the matched phrase will update to the user specified phrase in both the modal window and GUI text area.\n\n"));
        buttonFuncsAndDesc.add(new Pair<>("--Upload from File\n", "Reads in content from a text file specified from the user and populates the GUI text area with the content from file.\n\n"));
        buttonFuncsAndDesc.add(new Pair<>("--Download to File\n", "Writes the content currently in the GUI text area to a text file specified from the user. If the file already exists its content will be overwritten.\n\n"));

        textNode.setText(" Welcome to WordCounterJFX, this application displays the word count for any text entered into the prompt window.\nIt also displays character count, not including spaces.\n\n");
        textNode.setStyle("-fx-font-weight: bold;");
        this.textFlow.getChildren().add(textNode);

        for(Pair<String, String> p : buttonFuncsAndDesc) {
            textNode = new Text(p.getKey());
            textNode.setStyle("-fx-font-weight: bold;");
            this.textFlow.getChildren().add(textNode);

            textNode = new Text(p.getValue());
            textNode.setStyle("-fx-font-weight: normal;");
            this.textFlow.getChildren().add(textNode);
        }
    }
}