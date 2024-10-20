package WordCounterJFX.controller;

import WordCounterJFX.view.AppGUI;
import WordCounterJFX.model.UndoAndRedo;
import javafx.scene.control.Button;

public class RedoButtonController {

    private AppGUI appGUI;
    private Button redoButton;
    private UndoAndRedo undoAndRedo;

    public RedoButtonController(AppGUI appGUI) {
        this.appGUI = appGUI;
        this.redoButton = this.appGUI.getRedoButton();
        this.undoAndRedo = UndoAndRedo.getInstance(appGUI);
    }

    public void setupButtonActions() {
        if(this.redoButton != null && this.undoAndRedo != null) {
            this.redoButton.setOnAction(e -> { undoAndRedo.processRedoAction(); }); // Add action lister to the button
        } else { return; }
    }
}