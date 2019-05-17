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

    private JFrame calculatorFrame = new JFrame("calculator");
    private JPanel calculatorPanel = new JPanel();
    private JLabel valueFieldLabel = new JLabel();
    private JLabel resultTextFieldLabel = new JLabel();
    private JTextField valueField = new JTextField();
    private JTextField resultTextField = new JTextField() ;


    private JRadioButton bruttoToNettoRadioButton = new JRadioButton("Brutto -> Netto",true);
    private JRadioButton nettoToBruttoRadioButton = new JRadioButton("Netto -> Brutto",false);

    private JButton resultButton = new JButton();
    private JButton resetButton = new JButton();

    private JLabel alertLabel = new JLabel();
    private ButtonGroup buttonGroup = new ButtonGroup();



    String Reset = "";
    int result;
    public Calculator() {
        initializeComponents();

        prepareMenuBar();

        workSpace();
    }

    public void initializeComponents(){
        calculatorFrame.setContentPane(calculatorPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setResizable(false);
        calculatorFrame.pack();
        calculatorFrame.setVisible(true);
        calculatorFrame.setSize(230,320);
        buttonGroup.add(bruttoToNettoRadioButton);
        buttonGroup.add(nettoToBruttoRadioButton);
        calculatorFrame.add(bruttoToNettoRadioButton);
        calculatorFrame.add(nettoToBruttoRadioButton);
        calculatorFrame.add(valueField);
        valueFieldLabel.setLabelFor(valueField);
        valueField.setSize(150,30);
        resultTextFieldLabel.setLabelFor(resultTextField);
        calculatorFrame.add(resultTextField);
        resultTextField.setSize(150,30);


        valueField.setEditable(true);


    }
    public void prepareMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu infoMenu = new JMenu("Info");
        menuBar.add(infoMenu);


        JMenuItem infoMenuItem = new JMenuItem("Info about author");
        infoMenu.add(infoMenuItem);
        menuBar.setVisible(true);

        infoMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame infoFrame = new JFrame("InfoAbout");
                infoFrame.setContentPane(new InfoAbout().infoPanel);
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                infoFrame.pack();
                infoFrame.setVisible(true);
            }
        });
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
    public void workSpace() {
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
    }
}