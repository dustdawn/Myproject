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
			System.out.println("ˮ���Ƿ�û��ˮ:"+isEmpty());
			if(isEmpty()){
				try{Water.water.wait();}
				catch(InterruptedException e){e.printStackTrace();}
			}
			else{
				Water.ps++;
				System.out.println("ˮ��Ŀǰ��ˮ��"+Water.ps);
				}
		}
	}
	public boolean isEmpty(){
		return Water.mqsl==Water.ps?true:false;
	}
	public void run(){
		while(Water.mqsl<Water.total){
			if(isEmpty())
				System.out.println("ˮ��û����ˮ����ˮ�߱�����");
			System.out.println("��ˮ������ʼ");
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
			System.out.println("ˮ��Ŀǰ��ˮ��Ϊ"+Water.mqsl);
			}
		}
	public void run(){
		while(Water.mqsl<Water.total){
			System.out.println("��ˮ������ʼ");
			jswork();
			try{
				sleep(3000);
			}catch(InterruptedException e){e.printStackTrace();}
		}
	}
}
	


