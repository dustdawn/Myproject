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
	private PrintWriter cout;//��ʽ���ַ������
	private JButton[] buttons;
	
	public OrderTCPSocketJFrame(String name,Socket socket)throws IOException{
		super("��˴���     "+name);
		this.setBounds(610, 810, 700, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.text_receiver=new JTextArea();
		this.text_receiver.setBackground(Color.gray);
		this.text_receiver.setFont(new Font("����",Font.BOLD,22));
		this.text_receiver.setEnabled(false);
		this.getContentPane().add(new JScrollPane(this.text_receiver));
		
		JToolBar toolbar=new JToolBar();
		this.getContentPane().add(toolbar,"South");
		toolbar.add(this.text_sender=new JTextField(30));
		this.text_sender.setFont(new Font("����",Font.BOLD,22));
		String[] buttonstr={"���","ȡ�����","���µ��"};
		buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttonstr.length;i++){
			toolbar.add(buttons[i]=new JButton(buttonstr[i]));
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("����",Font.BOLD,12));
		}
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
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
		
		
		
	}
	public OrderTCPSocketJFrame(String name,String host,int port)throws IOException{
		this(name,new Socket(host,port));
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getActionCommand().equals("���")){
			this.cout.println(name+":"+text_sender.getText());
			text_receiver.append("��˵:"+text_sender.getText()+"\n");
			text_sender.setText("");
		}
		if(ev.getActionCommand().equals("ȡ�����")){
			text_receiver.append("����ȡ�����\n");
			this.cout.println(name+"ȡ�����\n"+"bye\n");
			buttons[0].setEnabled(false);
			buttons[1].setEnabled(false);
		}
		if(ev.getActionCommand().equals("���µ��")){
			text_receiver.append("�����µ��\n");
			this.cout.println(name+"���µ��\n"+"bye\n");
			buttons[0].setEnabled(true);
			buttons[1].setEnabled(true);
		}
	}
	/*public static void main(String[] args)throws IOException{
		
		new OrderTCPSocketJFrame("����","127.0.0.1",2001);
	}*/
	
}
