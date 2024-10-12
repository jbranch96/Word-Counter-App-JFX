package WordCounterJFX.model;

import WordCounterJFX.view.AppGUI;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class WordAndCharCounter {

    private AppGUI appGUI;
    private TextArea textArea;
    private Label labelWordCount, labelCharCount;

    private int charCount;
    private int wordCount;
    private String textAreaContent;

    public WordAndCharCounter(AppGUI appGUI) {
        this.appGUI = appGUI;
        this.textArea = this.appGUI.getTextArea();
        this.labelCharCount = this.appGUI.getCharCount();
        this.labelWordCount = this.appGUI.getWordCount();
    }

    public void setupTextAreaListener() {
        this.textArea.textProperty().addListener((obseravable, oldVal, newVal) -> { countWordAndChar(); });
    }

    private void countWordAndChar() {
        this.charCount = 0;
        this.wordCount = 0;
        this.textAreaContent = this.textArea.getText();

        Character prevChar = ' ';
        
        // TODO: Come back to this, and implement a proper Regex solution. 

        // Doesn't handle special chars well, but should be mostly accurate as is.
        // Known Edge Case: This considers sequences of all special chars as words, for example "------------------- ))))))" is consider two words.
        for(Character c : textAreaContent.toCharArray()) {
            if( (!c.equals(' ')) && (!c.equals('\n')) && (!c.equals(null)) ) { charCount++; }
            if( ((prevChar.equals(' ')) || (prevChar.equals('\n')) ) && ((!c.equals(' ')) && (!c.equals('\n'))) ) { wordCount++; }
            if( (charCount > 0) && (wordCount == 0) ) { wordCount = 1; }
            prevChar = c;
        }
        this.labelCharCount.setText("Characters (no spaces): " + charCount);
        this.labelWordCount.setText("Words: " + wordCount);
    }
}