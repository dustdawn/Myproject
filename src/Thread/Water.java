package Thread;

public class Water {
	static Object water=new Object();
	static int total=6;
	static int mqsl=3;
	static int ps=0;
public static void main(String[] args) {
		ThreadA threadA=new ThreadA();
		ThreadB threadB=new ThreadB();
		threadB.start();
		threadA.start();
	}
}
class ThreadA extends Thread{
	void pswork(){
		synchronized(Water.water){
			System.out.println("水塘是否没有水:"+isEmpty());
			if(isEmpty()){
				try{Water.water.wait();}
				catch(InterruptedException e){e.printStackTrace();}
			}
			else{
				Water.ps++;
				System.out.println("水塘目前排水量"+Water.ps);
				}
		}
	}
	public boolean isEmpty(){
		return Water.mqsl==Water.ps?true:false;
	}
	public void run(){
		while(Water.mqsl<Water.total){
			if(isEmpty())
				System.out.println("水塘没有排水，排水线被挂起");
			System.out.println("排水工作开始");
			pswork();
			try{
				sleep(1000);
			}catch(InterruptedException e){e.printStackTrace();}
		}
	}
}
class ThreadB extends Thread{
	void jswork(){
		synchronized(Water.water){
			Water.mqsl++;
			Water.water.notify();
			System.out.println("水塘目前进水量为"+Water.mqsl);
			}
		}
	public void run(){
		while(Water.mqsl<Water.total){
			System.out.println("进水工作开始");
			jswork();
			try{
				sleep(3000);
			}catch(InterruptedException e){e.printStackTrace();}
		}
	}
}
	


