import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class calcDisplay {
    public static void main(String[] args) {
        new MainWindow().displays();
    }
}
class HelpWindow extends JFrame{
    public HelpWindow(){
        setTitle("Help Window");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        JPanel helpPanel = new JPanel();
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("ADD FILES HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        helpPanel.add(picLabel);
        add(helpPanel);
        setVisible(true);
    }
}
