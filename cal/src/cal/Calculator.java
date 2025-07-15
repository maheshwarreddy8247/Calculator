package cal;

import javax.swing.*;
import java.awt.*;

public class Calculator {
    private JFrame frame;
    private JTextField display;
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        frame = new JFrame("Colorful Calculator");

        // 🔷 Text Field (Display)
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setBackground(new Color(230, 230, 250)); // light lavender
        display.setForeground(Color.BLACK);

        // 🔷 Panel for Buttons
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(245, 245, 245)); // light grey

        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "C", "=", "/"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));

            // 🎨 Set custom button colors
            if (text.matches("[+\\-*/=]")) {
                btn.setBackground(new Color(255, 165, 0)); // cornflower blue
                btn.setForeground(Color.WHITE);
            } else if (text.equals("C")) {
                btn.setBackground(new Color(0, 128, 0)); // tomato red
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }

            btn.setFocusPainted(false);
            panel.add(btn);

            btn.addActionListener(e -> handleButtonClick(e.getActionCommand()));
        }

        // 🔷 Frame Layout
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.getContentPane().setBackground(new Color(220, 220, 220)); // background color
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center window
        frame.setVisible(true);
    }

    private void handleButtonClick(String text) {
        if (text.matches("[0-9]")) {
            display.setText(display.getText() + text);
        } else if (text.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = text.charAt(0);
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (text.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            display.setText("Error: Divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (text.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
