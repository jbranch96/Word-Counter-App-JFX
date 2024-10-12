package WordCounterJFX.controller;

import WordCounterJFX.view.AppGUI;
import WordCounterJFX.model.UndoAndRedo;
import javafx.scene.control.Button;

public class UndoButtonController {

    private AppGUI appGUI;
    private Button undoButton;
    private UndoAndRedo undoAndRedo;

    public UndoButtonController(AppGUI appGUI) {
        this.appGUI = appGUI;
        this.undoButton = this.appGUI.getUndoButton();
        this.undoAndRedo = UndoAndRedo.getInstance(appGUI);
    }

    public void setupButtonActions() {
        if(this.undoButton != null) {
            this.undoButton.setOnAction(e -> { undoAndRedo.processUndoAction(); }); // Add action lister to the button
        } else { return; }
    }
}