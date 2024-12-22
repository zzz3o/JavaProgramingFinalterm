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

        // 식당
        JPanel restaurantPanel = new JPanel(new GridLayout(2, 2));
        JButton studentCafeteria = new JButton("학생식당");
        JButton employeeCafeteria = new JButton("교직원식당");
        JButton businessBuilding = new JButton("비즈니스대학");
        JButton dormCafeteria = new JButton("기숙사식당");

        restaurantPanel.add(studentCafeteria);
        restaurantPanel.add(employeeCafeteria);
        restaurantPanel.add(businessBuilding);
        restaurantPanel.add(dormCafeteria);

        frame.add(restaurantPanel);

        //이벤트
        studentCafeteria.addActionListener(e -> {
            selectedRestaurant = "학생식당";
            menuLabel.setText(selectedRestaurant);
            updateButtonColors(studentCafeteria, restaurantPanel);
        });

        employeeCafeteria.addActionListener(e -> {
            selectedRestaurant = "교직원식당";
            menuLabel.setText(selectedRestaurant);
            updateButtonColors(employeeCafeteria, restaurantPanel);
        });

        businessBuilding.addActionListener(e -> {
            selectedRestaurant = "비즈니스대학";
            menuLabel.setText(selectedRestaurant);
            updateButtonColors(businessBuilding, restaurantPanel);
        });

        dormCafeteria.addActionListener(e -> {
            selectedRestaurant = "기숙사식당";
            menuLabel.setText(selectedRestaurant);
            updateButtonColors(dormCafeteria, restaurantPanel);
        });

        paymentButton.addActionListener(e -> calculateAmount());

        frame.setVisible(true);
    }

    //버튼 색상 변경
    private void updateButtonColors(JButton selectedButton, JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button == selectedButton) {
                    button.setBackground(Color.ORANGE);
                } else {
                    button.setBackground(null);
                }
            }
        }
    }

    private void calculateAmount() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            int price = selectedRestaurant.equals("교직원식당") ? EMPLOYEE_PRICE : OTHER_PRICE;
            int amount = quantity * price;
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
