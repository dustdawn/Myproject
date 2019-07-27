package Menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import java.io.*;
import java.net.*;
public class OrderTCPServerJFrame extends JFrame{
	private String name;
	private JTextArea text_receiver;
	private PrintWriter cout;//格式化字符输出流
	public OrderTCPServerJFrame(String name,Socket socket)throws IOException{
		super("点菜窗口     "+name);
		this.setBounds(1295, 220, 400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.text_receiver=new JTextArea();
		this.text_receiver.setBackground(Color.gray);
		this.text_receiver.setFont(new Font("楷体",Font.BOLD,22));
		this.text_receiver.setEnabled(false);
		this.getContentPane().add(new JScrollPane(this.text_receiver));
		
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
		
		
	}
	public OrderTCPServerJFrame(String name,String host,int port)throws IOException{
		this(name,new Socket(host,port));
	}
	
	/*public static void main(String[] args)throws IOException{
		new OrderTCPServerJFrame("客人","127.0.0.1",2001);
	}*/
	
}

