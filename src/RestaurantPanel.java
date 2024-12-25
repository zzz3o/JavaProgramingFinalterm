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
        panel = new JPanel(new GridLayout(2, 2, 10, 10)); // 버튼 사이에 여백 추가
        panel.setBackground(ColorPalette.BACKGROUND_COLOR);
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
     *   <li>2024-12-24: 지정 색상 변경 (Seo  Woojin)</li>
     *   <li>2024-12-25: 버튼 크기 설정 (Seo  Woojin)</li>
     * </ul>
     */
    private JButton createButton(String restaurantName) {
        JButton button = new JButton(restaurantName);
        button.setPreferredSize(new Dimension(100, 50));
        button.setBackground(ColorPalette.Button_COLOR);
        button.setForeground(ColorPalette.Payment_COLOR);

        // 버튼의 "Pressed" 상태 색상 설정
        button.getModel().addChangeListener(e -> {
            ButtonModel model = button.getModel();
            if (model.isPressed()) {
                button.setBackground(new Color(255, 153, 153)); // Pressed 상태의 배경색 설정
                button.setForeground(Color.WHITE); // Pressed 상태의 텍스트 색상
            } else if (model.isRollover()) {
                button.setBackground(ColorPalette.ButtonChecked_COLOR); // Hover 상태의 배경색
            } else {
                button.setBackground(ColorPalette.Button_COLOR); // 기본 상태의 배경색
                button.setForeground(ColorPalette.Payment_COLOR); // 기본 텍스트 색상
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
