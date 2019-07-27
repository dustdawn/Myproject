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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class HeartJFrame extends JFrame {

	public boolean sign;
	private HeartCanvas canvas;
	File f;
	URI uri;
	URL url;
	public void Music(){
		try{
			this.f=new File("Faded.wav");
			uri=f.toURI();
			url=uri.toURL();//解析地址
			AudioClip aau;
			aau=Applet.newAudioClip(url);
			aau.loop();
			}catch(Exception e){e.printStackTrace();}
	}
	
	
	public HeartJFrame(){
		super("Heart");
		Dimension dim=getToolkit().getScreenSize();
	    this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas=new HeartCanvas();
		this.getContentPane().add(this.canvas,"Center");
		canvas.setBackground(Color.black);
		
		this.setVisible(true);
		this.Music();
		
	}
	/*public void run(){
		try{
			this.f=new File("Faded.wav");
			uri=f.toURI();
			url=uri.toURL();//解析地址
			AudioClip aau;
			aau=Applet.newAudioClip(url);
			aau.loop();
			Thread.sleep(10);
			}catch(Exception e){e.printStackTrace();}
	}*/
	public static void main(String[] args) {
		new HeartJFrame();
	}
}
class HeartCanvas extends Canvas implements ActionListener{
	private Timer timer;
	private Color color;
	public HeartCanvas(){
		
		timer=new Timer(5000,this);
		timer.start();
	}
	public void paint(Graphics g){
		this.color=new Color(
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128);
		int x0=this.getWidth()/2;
		int y0=this.getHeight()/2;
		g.setColor(this.color);
		
		for(int i=0;i<10000;i++){
			g.fillOval(new Random().nextInt(6000),new Random().nextInt(6000),5,5);
			}
		for(int j=70;j<240;j+=10){
			for(int i=0;i<1023;i++){
				double angle=i*Math.PI/512;
				double radius=j*(1+Math.sin(angle));
				int x=(int)Math.round(radius*Math.cos(angle)*2);
				int y=(int)Math.round(radius*Math.sin(angle));
				g.fillOval(x0+x, y0+y, 2, 2);
			}
		}
		
	}
	public void actionPerformed(ActionEvent ev){
		repaint();
		
	}
}