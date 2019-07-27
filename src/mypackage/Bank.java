package mypackage;
import java.util.Scanner;

public class Bank implements Save{
	protected int account;
	protected String name;
	protected String date;
	protected String idcard;
	protected double balance;
	
	public Bank(int account,String name,String date,String idcard,double balance){
		this.account=account;
		this.name=name;
		this.date=date;
		this.idcard=idcard;
		this.balance=balance;
	}
	
	public void Menu(){
	System.out.println("���������:   ");
	System.out.println("          1.����");
	System.out.println("          2.���");
	System.out.println("          3.ȡ��");
	System.out.println("          4.��ѯ");
	System.out.println("          5.����");
	System.out.println("          6.ע��Vip�˺�");
	}
	
	public int Save(double money){
		if(money>0)
		{
			this.balance+=money;
			return 1;
		}
		else			
			return 0;
	}
	
	public void Take(double money){
		if(money<=balance)
		{
			this.balance-=money;
			System.out.println("��ͨ�û������ɹ�");
		}
		else			
			System.out.println("��ͨ�û����� ����ʧ��");
	}
	
	public void Display(){
		System.out.println("���Ϊ:"+balance);
	}
	
	public void finalize(){
	}
	
public	static class Vip extends Bank implements Save{
		public double bnous;

	public Vip(int account, String name, String date, String idcard,
				double balance) {
			super(account, name, date, idcard, balance); 
			// TODO Auto-generated constructor stub
	}
			public double Bnous()
			{
				
				bnous=0.1;
				return 0.0;
			}
			
			
			public int Save(double money){
				if(money>0)
				{
					this.balance=(money+this.balance)*(1+bnous);
					return 1;
					
				}
				else
					return 0;
					
			}
				
				public void Take(double money){
					if(money<=balance)
					{
						this.balance-=money;
						System.out.println("Vip�û������ɹ�");
					}
					else			
						System.out.println("Vip�˻�����ʧ��");
				}
}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank user=new Bank(123456,"admin","20160322","360122201603220000",10000.00);
		Vip vip=new Vip(654321,"admin","20160322","360122201603220000",10000.00);
		
		user.Menu();
		
		while(true){
			Scanner input=new Scanner(System.in);
			int i=input.nextInt();
		switch(i){
		case 1:{
			System.out.println("�����ɹ�  �˻���Ϣ:");
			System.out.println("�˺�"+user.account);
			System.out.println("�洢����"+user.name);
			System.out.println("����ʱ��"+user.date);
			System.out.println("���֤��"+user.idcard);
			System.out.println("������"+user.balance);
			System.out.println("��ϢΪ0%");break;
			}
		case 2:{
			System.out.print("�����������:");
		Scanner p1=new Scanner(System.in);
		double j=p1.nextDouble();
			if(user.Save(j)==1)
				System.out.println("��ͨ�û������ɹ�");
			else
				System.out.println("��ͨ�û�����ʧ��");
				
			if(vip.Save(j)==1)
				System.out.println("Vip�û������ɹ�");
		    else			
		    	System.out.println("Vip�û����� ����ʧ��");break;
		}
		case 3:{
			System.out.print("������ȡ�����:");
			Scanner p1=new Scanner(System.in);
			double k=p1.nextDouble();
				user.Take(k);
				vip.Take(k);break;
		}
		case 4:{
			System.out.print("��ͨ�˻�");
			user.Display();
			System.out.print("Vip�˻�");
			vip.Display();break;
		}
		case 5:{
			user.finalize();
			vip.finalize();
			System.out.println("��ע��");break;
		}
		case 6:{
			System.out.println("vip�˻������ɹ�  �˻���Ϣ:");
			System.out.println("�˺�"+vip.account);
			System.out.println("�洢����"+vip.name);
			System.out.println("����ʱ��"+vip.date);
			System.out.println("���֤��"+vip.idcard);
			System.out.println("������"+vip.balance);
			System.out.println("��ϢΪ10%");break;   
		}
			
		}
		System.out.println("���ز˵���������������");
		Scanner m=new Scanner(System.in);
		int m0=input.nextInt();
		if(m0>=0&&m0<=99999)
			user.Menu();
		
		
	}
	}
}

	

	
	
	
	
	



