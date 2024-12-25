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
 * </ul>
 */
public class RestaurantPanel {
    private JPanel panel;
    private String selectedRestaurant;
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
     * </ul>
     */
    private JButton createButton(String restaurantName) {
        JButton button = new JButton(restaurantName);
        button.setBackground(ColorPalette.Button_COLOR);
        button.setForeground(ColorPalette.Payment_COLOR);
        button.addActionListener(e -> {
            selectedRestaurant = restaurantName;
            updateButtonColors(button);
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setBackground(Color.GRAY);
            }
        });

        buttonMap.put(button, restaurantName);
        return button;
    }

    /**
     * 구매 내역을 보여주는 히스토리 버튼을 생성합니다.
     * 버튼을 클릭하면 최근 5개의 구매 내역을 메시지 창에 표시합니다.
     *
     * @return 생성된 히스토리 버튼
     */
    private JButton createHistoryButton() {
        JButton historyButton = new JButton("History");
        historyButton.setBackground(ColorPalette.Button_COLOR);
        historyButton.setForeground(ColorPalette.Payment_COLOR);
        historyButton.addActionListener(e -> {
            StringBuilder historyMessage = new StringBuilder("최근 구매 내역:\n");
            int start = Math.max(0, purchaseHistory.size() - 5);
            for (int i = start; i < purchaseHistory.size(); i++) {
                historyMessage.append(purchaseHistory.get(i)).append("\n");
            }
            JOptionPane.showMessageDialog(panel, historyMessage.toString(), "구매 내역", JOptionPane.INFORMATION_MESSAGE);
        });
        return historyButton;
    }

    /**
     * Payment 버튼이 눌렸을 때 구매 내역에 추가합니다.
     *
     * @param restaurant 선택된 레스토랑 이름
     * @param amount 계산된 금액
     */
    public void addPurchaseHistory(String restaurant, int amount) {
        purchaseHistory.add(String.format("%s - %,d원", restaurant, amount));
    }

    /**
     * 선택된 버튼의 색상을 변경하고, 다른 버튼들의 색상을 초기화합니다.
     *
     * @param selectedButton 선택된 버튼
     */
    private void updateButtonColors(JButton selectedButton) {
        for (JButton button : buttonMap.keySet()) {
            button.setBackground(button == selectedButton ? ColorPalette.ButtonChecked_COLOR : ColorPalette.Button_COLOR);
        }
    }

    public String getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public JPanel getPanel() {
        return panel;
    }
}
