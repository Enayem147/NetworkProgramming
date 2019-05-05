import java.net.*;
import java.io.*;
import java.util.Scanner;

public class CalculatorClient{
	public static void main(String[] args) {
		try{
			Socket socket = new Socket("127.0.0.1",9003);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner keyboardScanner = new Scanner(System.in);
			Scanner inputScanner = new Scanner(is);
			PrintWriter outputPrint = new PrintWriter(os);

			while(true){
				System.out.print("Nhap phep toan : ");
				String request = keyboardScanner.nextLine();
				String newRequest = chuyenYeuCau(request);
				// gui yeu cau cho server
				outputPrint.println(newRequest);
				outputPrint.flush();

				if(newRequest.equals("exit"))
					break;

				// nhan ket qua tu server

				String result = inputScanner.nextLine();
				System.out.println("Result = "+result);
			}
		socket.close();
		}catch(IOException ex){
			System.out.println("Loi Client : "+ex.toString());
		}
		
	}

	public static String chuyenYeuCau(String request){
		request = request.trim();
		String result = "";
		int firstSpace = request.indexOf(" ");
		int secondSpace = request.lastIndexOf(" ");

		if(firstSpace > 0){
			String op = request.substring(firstSpace+1,secondSpace);
			String operant1 = request.substring(0,firstSpace);
			String operant2 = request.substring(secondSpace+1);
			result = op+" "+operant1+" "+operant2;
		}else{
			result = request;
		}
		return result;
	}
}