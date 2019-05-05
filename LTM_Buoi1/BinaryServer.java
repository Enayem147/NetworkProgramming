import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;


public class BinaryServer{
	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(9000);
			System.out.println("Da khoi tao thanh cong server");
			System.out.println("Dang cho client ket noi");
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Welcome socket "+socket.getPort());
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				while(true){
					byte [] inputByte = new byte[500];
					int n = is.read(inputByte);
					String inputStr = new String(inputByte,0,n);
					if(inputStr.equals("exit"))
						break;
					String binaryStr = "";
					try{
						int inputInt = Integer.parseInt(inputStr);
						binaryStr = Integer.toBinaryString(inputInt);
					}catch(NumberFormatException ex){
						binaryStr = "Khong phai la so nguyen";
					}
					
					
					os.write(binaryStr.getBytes());
				}
				socket.close();
				System.out.println("Socket close : "+socket.getPort());
				
			}
		}catch(IOException ex){
			System.out.println("Loi Server : "+ex.toString());
		}
		
	}
}