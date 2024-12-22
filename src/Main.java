import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame frame;
    private JTextField quantityField;
    private JLabel amountLabel;
    private RestaurantPanel restaurantPanel;

    public Main() {
        frame = new JFrame("CJU Meal Tickets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new GridLayout(6, 1));

        // 타이틀 패널
        JPanel titlePanel = createTitlePanel();
        frame.add(titlePanel);

        // 입력 패널
        JPanel inputPanel = createInputPanel();
        frame.add(inputPanel);

        // 금액 패널
        JPanel amountPanel = createAmountPanel();
        frame.add(amountPanel);

        // 식당 패널
        restaurantPanel = new RestaurantPanel();
        frame.add(restaurantPanel.getPanel());

        // 결제 버튼
        JButton paymentButton = new JButton("Payment");
        paymentButton.addActionListener(e -> handlePayment());
        frame.add(paymentButton);

        frame.setVisible(true);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new GridLayout(1, 2));
        JLabel titleLabel = new JLabel("<html>CJU<br />Meal<br />Tickets</html>", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel subtitleLabel = new JLabel("<html><div style='text-align:right;'>Nutritious<br/>Cheap<br/>Near</div></html>");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        return titlePanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Menu:", SwingConstants.CENTER));
        JLabel menuLabel = new JLabel("-", SwingConstants.CENTER);
        inputPanel.add(menuLabel);
        inputPanel.add(new JLabel("Quantity:", SwingConstants.CENTER));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        return inputPanel;
    }

    private JPanel createAmountPanel() {
        JPanel amountPanel = new JPanel(new GridLayout(1, 2));
        amountPanel.add(new JLabel("Amount:", SwingConstants.CENTER));
        amountLabel = new JLabel("0", SwingConstants.CENTER);
        amountPanel.add(amountLabel);
        return amountPanel;
    }

    private void handlePayment() {
        try {
            String restaurant = restaurantPanel.getSelectedRestaurant();
            int quantity = Integer.parseInt(quantityField.getText());
            int amount = PaymentCalculator.calculateAmount(restaurant, quantity);
            amountLabel.setText(String.valueOf(amount));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(frame, "Please select a restaurant.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
