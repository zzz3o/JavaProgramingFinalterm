import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextField quantityField;
    private JLabel amountLabel;
    private String selectedRestaurant;
    private static final int EMPLOYEE_PRICE = 6000;
    private static final int OTHER_PRICE = 4500;

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

        //입력창
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Menu:", SwingConstants.CENTER));
        JLabel menuLabel = new JLabel("-", SwingConstants.CENTER);
        inputPanel.add(menuLabel);
        inputPanel.add(new JLabel("Quantity:", SwingConstants.CENTER));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        frame.add(inputPanel);

        //결제
        JPanel amountPanel = new JPanel(new GridLayout(1, 2));
        amountPanel.add(new JLabel("Amount:", SwingConstants.CENTER));
        amountLabel = new JLabel("0", SwingConstants.CENTER);
        amountPanel.add(amountLabel);
        frame.add(amountPanel);

        JButton paymentButton = new JButton("Payment");
        frame.add(paymentButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
