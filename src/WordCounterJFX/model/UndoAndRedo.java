package WordCounterJFX.model;

import WordCounterJFX.view.AppGUI;
import javafx.util.Pair;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;


// Making this a singleton to allow non-static reference calls from the Undo & Redo controllers, 
// but limit instatiation for this class to a single instance
public class UndoAndRedo {

    public static Boolean stopTimedProcess = false;
    private static UndoAndRedo instance;

    private AppGUI appGUI;
    private int nextUID = 1;
    private String textAreaContent;
    private Stack<Pair<Integer, String>> undoStack = new Stack<>();
    private Stack<Pair<Integer, String>> redoStack = new Stack<>();

    private UndoAndRedo(AppGUI appGUI) { 
        this.appGUI = appGUI;
        this.getTextAreaContentOnFixedInterval();
    }

    public static UndoAndRedo getInstance(AppGUI appGUI) {
        // Lazy initialization
        if (instance == null) { instance = new UndoAndRedo(appGUI); }
        return instance;
    }
    
    public void processUndoAction() {
        if(this.undoStack.isEmpty()) { return; }

        if( !(this.redoStack.isEmpty()) ) {
            if( this.undoStack.peek().getKey() - this.redoStack.peek().getKey() != -1 ) { this.redoStack.clear(); }
        }
        this.redoStack.push(undoStack.pop());
        if(this.undoStack.isEmpty()) { this.textAreaContent = ""; }
        else { this.textAreaContent = undoStack.peek().getValue(); }
        setAppGUITextAreaContent();
        
        //this.debugPrintUndoStackElements();
        //this.debugPrintRedoStackElements();
    }

    public void processRedoAction() {
        if(this.redoStack.isEmpty()) { return; }

        if( !(this.undoStack.isEmpty()) ) {
            if( this.undoStack.peek().getKey() - this.redoStack.peek().getKey() != -1 ) { this.undoStack.clear(); }
        }
        this.undoStack.push(this.redoStack.pop());

        this.textAreaContent = this.undoStack.peek().getValue();
        setAppGUITextAreaContent();

        //this.debugPrintUndoStackElements();
        //this.debugPrintRedoStackElements();
    }

    public void getTextAreaContentOnFixedInterval() {
        Timer timer = new Timer();
        final int task_delay = 0;
        final int task_period = 5000;

        // Add'l synchronization might be needed to ensure this works properly
        timer.schedule(new TimerTask() {
            @Override
            public void run() { 
                if(!stopTimedProcess) { 
                    updateUndoStack();
                } else { timer.cancel(); }
            }
        }, task_delay, task_period);
    }

    private void getAppGUITextAreaContent() { this.textAreaContent  = this.appGUI.getTextArea().getText(); }

    private void setAppGUITextAreaContent() { this.appGUI.getTextArea().setText(textAreaContent); }
    
    private void updateUndoStack() {
        this.getAppGUITextAreaContent();
        if (this.undoStack.isEmpty()) { 
            this.undoStack.push(new Pair<>(this.nextUID++, this.textAreaContent));
         } else {
            if( !this.textAreaContent.equals(this.undoStack.peek().getValue()) ) { 
                this.undoStack.push(new Pair<>(this.nextUID++, this.textAreaContent));
            }
        }
        //this.debugPrintUndoStackElements();
    }

    // Debug Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void debugPrintHello() { System.out.println("Printing hello from UndoAndRedo class."); } // Remove later, just for debugging

    private void debugPrintUndoStackElements() {
        System.out.println("Debug print of undo stack elements: \n");
        // Check if the stack is empty
        if (undoStack.isEmpty()) {
            System.out.println("The undo stack is empty.\n");
            return;
        }

        // Iterate through the stack and print elements
        for (Pair<Integer, String> pair : this.undoStack) {
            System.out.println("Key: " + pair.getKey() + ", Value: " + pair.getValue());
        }
        System.out.println("\n");
    }

    private void debugPrintRedoStackElements() {
        System.out.println("Debug print of redo stack elements: \n");
        // Check if the stack is empty
        if (undoStack.isEmpty()) {
            System.out.println("The redo stack is empty.");
            return;
        }

        // Iterate through the stack and print elements
        for (Pair<Integer, String> pair : this.redoStack) {
            System.out.println("Key: " + pair.getKey() + ", Value: " + pair.getValue());
        }
        System.out.println("\n");
    }
}
