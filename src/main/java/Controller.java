import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Controller {
    // Define the window
    private JFrame frame;

    // Define the "container" panel of the "pages"
    private JPanel appPanel;

    // Define the different "pages"
    private LoginPanel loginPanel;
    private ActionPanel actionPanel;

    //Define ACA-PY service
    private AcaPyService acaPyService;

    // Define the top-level pages manager
    private CardLayout appCL;

    // Define the properties
    private Properties prop;

    public Controller() {

        // Create the CardLayout
        appCL = new CardLayout();

        // Create the first panel
        frame = new JFrame("SSI Tracker");
        appPanel = new JPanel(appCL);

        // Create the properties
        prop = new Properties();

        acaPyService = new AcaPyService();

        setupGUI();

        frame.pack();
        frame.setVisible(true);
    }

    public void setupGUI() {
        // Set up the the login menu
        setupLogin();

        // Set up the window.
        frame.add(appPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/media/" +
        //        "papiboi99_icon.png"));
        //frame.setIconImage(image);
    }

    public void setupLogin(){
        // Create login menu and configure it
        loginPanel = new LoginPanel();
        appPanel.add(loginPanel, "1");
        appCL.show(appPanel, "1");
        appPanel.validate();
        appPanel.repaint();
        frame.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim = new Dimension(dim.width/4,dim.height/2);
        frame.setSize(dim);
        frame.setLocationRelativeTo(null);
        appPanel.setPreferredSize(dim);
        loginPanel.setPreferredSize(dim);

        JPasswordField keyField = loginPanel.getKeyField();
        JButton loginButton = loginPanel.getLoginButton();

        // When Login button pressed check the credentials to go next
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // The getPassword from JPasswordField returns a char array for security purpose
                    String key = new String(keyField.getPassword());

                    // Try to connect
                    acaPyService.start(key);

                    setupActionMenu();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    // When wrong username or password
                    loginPanel.authFailed();
                    loginPanel.revalidate();
                    frame.pack();
                }
            }
        });

        // When in Password field hits Enter perform button Login pressed
        keyField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
    }

    public void setupActionMenu(){
        // Create mailBox menu and configure it
        actionPanel = new ActionPanel();
        appPanel.add(actionPanel, "2");
        appPanel.validate();
        appPanel.repaint();
        appCL.show(appPanel, "2");
        frame.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dim = new Dimension(dim.width/2,dim.height/2);
        frame.setSize(dim);
        frame.setLocationRelativeTo(null);
        actionPanel.setPreferredSize(dim);

        JButton createInvButton = actionPanel.getCreateInvButton();
        JButton fetchCredentialButton = actionPanel.getFetchCredentialButton();
        JTextArea credTextArea = actionPanel.getCredTextArea();

        // When create connection invitation button pressed calls
        createInvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    acaPyService.createConnectionInvitation();

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null,
                            "Something went wrong while creating the invitation",
                            "Connection error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // When create connection invitation button pressed calls
        fetchCredentialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cred = acaPyService.fetchWalletCredential();
                    credTextArea.setText(cred);

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null,
                            "Something went wrong while fetching the credential",
                            "Wallet error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }

    //TO DO:
    //FIRST PANEL:
    //  - Enter verkey to log in
    //SECOND PANEL:
    //  - Create connection invitation
    //  - See credentials in wallet
    //



}
