import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Calculator {
    private static JFrame calculatorFrame;

    private JPanel calculatorPanel;

    private JTextField valueField;
    private JTextField resultTextField;

    private JRadioButton bruttoToNettoRadioButton;
    private JRadioButton nettoToBruttoRadioButton;

    private JButton resultButton;
    private JButton resetButton;

    private JLabel alertLabel;

    private double tax = 1.23;

    private boolean bruttoToNettoChosen = true;


    public static void main(String[] args) {
        calculatorFrame = new JFrame("Calculator");

        Calculator calculator = new Calculator();
        calculator.prepareComponents();
        calculator.prepareMenuBar();

        calculatorFrame.setContentPane(calculator.calculatorPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setResizable(false);
        calculatorFrame.pack();
        calculatorFrame.setVisible(true);
    }

    private void prepareComponents() {
        bruttoToNettoRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bruttoToNettoChosen = true;
            }
        });

        nettoToBruttoRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bruttoToNettoChosen = false;
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valueField.setText("");
                resultTextField.setText("");
            }
        });

        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult(bruttoToNettoChosen);
            }
        });

        valueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.'|| e.getKeyChar()==8)){
                    e.consume();
                    alertLabel.setText("Type only numbers!");
                }else {
                    alertLabel.setText("");
                }
            }
        });
    }

    private void prepareMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu infoMenu = new JMenu("Info");
        menuBar.add(infoMenu);

        JMenuItem infoMenuItem = new JMenuItem("Info about author");
        infoMenu.add(infoMenuItem);
        menuBar.setVisible(true);

        infoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Info About");
                frame.setContentPane(new InfoAbout().infoPanel);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });

        calculatorFrame.setJMenuBar(menuBar);
    }

    private void calculateResult(boolean multiply) {
        double result;

        if (multiply) {
            result = Double.parseDouble(valueField.getText()) * tax;
        } else {
            result = Double.parseDouble(valueField.getText()) / tax;
        }

        java.text.DecimalFormat resultBnNc = new java.text.DecimalFormat();
        resultBnNc.setMaximumFractionDigits(2);
        resultBnNc.setMinimumFractionDigits(2);
        resultTextField.setText(resultBnNc.format(result));
    }
}
