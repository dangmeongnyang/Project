package health;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TodayTotal extends JDialog {
    private JTextField caloriesField;
    private JTextField exerciseField;

    public TodayTotal(JFrame parent) {
    	super(parent, "Today's Total", true);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Calories Total (Kcal):"));
        caloriesField = new JTextField(10);
        add(caloriesField);

        add(new JLabel("Exercise Total(minutes):"));
        exerciseField = new JTextField(10);
        add(exerciseField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveData());
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        pack();
        setLocationRelativeTo(parent);
    }

    private void saveData() {
        int calories = Integer.parseInt(caloriesField.getText());
        int exercise = Integer.parseInt(exerciseField.getText());

        Server.saveData(calories, exercise);
        dispose();
    }
}