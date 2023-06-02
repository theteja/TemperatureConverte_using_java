import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton convertButton;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        inputField = new JTextField(10);
        convertButton = new JButton("Convert");
        fromComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        toComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        resultLabel = new JLabel("Result:");

        // Create panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Temperature:"));
        inputPanel.add(inputField);

        // Create panel for unit selection components
        JPanel unitPanel = new JPanel();
        unitPanel.setLayout(new FlowLayout());
        unitPanel.add(new JLabel("From:"));
        unitPanel.add(fromComboBox);
        unitPanel.add(new JLabel("To:"));
        unitPanel.add(toComboBox);

        // Create panel for result display
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultPanel.add(resultLabel);

        // Create panel for convert button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(convertButton);

        // Create panel for logo
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout());
        ImageIcon icon = new ImageIcon("logo.png"); // Replace "logo.png" with your logo file path
        JLabel logoLabel = new JLabel(icon);
        logoPanel.add(logoLabel);

        // Set layout for the main frame
        setLayout(new GridLayout(5, 1));

        // Add panels to the main frame
        add(logoPanel);
        add(inputPanel);
        add(unitPanel);
        add(resultPanel);
        add(buttonPanel);

        // Add action listener for the convert button
        convertButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            convertTemperature();
        }
    }

    private void convertTemperature() {
        String inputValue = inputField.getText();
        double inputTemperature;
        try {
            inputTemperature = Double.parseDouble(inputValue);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fromUnit = fromComboBox.getSelectedItem().toString();
        String toUnit = toComboBox.getSelectedItem().toString();

        double result;
        if (fromUnit.equals(toUnit)) {
            result = inputTemperature;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = celsiusToFahrenheit(inputTemperature);
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
            result = celsiusToKelvin(inputTemperature);
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = fahrenheitToCelsius(inputTemperature);
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) {
            result = fahrenheitToKelvin(inputTemperature);
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
            result = kelvinToCelsius(inputTemperature);
        } else {
            result = kelvinToFahrenheit(inputTemperature);
        }

        resultLabel.setText("Result: " + result);
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5 / 9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 9 / 5) - 459.67;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set look and feel to the system's default
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new TemperatureConverterGUI();
            }
        });
    }
}
