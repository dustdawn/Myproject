package Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Menu.Menu.Equalable;
public class MenuJFrame extends JFrame implements ActionListener, ListSelectionListener{
	protected JList<Menu>jlist;
	protected DefaultListModel<Menu>listmodel;
	protected MenuJPanel menu;
	protected RollbyJPanel rollby;
	protected JComboBox<String>comboxs[];
	private static Equalable equal[]={new EqualNum(),new EqualName()};
	private static Comparator comparators[]={new CompareNum(),new CompareName()};
	
	private JPanel toolbar;//工具栏
	private JMenuBar menubar;//菜单栏
	private JMenu Menu;
	private JMenuItem infoItem1;
	private JMenuItem infoItem2;
	
	protected JFileChooser fchooser;

	public MenuJFrame(MenuJPanel menu,Menu value[], RollbyJPanel rollby)throws IOException {
		super("菜单管理");
		this.setSize(700,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
		this.menubar=new JMenuBar();
		this.Menu=new JMenu("文件");
		this.infoItem1=new JMenuItem("打开");
		Menu.add(infoItem1);
		this.infoItem1.addActionListener(this);
		this.infoItem2=new JMenuItem("保存");
		Menu.add(infoItem2);
		this.infoItem2.addActionListener(this);
		menubar.add(Menu);
		setJMenuBar(menubar);//设置菜单栏
		
		this.menu=menu;      //子类面板
		JPanel downpanel=new JPanel(new BorderLayout());
		JSplitPane split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,this.menu,downpanel);
		//此处切割
		split.setDividerLocation(90);
		this.getContentPane().add(split);  //框架内容窗格添加分割窗格
		downpanel.setBackground(Color.LIGHT_GRAY);
		this.listmodel=new DefaultListModel<Menu>();  //创建空的列表框模型
		
		if(value!=null)
			for(int j=0;j<value.length;j++)
				this.listmodel.addElement(value[j]);  //列表框模型添加数据项
		this.jlist=new JList<Menu>(this.listmodel);//创建列表框，指定列表框模型
		this.jlist.addListSelectionListener(this);
		this.jlist.setFont(new Font("楷体",Font.BOLD,22));
		this.jlist.setBackground(Color.LIGHT_GRAY);
		downpanel.add(new JScrollPane(this.jlist));//面板添加包含列表框的滚动窗格    
		JPanel buttonpanel=new JPanel();
		downpanel.add(buttonpanel,"South");   //低端添加添加等操作按钮
		downpanel.add(rollby,"North");
		String str[][]={{"添加","修改","删除选择项"},{"查找关键字","排序关键字"},{"序号","价格"}};
		for(int i=0;i<str[0].length;i++){
			JButton button=new JButton(str[0][i]);
			button.addActionListener(this);
			buttonpanel.add(button);
		}
		this.comboxs=new JComboBox[str[1].length];
		for(int i=0;i<str[1].length;i++){
			buttonpanel.add(new JLabel(str[1][i]));
			buttonpanel.add(this.comboxs[i]=new JComboBox<String>(str[2]));
			this.comboxs[i].addActionListener(this);
		}
		
		this.setVisible(true);
	}
	public void valueChanged(ListSelectionEvent ev){
		this.menu.set(this.jlist.getSelectedValue());//按列表框选中项设置Menu面板各组件数据
	}
	
	public void readFrom(DefaultListModel<Menu>listmodel){
		try{
			FileReader fr=new FileReader("test.txt");
			BufferedReader br=new BufferedReader(fr);
			this.listmodel.removeAllElements();
			String s=br.readLine();	
			while(s!=null){
				this.listmodel.addElement(new Menu(s));
			s=br.readLine();
			}
			br.close();
			fr.close();
				
		}catch(IOException ioex){ioex.printStackTrace();}
	}
	public void writeTo(DefaultListModel<Menu>listmodel){
		try{
			FileWriter fw=new FileWriter("test.txt");
			BufferedWriter out=new BufferedWriter(fw);
			for(int i=0;i<this.listmodel.getSize();i++){
				out.write(this.listmodel.getElementAt(i).toString());
				out.newLine();
			}
			out.close();
			fw.close();
					
		}catch(IOException ioex){}
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource() instanceof JMenuItem){
			if(ev.getActionCommand().equals("读取")){
				this.readFrom(this.listmodel);
				JOptionPane.showMessageDialog(this, "读取成功");
				return;
			}
			if(ev.getActionCommand().equals("保存")){
				this.writeTo(this.listmodel);
				JOptionPane.showMessageDialog(this, "保存成功");
				return;
			}
		}
		else if(ev.getSource() instanceof JButton){
		if(ev.getActionCommand().equals("添加"))
			this.listmodel.addElement(this.menu.get());
		if(ev.getActionCommand().equals("修改")){
			int i=this.jlist.getSelectedIndex();//列表框中选中项序号
			if(i==-1)
				JOptionPane.showMessageDialog(this, "请选择列表框中数据项");
			else
				this.listmodel.setElementAt(this.menu.get(), i);
		}
			
		if(ev.getActionCommand().equals("删除选择项"))
			if(this.listmodel.getSize()==0)
				JOptionPane.showMessageDialog(this,"列表为空，不能删除");
			else{
				int i=this.jlist.getSelectedIndex();//列表框中选中项序号
				if(i==-1)
					JOptionPane.showMessageDialog(this, "请选择列表框中数据项");
				else
					this.listmodel.removeElementAt(i);
			}
		}
		if(ev.getSource()==this.comboxs[0]){
			int i=this.comboxs[0].getSelectedIndex();
			if(i<equal.length)
				search(this.menu.get(),equal[i]);
		}
		if(ev.getSource()==this.comboxs[1]){
			int i=this.comboxs[1].getSelectedIndex();
			if(i<comparators.length)
				sort(comparators[i]);
		}
		
	}
	
	public<T>void search(T obj,Equalable<?super T>eq){
		int n=this.listmodel.getSize();
		for(int i=0;i<n;i++)
			if(eq.equals(obj,(T)this.listmodel.elementAt(i))){
				this.jlist.setSelectedIndex(i);
				return;
			}
	}
	
	public<T>void sort(java.util.Comparator<?super T>c){
		for(int i=0;i<this.listmodel.getSize()-1;i++){
			int min=i;
			for(int j=i+1;j<this.listmodel.getSize();j++)
				if(c.compare((T)listmodel.getElementAt(j),(T)listmodel.getElementAt(min))<0)
					min=j;
			if(min!=i){
				Menu temp=this.listmodel.getElementAt(i);
				this.listmodel.setElementAt(this.listmodel.getElementAt(min),i);
				this.listmodel.setElementAt(temp,min);
			}
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str[][]={
				{"宫保鸡丁"},{"一品锅黄鱼锅子炒肉丝儿"},{"炒肉片"},{"鱼香肉丝"},{"淮南牛肉汤"},{"水煮肉片"},
				{"素炝春不老"},{"清焖莲子"},{"肉丁辣酱"},{"煮饽饽锅子"},{"菊花锅子"},{"汤圆子锅"},
				{"麻辣香锅"},{"砂锅"},{"黄焖鸡米饭"},{"什锦锅子"},{"白菜锅子"},
				{"炒银枝儿"},{"酸黄菜"},{"八宝榛子酱"}};
		String str1="宫保鸡丁,一品锅黄鱼锅子炒肉丝儿,炒肉片,鱼香肉丝,淮南牛肉汤,水煮肉片";
				
		
		Menu[] value={new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),
				new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),
				new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu()};
		
		for(int i=1;i<21;i++){
			for(int j=0;j<str[0].length;j++){
				value[i-1]=new Menu(i,str[i-1][j],new Random().nextInt(40)+10);
				
			}	
		}
		new MenuJFrame(new MenuJPanel(),value,new RollbyJPanel("今日特惠:"+str1));
		
		new OrderTCPSocketJFrame("客人","127.0.0.1",2001);
	}
}