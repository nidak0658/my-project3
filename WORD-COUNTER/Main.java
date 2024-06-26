import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCountApp {
    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Word Count Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new WordCountPanel());
            frame.setVisible(true);
        });
    }
}

class WordCountPanel extends JPanel {
    private JTextArea textArea;
    private JButton countButton;
    private JLabel resultLabel;

    public WordCountPanel() {
        // Set up layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Initialize components
        textArea = new JTextArea(10, 30);
        countButton = new JButton("Count Words");
        resultLabel = new JLabel("Number of words: 0");

        // Add components to the panel
        add(new JScrollPane(textArea));
        add(countButton);
        add(resultLabel);

        // Add button click listener
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                int wordCount = countWords(text);
                resultLabel.setText("Number of words: " + wordCount);
            }
        });
    }

    // Method to count words in a given text
    private int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
