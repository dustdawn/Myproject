package mypackage;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;


public class BankJFrame_1 extends JFrame implements ActionListener{
	private JTextField text_save,text_take,text_show;
	private JButton button_enter,button_enter1;
	private JLabel label_save,label_take,label_show;
	private JPanel jpanel_1,jpanel_2,jpanel_3;
	double money;
	public BankJFrame_1()
	{
		double money=10000.00;
		this.setTitle("����");
		this.setBounds(500,200,900,500);
		this.setBackground(Color.lightGray);
		this.setFont(new Font("Arial",Font.BOLD,18));
		this.setLayout(new GridLayout(5,1,6,6));
		
		text_save=new JTextField("",10);
		text_take=new JTextField("",10);
		text_show=new JTextField("0.0",10);
		button_enter=new JButton("ȷ��");
		button_enter.setFont(new Font("����",Font.BOLD,18));
		button_enter.addActionListener(this);
		button_enter1=new JButton("ȷ��");
		button_enter1.setFont(new Font("����",Font.BOLD,18));
		button_enter1.addActionListener(this);
		label_save=new JLabel    ("�����:");
		label_save.setFont(new Font("����",Font.BOLD,22));
		label_take=new JLabel("ȡ����:");
		label_take.setFont(new Font("����",Font.BOLD,22));
		label_show=new JLabel("����:");
		label_show.setFont(new Font("����",Font.BOLD,22));
		jpanel_1=new JPanel();
		jpanel_2=new JPanel();
		jpanel_3=new JPanel();
		
		jpanel_1.add(label_save);
		jpanel_1.add(text_save);
		jpanel_1.add(button_enter);
		
		jpanel_2.add(label_take);
		jpanel_2.add(text_take);
		jpanel_2.add(button_enter1);
		
		jpanel_3.add(label_show);
		jpanel_3.add(text_show);
		
		this.add(jpanel_1);
		this.add(jpanel_2);
		this.add(jpanel_3);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    text_save.setFont(new Font("����",Font.BOLD,22));
	    text_take.setFont(new Font("����",Font.BOLD,22));
	    text_show.setFont(new Font("����",Font.BOLD,22));
	}
	
	
	
	public void actionPerformed(ActionEvent ev){
	try{
		if(ev.getSource()==button_enter){
	          String str=text_save.getText();
	          if(str.equals(""))
	        	 {return;}
	          double m=Double.parseDouble(str);
	          money+=m;
	          String str1=""+money;
	          text_save.setText("");
	          text_show.setText(""+str1);
	          
		}
		else if(ev.getSource()==button_enter1){
			String str=text_take.getText();
	          if(str.equals(""))
	        	 {return;}
	          double m=Double.parseDouble(str);
	          if(money<m)
	        	  JOptionPane.showMessageDialog(this,"���㣬������!");
		      else{  
		    	  money-=m;
		    	  String str1=""+money;
		    	  text_take.setText("");
	              text_show.setText(""+str1);
		      }
		}
	          
	          
		
	}
	
	catch(NumberFormatException nfex){
		JOptionPane.showMessageDialog(this,"������ʽ����ȷ������������!");}
	finally{}
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		       new BankJFrame_1();
	  }*/
	
	
	
}
