import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

    private JButton createButton(String restaurantName) {
        JButton button = new JButton(restaurantName);
        button.addActionListener(e -> {
            selectedRestaurant = restaurantName;
            updateButtonColors(button);
        });
        buttonMap.put(button, restaurantName);
        return button;
    }

    private void updateButtonColors(JButton selectedButton) {
        for (JButton button : buttonMap.keySet()) {
            button.setBackground(button == selectedButton ? Color.ORANGE : null);
        }
    }

    public String getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public JPanel getPanel() {
        return panel;
    }
}
