import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame {

    JPanel panel;
    JTextField field;
    JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    JButton bAdd, bSub, bMul, bDiv;
    JButton bClear, bEqual, bPoint, bSquare, bSqrt, bReciprocal, bReset;
    String operatorOne, operatorTwo, operand;
    
    frame(){
        initializeComponents();
        setUpBounds();
        enableButtons();
        setUpListeners();

        setSize(500,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void initializeComponents() {
        panel = new JPanel();
        field = new JTextField(16);
        field.setEditable(false);

        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        bAdd = new JButton("+");
        bSub = new JButton("-");
        bMul = new JButton("*");
        bDiv = new JButton("/");


        bClear = new JButton("C");
        bPoint = new JButton(".");
        bEqual = new JButton("=");
        bSquare = new JButton("x\u00B2");
        bReciprocal = new JButton("1/x");
        bSqrt = new JButton("\u221A");
        bReset = new JButton("RESET");

    }
    private void enableButtons() {
        field.setEnabled(true);
        bClear.setEnabled(true);
        bReset.setEnabled(true);
        bDiv.setEnabled(true);
        bSqrt.setEnabled(true);
        bSquare.setEnabled(true);
        bReciprocal.setEnabled(true);
        bSub.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        bMul.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        bAdd.setEnabled(true);
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        bEqual.setEnabled(true);
        b0.setEnabled(true);
        bPoint.setEnabled(true);
    }
    private void setUpBounds() {

        getContentPane().setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        JButton[] buttons = {
                bSqrt, bReciprocal,bClear,bReset,
                b7, b8, b9, bDiv,
                b4, b5, b6, bMul,
                b1, b2, b3, bSub,
                b0, bPoint, bEqual, bAdd
        };

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 13));
            buttonPanel.add(button);
        }

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        field.setFont(new Font("Arial", Font.BOLD, 24));
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 80));
        getContentPane().add(field, BorderLayout.NORTH);

    }

    private void setUpListeners() {

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonText = button.getText();


                if (buttonText.matches("[0-9]")) {
                    field.setText(field.getText() + buttonText);
                } else if (buttonText.equals(".")) {
                    if (!field.getText().contains(".")) {
                        field.setText(field.getText() + buttonText);
                    }
                } else if (buttonText.equals("C")) {
                    field.setText("");
                } else if (buttonText.equals("RESET")) {
                    field.setText("");
                    operatorOne = "";
                    operatorTwo = "";
                    operand = "";
                } else if (buttonText.matches("[+\\-*/]")) {
                    operatorOne = field.getText();
                    operand = buttonText;
                    field.setText("");
                } else if (buttonText.equals("=")) {
                    operatorTwo = field.getText();
                    double num1 = Double.parseDouble(operatorOne);
                    double num2 = Double.parseDouble(operatorTwo);
                    double result = 0.0;
                    switch (operand) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            result = num1 / num2;
                            break;
                    }
                    field.setText(Double.toString(result));
                    operatorOne = "";
                    operatorTwo = "";
                    operand = "";
                } else if (buttonText.equals("\u221A")) { // Square Root
                    double num = Double.parseDouble(field.getText());
                    double result = Math.sqrt(num);
                    field.setText(Double.toString(result));
                } else if (buttonText.equals("1/x")) { // Reciprocal
                    double num = Double.parseDouble(field.getText());
                    double result = 1.0 / num;
                    field.setText(Double.toString(result));
                } else if (buttonText.equals("x\u00B2")) { // Square
                    double num = Double.parseDouble(field.getText());
                    double result = num * num;
                    field.setText(Double.toString(result));
                }
            }
        };

        // Add the same listener to all buttons
        JButton[] buttons = {
                bSqrt, bReciprocal, bClear, bReset,
                b7, b8, b9, bDiv,
                b4, b5, b6, bMul,
                b1, b2, b3, bSub,
                b0, bPoint, bEqual, bAdd
        };

        for (JButton button : buttons) {
            button.addActionListener(buttonListener);
        }
    }


    public static void main(String[] args) {
       new frame();
    }
}