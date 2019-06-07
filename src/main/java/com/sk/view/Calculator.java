package com.sk.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import com.sk.service.MathOperationsImpl;
import com.sk.service.interfaces.IMathOperations;

public class Calculator extends MathOperationsImpl {
    private static final int MAXIMUM_FRACTION_DIGITS = 2;

    private JFrame calculatorFrame = new JFrame("calculator");
    private JPanel calculatorPanel = new JPanel();
    private JTextField valueField = new JTextField(18);
    private JTextField resultTextField = new JTextField(18) ;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu infoMenu = new JMenu("Info");
    private JMenuItem infoMenuItem = new JMenuItem("Info about author");
    private JFrame infoFrame = new JFrame("InfoAbout");
    private JFrame InfoAboutFrame = new JFrame("Info About");
    private JPanel InfoAboutPanel = new JPanel();
    private JLabel InfoLabel = new JLabel();
    private JLabel InfoLabel2 = new JLabel();




    private JRadioButton bruttoToNettoRadioButton = new JRadioButton("Brutto -> Netto",true);
    private JRadioButton nettoToBruttoRadioButton = new JRadioButton("Netto -> Brutto",false);

    private JButton resultButton = new JButton("wynik");
    private JButton resetButton = new JButton("reset");

    private JLabel alertLabel = new JLabel();
    private ButtonGroup buttonGroup = new ButtonGroup();

    private IMathOperations mathOperations;

    private String Reset = "";

    public Calculator() {
        mathOperations = new MathOperationsImpl();
        prepareMenuBar();

        initializeComponents();

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

        calculatorPanel.add(bruttoToNettoRadioButton);
        calculatorPanel.add(nettoToBruttoRadioButton);
        calculatorPanel.add(valueField);
        calculatorPanel.add(resultTextField);
        calculatorPanel.add(resetButton);
        calculatorPanel.add(resultButton);
        calculatorPanel.add(alertLabel);

        valueField.setEditable(true);
        resultTextField.setEditable(false);


    }
    public void prepareMenuBar() {

        menuBar.add(infoMenu);
        infoMenu.add(infoMenuItem);
        menuBar.setVisible(true);

        infoMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                infoFrame.setSize(250,250);
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                infoFrame.pack();
                infoFrame.setVisible(true);
                infoFrame.setResizable(true);
            }
        });
        infoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                InfoAboutFrame.setSize(250,250);
                InfoAboutFrame.setMaximumSize(new Dimension(250,250));
                InfoAboutFrame.setMinimumSize(new Dimension(250,250));
                InfoAboutFrame.setPreferredSize(new Dimension(250,250));
                InfoAboutFrame.setResizable(false);
                InfoAboutPanel.setSize(250,250);
                InfoAboutPanel.setMaximumSize(new Dimension(250,250));
                InfoAboutPanel.setMinimumSize(new Dimension(250,250));
                InfoAboutPanel.setPreferredSize(new Dimension(250,250));
                InfoAboutFrame.add(InfoAboutPanel);

                InfoAboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                InfoAboutFrame.pack();
                InfoAboutFrame.setVisible(true);
                InfoAboutFrame.setResizable(true);
                InfoLabel.setText("              Szymon Kwasiborski               ");
                InfoAboutPanel.add(InfoLabel);
                InfoLabel2.setText("mechatronik");
                InfoAboutPanel.add(InfoLabel2);




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
                DecimalFormat resultcorrected = new DecimalFormat();
                resultcorrected.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
                resultTextField.setText(resultcorrected.format(mathOperations.calculateResult(nettoToBruttoRadioButton.isSelected() ? true : false, valueField.getText())));
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