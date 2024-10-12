package WordCounterJFX.mainapp;

import WordCounterJFX.view.AppGUI;
import WordCounterJFX.controller.*;
import WordCounterJFX.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class serves as the Main Application code, and extends the Application abstract class provided by the JavaFX framework. 
 * It overrides the start method, invoking an instance of the AppGUI class, which is defined in this project and is used to generate the GUI.  
 */

public class MainApp extends Application{
    public static void main(String[] args) {
        Application.launch(args); // Launch the GUI
    }

    @Override
    public void start(Stage primaryStage) {
        AppGUI appGUI = new AppGUI();

        appGUI.start(primaryStage);

        WordAndCharCounter counter = new WordAndCharCounter(appGUI);
        counter.setupTextAreaListener();

        ClearButtonController clearButton = new ClearButtonController(appGUI);
        clearButton.setupButtonActions();

        UndoButtonController undoButton = new UndoButtonController(appGUI);
        undoButton.setupButtonActions();

        RedoButtonController redoButton = new RedoButtonController(appGUI);
        redoButton.setupButtonActions();

        FindButtonController findButton = new FindButtonController(appGUI);
        findButton.setupButtonActions();

        FindReplaceButtonController findReplaceButton = new FindReplaceButtonController(appGUI);
        findReplaceButton.setupButtonActions();

        UploadButtonController uploadButton = new UploadButtonController(appGUI);
        uploadButton.setupButtonActions();

        DownloadButtonController downloadButton = new DownloadButtonController(appGUI);
        downloadButton.setupButtonActions();

        HelpButtonController helpButton = new HelpButtonController(appGUI);
        helpButton.setupButtonActions();

        primaryStage.setOnCloseRequest(e -> { UndoAndRedo.stopTimedProcess = true; });
    }
}