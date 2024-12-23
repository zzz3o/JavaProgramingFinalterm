import javax.swing.*;
import java.awt.*;
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
 * </ul>
 */
public class RestaurantPanel {
    private JPanel panel;
    private String selectedRestaurant;
    private Map<JButton, String> buttonMap;


    public RestaurantPanel() {
        panel = new JPanel(new GridLayout(2, 2));
        buttonMap = new HashMap<>();

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
     * </ul>
     */
    private JButton createButton(String restaurantName) {
        JButton button = new JButton(restaurantName);
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
        for (JButton button : buttonMap.keySet()) {
            button.setBackground(button == selectedButton ? ColorPalette.ButtonChecked_COLOR : null);
        }
    }

    public String getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public JPanel getPanel() {
        return panel;
    }
}
