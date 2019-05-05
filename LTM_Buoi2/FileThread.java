import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
public class FileThread extends Thread{

	Socket socket;
	PrintWriter outputPrint;
	String title = "";
	String directory = "";
	public FileThread(Socket socket){
		this.socket = socket;
	}

	public void run(){
		try{
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner inputScanner = new Scanner(is);
			outputPrint = new PrintWriter(os);
			while(true){
				String request = inputScanner.nextLine();
				if(request.equals("exit"))
					break;
				tachChuoi(request);

				if(title.equals("LIST")){
					File file = new File(directory);
					if(file.isDirectory()){
						duyetFile(file);
						outputPrint.println(".");
						outputPrint.flush();
					}else{
						outputPrint.println("Thu muc khong ton tai");
						outputPrint.println(".");
						outputPrint.flush();
					}
				}else{
					outputPrint.println("Yeu cau khong hop le");
					outputPrint.println(".");
					outputPrint.flush();
				}
				
				
			}
			socket.close();
			System.out.println("Socket closed : "+socket.getPort());	
		}catch(IOException ex){
			System.out.println("Loi process : "+ex.toString());
		}
		
	}

	// 3 cách
	// 1 : chuyển thành byte xong đẩy tuần tự
	// 2 : đẩy số lượng dòng trước , for để duyệt
	// 3 : dùng 1 kỳ tự để kết thúc
	public void tachChuoi(String request){
		request = request.trim();
		int i = request.lastIndexOf(" ");
		title = request.substring(0,i);
		directory = request.substring(i+1);
	}

	public void duyetFile(File file){
		String path = file.getAbsolutePath();
		outputPrint.println(path);
		outputPrint.flush();
		if(file.isDirectory()){
			File []listFile = file.listFiles();
			for(File item:listFile){
				duyetFile(item);
			}
		}
	}


}