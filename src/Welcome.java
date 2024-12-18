import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame implements ActionListener{
    private JButton loginButton;
    private JButton createAccount;
    private JLabel wallpaper;
    private JFrame jFrame1;

    public Welcome() {

        //super("Welcome!");

        initComponents();

        loginButton.setText("Login");
        createAccount.setText("Create Account");

        setLayout(null);
        setVisible(true);

        //setVisible(true);
    }
    private void initComponents() {

        jFrame1 = new JFrame();
        loginButton = new JButton();
        createAccount = new JButton();



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        wallpaper = new JLabel(new ImageIcon("src\\imgs\\wallpaper.png"));
        wallpaper.setText("wallpaper");
        wallpaper.setMaximumSize(new java.awt.Dimension(1000, 700));
        wallpaper.setMinimumSize(new java.awt.Dimension(1000, 700));
        getContentPane().add(wallpaper);
        wallpaper.setBounds(0, 0, 1000, 700);

        loginButton.setText("Login");
        wallpaper.add(loginButton);
        loginButton.setBounds(250, 400, 140, 39);
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == loginButton) {
                    dispose();
                    Login log = new Login();
                    log.setVisible(true);
                }
            }
        });
        //loginButton.setVisible(true);

        createAccount.setText("Create Account");
        wallpaper.add(createAccount);
        createAccount.setBounds(500, 400, 140, 39);
        createAccount.addActionListener(this);
        createAccount.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == createAccount) {
                    //dispose();
                    CreateAccount CA = new CreateAccount();
                    CA.setVisible(true);
                }
            }
        });

        pack();


    }
    public static void main(String []args) {
        new Welcome();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
