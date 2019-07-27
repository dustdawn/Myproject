package Menu;
import java.net.*;
import java.io.*;
public class OrderTCPServer {
	public OrderTCPServer(int port,String name)throws IOException{
		ServerSocket server = new ServerSocket(port);  //ServerSocket�ṩ��TCP���ӷ���
		Socket client = server.accept();//�ȴ����ܿͻ��˵�TCP��������
		new OrderTCPServerJFrame(name,client);//�������������ң�ͼ���û�����ͬ�ͻ���
		server.close();
	}
	public static void main(String[] args)throws IOException{
		new OrderTCPServer(2001,"����");
	}
}
