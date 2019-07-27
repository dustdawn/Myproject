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
	
	private JPanel toolbar;//������
	private JMenuBar menubar;//�˵���
	private JMenu Menu;
	private JMenuItem infoItem1;
	private JMenuItem infoItem2;
	
	protected JFileChooser fchooser;

	public MenuJFrame(MenuJPanel menu,Menu value[], RollbyJPanel rollby)throws IOException {
		super("�˵�����");
		this.setSize(700,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
		this.menubar=new JMenuBar();
		this.Menu=new JMenu("�ļ�");
		this.infoItem1=new JMenuItem("��");
		Menu.add(infoItem1);
		this.infoItem1.addActionListener(this);
		this.infoItem2=new JMenuItem("����");
		Menu.add(infoItem2);
		this.infoItem2.addActionListener(this);
		menubar.add(Menu);
		setJMenuBar(menubar);//���ò˵���
		
		this.menu=menu;      //�������
		JPanel downpanel=new JPanel(new BorderLayout());
		JSplitPane split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,this.menu,downpanel);
		//�˴��и�
		split.setDividerLocation(90);
		this.getContentPane().add(split);  //������ݴ�����ӷָ��
		downpanel.setBackground(Color.LIGHT_GRAY);
		this.listmodel=new DefaultListModel<Menu>();  //�����յ��б��ģ��
		
		if(value!=null)
			for(int j=0;j<value.length;j++)
				this.listmodel.addElement(value[j]);  //�б��ģ�����������
		this.jlist=new JList<Menu>(this.listmodel);//�����б��ָ���б��ģ��
		this.jlist.addListSelectionListener(this);
		this.jlist.setFont(new Font("����",Font.BOLD,22));
		this.jlist.setBackground(Color.LIGHT_GRAY);
		downpanel.add(new JScrollPane(this.jlist));//�����Ӱ����б��Ĺ�������    
		JPanel buttonpanel=new JPanel();
		downpanel.add(buttonpanel,"South");   //�Ͷ������ӵȲ�����ť
		downpanel.add(rollby,"North");
		String str[][]={{"���","�޸�","ɾ��ѡ����"},{"���ҹؼ���","����ؼ���"},{"���","�۸�"}};
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
		this.menu.set(this.jlist.getSelectedValue());//���б��ѡ��������Menu�����������
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
			if(ev.getActionCommand().equals("��ȡ")){
				this.readFrom(this.listmodel);
				JOptionPane.showMessageDialog(this, "��ȡ�ɹ�");
				return;
			}
			if(ev.getActionCommand().equals("����")){
				this.writeTo(this.listmodel);
				JOptionPane.showMessageDialog(this, "����ɹ�");
				return;
			}
		}
		else if(ev.getSource() instanceof JButton){
		if(ev.getActionCommand().equals("���"))
			this.listmodel.addElement(this.menu.get());
		if(ev.getActionCommand().equals("�޸�")){
			int i=this.jlist.getSelectedIndex();//�б����ѡ�������
			if(i==-1)
				JOptionPane.showMessageDialog(this, "��ѡ���б����������");
			else
				this.listmodel.setElementAt(this.menu.get(), i);
		}
			
		if(ev.getActionCommand().equals("ɾ��ѡ����"))
			if(this.listmodel.getSize()==0)
				JOptionPane.showMessageDialog(this,"�б�Ϊ�գ�����ɾ��");
			else{
				int i=this.jlist.getSelectedIndex();//�б����ѡ�������
				if(i==-1)
					JOptionPane.showMessageDialog(this, "��ѡ���б����������");
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
				{"��������"},{"һƷ��������ӳ���˿��"},{"����Ƭ"},{"������˿"},{"����ţ����"},{"ˮ����Ƭ"},
				{"����������"},{"��������"},{"�ⶡ����"},{"�����Ĺ���"},{"�ջ�����"},{"��Բ�ӹ�"},
				{"�������"},{"ɰ��"},{"���˼��׷�"},{"ʲ������"},{"�ײ˹���"},
				{"����֦��"},{"��Ʋ�"},{"�˱���ӽ�"}};
		String str1="��������,һƷ��������ӳ���˿��,����Ƭ,������˿,����ţ����,ˮ����Ƭ";
				
		
		Menu[] value={new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),
				new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),
				new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu(),new Menu()};
		
		for(int i=1;i<21;i++){
			for(int j=0;j<str[0].length;j++){
				value[i-1]=new Menu(i,str[i-1][j],new Random().nextInt(40)+10);
				
			}	
		}
		new MenuJFrame(new MenuJPanel(),value,new RollbyJPanel("�����ػ�:"+str1));
		
		new OrderTCPSocketJFrame("����","127.0.0.1",2001);
	}
}