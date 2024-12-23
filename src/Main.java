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
        frame.getContentPane().setBackground(ColorPalette.BACKGROUND_COLOR);

        // 타이틀 패널
        JPanel titlePanel = createTitlePanel();

        // 입력 패널
        JPanel inputPanel = createInputPanel();

        // 결제 버튼
        JButton paymentButton = new JButton("Payment");
        paymentButton.addActionListener(e -> handlePayment());


        // 식당 패널
        restaurantPanel = new RestaurantPanel();

        // GridBagLayout을 사용하여 레이아웃 설정
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 타이틀 패널 추가
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10); // 여백 추가
        frame.add(titlePanel, gbc);

        // 입력 패널 추가
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(inputPanel, gbc);

        // 식당 패널 추가
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(restaurantPanel.getPanel(), gbc);

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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // 외부 여백 설정
        titlePanel.setBackground(ColorPalette.BACKGROUND_COLOR);
        JLabel titleLabel = new JLabel("<html>CJU<br />Meal<br />Tickets</html>", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(ColorPalette.Payment_COLOR);
        JLabel subtitleLabel = new JLabel("<html><div style='text-align:right;'>Nutritious<br/>Cheap<br/>Near</div></html>", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        subtitleLabel.setForeground(ColorPalette.Payment_COLOR);

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
     *   <li>2024-12-23: AmountPanel과 합쳐짐 (Seo  Woojin)</li>
     * </ul>
     */
    private JPanel createInputPanel() {
        // 최상위 패널 생성
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        inputPanel.setPreferredSize(new Dimension(260, 161));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 외부 여백 설정

        // Menu 패널 생성
        JPanel menuPanel = new JPanel(new BorderLayout());
        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
        menuLabel.setForeground(ColorPalette.Payment_COLOR);
        menuPanel.setBackground(new Color(85, 107, 47)); // 배경색 설정
        menuPanel.add(menuLabel);

        // Quantity 패널
        JPanel quantityPanel = new JPanel(new BorderLayout());
        quantityField = new JTextField("Quantity");
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField.setForeground(ColorPalette.Button_COLOR);
        quantityPanel.setBackground(ColorPalette.Payment_COLOR); // 배경색 설정
        quantityPanel.add(quantityField);

        // Amount 패널
        JPanel amountPanel = new JPanel(new BorderLayout());
        amountLabel = new JLabel("Amount", SwingConstants.CENTER);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setForeground(ColorPalette.Payment_COLOR);
        amountPanel.setBackground(ColorPalette.ButtonChecked_COLOR); // 배경색 설정
        amountPanel.add(amountLabel);

        // GridBagConstraints 설정
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputPanel.add(menuPanel, gbc);

        // Quantity 패널을 0,1 위치에 배치
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // 너비를 1로 설정
        inputPanel.add(quantityPanel, gbc);

        // Amount 패널을 1,1 위치에 배치
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // 너비를 1로 설정
        inputPanel.add(amountPanel, gbc);

        return inputPanel;
    }

    /**
     * 결제 처리 로직을 수행합니다.
     * 사용자가 입력한 수량을 기반으로 선택된 레스토랑에 대해 결제 금액을 계산하고 표시합니다.
     * 만약 수량이 유효하지 않거나 레스토랑이 선택되지 않은 경우 오류 메시지가 표시됩니다.
     *
     * @throws NumberFormatException 사용자가 입력한 수량이 유효한 숫자가 아닐 경우 발생합니다.
     * @throws NullPointerException 사용자가 레스토랑을 선택하지 않은 경우 발생합니다.
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
    private void handlePayment() {
        try {
            String restaurant = restaurantPanel.getSelectedRestaurant();
            int quantity = Integer.parseInt(quantityField.getText());
            int amount = PaymentCalculator.calculateAmount(restaurant, quantity);
            amountLabel.setText(String.valueOf(amount)); // amountLabel 업데이트
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
