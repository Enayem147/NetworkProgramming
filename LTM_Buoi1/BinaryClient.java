import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
public class BinaryClient{
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("127.0.01",9000);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// nhap chuoi tu ban phim
			Scanner scn = new Scanner(System.in);
			while(true){
				System.out.print("Nhap chuoi so :");
				String inputStr = scn.nextLine();

				// day du lieu qua Server
				byte [] inputByte = inputStr.getBytes();
				os.write(inputByte);
				if(inputStr.equals("exit"))
					break;
				// nhan ket qua tu Server
				byte [] resultByte = new byte[500];
				int n = is.read(resultByte);
				String resultStr = new String(resultByte,0,n);
				System.out.println("Result : "+resultStr);
			}
			socket.close();

		}catch(IOException ex){
			System.out.println("Client loi : "+ex.toString());
		}
		
	}
}