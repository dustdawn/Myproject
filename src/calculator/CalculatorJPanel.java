package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorJPanel extends JFrame implements ActionListener{
	private JTextField text_show;
	private JButton button_sqrt,button_mark,button_CE,button_C,
	                button_7,button_8,button_9,button_divide,
	                button_4,button_5,button_6,button_multiply,
	                button_1,button_2,button_3,button_minus,
	                button_0,button_dot,button_equal,button_plus;
	private JPanel jpanel;
	
    private char sign;
	private double v1;
	private double v2;
	private boolean reop;
	
	
	public CalculatorJPanel(){
		this.setTitle("¼ÆËãÆ÷");
		this.setBounds(700,400,300,324);
		this.setBackground(Color.lightGray);
		this.setFont(new Font("Arial",Font.BOLD,18));
		JFrame f=new JFrame();
		f.setLayout(new BorderLayout());
		text_show=new JTextField("",100);
		jpanel=new JPanel();
		
		Container container=getContentPane();
		container.add(text_show, BorderLayout.NORTH);
		container.add(jpanel, BorderLayout.CENTER);
		
		
		text_show.setEditable(false);
		text_show.setHorizontalAlignment(JTextField.RIGHT);
		jpanel.setLayout(new GridLayout(5,4));
		
		
		
		
		button_sqrt=new JButton("sqrt");button_mark=new JButton("%");
		button_CE=new JButton("CE");button_C=new JButton("C");
        button_7=new JButton("7");button_8=new JButton("8");
        button_9=new JButton("9");button_divide=new JButton("/");
        button_4=new JButton("4");button_5=new JButton("5");
        button_6=new JButton("6");button_multiply=new JButton("*");
        button_1=new JButton("1");button_2=new JButton("2");
        button_3=new JButton("3");button_minus=new JButton("-");
        button_0=new JButton("0");button_dot=new JButton(".");
        button_equal=new JButton("=");button_plus=new JButton("+");
		
		
        button_sqrt.addActionListener(this);
        button_mark.addActionListener(this);
        button_CE.addActionListener(this);
        button_C.addActionListener(this);
        button_7.addActionListener(this);
        button_8.addActionListener(this);
        button_9.addActionListener(this);
        button_divide.addActionListener(this);
        button_4.addActionListener(this);
        button_5.addActionListener(this);
        button_6.addActionListener(this);
        button_multiply.addActionListener(this);
        button_1.addActionListener(this);
        button_2.addActionListener(this);
        button_3.addActionListener(this);
        button_minus.addActionListener(this);
        button_0.addActionListener(this);
        button_dot.addActionListener(this);
        button_equal.addActionListener(this);
        button_plus.addActionListener(this);
		
		
		
		jpanel.add(button_sqrt);
		jpanel.add(button_mark);
		jpanel.add(button_CE);
		jpanel.add(button_C);
		
		jpanel.add(button_7);
		jpanel.add(button_8);
		jpanel.add(button_9);
		jpanel.add(button_divide);
		
		jpanel.add(button_4);
		jpanel.add(button_5);
		jpanel.add(button_6);
		jpanel.add(button_multiply);
		
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.add(button_3);
		jpanel.add(button_minus);
		
		jpanel.add(button_0);
		jpanel.add(button_dot);
		jpanel.add(button_equal);
		jpanel.add(button_plus);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ev){
		double value=0;
	    char operator=0;
	    if(reop)
        {
            text_show.setText("");
            reop = false;
        }
		if(ev.getSource()==button_0){
			String str=text_show.getText();
			if(str.equals(""))
				return;
			text_show.setText(str+'0');
			}
		if(ev.getSource()==button_1){
			String str=text_show.getText();
			text_show.setText(str+'1');
		}
		if(ev.getSource()==button_2){
			String str=text_show.getText();
			text_show.setText(str+'2');
		}
		if(ev.getSource()==button_3){
			String str=text_show.getText();
			text_show.setText(str+'3');
		}
		if(ev.getSource()==button_4){
			String str=text_show.getText();
			text_show.setText(str+'4');
		}
		if(ev.getSource()==button_5){
			String str=text_show.getText();
			text_show.setText(str+'5');
		}
		if(ev.getSource()==button_6){
			String str=text_show.getText();
			text_show.setText(str+'6');
		}
		if(ev.getSource()==button_7){
			String str=text_show.getText();
			text_show.setText(str+'7');
		}
		if(ev.getSource()==button_8){
			String str=text_show.getText();
			text_show.setText(str+'8');
		}
		if(ev.getSource()==button_9){
			String str=text_show.getText();
			text_show.setText(str+'9');
		}
		
		if(ev.getSource()==button_dot){
			String str=text_show.getText();
			text_show.setText(str+'.');
		}
		if(ev.getSource()==button_C){
			text_show.setText("");
			return;
		}
		if(ev.getSource()==button_plus){
			operator='+';
		}
		if(ev.getSource()==button_minus){
			operator='-';
		}
		if(ev.getSource()==button_multiply){
			operator='*';
		}
		if(ev.getSource()==button_divide){
			operator='/';
		}
		if(ev.getSource()==button_equal){
			operator='=';
		}
		if(ev.getSource()==button_mark){
			operator='%';
		}
		if(ev.getSource()==button_sqrt){
			operator='^';
		}
		if(ev.getSource()==button_CE){
			operator='e';
		}
		switch(operator){
		case'e' :
		          String s=text_show.getText();
		          text_show.setText(s.substring(0,s.length()-1));
		          break;
		case'^' :
			      v1=Double.parseDouble(text_show.getText());
			      value=Math.sqrt(v1);text_show.setText(String.valueOf(value));reop = true;
			      break;
		case'%' : 
		          v1=Double.parseDouble(text_show.getText());
                  value=v1/100;text_show.setText(String.valueOf(value));reop = true;
                  break;
		case'+' : 
		          v1=Double.parseDouble(text_show.getText());
		          text_show.setText("");sign='+';break;
		case'-' : 
				  v1=Double.parseDouble(text_show.getText());
                  text_show.setText("");sign='-';break;
		case'*' : 
	              v1=Double.parseDouble(text_show.getText());
	              text_show.setText("");sign='*';break;
	    case'/' : 
			      v1=Double.parseDouble(text_show.getText());
                  text_show.setText("");sign='/';break;
	    case'=' : 
	    	      reop = true;
	    	      v2=Double.parseDouble(text_show.getText());
		          switch(sign){
		          case'+' : 
			          value=v1+v2;break;
			      case'-' : 
			    	  value=v1-v2;break;
			      case'*' : 
			    	  value=v1*v2;break;
		          case'/' : 
		        	  value=v1/v2;break;
		        	  default:value=v1;
		          }
		          String str=String.valueOf(value);
		          text_show.setText(str);
		          break;
                  default:;  
		
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalculatorJPanel();

	}
}
