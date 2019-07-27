package mypackage;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;



public class BankJFrame extends JFrame implements ActionListener{
	private JTextField text_name,text_password;
	private JButton button_enter,button_cancel;
	private JLabel label_name,label_password;
	private JPanel jpanel_1,jpanel_2,jpanel_3;
	public BankJFrame()
	{
		this.setTitle("登录窗口");
		this.setBounds(800,300,400,160);
		this.setBackground(Color.lightGray);
		this.setFont(new Font("Arial",Font.BOLD,18));
		this.setLayout(new GridLayout(3,1,5,5));
		
		text_name=new JTextField("",10);
		text_name.setFont(new Font("楷体",Font.BOLD,18));
		text_password=new JTextField("",10);
		text_password.setFont(new Font("楷体",Font.BOLD,18));
		button_enter=new JButton("确认");
		button_enter.setFont(new Font("楷体",Font.BOLD,18));
		button_enter.addActionListener(this);
		button_cancel=new JButton("取消");
		button_cancel.setFont(new Font("楷体",Font.BOLD,18));
		button_cancel.addActionListener(this);
		label_name=new JLabel    ("用户名:");
		label_name.setFont(new Font("楷体",Font.BOLD,22));
		label_password=new JLabel("密   码:");
		label_password.setFont(new Font("楷体",Font.BOLD,22));
		jpanel_1=new JPanel();
		jpanel_2=new JPanel();
		jpanel_3=new JPanel();
		
		jpanel_1.add(label_name);
		jpanel_1.add(text_name);
		
		jpanel_2.add(label_password);
		jpanel_2.add(text_password);
		
		jpanel_3.add(button_enter);
		jpanel_3.add(button_cancel);
		
		this.add(jpanel_1);
		this.add(jpanel_2);
		this.add(jpanel_3);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	   
		

		
		
	}






	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==button_cancel)
	     	this.setVisible(false);
		else if(ev.getSource()==button_enter){
			if(text_name.getText().equals("user")&&text_password.getText().equals("123")){
			this.setVisible(false);
			new BankJFrame_1();
			}
			else{
				JOptionPane.showMessageDialog(this,"用户名不存在或密码错误,请重新输入！");
			}
		}
			
		
	}



public static void main(String[] args) {
	// TODO Auto-generated method stub
	       new BankJFrame();
  }
}
