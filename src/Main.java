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
        frame.setSize(340, 568);
        frame.getContentPane().setBackground(ColorPalette.BACKGROUND_COLOR);

        JPanel titlePanel = createTitlePanel();

        JPanel inputPanel = createInputPanel();

        // 결제 버튼
        JButton paymentButton = new JButton("Payment") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // 테두리 제거
            }
        };
        paymentButton.setBackground(ColorPalette.Payment_COLOR);
        paymentButton.setForeground(ColorPalette.BACKGROUND_COLOR);
        paymentButton.setFont(new Font("Inter", Font.BOLD, 20));
        paymentButton.setPreferredSize(new Dimension(260, 56));
        paymentButton.setFocusPainted(false);
        paymentButton.setContentAreaFilled(false);
        paymentButton.setOpaque(false);
        paymentButton.addActionListener(e -> handlePayment());

        restaurantPanel = new RestaurantPanel();

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 타이틀 패널
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 20, 10, 20);
        frame.add(titlePanel, gbc);

        // 입력 패널
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(inputPanel, gbc);


        // 결제 버튼
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);
        frame.add(paymentButton, gbc);

        // 식당 패널
        gbc.gridx = 0;
        gbc.gridy = 3;
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
     *   <li>2024-12-22: 패널 크기 설정 (Seo  Woojin)</li>
     *
     * </ul>
     */
    private JPanel createTitlePanel() {
        RoundedPanel titlePanel = new RoundedPanel(20); // 코너 반경 설정
        titlePanel.setPreferredSize(new Dimension(250, 116));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        titlePanel.setBackground(ColorPalette.BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("<html>CJU<br />Meal<br />Tickets</html>", JLabel.CENTER);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 28));
        titleLabel.setForeground(ColorPalette.Payment_COLOR);

        JLabel subtitleLabel = new JLabel("<html><div style='text-align:right;'>Nutritious<br/>Cheap<br/>Near</div></html>", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Inter", Font.ITALIC, 18));
        subtitleLabel.setForeground(ColorPalette.Payment_COLOR);

        titlePanel.setLayout(new GridLayout(1, 2));
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
     *   <li>2024-12-23: GridBagLayout으로 변경 (Seo  Woojin)</li>
     *   <li>2024-12-23: 각 패널 위치 수정 (Seo  Woojin)</li>
     *   <li>2024-12-24: 패널의 텍스트 위치 변경 (Seo  Woojin)</li>
     *   <li>2024-12-26: 전체 폰트 변경 (Seo  Woojin)</li>
     *   <li>2024-12-26: history  패널 추가 (Seo  Woojin)</li>
     *
     * </ul>
     */
    private JPanel createInputPanel() {
        RoundedPanel inputPanel = new RoundedPanel(20); // 코너 반경 설정
        GridBagConstraints gbc = new GridBagConstraints();
        inputPanel.setPreferredSize(new Dimension(260, 160));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(ColorPalette.InputPanel_COLOR);

        // Menu 패널
        RoundedPanel menuPanel = new RoundedPanel(10);
        menuPanel.setLayout(new BorderLayout());

        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Inter", Font.BOLD, 16));
        menuLabel.setForeground(ColorPalette.Payment_COLOR);
        menuLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel menuList = new JLabel("<html><div style='text-align:center;'>오돈불고기<br/>쌀밥/잡곡밥<br/>미역국<br/>고들빼기무침<br/>배추김치</html>", SwingConstants.CENTER);
        menuList.setFont(new Font("Inter", Font.PLAIN, 12));
        menuList.setForeground(ColorPalette.Payment_COLOR);

        menuPanel.add(menuLabel, BorderLayout.NORTH);
        menuPanel.add(menuList, BorderLayout.CENTER);

        menuPanel.setBackground(ColorPalette.Button_COLOR);

        // Quantity 패널
        RoundedPanel quantityPanel = new RoundedPanel(10);
        quantityField = new JTextField("Quantity");
        quantityField.setBorder(null);
        quantityField.setFont(new Font("Inter", Font.PLAIN, 16));
        quantityField.setForeground(ColorPalette.Button_COLOR);
        quantityField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        quantityPanel.setBackground(ColorPalette.Payment_COLOR);
        quantityPanel.add(quantityField);

        // History 버튼
        RoundedPanel historyPanel = new RoundedPanel(10);
        JButton historyButton = new JButton("History");
        historyButton.setFont(new Font("Inter", Font.BOLD, 16));
        historyButton.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        historyButton.setForeground(ColorPalette.Payment_COLOR);
        historyButton.setBackground(ColorPalette.ButtonChecked_COLOR);
        historyButton.addActionListener(e -> showPurchaseHistory());
        historyPanel.setBackground(ColorPalette.ButtonChecked_COLOR);
        historyPanel.add(historyButton);

        // Layout 설정
        inputPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Menu
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        gbc.weightx = 1.0;
        inputPanel.add(menuPanel, gbc);

        // Quantity
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        inputPanel.add(quantityPanel,gbc);


        // history
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 0.2;
        inputPanel.add(historyPanel, gbc);
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
     * @lastModified 2024-12-23
     *
     * @changelog
     * <ul>
     *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
     *   li>2024-12-22: amountLabel 업데이트 (Seo  Woojin)</li>
     *   li>2024-12-26: 결제 메시지 업데이트 (Seo  Woojin)</li>
     * </ul>
     */
    private void handlePayment() {
        try {
            String restaurant = restaurantPanel.getSelectedRestaurant();
            int quantity = Integer.parseInt(quantityField.getText());
            int amount = PaymentCalculator.calculateAmount(restaurant, quantity);

            // 구매 내역에 추가
            restaurantPanel.addPurchaseHistory(restaurant, amount);

            // 총 금액과 선택된 레스토랑을 메시지 창에 표시
            JOptionPane.showMessageDialog(frame,
                    String.format("총 금액: %,d원\n식당: %s", amount, restaurant),
                    "결제 완료",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(frame, "Please select a restaurant.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 최근 구매 내역을 메시지 창에 표시합니다.
     * <p>
     * {@link RestaurantPanel#getRecentHistory()} 메서드를 호출하여
     * 최근 5개의 구매 내역을 가져오고 이를 메시지 창으로 출력합니다.
     * </p>
     *
     * @author Seo  Woojin
     *
     * @created 2024-12-26
     * @lastModified 2024-12-26
     *
     * @changelog
     * <ul>
     *   <li>2024-12-26: 최초 생성 (Seo Woojin)</li>
     * </ul>
     */
    private void showPurchaseHistory() {
        java.util.List<String> history = restaurantPanel.getRecentHistory();
        StringBuilder historyMessage = new StringBuilder("최근 구매 내역:\n");
        for (String entry : history) {
            historyMessage.append(entry).append("\n");
        }

        JOptionPane.showMessageDialog(frame, historyMessage.toString(), "Purchase History", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new Main();
    }
}
