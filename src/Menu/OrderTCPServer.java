package Menu;
import java.net.*;
import java.io.*;
public class OrderTCPServer {
	public OrderTCPServer(int port,String name)throws IOException{
		ServerSocket server = new ServerSocket(port);  //ServerSocket提供的TCP连接服务
		Socket client = server.accept();//等待接受客户端的TCP连接申请
		new OrderTCPServerJFrame(name,client);//服务器的聊天室，图形用户界面同客户端
		server.close();
	}
	public static void main(String[] args)throws IOException{
		new OrderTCPServer(2001,"主厨");
	}
}
