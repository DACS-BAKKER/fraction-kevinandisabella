import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/* KEVIN AND ISABELLA'S CALCULATOR.JAVA
   EXTENSIONS:
   COMPLEX NUMBER
   ORDER OF OPERATIONS
   SQUARE BUTTON
   INVERSE BUTTON
   NEGATIVE BUTTON
 */
public class Calculator implements ActionListener {

    /* BUTTON AND TEXT DIMENSIONS */
    private static final int BUTTON_WIDTH = 75;
    private static final int BUTTON_HEIGHT = 55;
    private static final int BOX_WIDTH = BUTTON_WIDTH * 4;
    private static final int BOX_HEIGHT = 80;
    private static final Font TEXT_FONT = new Font("Heiti SC", Font.PLAIN, 15);

    /* APPLICATION DIMENSIONS */
    private static final int APPLICATION_WIDTH = BOX_WIDTH;
    private static final int APPLICATION_HEIGHT = BUTTON_HEIGHT * 5 + BOX_HEIGHT;
    private static final int APPLICATION_OFFSET = 22;
    private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /* COMPONENT COLORS */
    private static final Color boxColor = new Color(105, 105, 105);
    private static final Color numberColor = new Color(136, 136, 138);
    private static final Color specialColor = new Color(118, 118, 118);
    private static final Color functionColor = new Color(241, 162, 61);

    /* LIMIT OF CALCULATOR TEXT LENGTH */
    private static final int MAX_LETTERS = 9;

    /* CALCULATOR COMPONENTS */
    private JFrame frame;
    private JLabel textBox;

    /* VALUES */
    private ComplexNumber previousNumber = new ComplexNumber(0);
    private ComplexNumber total = new ComplexNumber(0);
    private int currentOperation = -2;
    private int previousOperation = -1;
    private boolean isComplex = false;
    private boolean emptyScreen = false;

    /* MAIN METHOD */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /* CALCULATOR */
    private Calculator() {
        initialize();
    }

    /* CREATES CALCULATOR COMPONENTS */
    private void initialize() {
        /* FRAME */
        frame = new JFrame();
        frame.setBackground(boxColor);
        frame.setBounds(SCREEN_WIDTH / 2 - APPLICATION_WIDTH / 2, SCREEN_HEIGHT / 2 - APPLICATION_HEIGHT / 2, APPLICATION_WIDTH, APPLICATION_HEIGHT + APPLICATION_OFFSET);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        /* TEXT BOX */
        textBox = new JLabel();
        textBox.setForeground(Color.WHITE);
        textBox.setFont(new Font("Heiti SC", Font.PLAIN, 50));
        textBox.setHorizontalAlignment(SwingConstants.RIGHT);
        textBox.setOpaque(true);
        textBox.setBackground(boxColor);
        textBox.setBounds(0, 0, BOX_WIDTH, BOX_HEIGHT);
        textBox.setBorder(new LineBorder(Color.DARK_GRAY));
        textBox.setText(String.valueOf(0));
        frame.getContentPane().add(textBox);

        /* CALCULATOR BUTTONS */
        for (int i = 1; i <= 4; i++)
            for (int j = 0; j <= 4; j++) {
                int n = i * 10 + j;
                switch (n) {
                    case (10):
                        createButton(String.valueOf(0), numberColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (14):
                        createButton("AC", specialColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (20):
                        createButton("1/x", numberColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (24):
                        createButton("+/-", specialColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (30):
                        createButton("x\u00b2", numberColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (40):
                        createButton("=", functionColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (41):
                        createButton("+", functionColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (42):
                        createButton("-", functionColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (43):
                        createButton("×", functionColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (44):
                        createButton("÷", functionColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    case (34):
                        createButton("cpx", specialColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                    default:
                        createButton(String.valueOf(i + (j - 1) * 3), numberColor, (i - 1) * BUTTON_WIDTH, APPLICATION_HEIGHT - (1 + j) * BUTTON_HEIGHT);
                        break;
                }
            }
    }

    /* CREATES A BUTTON */
    private void createButton(final String s, final Color background, final int x, final int y) {
        JButton button = new JButton(s);
        button.setOpaque(true);
        button.setForeground(Color.WHITE);
        button.setFont(TEXT_FONT);
        button.setBackground(background);
        button.setBounds(x, y, Calculator.BUTTON_WIDTH, Calculator.BUTTON_HEIGHT);
        button.setBorder(new LineBorder(Color.DARK_GRAY));
        button.addActionListener(this);
        frame.getContentPane().add(button);
    }

    /* CHECKS ACTION PERFORMED */
    public void actionPerformed(ActionEvent e) {
        /* BUTTON SOURCE AND DISPLAYED TEXT*/
        JButton a = (JButton) e.getSource();
        String displayedText = textBox.getText();

        /* FIND BUTTON FUNCTION */
        switch (a.getText()) {
            /* ADDITION */
            case "+":
                operate(0);
                break;
            /* SUBTRACTION */
            case "-":
                operate(1);
                break;

            /* MULTIPLICATION */
            case "×":
                operate(2);
                break;

            /* DIVISION */
            case "÷":
                operate(3);
                break;

            /* NEGATIVE */
            case "+/-":
                if (!isComplex && !displayedText.equals("0") && displayedText.charAt(0) != '-')
                    textBox.setText("-" + displayedText);
                else if (!isComplex && !displayedText.equals("0") && displayedText.charAt(0) == '-')
                    textBox.setText(displayedText.substring(1));
                else {
                    int i = displayedText.indexOf('+');
                    if (i != -1)
                        textBox.setText(textBox.getText().substring(0, i) + "-" + textBox.getText().substring(i + 1));
                }
                break;

            /* INVERSE */
            case "1/x":
                textBox.setText(new ComplexNumber(1).divide(new ComplexNumber(displayedText)).toString());
                checkOverChar(textBox.getText());
                break;

            /* INPUT COMPLEX NUMBER */
            case "cpx":
                if (!isComplex && displayedText.length() < MAX_LETTERS - 3)
                    complexNumberMode(displayedText);
                break;

            /* SQUARE */
            case "x\u00b2":
                if (displayedText.length() >= 6)
                    System.out.println("Out of integer range.");
                else if (displayedText.length() > 0) {
                    if (!isComplex)
                        previousNumber = calculate(new ComplexNumber(new Fraction(displayedText), new Fraction(0)), new ComplexNumber(new Fraction(displayedText), new Fraction(0)), 2);
                    else
                        previousNumber = calculate(new ComplexNumber(displayedText), new ComplexNumber(displayedText), 2);
                }
                textBox.setText(previousNumber.toString());
                checkOverChar(textBox.getText());
                break;

            /* ALL CLEAR */
            case "AC":
                previousNumber = new ComplexNumber(0);
                reset(previousNumber.toString(), -2);
                isComplex = false;
                break;

            /* EVALUATE */
            case "=":
                finalEvaluation(displayedText);
                break;

            default:
                updateNumbers(displayedText, a.getText());
                break;
        }
    }

    /* RESETS DISPLAY AND VALUES */
    private void reset(String set, int current) {
        textBox.setText(set);
        total = new ComplexNumber(0);
        previousOperation = -1;
        currentOperation = current;
    }

    /* PERFORMS GIVEN OPERATION WITH CORRECT ORDER */
    private void operate(int op) {
        /* FEEDS VALUE INTO COMPLEX NUMBER FOR PARSE DEPENDING ON IF COMPLEX */
        ComplexNumber n;
        if (textBox.getText().indexOf("i") != 1)
            n = new ComplexNumber(textBox.getText());
        else
            n = new ComplexNumber(new Fraction(textBox.getText()));

        /* STORES RUNNING TOTAL FOR ORDER OF OPERATIONS (+/-) */
        if (op < 2 && textBox.getText().length() > 0 && !emptyScreen) {
            if (currentOperation == -1) {
                previousNumber = n;
                total = previousNumber;
                /* ADDS ONTO EXISTING TOTAL */
            } else if (previousOperation >= 0 && currentOperation >= 2) {
                previousNumber = calculate(previousNumber, n, currentOperation);
                previousNumber = calculate(total, previousNumber, previousOperation);
                /* FOR HIGHER ORDER OF OPERATIONS (MULIPLYT/DIVIDE) */
            } else if (previousOperation >= 0) {
                previousNumber = calculate(previousNumber, n, op);
                total = previousNumber;
            } else {
                previousNumber = calculate(previousNumber, n, currentOperation);
                total = previousNumber;
            }
            previousOperation = op;
        } else if (op >= 2 && textBox.getText().length() > 0 && !emptyScreen) {
            if (currentOperation >= 2)
                previousNumber = calculate(n, previousNumber, currentOperation);
            else
                previousNumber = n;
        }
        isComplex = false;
        currentOperation = op;
        emptyScreen = true;
    }

    /* CALCULATES OPERATION */
    private ComplexNumber calculate(ComplexNumber one, ComplexNumber two, int operation) {
        switch (operation) {
            case 0:
                return one.add(two);
            case 1:
                return one.subtract(two);
            case 2:
                return one.multiply(two);
            case 3:
                if (two.compareTo(new ComplexNumber(0)) != 0)
                    return one.divide(two);
                else
                    System.out.println("Undefined.");
        }
        return two;
    }

    /* ENTER COMPLEX NUMBER MODE */
    private void complexNumberMode(String displayedText) {
        if (displayedText.length() > 0)
            textBox.setText(displayedText + "+" + "0i");
        else
            textBox.setText("0+" + "0i");
        isComplex = true;
    }

    /* UPDATES DISPLAY */
    private void updateNumbers(String displayedText, String a) {
        if (!isComplex) {
            /* FOR RESET SCREEN */
            if (currentOperation == -2) {
                textBox.setText("");
                currentOperation++;
            }
            /* UPDATES DISPLAY */
            if (!emptyScreen)
                textBox.setText(textBox.getText() + a);
            else {
                textBox.setText(a);
                emptyScreen = false;
            }
            /* UPDATES DISPLAY FOR MAX LETTERS */
            if (textBox.getText().length() > MAX_LETTERS)
                textBox.setText(textBox.getText().substring(1));
        } else if (isComplex && displayedText.length() < MAX_LETTERS) {
            /* UPDATES DISPLAY FOR COMPLEX NUMBERS WITH CORRECT INDEX */
            int i = Math.max(displayedText.indexOf("+"), displayedText.substring(1).indexOf("-") + 1);
            if (displayedText.charAt(i + 1) == '0')
                textBox.setText(displayedText.substring(0, displayedText.length() - 2) + a + "i");
            else
                textBox.setText(displayedText.substring(0, displayedText.length() - 1) + a + "i");
        }
    }

    /* FINAL EQUALS EVALUATION WITH ORDER OF OPERATIONS */
    private void finalEvaluation(String displayedText) {
        /* ADDS ONTO TOTAL */
        if (previousOperation >= 0 && currentOperation >= 2) {
            previousNumber = calculate(previousNumber, new ComplexNumber(displayedText), currentOperation);
            previousNumber = calculate(total, previousNumber, previousOperation);
            /* CALCULATES FOR HIGHER ORDER OF OPERATIONS */
        } else if (previousOperation >= 0) {
            previousNumber = calculate(previousNumber, new ComplexNumber(displayedText), previousOperation);
        } else {
            /* FOR NO OPERATIONS */
            previousNumber = calculate(previousNumber, new ComplexNumber(displayedText), currentOperation);
        }
        textBox.setText(previousNumber.toString());
        checkOverChar(textBox.getText());
    }

    /* CHECK IF OVER CHARACTER LIMIT */
    private void checkOverChar(String displayedText) {
        if (displayedText.length() > MAX_LETTERS) {
            previousNumber = new ComplexNumber(0);
            reset("0", -2);
            isComplex = false;
            System.out.println("Too many characters.");
        }
    }
}

