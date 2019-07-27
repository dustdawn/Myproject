package Menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import java.io.*;
import java.net.*;
public class OrderTCPServerJFrame extends JFrame{
	private String name;
	private JTextArea text_receiver;
	private PrintWriter cout;//��ʽ���ַ������
	public OrderTCPServerJFrame(String name,Socket socket)throws IOException{
		super("��˴���     "+name);
		this.setBounds(1295, 220, 400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.text_receiver=new JTextArea();
		this.text_receiver.setBackground(Color.gray);
		this.text_receiver.setFont(new Font("����",Font.BOLD,22));
		this.text_receiver.setEnabled(false);
		this.getContentPane().add(new JScrollPane(this.text_receiver));
		
		this.setVisible(true);
		
		
		this.name=name;
		this.cout=new PrintWriter(socket.getOutputStream(),true);//���Socket�����������
		this.cout.println(name);
		//��Socket���ֽ�������ת�����ַ�����Ĭ��GBK�ַ������ٴ��������ַ������
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line="����"+br.readLine()+"�ɹ�";
		while(line!=null&&!line.endsWith("bye")){
			this.text_receiver.append(line+"\r\n");//��ʾ�Է�������������
			line=br.readLine();//�����������նԷ����������ַ���
		}
		br.close();
		this.cout.close();
		socket.close();
		
		
	}
	public OrderTCPServerJFrame(String name,String host,int port)throws IOException{
		this(name,new Socket(host,port));
	}
	
	/*public static void main(String[] args)throws IOException{
		new OrderTCPServerJFrame("����","127.0.0.1",2001);
	}*/
	
}

