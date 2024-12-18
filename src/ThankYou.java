import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ThankYou extends JFrame{
    private JLabel wallpaper;

    public ThankYou(){
        super("Thank You!");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1155, 790));
        setResizable(false);
        setLayout(null);

        wallpaper = new JLabel(new ImageIcon("src\\thankyou.png"));
        wallpaper.setText("wallpaper");
        wallpaper.setMaximumSize(new java.awt.Dimension(1155, 790));
        wallpaper.setMinimumSize(new java.awt.Dimension(1155, 790));
        getContentPane().add(wallpaper);
        wallpaper.setBounds(0, 0, 1155, 790);
    }

    public static void main(String[]args) {
        new ThankYou().setVisible(true);
    }

}
