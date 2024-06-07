package health;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QnA extends JDialog {
    private JTextArea questionTextArea;
    private JList<String> questionList;
    private DefaultListModel<String> listModel;
    private List<String> questions;

    public QnA(MainFrame mainFrame) {
        super(mainFrame, "Q&A", true);
        setSize(400, 400);
        setLocationRelativeTo(mainFrame);

        questions = new ArrayList<>();
        listModel = new DefaultListModel<>();
        questionList = new JList<>(listModel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(questionList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Question");
        JButton removeButton = new JButton("Remove Question");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        questionTextArea = new JTextArea(5, 20);
        panel.add(new JScrollPane(questionTextArea), BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestion();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeQuestion();
            }
        });

        add(panel);
    }

    private void addQuestion() {
        String question = questionTextArea.getText().trim();
        if (!question.isEmpty()) {
            questions.add(question);
            listModel.addElement(question);
            questionTextArea.setText("");
        }
    }

    private void removeQuestion() {
        int selectedIndex = questionList.getSelectedIndex();
        if (selectedIndex != -1) {
            questions.remove(selectedIndex);
            listModel.remove(selectedIndex);
        }
    }
}