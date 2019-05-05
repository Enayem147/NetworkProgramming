import java.net.*;
import java.io.*;
import java.util.Scanner;

public class FileServer{
	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(9002);
			System.out.println("Server da khoi dong ");
			System.out.println("Dang cho phuc vu ");
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Welcome socket : "+socket.getPort());
				FileThread serviceProcess = new FileThread(socket);
				serviceProcess.start();
			}
		}catch(IOException ex){
			System.out.println("Loi server : "+ex.toString());
		}
		
	}
}