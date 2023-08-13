package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import backend.Util;


public class Calculator extends JFrame implements ActionListener{
    private JTextField displayField;
    private JButton[] buttons;
    private final SpringLayout springLayout = new SpringLayout();
    private Util util;
    private boolean pressedEquals = false;
    private boolean pressedOperator = false; 

    public Calculator() {
        super("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(springLayout);
        util = new Util();
        addGuiComponents();
    }

    public void addGuiComponents(){
        //display field
        addDisplayField();
        //button field
        addButtonComponents();

    }
    public void addDisplayField(){
        JPanel displayFieldPanel= new JPanel();
        displayField = new JTextField(10);
        displayField.setFont(new Font("Dialog", Font.PLAIN,36));
        displayField.setEditable(false);
        displayField.setText("0");
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayFieldPanel.add(displayField);
        this.getContentPane().add(displayFieldPanel);
        springLayout.putConstraint(SpringLayout.NORTH, displayFieldPanel,30,SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST, displayFieldPanel,33,SpringLayout.WEST,this);
    }

    public void addButtonComponents(){
        GridLayout gridLayout= new GridLayout(4,4);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(gridLayout);
        buttons = new JButton[16];
        for(int i=0; i<16; i++){
            JButton button = new JButton(getButtonLabel(i));
            button.setFont(new Font("Dialog", Font.PLAIN,46));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        gridLayout.setHgap(25);
        gridLayout.setVgap(25);
        this.getContentPane().add(buttonPanel);
        springLayout.putConstraint(SpringLayout.NORTH,buttonPanel,150,SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST, buttonPanel,33,SpringLayout.WEST,this);

        
    }

    public String getButtonLabel(int buttonindex){
        switch(buttonindex){
            case 0:
                return "7";
            case 1:
                return "8";
            case 2:
                return "9";
            case 3:
                return "/";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "x";
            case 8:
                return "1";
            case 9:
                return "2";
            case 10:
                return "3";
            case 11:
                return "-";
            case 12:
                return "0";
            case 13:
                return ".";
            case 14:
                return "+";
            case 15:
                return "=";

        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            if(pressedEquals || pressedOperator || displayField.getText().equals("0") ){
                displayField.setText(command);
            }else{
                displayField.setText(displayField.getText() + command);
            }
            pressedEquals = false;
            pressedOperator = false;

            
        } else if (command.equals("=")){
            util.setnum2(Double.parseDouble(displayField.getText()));
            double result=0;
            switch(util.getSymbol()){
                case '+':
                    result = util.add();
                    break;
                case '-':
                    result = util.sub();
                    break;
                case '/':
                    result = util.div();
                    break;
                case 'x':
                    result = util.mul();
                break;
                
            }
            displayField.setText(Double.toString(result));
            pressedEquals = true;
            pressedOperator = true;
        } else if(command.equals(".")){
            if (!displayField.getText().contains(".")){
                displayField.setText(displayField.getText()+command);
            }
        } else{
            if(!pressedOperator){
                util.setnum1(Double.parseDouble(displayField.getText()));
            }
            util.setSymbol(command.charAt(0));
            pressedOperator = true;
            pressedEquals = false;
        }
        
    }
}