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
        frame.setSize(320, 568);
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

    /**
     * 타이틀 패널을 설정하는 클래스입니다.
     *
     * @author Seo  Woojin (zzzeow3@gmail.com)
     *
     * @created 2024-12-22
     * @lastModified 2024-12-26
     *
     * @changelog
     * <ul>
     *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
     * </ul>
     */
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

    /**
     * 이 메소드는 입력을 받기 위한 패널을 생성합니다.
     *
     * @author Seo  Woojin (zzzeow3@gmail.com)
     *
     * @created 2024-12-22
     * @lastModified 2024-12-26
     *
     * @return 입력 패널을 반환합니다.
     *
     * @changelog
     * <ul>
     *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
     * </ul>
     */
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

    /**
     * Amount 정보를 표시하는 패널을 생성합니다.
     *
     * @author Seo  Woojin (zzzeow3@gmail.com)
     *
     * @created 2024-12-22
     * @lastModified 2024-12-26
     *
     * @return 생성된 패널(JPanel) 객체를 반환합니다.
     *
     * @changelog
     * <ul>
     *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
     * </ul>
     */
    private JPanel createAmountPanel() {
        JPanel amountPanel = new JPanel(new GridLayout(1, 2));
        amountPanel.add(new JLabel("Amount:", SwingConstants.CENTER));
        amountLabel = new JLabel("0", SwingConstants.CENTER);
        amountPanel.add(amountLabel);
        return amountPanel;
    }

    /**
     * 결제 처리 로직을 수행합니다.
     * 사용자가 입력한 수량을 기반으로 선택된 레스토랑에 대해 결제 금액을 계산하고 표시합니다.
     * 만약 수량이 유효하지 않거나 레스토랑이 선택되지 않은 경우 오류 메시지가 표시됩니다.
     *
     * @throws NumberFormatException 사용자가 입력한 수량이 유효한 숫자가 아닐 경우 발생합니다.
     * @throws NullPointerException 사용자가 레스토랑을 선택하지 않은 경우 발생합니다.

     * @author Seo  Woojin (zzzeow3@gmail.com)
     *
     * @created 2024-12-22
     * @lastModified 2024-12-26
     *
     * @changelog
     * <ul>
     *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
     * </ul>
     */
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
