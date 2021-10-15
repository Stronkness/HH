import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * <hi>Calculator.java</h1>
 * <p>
 * Creates an functional calculator with the simplest tasks as operators
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-29
 */
public class Calculator extends JFrame implements ActionListener {
	
	private String buttonText[] = {"7", "8", "9", "^", "C", "4", "5", "6", "(", ")", "1", "2", "3", "*", "/", "0", "+", "-", "="};
	private JButton[] buttons = new JButton[19];
	private JTextField expressionField;
	private String exp="";
	
	public Calculator( ){
		super("Calculator");
		setSize(320, 240);
		expressionField = new JTextField();
		JPanel panel = new JPanel(new GridLayout(4, 5));
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonText[i]);
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		panel.add(new Label());
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(expressionField, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "C"){
			exp = "";
			expressionField.setText(exp);
		}
		else if(e.getActionCommand() == "="){
			expressionField.setText(""+evaluate(infixToPostfix(exp)));
		}
		
		else{
			exp = exp + e.getActionCommand();
			expressionField.setText(exp);
		}
		
	}
	
	
	
	public static void main(String [] arg) {
	
		Calculator calc = new Calculator();
		calc.setVisible(true);

	}
	
	/**
	 * 
	 * @param exp; the expression sent in
	 * @return; the correct order of operations 
	 */
	public static LinkedList<String> infixToPostfix( String exp){
        Stack<String> stack = new Stack<String>();
        LinkedList <String>list= new LinkedList<String>();
        for (int i=0; i<exp.length(); i++) {
           String s = exp.charAt(i)+"";
           if(s.equals("+")) {
              while(!stack.isEmpty())
              {
                 String tkn=stack.pop();
                 if(tkn.equals("*")|| tkn.equals("/") || tkn.equals("+")||tkn.equals("-")||tkn.equals("^"))
                    list.add(tkn);
                 else{
                    stack.push(tkn);
                    break;
                 }
              }
              stack.push(s);
           
           
           }
           else if (s.equals("-")) {
           	 while(!stack.isEmpty())
                {
                   String tkn=stack.pop();
                   if(tkn.equals("*")|| tkn.equals("/") || tkn.equals("+")||tkn.equals("-")||tkn.equals("^"))
                      list.add(tkn);
                   else{
                      stack.push(tkn);
                      break;
                   }
                }
                stack.push(s);
             
           }
           
           else if (s.equals("*"))
           {
           	while(!stack.isEmpty()) {
               String peeking = stack.peek();
           if(peeking.equals("*")|| peeking.equals("/")|| peeking.equals("^")) {
           	peeking =stack.pop();
           	list.add(peeking);
           }
           else {
           	stack.push(s);
           	break;
           }
           	}
               if(stack.isEmpty()) {
                   stack.push(s);
                   }
           }
           else if (s.equals("/"))
           {
           	while(!stack.isEmpty()) {
           	String peeking = stack.peek();
               if(peeking.equals("*")|| peeking.equals("/")|| peeking.equals("^")) {
               	peeking = stack.pop();
               	list.add(peeking);
               
               }
               else {
               	stack.push(s);
               	break;
               }          
           	}
               if(stack.isEmpty()) {
                   stack.push(s);
                   }
           }
				 else if (s.equals("^"))
           {		while(!stack.isEmpty()) {
					 String peeking = stack.peek();
			            if(peeking.equals("^")) {
			            	peeking = stack.pop();
			            	list.add(peeking);
			            
			            }
			            
           
			            else {
			            	stack.push(s);
			            	break;
			            }        
           }
           if(stack.isEmpty()) {
           stack.push(s);
           }
           }

           
           else if (s.equals(")")) 
           {
           	while(!stack.isEmpty()) {
           		String popop = stack.pop();
           		if(popop.equals("(")){
           			break;
           		}
           		else {
           			list.add(popop);
           		}
           	}
           }
           else if (s.equals("(")) {
           	stack.push(s);
           }
           else                   
             
              list.add(s);
        }
      
        while(!stack.isEmpty())
        {
           list.add(stack.pop());
        }
     
        return list;
		} 
	/**
	 * 
	 * @param exp; the mathematical expression
	 * @return the correct answer
	 */
	public static double evaluate( LinkedList<String> exp) {
        Stack<Double> stack = new Stack<Double>();
        double first;
        double second;
        while (!exp.isEmpty()) {
            String s = exp.remove();
            if(s.equals("+")) {
            	first = stack.pop();
            	second = stack.pop();
            	stack.push(first+second);
            }
            else if(s.equals("*")) {
            	first = stack.pop();
            	second = stack.pop();
            	stack.push(first*second);
            }
            else if(s.equals("-")) {
            	first = stack.pop();
            	second = stack.pop();
            	stack.push(second - first);
            }
            else if(s.equals("/")) {
            	first = stack.pop();
            	second = stack.pop();
            	stack.push(second/first);
            }
            else if(s.equals("^")) {
            	first = stack.pop();
            	second = stack.pop();
            	stack.push(Math.pow(second, first));
            }
            else {
            	stack.push(Double.parseDouble(s));
            }
        }
		double answer = stack.pop();
        
		return answer;
		}


}