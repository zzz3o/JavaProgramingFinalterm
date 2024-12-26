import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 레스토랑 선택을 위한 패널을 생성하는 클래스입니다.
 * 이 클래스는 여러 레스토랑 버튼을 생성하고, 사용자가 선택한 레스토랑을 표시합니다.
 *
 * @author Seo  Woojin (zzzeow3@gmail.com)
 *
 * @created 2024-12-22
 * @lastModified 2024-12-26
 *
 * @changelog
 * <ul>
 *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
 *   <li>2024-12-25: 버튼 여백 추가 (Seo  Woojin)</li>
 *   <li>2024-12-26: 구매 내역 관리 기능 추가 (Seo  Woojin)</li>
 *   <li>2024-12-26: 둥근 버튼 적용 (Seo Woojin)</li>
 *   <li>2024-12-26: 클릭 상태 둥근 버튼 유지 수정 (Seo Woojin)</li>
 * </ul>
 */
public class RestaurantPanel {
    private JPanel panel;
    private String selectedRestaurant;
    private JButton selectedButton;
    private Map<JButton, String> buttonMap;
    private ArrayList<String> purchaseHistory;

    public RestaurantPanel() {
        panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(ColorPalette.BACKGROUND_COLOR);
        buttonMap = new HashMap<>();
        purchaseHistory = new ArrayList<>();

        JButton studentCafeteria = createButton("학생식당");
        JButton employeeCafeteria = createButton("교직원식당");
        JButton businessBuilding = createButton("비즈니스대학");
        JButton dormCafeteria = createButton("기숙사식당");

        panel.add(studentCafeteria);
        panel.add(employeeCafeteria);
        panel.add(businessBuilding);
        panel.add(dormCafeteria);
    }

    /**
     * 주어진 레스토랑 이름을 가진 버튼을 생성합니다.
     * 버튼을 클릭하면 해당 레스토랑이 선택되며, 선택된 버튼은 색상이 변경됩니다.
     *
     * @author Seo  Woojin (zzzeow3@gmail.com)
     *
     * @created 2024-12-22
     * @lastModified 2024-12-26
     *
     * @param restaurantName 레스토랑 이름
     * @return 생성된 JButton 객체
     *
     * @changelog
     * <ul>
     *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
     *   <li>2024-12-24: 지정 색상 변경 (Seo  Woojin)</li>
     *   <li>2024-12-26: 마우스 클릭 시 색상 변경 추가 (Seo  Woojin)</li>
     *   <li>2024-12-26: 둥근 버튼 적용 (Seo Woojin)</li>
     *   <li>2024-12-26: 클릭 상태 둥근 버튼 유지 수정 (Seo Woojin)</li>
     * </ul>
     */
    private JButton createButton(String restaurantName) {
        JButton button = new JButton(restaurantName) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 둥근 배경 그리기
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // 버튼 내용 그리기
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // 테두리 비활성화
            }
        };

        button.setFocusPainted(false);
        button.setContentAreaFilled(false); // 기본 배경 비활성화
        button.setOpaque(false); // 투명 배경 활성화
        button.setBackground(ColorPalette.Button_COLOR);
        button.setForeground(ColorPalette.Payment_COLOR);
        button.setPreferredSize(new Dimension(100, 55));

        // 버튼 클릭 상태에 따른 색상 변경
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setBackground(ColorPalette.ButtonChecked_COLOR);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setBackground(button == selectedButton ? ColorPalette.ButtonChecked_COLOR : ColorPalette.Button_COLOR);
            }
        });

        button.addActionListener(e -> {
            selectedRestaurant = restaurantName;
            updateButtonColors(button);
        });

        buttonMap.put(button, restaurantName);
        return button;
    }


    /**
     * 선택된 버튼의 색상을 변경하고, 다른 버튼들의 색상을 초기화합니다.
     *
     * @param selectedButton 선택된 버튼
     */
    private void updateButtonColors(JButton selectedButton) {
        this.selectedButton = selectedButton;
        for (JButton button : buttonMap.keySet()) {
            button.setBackground(button == selectedButton ? ColorPalette.ButtonChecked_COLOR : ColorPalette.Button_COLOR);
        }
    }

    /**
     * 구매 내역에 새로운 항목을 추가합니다.
     *
     * @param restaurantName 선택된 레스토랑 이름
     * @param amount 총 결제 금액
     */
    public void addPurchaseHistory(String restaurantName, int amount) {
        String historyEntry = "식당: " + restaurantName + ", 총 금액: " + amount + "원";
        purchaseHistory.add(historyEntry);
    }

    /**
     * 최근 5개의 구매 내역을 반환합니다.
     *
     * @return 최근 구매 내역 리스트
     */
    public ArrayList<String> getRecentHistory() {
        int size = purchaseHistory.size();
        int start = Math.max(0, size - 5);
        return new ArrayList<>(purchaseHistory.subList(start, size));
    }

    public String getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public JPanel getPanel() {
        return panel;
    }
}
