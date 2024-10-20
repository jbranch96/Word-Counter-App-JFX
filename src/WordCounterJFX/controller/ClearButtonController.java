package WordCounterJFX.controller;

import WordCounterJFX.view.AppGUI;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ClearButtonController {

    private AppGUI appGUI;
    private Button clearButton;
    private TextArea textArea;

    public ClearButtonController(AppGUI appGUI) {
        this.appGUI = appGUI;
        this.clearButton = this.appGUI.getClearButton();
        this.textArea = this.appGUI.getTextArea();
    }
    
    public void setupButtonActions() {
        if(this.clearButton != null && this.textArea != null) {
            this.clearButton.setOnAction(e -> { this.textArea.clear(); }); // Add action lister to the button, listener clears text box on click
        } else { return; }
    }
}