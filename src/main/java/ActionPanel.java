import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {
    // Define the layout configurations
    private GridBagConstraints constraints;

    // Define components
    private JButton createInvButton;
    private JButton fetchCredentialButton;
    private JTextArea credTextArea;
    private JLabel logoLabel;

    public ActionPanel() {
        super(new GridBagLayout());
        constraints = new GridBagConstraints();

        // Create components
        createInvButton = new JButton("Create Connection Invitation");
        fetchCredentialButton = new JButton("Fetch Wallet Credential");
        credTextArea = new JTextArea();

        // Creating and scaling the logo
        logoLabel = new JLabel();
                //new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/media/" +
        //        "papiboi99_logo.png")).getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH)));

        setupGUI();
    }

    public void setupGUI(){
        setBackground(Color.CYAN);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(40, 40, 40, 40);
        createInvButton.setFont(new java.awt.Font("Calibri", Font.BOLD,14));
        add(createInvButton, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 40, 40, 40);
        fetchCredentialButton.setFont(new java.awt.Font("Calibri", Font.BOLD,14));
        add(fetchCredentialButton, constraints);

        constraints.gridy = 2;
        constraints.gridheight = 5;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 40, 40, 40);
        credTextArea.setEditable(false);
        credTextArea.setLineWrap(true);
        credTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(credTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, constraints);
    }

    public JButton getCreateInvButton() { return createInvButton; }
    public JButton getFetchCredentialButton() { return fetchCredentialButton; }
    public JTextArea getCredTextArea() { return credTextArea; }
}
