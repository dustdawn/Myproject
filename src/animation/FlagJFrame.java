package animation;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlagJFrame extends JFrame implements ActionListener{

	public boolean sign;
	private FlagCanvas1 canvas1;
	private FlagCanvas canvas;
	File f;
	URI uri;
	URL url;
	
	
	public FlagJFrame(){
		super("����");
		Dimension dim=getToolkit().getScreenSize();
	    this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);
		setLocationRelativeTo(null);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel jpanel=new JPanel();
		this.getContentPane().add(jpanel,"South");
		JButton button_start=new JButton("����");
		button_start.setFont(new Font("����",Font.BOLD,18));
		jpanel.add(button_start);
		button_start.addActionListener(this);
		this.canvas1=new FlagCanvas1();
		this.getContentPane().add(this.canvas1,"Center");
		this.setVisible(true);
			
	}
	
	
	public void actionPerformed(ActionEvent ev){
		this.getContentPane().remove(this.canvas1);
		this.canvas=new FlagCanvas();
		this.getContentPane().add(this.canvas,"Center");
		this.setVisible(true);	
		try{
			this.f=new File("song.wav");
			uri=f.toURI();
			url=uri.toURL();//������ַ
			AudioClip aau;
			aau=Applet.newAudioClip(url);
			aau.play();
			}catch(Exception e){e.printStackTrace();}
		
	}
	public static void main(String[] args) {
		new FlagJFrame();
	}
}
class FlagCanvas extends Canvas implements ActionListener{
	private Timer timer;
	private Flag flag;
	private static class Flag{
		int y;
		public boolean up;
		Flag(int y){
			this.y=y;
			boolean up=false;
		}
	}
	public FlagCanvas(){
		this.flag=new Flag(380);
		
		timer=new Timer(1200,this);
		timer.start();
	}
	public void paint(Graphics g){
		int x=this.getWidth();
		int y=this.getHeight();
		g.setColor(Color.black);
		g.drawLine(x*1/3,y,x*1/3,y*1/5);
		for(int i=1;i<6;i++){
			g.drawLine(x*1/3+i,y,x*1/3+i,y*1/5);//���
		}
		g.setColor(Color.red);
		g.fillOval(x*4/5, y*1/50,100,100);//̫��
		g.fill3DRect(x*1/3+5,flag.y, 120, 80, true);
			flag.y=flag.up?91:flag.y-10;
			if(flag.y<=90){
				flag.up=!flag.up;
			}
			String str="�������೪����";
			/*String lyric[][]={{"����"},{"��Ը��ū��������"},{"�����ǵ�Ѫ�����������ĵĳ���"},
			{"�л����嵽����Σ�ѵ�ʱ��"},{"ÿ���˶����ȷ������ĺ���"},{"����,����"},
			{"��������һ��,ð�ŵ��˵��ڻ�"},
			{"ǰ��ǰ��ǰ����!"}};*/
			g.setFont(new Font("����",Font.BOLD,40));
			g.setColor(Color.darkGray);
			g.drawString(str,200,40);
			
			String lyric1="����,��Ը��ū��������,�����ǵ�Ѫ�����������ĵĳ���,�л����嵽����Σ�ѵ�ʱ��,",
				lyric2="ÿ���˶����ȷ������ĺ���,����,����,��������һ��,ð�ŵ��˵��ڻ�,ǰ��ǰ��ǰ����!";
			g.setFont(new Font("����",Font.BOLD,20));
			g.drawString(lyric1,0,70);
			g.drawString(lyric2,0,90);
	}
	public void actionPerformed(ActionEvent ev){
		repaint();
	}
	
	
}
//����ֹ����
class FlagCanvas1 extends Canvas{
	public void paint(Graphics g){
		int x=this.getWidth();
		int y=this.getHeight();
		g.setColor(Color.black);
		g.drawLine(x*1/3,y,x*1/3,y*1/5);
		for(int i=1;i<6;i++){
			g.drawLine(x*1/3+i,y,x*1/3+i,y*1/5);//���
		}
		g.setColor(Color.red);
		g.fillOval(x*4/5, y*1/50,100,100);//̫��
		g.fill3DRect(x*1/3+5,380, 120, 80, true);	
	}

}