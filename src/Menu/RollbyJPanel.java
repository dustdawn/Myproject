package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RollbyJPanel extends JPanel implements ActionListener,Runnable{
	JTextField text_word,text_sleep,text_state;
	JButton button_start,button_interrupt;
	Thread thread;
	int sleeptime;
	
	
	RollbyJPanel(String text){
		this.setLayout(new GridLayout(2,1));
		text_word=new JTextField(String.format("%115s", text));
		this.add(text_word);
		JPanel panel_sub = new JPanel();
		this.add(panel_sub);
		//panel_sub.add(new JLabel("sleep"));
		this.sleeptime=70;//产生随机数作为间隔时间
		//text_sleep=new JTextField(""+sleeptime,5);//线程睡眠时间文本行
		//panel_sub.add(text_sleep);
		//text_sleep.addActionListener(this);
		text_word.setFont(new Font("楷体",Font.BOLD,30));
		text_word.setForeground(new Color(0,0,139));
		//button_start=new JButton("启动");
		//panel_sub.add(button_start);
		//button_start.addActionListener(this);
		//button_interrupt=new JButton("中断");
		//panel_sub.add(button_interrupt);
		//button_interrupt.addActionListener(this);
		
		thread=new Thread(this);
		thread.start();
		//button_interrupt.setEnabled(false);
		//panel_sub.add(new JLabel("state"));
		//text_state=new JTextField(""+thread.getState(),10);
		//text_state.setEditable(false);
		//panel_sub.add(text_state);
	}
		
	public void run(){
		while(true)
			try{
				String str=text_word.getText();
				text_word.setText(str.substring(1)+str.substring(0,1));
				Thread.sleep(sleeptime);
			}
		catch(InterruptedException ex){break;}
		
	}
	
	public void actionPerformed(ActionEvent ev){
		//if(ev.getSource()==button_start){
			thread=new Thread(this);
			thread.start();
			//text_state.setText(""+thread.getState());
			//button_start.setEnabled(false);
			//button_interrupt.setEnabled(true);
		//}
		/*if(ev.getSource()==button_interrupt){
			thread.interrupt();
			text_state.setText(""+thread.getState());
			button_start.setEnabled(true);
			button_interrupt.setEnabled(false);
		}
		if(ev.getSource()==text_sleep){
			try{
				sleeptime=Integer.parseInt(text_sleep.getText());}
			catch(NumberFormatException nfex){
				JOptionPane.showMessageDialog(this,
						"\""+text_sleep.getText()+"\"不能转换成证书，请重新输入!");}
			
		}*/
	}
}


