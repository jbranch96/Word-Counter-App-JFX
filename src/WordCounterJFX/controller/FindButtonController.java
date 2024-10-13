package WordCounterJFX.controller;

import WordCounterJFX.utils.GetIconPath;
import WordCounterJFX.view.AppGUI;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FindButtonController {

    private AppGUI appGUI;
    private Button findButton;
    protected Label matchesFoundLabel = new Label("No. Matches Found");
    protected TextArea textArea;
    protected TextFlow textFlow = new TextFlow();

    public FindButtonController(AppGUI appGUI) {
        this.appGUI = appGUI;
        this.findButton = this.appGUI.getFindButton();
        this.textArea = this.appGUI.getTextArea();
    }
    
    public void setupButtonActions() {
        if(this.findButton != null) {
            this.findButton.setOnAction(e -> { openModalWindow(); }); // Add action lister to the button, listener launches model on click
        } else { return; }
    }

    protected void openModalWindow() {
        this.textFlow.getChildren().clear();
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Set modality to block main GUI when open
        modalStage.setTitle("Find Window");

        TextField phraseField = new TextField();
        phraseField.setPromptText("Enter phrase to find");

        CheckBox caseSensitiveCheck = new CheckBox("Case sensitive");

        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String phrase = phraseField.getText();
            Boolean caseSensitive = caseSensitiveCheck.isSelected();
            if(!this.textArea.getText().equals("") && !phrase.equals("")) {
                findPhraseInTextArea(phrase, caseSensitive);
            }
        });

        // Wrap TextFlow in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(this.textFlow);
        scrollPane.setFitToWidth(true); // Allow the ScrollPane to fit the width of the modal

        VBox modalLayout = new VBox(20); // 20px space between elements
        modalLayout.getChildren().addAll(phraseField, caseSensitiveCheck, searchButton, this.matchesFoundLabel, scrollPane);
        modalLayout.setAlignment(Pos.CENTER); // center alignment of elements
        modalLayout.setStyle("-fx-background-color: lightblue;"); // set widow background color
        
        Scene modalScene = new Scene(modalLayout, 600, 400);
        Image icon = new Image(GetIconPath.getIconPath());

        modalStage.getIcons().add(icon);
        this.textFlow.setMinWidth(550); // Setting min width allows for text wrapping in the modal
        modalStage.setScene(modalScene);
        modalStage.show(); // Shows the modal, and blocks GUI access until modal is closed
    }

    protected void findPhraseInTextArea(String phrase, Boolean caseSensitive) {
        this.textFlow.getChildren().clear();
        Text textNode;
        String textAreaContent = this.textArea.getText();

        if(!caseSensitive) {
            textAreaContent = textAreaContent.toLowerCase();
            phrase = phrase.toLowerCase();
        }

        int startIndex = 0; // Start index for tracking position in the original content
        int counter = 0; // Counter to track no. of matches found

        // Find occurrences of the search term
        while(startIndex < textAreaContent.length()) {
            // Find the index of the next occurrence of the search term
            int matchIndex = textAreaContent.indexOf(phrase, startIndex);
            
            // If no more matches are found, add the rest of the text
            if(matchIndex == -1) {
                textNode = new Text(textAreaContent.substring(startIndex));
                textNode.setFill(Color.BLACK); // Regular text
                textFlow.getChildren().add(textNode);
                break;
            }

            // Add text before the match
            if(matchIndex > startIndex) {
                Text beforeMatch = new Text(textAreaContent.substring(startIndex, matchIndex));
                beforeMatch.setFill(Color.BLACK); // Regular text
                textFlow.getChildren().add(beforeMatch);
            }

            // Add the matched text with highlighting
            String matchedText = textAreaContent.substring(matchIndex, matchIndex + phrase.length());
            Text matchNode = new Text(matchedText);
            matchNode.setFill(Color.RED); // Highlight matches in red
            matchNode.setStyle("-fx-font-weight: bold;"); // Make matches bold
            textFlow.getChildren().add(matchNode);

            startIndex = matchIndex + phrase.length(); // Update startIndex for the next search
            counter += 1; // Update counter before the next search
        }
        this.matchesFoundLabel.setText(counter + " matches found");
    }
}