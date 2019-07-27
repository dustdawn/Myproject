package Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MenuJPanel extends JPanel{
	private JTextField[] text_num,text_name,text_price;
	private JLabel label_num,label_name,label_price;
	public int num;
	public String name;
	public int price;
	public MenuJPanel(){
		this.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK,2)),
				"菜单",TitledBorder.CENTER,TitledBorder.TOP,new Font("",0,26)));	
		this.setBackground(Color.LIGHT_GRAY);
		
		this.setLayout(new GridLayout(1,6));
		
		String str[][]={{"0"},{"宫保鸡丁"},{"30"}};
		
		label_num=new JLabel("序号:");
		label_num.setFont(new Font("",Font.BOLD,22));
		label_name=new JLabel("菜名:");
		label_name.setFont(new Font("",Font.BOLD,22));
		label_price=new JLabel("价格:");
		label_price.setFont(new Font("",Font.BOLD,22));
		
		this.add(label_num);
		text_num=new JTextField[str[0].length];
		
		for(int i=0;i<text_num.length;i++){
			this.add(text_num[i]=new JTextField(str[0][i]));
		    text_num[i].setFont(new Font("楷体",Font.BOLD,18));
		    text_num[i].setForeground(new Color(0,0,139));
		}
		
		
		this.add(label_name);
		text_name=new JTextField[str[1].length];
		for(int i=0;i<text_name.length;i++){
			this.add(text_name[i]=new JTextField(str[1][i]));
		    text_name[i].setFont(new Font("楷体",Font.BOLD,18));
		    text_name[i].setForeground(new Color(0,0,139));
		}
		
		this.add(label_price);
		text_price=new JTextField[str[2].length];
		for(int i=0;i<text_price.length;i++){
			this.add(text_price[i]=new JTextField(str[2][i]));
			text_price[i].setFont(new Font("楷体",Font.BOLD,18));
			text_price[i].setForeground(new Color(0,0,139));
		}
		
		
		
		
	}
	//点击信息栏显示在上方列表处
	public void set(Menu m){
		if(m==null)
			return;
		for(int i=0;i<text_num.length;i++)
			this.text_num[i].setText(""+m.num);
		for(int i=0;i<text_name.length;i++)
			this.text_name[i].setText(""+m.name);
		for(int i=0;i<text_price.length;i++)
			this.text_price[i].setText(""+m.price);
	}
	public Menu get(){
		for(int i=0;i<text_num.length;i++)
			num=Integer.parseInt(this.text_num[i].getText());
		for(int i=0;i<text_name.length;i++)
			name=this.text_name[i].getText();
		for(int i=0;i<text_price.length;i++)
			price=Integer.parseInt(this.text_price[i].getText());
		return new Menu(num,name,price);
	}
	



	
}
