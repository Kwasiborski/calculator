package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import service.MathOperationsImpl;


public class Calculator extends MathOperationsImpl {
    private static final int MAXIMUM_FRACTION_DIGITS = 2;

    private static JFrame calculatorFrame = new JFrame();

    private JTextField valueField;
    private JTextField resultTextField;

    private JPanel calculatorPanel;

    private JRadioButton bruttoToNettoRadioButton;
    private JRadioButton nettoToBruttoRadioButton;

    private JButton resultButton;
    private JButton resetButton;

    private JLabel alertLabel;

// deklaracja zmiennej boolean potrzebnej do okreslenia wyboru na radiobuttonie
    private boolean bruttoToNettoChosen = true;


    public Calculator() {
        initializeComponents();

        prepareComponents();

        prepareMenuBar();
    }

    private void initializeComponents(){
        calculatorFrame = new JFrame("Calculator");
        calculatorFrame.setContentPane(calculatorPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setResizable(false);
        calculatorFrame.pack();
        calculatorFrame.setVisible(true);
    }

    //todo zmienić nazwę
    public void prepareComponents() {
        // jeżeli wybrany jest przycisk bruttoToNettoRadioButton wtedy zmienna boolean ma wartosc true
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
        //opis dzialania przycisku reset
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valueField.setText("");
                resultTextField.setText("");
            }
        });

        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //deklaracja zmiennej result typu double, korzysta z funkcji calculateResult
                double result = calculateResult(bruttoToNettoChosen, valueField.getText());
                //stworzenie formatu wyswietlania result do formy do dwoch miejsc po przecinku
                DecimalFormat resultBnNc = new DecimalFormat();
                resultBnNc.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
                resultTextField.setText(resultBnNc.format(result));
            }
        });
        // zablokowanie mozliwosci wpisywania liter oraz wyswietlanie napisu przy probie wpisania niepozadanego znaku
        valueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.' || e.getKeyChar() == 8)) {
                    e.consume();
                    alertLabel.setText("Type only numbers!");
                } else {
                    alertLabel.setText("");
                }
            }
        });
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
                frame.setResizable(false);
            }
        });

        calculatorFrame.setJMenuBar(menuBar);
    }
}