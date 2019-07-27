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
	System.out.println("请输入操作:   ");
	System.out.println("          1.开户");
	System.out.println("          2.存款");
	System.out.println("          3.取款");
	System.out.println("          4.查询");
	System.out.println("          5.销户");
	System.out.println("          6.注册Vip账号");
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
			System.out.println("普通用户操作成功");
		}
		else			
			System.out.println("普通用户余额不足 操作失败");
	}
	
	public void Display(){
		System.out.println("余额为:"+balance);
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
						System.out.println("Vip用户操作成功");
					}
					else			
						System.out.println("Vip账户操作失败");
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
			System.out.println("开户成功  账户信息:");
			System.out.println("账号"+user.account);
			System.out.println("存储姓名"+user.name);
			System.out.println("开户时间"+user.date);
			System.out.println("身份证号"+user.idcard);
			System.out.println("存款余额"+user.balance);
			System.out.println("利息为0%");break;
			}
		case 2:{
			System.out.print("请输入存款余额:");
		Scanner p1=new Scanner(System.in);
		double j=p1.nextDouble();
			if(user.Save(j)==1)
				System.out.println("普通用户操作成功");
			else
				System.out.println("普通用户操作失败");
				
			if(vip.Save(j)==1)
				System.out.println("Vip用户操作成功");
		    else			
		    	System.out.println("Vip用户余额不足 操作失败");break;
		}
		case 3:{
			System.out.print("请输入取款余额:");
			Scanner p1=new Scanner(System.in);
			double k=p1.nextDouble();
				user.Take(k);
				vip.Take(k);break;
		}
		case 4:{
			System.out.print("普通账户");
			user.Display();
			System.out.print("Vip账户");
			vip.Display();break;
		}
		case 5:{
			user.finalize();
			vip.finalize();
			System.out.println("已注销");break;
		}
		case 6:{
			System.out.println("vip账户开户成功  账户信息:");
			System.out.println("账号"+vip.account);
			System.out.println("存储姓名"+vip.name);
			System.out.println("开户时间"+vip.date);
			System.out.println("身份证号"+vip.idcard);
			System.out.println("存款余额"+vip.balance);
			System.out.println("利息为10%");break;   
		}
			
		}
		System.out.println("返回菜单请输入任意数字");
		Scanner m=new Scanner(System.in);
		int m0=input.nextInt();
		if(m0>=0&&m0<=99999)
			user.Menu();
		
		
	}
	}
}

	

	
	
	
	
	



