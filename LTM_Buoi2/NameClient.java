import java.net.*;
import java.io.*;
import java.util.Scanner;
public class NameClient{
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("127.0.0.1",9001);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner scn = new Scanner(System.in);
			while(true){
				System.out.print("Nhap ho ten :");
				String hoTen = scn.nextLine();
				os.write(hoTen.getBytes());
				if(hoTen.equals("exit"))
					break;
				byte[] resultByte = new byte[500];
				int n = is.read(resultByte);
				String resultStr = new String(resultByte,0,n);

				System.out.println("Result : "+resultStr);
			}
			socket.close();

		}catch(IOException ex){
			System.out.println("Loi Client : "+ex.toString());
		}
		

	}
}