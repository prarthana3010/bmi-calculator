package com.phase1;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class bmi {

    private JFrame frame;
    private JTextField textFieldHeight;
    private JTextField textFieldWeight;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    bmi window = new bmi();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public bmi() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.setBounds(100, 100, 352, 371);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("BMI CALCULATOR");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        lblTitle.setBounds(51, 10, 252, 60);
        frame.getContentPane().add(lblTitle);

        textFieldHeight = new JTextField();
        textFieldHeight.setFont(new Font("Consolas", Font.ITALIC, 25));
        textFieldHeight.setBounds(126, 106, 96, 41);
        frame.getContentPane().add(textFieldHeight);
        textFieldHeight.setColumns(10);

        textFieldWeight = new JTextField();
        textFieldWeight.setFont(new Font("Consolas", Font.ITALIC, 25));
        textFieldWeight.setColumns(10);
        textFieldWeight.setBounds(126, 165, 96, 41);
        frame.getContentPane().add(textFieldWeight);

        JLabel lblHeight = new JLabel("Height");
        lblHeight.setForeground(new Color(255, 255, 255));
        lblHeight.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lblHeight.setBounds(43, 105, 130, 36);
        frame.getContentPane().add(lblHeight);

        JLabel lblWeight = new JLabel("Weight");
        lblWeight.setForeground(new Color(255, 255, 255));
        lblWeight.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lblWeight.setBounds(43, 164, 130, 36);
        frame.getContentPane().add(lblWeight);

        JButton btnCalculate = new JButton("Calculate BMI");
        btnCalculate.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        btnCalculate.setBounds(68, 251, 212, 36);
        frame.getContentPane().add(btnCalculate);

        JLabel lblInches = new JLabel("inches");
        lblInches.setForeground(new Color(255, 255, 255));
        lblInches.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lblInches.setBounds(232, 106, 85, 36);
        frame.getContentPane().add(lblInches);
        
        JLabel lblKgs = new JLabel("kgs");
        lblKgs.setForeground(new Color(255, 255, 255));
        lblKgs.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lblKgs.setBounds(232, 165, 85, 36);
        frame.getContentPane().add(lblKgs);

        // Add an ActionListener to the Calculate BMI button
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        try {
            // Get height and weight input from text fields
            double heightInFeet = Double.parseDouble(textFieldHeight.getText());
            double weightInKgs = Double.parseDouble(textFieldWeight.getText());

            // Convert height to meters (1 foot = 0.3048 meters)
            double heightInMeters = heightInFeet * 0.3048;

            // Calculate BMI (BMI = weight in kgs / (height in meters * height in meters))
            double bmi = weightInKgs / (heightInMeters * heightInMeters);

            // Determine the BMI category based on the calculated BMI
            String category = getBMICategory(bmi);

            // Create a custom font for the message text
            Font messageFont = new Font("Consolas", Font.PLAIN, 30);

            // Create a custom JPanel to set the font for the message text
            JPanel panel = new JPanel();
            JLabel label = new JLabel(String.format("Your BMI is %.2f - %s", bmi, category));
            label.setFont(messageFont);

            // Set color based on BMI category
            setColorBasedOnCategory(label, category);

            panel.add(label);

            // Display the BMI result in a dialog box with custom font and color
            JOptionPane.showMessageDialog(frame, panel, "BMI Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal Weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private void setColorBasedOnCategory(JLabel label, String category) {
        switch (category) {
            case "Underweight":
                label.setForeground(Color.BLUE);
                break;
            case "Normal Weight":
                label.setForeground(Color.GREEN);
                break;
            case "Overweight":
                label.setForeground(Color.ORANGE);
                break;
            case "Obesity":
                label.setForeground(Color.RED);
                break;
            default:
                label.setForeground(Color.BLACK);
                break;
        }
    }
}
