package Stream;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class RandomIntFileJFrame extends JFrame implements ActionListener{
	protected String filename;
	protected DefaultTableModel tablemodel;
	protected JTextField text_filename,text_count;
	protected JToolBar toolbar;//工具栏
	public RandomIntFileJFrame(String filename){
		super("采用整数文件保存");
		this.setBounds(300,240,800,600);
		this.setBackground(Color.gray);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.filename=filename;
		this.getContentPane().add(toolbar=new JToolBar(),"North");
		toolbar.add(new JLabel("随机数个数"));
		toolbar.add(text_count=new JTextField(0+"",5));
		text_count.addActionListener(this);
		JButton button=new JButton("保存");
		button.addActionListener(this);
		toolbar.add(text_filename=new JTextField(filename,20));
		
		String title[]=new String[10];
		for(int i=0;i<title.length;i++)
			title[i]=(i+1)+"";
		this.tablemodel=new DefaultTableModel(title,1);
		JTable jtable=new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		readFrom(this.filename,this.tablemodel);
		this.setVisible(true);
	}
	
	public void readFrom(String filename,DefaultTableModel tablemodel){
		try{
			FileInputStream fin=new FileInputStream(filename);
			DataInputStream din=new DataInputStream(fin);
			int i=0,j=0;
			while(true){
				try{
					j=0;
					while(j<tablemodel.getColumnCount())
						tablemodel.setValueAt(din.readInt(), i, j++);
						i++;
						tablemodel.addRow(new Object[tablemodel.getColumnCount()]);
					}
					catch(EOFException ex){
							text_count.setText((i*10+j)+"");
							break;
					}
				}
				din.close();
				fin.close();
			}
			catch(IOException e){}
	}
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==text_count){
			try{ random(Integer.parseInt(this.text_count.getText()));}
			catch(NumberFormatException nfex){
				JOptionPane.showMessageDialog(this,"\""+this.text_count.getText()
						+"\"不能转换为正数，请重新输入");
			}
		}
		if(ev.getActionCommand().equals("保存")){
			writeTo(this.filename,this.tablemodel);
			writeTo("random.txt",this.tablemodel);
		}
	}
	private void random(int n){
		this.tablemodel.setRowCount(n/10+1);
		for(int i=0;i<this.tablemodel.getRowCount();i++)
			for(int j=0;j<10;j++)
				if(i*10+j<n)
					this.tablemodel.setValueAt((int)(Math.random()*100), i,j);
				else  this.tablemodel.setValueAt(null, i, j);
	}
	public void writeTo(String filename,DefaultTableModel tablemodel){
		try{
			FileOutputStream fout=new FileOutputStream(filename);
			DataOutputStream dout=new DataOutputStream(fout);
			for(int i=0;i<this.tablemodel.getRowCount();i++){
				for(int j=0;j<this.tablemodel.getColumnCount();j++){
					Object obj=tablemodel.getValueAt(i, j);
					if(obj==null)	break;
					if(obj instanceof Integer)//从文件中读取的整数
						dout.writeInt(((Integer)obj).intValue());
					if(obj instanceof String)
						try{dout.writeInt(  Integer.parseInt(  (String)   obj  )    );}
						catch(NumberFormatException nefx){}
				}
			}
			dout.close();
			fout.close();
		}
		catch(IOException ex){}
	}
	public static void main(String[] args) {
		new RandomIntFileJFrame("random.int");

	}

}
