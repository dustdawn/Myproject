package Menu;

import java.util.Comparator;

public class Menu {
	public int num;
	public String name;
	public int price;
	public Menu(){
		this(0,"",0);
	}
	public Menu(String str){
		/*int spaceindex=str.indexOf(32);
		int priceindex=str.indexOf(157);
		for(int i=0;i<spaceindex;i++)
			String str1=str;*/
		String str1=str.substring(0,str.indexOf(" "));//¿Õ¸ñ
		String str2=str.substring(str.indexOf(" ")+1,str.indexOf("£¤"));//£¤
		String str3=str.substring(str.indexOf("£¤")+1,str.length());
		this.num=Integer.parseInt(str1);
		this.name=str2;
		this.price=Integer.parseInt(str3);
	}
	public Menu(int num,String name,int price){
		this.num=num;
		this.name=name;
		this.price=price;
	}
	public String toString(){
		return num+" "+name+"£¤"+price;
	}
	public interface Equalable<T>{
		public boolean equals(T t1,T t2);
	}
	
	

}
