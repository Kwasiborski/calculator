package com.sk.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import com.sk.service.MathOperationsImpl;
public class Calculator extends MathOperationsImpl {
    private static final int MAXIMUM_FRACTION_DIGITS = 2;

    private JFrame calculatorFrame = new JFrame();

    private JTextField valueField;
    private JTextField resultTextField;

    private JPanel calculatorPanel;

    private JRadioButton bruttoToNettoRadioButton;
    private JRadioButton nettoToBruttoRadioButton;

    private JButton resultButton;
    private JButton resetButton ;

    private JLabel alertLabel;

    // declaration boolean to indicate choose on radiobutton
    private boolean bruttoToNettoChosen = false;

    String Reset = "";

    public Calculator() {
        workSpace();

        prepareMenuBar();

        initializeComponents();
    }

    public void initializeComponents(){
        calculatorFrame = new JFrame("Calculator");
        calculatorFrame.setContentPane(calculatorPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setResizable(false);
        calculatorFrame.pack();
        calculatorFrame.setVisible(true);

    }

    public void workSpace() {
        // if bruttoToNettoRadioButton is choosen that boolean is false
        bruttoToNettoRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bruttoToNettoChosen = false;
            }
        });
        nettoToBruttoRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bruttoToNettoChosen = true;
            }
        });
        //How to work reset button
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valueField.setText(Reset);
                resultTextField.setText(Reset);
                alertLabel.setText(Reset);
            }
        });
        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //declaration variable result type double, uses function calculateResult
                double result = calculateResult(bruttoToNettoChosen, valueField.getText());
                //make format to show only number to two decimal places
                DecimalFormat resultBnNc = new DecimalFormat();
                resultBnNc.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
                resultTextField.setText(resultBnNc.format(result));
            }
        });
        //disable to enter letters and show alert when trying to enter unsupported char
        valueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.' || e.getKeyChar() == 8)) {
                    e.consume();
                    alertLabel.setText("Type only numbers!");
                } else {
                    alertLabel.setText(Reset);
                }
            }
        });
        //disable to enter char in resultTextField
        resultTextField.setEditable(false);
    }

    public void prepareMenuBar() {
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
                frame.setResizable(true);
            }
        });
        calculatorFrame.setJMenuBar(menuBar);
    }

}