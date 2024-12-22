import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextField quantityField;
    private JLabel amountLabel;
    private String selectedRestaurant;


    public Main() {
        frame = new JFrame("CJU Meal Tickets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new GridLayout(6, 1));

        // 타이틀
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel("CJU MEAL TICKETS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subtitleLabel = new JLabel("Nutritious  Cheap  Near", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        frame.add(titlePanel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}
