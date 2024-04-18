 import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
public class GUI {
    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(520, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel label = new JLabel("Welcome to Nasa");
        label.setBounds(210, 220, 300, 25);
        panel.add(label);

        frame.setVisible(true);

    }

}

