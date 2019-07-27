package Menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;
public class OrderTCPSocketJFrame extends JFrame implements ActionListener{
	private String name;
	private JTextArea text_receiver;
	private JTextField text_sender;
	private PrintWriter cout;//格式化字符输出流
	private JButton[] buttons;
	
	public OrderTCPSocketJFrame(String name,Socket socket)throws IOException{
		super("点菜窗口     "+name);
		this.setBounds(610, 810, 700, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.text_receiver=new JTextArea();
		this.text_receiver.setBackground(Color.gray);
		this.text_receiver.setFont(new Font("楷体",Font.BOLD,22));
		this.text_receiver.setEnabled(false);
		this.getContentPane().add(new JScrollPane(this.text_receiver));
		
		JToolBar toolbar=new JToolBar();
		this.getContentPane().add(toolbar,"South");
		toolbar.add(this.text_sender=new JTextField(30));
		this.text_sender.setFont(new Font("楷体",Font.BOLD,22));
		String[] buttonstr={"点菜","取消点菜","重新点菜"};
		buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttonstr.length;i++){
			toolbar.add(buttons[i]=new JButton(buttonstr[i]));
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("楷体",Font.BOLD,12));
		}
		this.setVisible(true);
		
		this.name=name;
		this.cout=new PrintWriter(socket.getOutputStream(),true);//获得Socket的输出流传输
		this.cout.println(name);
		//将Socket的字节输入流转换成字符流，默认GBK字符集，再创建缓冲字符输出流
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line="连接"+br.readLine()+"成功";
		while(line!=null&&!line.endsWith("bye")){
			this.text_receiver.append(line+"\r\n");//显示对方发过来的内容
			line=br.readLine();//从输入流接收对方发过来的字符串
		}
		br.close();
		this.cout.close();
		socket.close();
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
		
		
		
	}
	public OrderTCPSocketJFrame(String name,String host,int port)throws IOException{
		this(name,new Socket(host,port));
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getActionCommand().equals("点菜")){
			this.cout.println(name+":"+text_sender.getText());
			text_receiver.append("您说:"+text_sender.getText()+"\n");
			text_sender.setText("");
		}
		if(ev.getActionCommand().equals("取消点菜")){
			text_receiver.append("您已取消点菜\n");
			this.cout.println(name+"取消点菜\n"+"bye\n");
			buttons[0].setEnabled(false);
			buttons[1].setEnabled(false);
		}
		if(ev.getActionCommand().equals("重新点菜")){
			text_receiver.append("请重新点菜\n");
			this.cout.println(name+"重新点菜\n"+"bye\n");
			buttons[0].setEnabled(true);
			buttons[1].setEnabled(true);
		}
	}
	/*public static void main(String[] args)throws IOException{
		
		new OrderTCPSocketJFrame("客人","127.0.0.1",2001);
	}*/
	
}
