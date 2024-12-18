import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home extends JFrame {
    private JLabel image;
    private JButton italian;
    private JButton french;
    private JButton american;
    private JButton indian;
    private JButton viewprof;
    private JButton viewcart;

    public Home() {
        super("Home");

        image = new JLabel();
        italian = new JButton();
        french = new JButton();
        american = new JButton();
        indian = new JButton();
        viewprof = new JButton("View Profile");
        viewcart = new JButton("View Cart");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        image.setIcon(new ImageIcon("src\\imgs\\homescreen.png"));
        image.setText("jLabel1");
        getContentPane().add(image);
        image.setBounds(0, 0, 800, 800);

        italian.setMaximumSize(new java.awt.Dimension(400, 329));
        italian.setMinimumSize(new java.awt.Dimension(400, 329));
        italian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ItalianMenu im = new ItalianMenu();
                im.setVisible(true);
            }
        });
        getContentPane().add(italian);
        italian.setBounds(0, 145, 400, 329);

        //Make the button invisible
        italian.setOpaque(false);
        italian.setContentAreaFilled(false);
        italian.setBorderPainted(false);

        french.setMaximumSize(new java.awt.Dimension(400, 329));
        french.setMinimumSize(new java.awt.Dimension(400, 329));
        french.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrenchMenu fm = new FrenchMenu();
                fm.setVisible(true);
            }
        });
        getContentPane().add(french);
        french.setBounds(400, 145, 400, 329);

        //Make the button invisible
        french.setOpaque(false);
        french.setContentAreaFilled(false);
        french.setBorderPainted(false);

        american.setMaximumSize(new java.awt.Dimension(400, 329));
        american.setMinimumSize(new java.awt.Dimension(400, 329));
        american.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AmericanMenu am = new AmericanMenu();
                am.setVisible(true);
            }
        });
        getContentPane().add(american);
        american.setBounds(0, 473, 400, 329);

        //Make the button invisible
        american.setOpaque(false);
        american.setContentAreaFilled(false);
        american.setBorderPainted(false);

        indian.setMaximumSize(new java.awt.Dimension(400, 329));
        indian.setMinimumSize(new java.awt.Dimension(400, 329));
        indian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                IndianMenu im = new IndianMenu();
                im.setVisible(true);
            }
        });
        getContentPane().add(indian);
        indian.setBounds(400, 473, 400, 329);

        //Make the button invisible
        indian.setOpaque(false);
        indian.setContentAreaFilled(false);
        indian.setBorderPainted(false);

        viewprof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewProfile vp = new ViewProfile();
                vp.setVisible(true);
            }
        });
        image.add(viewprof);
        viewprof.setBounds(600, 60, 120, 40);

        viewcart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Cart c = new Cart();
                c.setVisible(true);
            }
        });
        image.add(viewcart);
        viewcart.setBounds(480, 60, 100, 40);
        // Center the frame
        setLocationRelativeTo(null); // This will center the JFrame on the screen

        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Home();

    }

}
