import java.net.*;
import java.io.*;

public class CalculatorServer{
	public static void main(String[] args) {
		try{
		ServerSocket serverSocket = new ServerSocket(9003);
		System.out.println("Server da duoc khoi dong");
		while(true){
			Socket socket = serverSocket.accept();
			System.out.println("Welcome socket : "+socket.getPort());
			CalculatorThread serviceProcess = new CalculatorThread(socket);
			serviceProcess.start();
		}
		}catch(IOException ex){
			System.out.println("Loi Server : "+ex.toString());
		}
	}

} 