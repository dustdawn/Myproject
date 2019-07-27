/*package mypackage;

public class Vip extends Bank implements Bnous{
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
					this.balance+=money;
					System.out.println("Vip用户操作成功");
				}
				else			
					System.out.println("Vip账户操作失败");
			}
	
		
		
		public void Display(){
			System.out.println("余额为:"+balance);
		}
}

*/
