package client.forms;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageDialog {
    public static void showError(String title, String message) {
    JOptionPane.showMessageDialog(new JFrame(), message, title,
        JOptionPane.ERROR_MESSAGE);
    }
}
