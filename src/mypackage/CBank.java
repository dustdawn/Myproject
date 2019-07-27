package mypackage;

public class CBank {
	private KBank kbank;
	public static interface Bnous{
		double bnous=0.05;
	}
	//内部类
	public static class KBank implements Bnous{
		private double money;
		public KBank(double money){
			this.money=money;
		}
		public void save(double m){
			this.money=this.money*(CBank.Bnous.bnous+1.0)+m;
			System.out.println("余额为:"+this.money);
		}
		public void take(double m){
			this.money=this.money*(CBank.Bnous.bnous+1)-m;
			System.out.println("余额为:"+this.money);
		}
	}
	public static void main(String args[]){
		CBank cbank = new CBank();
		cbank.kbank=new KBank(10000);
		System.out.println("您的初始余额为:"+cbank.kbank.money);
		cbank.kbank.take(8000);
		cbank.kbank.save(3000);
	}
}
