import javax.swing.*;
import java.awt.*;


public class LoginPanel extends JPanel {
    // Define the layout configurations
    private GridBagConstraints constraints;

    // Define components
    private JPasswordField keyField;
    private JLabel titleLabel;
    private JLabel logoLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginPanel() {
        // The login panel will be managed by GridBagLayout
        super(new GridBagLayout());
        constraints = new GridBagConstraints();

        // Create components
        keyField = new JPasswordField(25);
        passwordLabel = new JLabel("Wallet key:");
        titleLabel = new JLabel("SSI TRACKER");
        loginButton = new JButton("Sign in");
        errorLabel = new JLabel();

        // Creating and scaling the logo
        logoLabel = new JLabel();
                //new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/media/" +
        //        "papiboi99_logo.png")).getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH)));

        setupGUI();
    }

    public void setupGUI(){
        setBackground(Color.CYAN);

        // Add the components to the panel
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(40, 40, 2, 40);
        add(logoLabel, constraints);

        constraints.gridy = 1;
        titleLabel.setFont(new java.awt.Font("Calibri", Font.BOLD,20));
        constraints.insets = new Insets(2, 40, 10, 40);
        add(titleLabel, constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.fill = GridBagConstraints.NONE;
        passwordLabel.setFont(new java.awt.Font("Calibri", Font.PLAIN,14));
        constraints.insets = new Insets(5, 40, 0, 40);
        add(passwordLabel, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 40, 10, 40);
        add(keyField, constraints);

        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 40, 40, 40);
        loginButton.setFont(new java.awt.Font("Calibri", Font.BOLD,14));
        add(loginButton, constraints);
    }

    public void authFailed(){
        errorLabel.setText("<html><font color=red size=3><b>" +
                "Incorrect key</b></html>");

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(2, 40, 10, 40);
        add(errorLabel, constraints);

        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 40, 40, 40);
        add(loginButton, constraints);
    }


    public JPasswordField getKeyField() {
        return keyField;
    }
    public JButton getLoginButton() {
        return loginButton;
    }

}
