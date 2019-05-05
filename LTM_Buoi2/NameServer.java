
import java.net.*;
import java.io.*;

public class NameServer{
	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(9001);
			System.out.println("Server da khoi tao");
			System.out.println("Dang doi client");
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Welcome client : "+socket.getPort());
				NameThread serviceProcess = new NameThread(socket);
				serviceProcess.start();
			}
		}catch(IOException ex){
			System.out.println("Loi Server "+ex.toString());
		}
		

	}
}