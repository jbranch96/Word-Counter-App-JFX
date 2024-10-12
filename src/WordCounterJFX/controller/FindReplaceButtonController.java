package WordCounterJFX.controller;

import WordCounterJFX.utils.GetIconPath;
import WordCounterJFX.view.AppGUI;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FindReplaceButtonController extends WordCounterJFX.controller.FindButtonController {

    private AppGUI appGUI;
    private Button findReplaceButton;
    private Label matchesFoundLabel = new Label("No. Matches Found");

    public FindReplaceButtonController(AppGUI appGUI) {
        super(appGUI);
        this.appGUI = appGUI;
        this.findReplaceButton = this.appGUI.getFindReplaceButton();
    }

    @Override
    public void setupButtonActions() {
        if(this.findReplaceButton != null) {
            this.findReplaceButton.setOnAction(e -> { openModalWindow(); }); // Add action lister to the button, listener launches model on click
        } else { return; }
    }

    @Override
    protected void openModalWindow() {
        super.textFlow.getChildren().clear();
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Set modality to block main GUI when open
        modalStage.setTitle("Find & Replace Window");

        TextField phraseField = new TextField();
        phraseField.setPromptText("Enter phrase to find");

        TextField replaceField = new TextField();
        replaceField.setPromptText("Enter phrase to use in replacement");

        CheckBox caseSensitiveCheck = new CheckBox("Case sensitive");

        Button searchButton = new Button("Find");

        Button replaceButton = new Button("Replace");

        searchButton.setOnAction(e -> {
            String phrase = phraseField.getText();
            Boolean caseSensitive = caseSensitiveCheck.isSelected();
            if(!this.textArea.getText().equals("") && !phrase.equals("")) { 
                super.findPhraseInTextArea(phrase, caseSensitive);
                this.matchesFoundLabel.setText(super.matchesFoundLabel.getText());
            }
        });

        replaceButton.setOnAction(e -> {
            String oldText = phraseField.getText();
            String replaceText = replaceField.getText();
            Boolean caseSensitive = caseSensitiveCheck.isSelected();
            if(!this.textArea.getText().equals("") && !oldText.equals("") && !replaceText.equals(""))
            replacePhraseInTextArea(oldText, replaceText, caseSensitive); 
        });

        // Wrap TextFlow in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(this.textFlow);
        scrollPane.setFitToWidth(true); // Allow the ScrollPane to fit the width of the modal

        VBox modalLayout = new VBox(20); // 20px space between elements
        modalLayout.getChildren().addAll(phraseField, replaceField, caseSensitiveCheck, searchButton, replaceButton, this.matchesFoundLabel, scrollPane);
        modalLayout.setAlignment(Pos.CENTER); // center alignment of elements
        
        Scene modalScene = new Scene(modalLayout, 600, 400);
        Image icon = new Image(GetIconPath.getIconPath());

        modalStage.getIcons().add(icon);
        this.textFlow.setMinWidth(550); // Setting min width allows for text wrapping in the modal
        modalStage.setScene(modalScene);
        modalStage.show(); // Shows the modal, and blocks GUI access until modal is closed
    }

    protected void replacePhraseInTextArea(String oldText, String replaceText, Boolean caseSensitive) {
        Text textNode = new Text();

        // Iterate over the TextFlow nodes, if they are Red then replace them w/the replaceText content
        for(Node node : this.textFlow.getChildren()) {
            if(node instanceof Text) {
                textNode = (Text) node;
                if(textNode.getFill().equals(Color.RED)) { textNode.setText(replaceText); }
            }
        }

        if(!caseSensitive) { this.textArea.setText(this.textArea.getText().replaceAll("(?i)"+oldText, replaceText)); }// (?i) = regex pattern for case-insensitive match 
        else { this.textArea.setText(this.textArea.getText().replace(oldText, replaceText)); }
    }
}