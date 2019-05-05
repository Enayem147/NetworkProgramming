
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class FileClient{
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("127.0.0.1",9002);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner keyboardScanner = new Scanner(System.in);
			Scanner inputScanner = new Scanner(is);
			PrintWriter outputPrint = new PrintWriter(os);
			while(true){
				System.out.print("Nhap cau lenh : ");
				String request = keyboardScanner.nextLine();
				// chuyen du lieu qua server
				outputPrint.println(request);
				outputPrint.flush();
				if(request.equals("exit"))
					break;

				while(true){
					String resultStr = inputScanner.nextLine();
					if(resultStr.equals("."))
						break;
					System.out.println(resultStr);

				}
				// nhan du lieu
			}
			socket.close();
		}catch(IOException ex){
			System.out.println("Loi Server : "+ex.toString());
		}
		

	}
}