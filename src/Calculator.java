import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math.*;
public class Calculator extends JFrame implements ActionListener {
    JButton[] number = new JButton[10];
    JButton[] operation = new JButton[10];
    JButton delete = new JButton("Delete");
    JButton clear = new JButton("Clear");
    JButton negative = new JButton("(-)");
    JButton next = new JButton("Next");
    JPanel calculatorPanel = new JPanel();
    JTextField numbersField = new JTextField();
    String result = "";
    double tmp = 0.0;
    double[] fact = new double[12];
    boolean lastIsOPeration = false;
    String lastOperation = "";
    Calculator() {

        buildFact();
        buildNumberButton();
        buildOperationButton();
        buildPannel();
        buildNumbersField();
        buildLastRow();

        this.setTitle("Calculator");
        this.setSize(700, 750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.add(numbersField);
        this.add(calculatorPanel);
        this.add(delete);
        this.add(next);
        this.add(negative);
        this.add(clear);
    }

    public void buildFact() {
        fact[0] = 1;
        for (int i = 1; i < 11; i++)
            fact[i] = fact[i - 1] * i;
    }
    public void buildNumberButton() {
        for (int i = 0; i < 10; i++) {
            number[i] = new JButton(String.valueOf(i));
            number[i].setFont(new Font("French Script MT", Font.BOLD, 40));
            number[i].setForeground(Color.white);
            number[i].setBackground(Color.black);
            number[i].addActionListener(this);
        }
    }
    public void buildOperationButton() {
        operation[0] = new JButton("+");
        operation[1] = new JButton("-");
        operation[2] = new JButton("*");
        operation[3] = new JButton("/");
        operation[4] = new JButton("=");
        operation[5] = new JButton(".");
        operation[6] = new JButton("Log"); //a Log
        operation[7] = new JButton("Pow"); //a Pow b
        operation[8] = new JButton("Fac"); //a Fac -> until 10!
        operation[9] = new JButton("Rad"); //a Rad

        for (int i = 0; i < 10; i++) {
            operation[i].setFont(new Font("French Script MT", Font.BOLD, 30));
            operation[i].setForeground(Color.white);
            operation[i].setBackground(Color.black);
            operation[i].addActionListener(this);
        }
    }
    public void buildPannel() {
        calculatorPanel.setBounds(100, 150, 500, 400);
        calculatorPanel.setLayout(new GridLayout(4, 5, 10, 10));
        calculatorPanel.add(number[1]);
        calculatorPanel.add(number[2]);
        calculatorPanel.add(number[3]);
        calculatorPanel.add(operation[0]);
        calculatorPanel.add(operation[6]);
        calculatorPanel.add(number[4]);
        calculatorPanel.add(number[5]);
        calculatorPanel.add(number[6]);
        calculatorPanel.add(operation[1]);
        calculatorPanel.add(operation[7]);
        calculatorPanel.add(number[7]);
        calculatorPanel.add(number[8]);
        calculatorPanel.add(number[9]);
        calculatorPanel.add(operation[2]);
        calculatorPanel.add(operation[8]);
        calculatorPanel.add(operation[5]);
        calculatorPanel.add(number[0]);
        calculatorPanel.add(operation[4]);
        calculatorPanel.add(operation[3]);
        calculatorPanel.add(operation[9]);
    }
    public void buildNumbersField() {
        numbersField.setBounds(100, 30, 500, 80);
        numbersField.setFont(new Font("French Script MT", Font.PLAIN, 50));
        numbersField.setForeground(Color.white);
        numbersField.setBackground(Color.black);
        numbersField.setCaretColor(Color.white);
    }
    public void buildLastRow() {
        delete.setFont(new Font("French Script MT", Font.BOLD, 30));
        delete.setForeground(Color.white);
        delete.setBackground(Color.black);
        delete.addActionListener(this);
        delete.setBounds(100, 580, 110, 60);

        clear.setFont(new Font("French Script MT", Font.BOLD, 30));
        clear.setForeground(Color.white);
        clear.setBackground(Color.black);
        clear.addActionListener(this);
        clear.setBounds(230, 580, 110, 60);

        negative.setFont(new Font("French Script MT", Font.BOLD, 30));
        negative.setForeground(Color.white);
        negative.setBackground(Color.black);
        negative.addActionListener(this);
        negative.setBounds(360, 580, 110, 60);

        next.setFont(new Font("French Script MT", Font.BOLD, 30));
        next.setForeground(Color.white);
        next.setBackground(Color.black);
        next.addActionListener(this);
        next.setBounds(490, 580, 110, 60);

    }
    public void updateNumbersField() {
        numbersField.setText(result);
    }
    public void updateByEnterOperation() {
        double res = Double.parseDouble(result);
        if (lastOperation == "" || lastOperation == "=")
            tmp = res;
        if (lastOperation == "+")
            tmp = res + tmp;
        if (lastOperation == "-")
            tmp = tmp - res;
        if (lastOperation == "/")
            tmp = tmp / res;
        if (lastOperation == "*")
            tmp = tmp * res;
        if (lastOperation == "Pow")
            tmp = Math.pow(tmp, res);
        result = String.valueOf(tmp);
        updateNumbersField();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++)
            if (e.getSource() == number[i]) {
                if(lastIsOPeration) {
                    result = "";
                    updateNumbersField();
                    lastIsOPeration = false;
                }
                result = result + i;
                updateNumbersField();
                return;
            }

        if (e.getSource() == operation[5]) { // .
            result = result + operation[5].getText();
            updateNumbersField();
            return;
        }

        if (e.getSource() == delete) {
            result = result.substring(0, result.length() - 1);
            updateNumbersField();
            return;
        }
        if (e.getSource() == clear) {
            result = "";
            tmp = 0.0;
            lastIsOPeration = false;
            lastOperation = "";
            updateNumbersField();
            return;
        }
        if (e.getSource() == negative) {
            result = "-" + result;
            updateNumbersField();
            return;
        }

        // now do it
        updateByEnterOperation();
        lastIsOPeration = true;
        if (e.getSource() == operation[0]) {// +
            lastOperation = operation[0].getText();
        }
        if (e.getSource() == operation[1]) {// -
            lastOperation = operation[1].getText();
        }
        if (e.getSource() == operation[2]) {// /
            lastOperation = operation[2].getText();
        }
        if (e.getSource() == operation[3]) {// *
            lastOperation = operation[3].getText();
        }
        if (e.getSource() == operation[4]) {// =
            lastOperation = operation[4].getText();
            lastIsOPeration = false;
        }
        if (e.getSource() == operation[7]) {// Pow
            lastOperation = operation[7].getText();
        }
        if (e.getSource() == operation[6]) {// Log
            double lastNumber = Double.parseDouble(result);
            result = String.valueOf(Math.log(lastNumber));
            updateNumbersField();
            lastIsOPeration = false;
        }
        if (e.getSource() == operation[8]) {// Fac
            double lastNumber = Double.parseDouble(result);
            result = String.valueOf(fact[(int)lastNumber]);
            updateNumbersField();
            lastIsOPeration = false;
        }
        if (e.getSource() == operation[9]) {// Rad
            double lastNumber = Double.parseDouble(result);
            result = String.valueOf(Math.sqrt(lastNumber));
            updateNumbersField();
            lastIsOPeration = false;
        }
    }
}
