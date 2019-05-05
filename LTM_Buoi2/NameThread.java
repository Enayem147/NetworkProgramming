
import java.net.*;
import java.io.*;

public class NameThread extends Thread{

	Socket socket;

	public NameThread(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run(){
		try{
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			while(true){
				byte [] hoTenByte = new byte[500];
				int n = is.read(hoTenByte);
				String hoTen = new String(hoTenByte,0,n);
				if(hoTen.equals("exit"))
					break;
				String ten = tachTen(hoTen);
				os.write(ten.getBytes());
		}
			socket.close();
			System.out.println("Socket closed : "+socket.getPort());
		}catch(IOException ex){
			System.out.println("Loi Process Server "+ex.toString());
		}
		
		
	}	

	private String tachTen(String hoTen){
		hoTen = hoTen.trim();
		int i = hoTen.lastIndexOf(" ");
		String ten = hoTen.substring(i+1);
		return ten;
	}
}