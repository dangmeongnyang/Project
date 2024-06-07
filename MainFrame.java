package health;
	
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel cardPanel;
    private JPanel topPanel;
    private JLabel imageLabel;
    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;

    public MainFrame() {
        setTitle("고객님 운동 잘하고 계신거죠?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        cardPanel = new JPanel(new CardLayout());
        cardPanel.setOpaque(false);

        loginPanel = new LoginPanel();
        registrationPanel = new RegistrationPanel();

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registrationPanel, "Registration");

        topPanel = new JPanel();
        imageLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("images/banner.jpg");
        Image image = backgroundImage.getImage().getScaledInstance(550, 350, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        topPanel.add(imageLabel);

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(cardPanel, BorderLayout.CENTER);

        add(backgroundPanel);
    }
    
    public void showMainMenuPanel(User user) {
        MainMenuPanel mainMenuPanel = new MainMenuPanel(user);
        cardPanel.add(mainMenuPanel, "MainMenu");
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "MainMenu");
        topPanel.setVisible(false);
    }
    
    private class LoginPanel extends JPanel implements ActionListener {
        private JTextField IDfield	;
        private JPasswordField passwordfield;
        private JButton loginButton;
        private JButton registerButton;

        public LoginPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("ID :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            IDfield = new JTextField(15);
            add(IDfield, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Password :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            passwordfield = new JPasswordField(15);
            add(passwordfield, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            loginButton = new JButton("Login");
            loginButton.addActionListener(this);
            add(loginButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            registerButton = new JButton("Register");
            registerButton.addActionListener(this);
            add(registerButton, gbc);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                String ID = IDfield.getText();
                String password = new String(passwordfield.getPassword());

                User user = Server.authenticateUser(ID, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    showMainMenuPanel(user);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid ID or password!");
                }
            } else if (e.getSource() == registerButton) {
                // 등록 패널로 전환
                CardLayout cardLayout = (CardLayout) ((JPanel) getParent()).getLayout();
                cardLayout.show(getParent(), "Registration");
            }
        }
    }

    private class RegistrationPanel extends JPanel implements ActionListener {
        private JTextField IDfield;
        private JTextField namefield;
        private JTextField phonenumberfield;
        private JPasswordField passwordfield;
        private JPasswordField confirmPasswordfield;
        private JButton registerButton;
        private JButton backButton;
        

        public RegistrationPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("ID :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            IDfield = new JTextField(15);
            add(IDfield, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Password :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            passwordfield = new JPasswordField(15);
            add(passwordfield, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Confirm Password :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            confirmPasswordfield = new JPasswordField(15);
            add(confirmPasswordfield, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("Name :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            namefield = new JTextField(15);
            add(namefield, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            add(new JLabel("Phone number :"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            phonenumberfield = new JTextField(15);
            add(phonenumberfield, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            registerButton = new JButton("Register");
            registerButton.addActionListener(this);
            add(registerButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            backButton = new JButton("Back");
            backButton.addActionListener(this);
            add(backButton, gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerButton) {
                String name = namefield.getText();
                String ID = IDfield.getText();
                String phoneNumber = phonenumberfield.getText();
                String password = new String(passwordfield.getPassword());
                String confirmPassword = new String(confirmPasswordfield.getPassword());

                if (password.equals(confirmPassword)) {
                    User user = new User(name, ID, phoneNumber, password);
                    if (Server.registerUser(user)) {
                        JOptionPane.showMessageDialog(this, "Registration successful!");
                        // 로그인 패널로 전환
                        CardLayout cardLayout = (CardLayout) ((JPanel) getParent()).getLayout();
                        cardLayout.show(getParent(), "Login");
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration failed!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Passwords do not match!");
                }
            } else if (e.getSource() == backButton) {
                // 로그인 패널로 전환
                CardLayout cardLayout = (CardLayout) ((JPanel) getParent()).getLayout();
                cardLayout.show(getParent(), "Login");
            }
        }
    }
}