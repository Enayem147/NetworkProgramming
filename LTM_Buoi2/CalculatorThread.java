import java.net.*;
import java.io.*;
import java.util.Scanner;

public class CalculatorThread extends Thread{
	Socket socket;
	String op;
	double operant1,operant2;
	public CalculatorThread (Socket socket){
		this.socket = socket;
	}


	@Override
	public void run(){
		try{
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner keyboardScanner = new Scanner(System.in);
			Scanner inputScanner = new Scanner(is);
			PrintWriter outputPrint = new PrintWriter(os);
			while(true){
				String request = inputScanner.nextLine();
				if(request.equals("exit"))
					break;
				tachYeuCau(request);
				double result = 0.0;
				switch(op){
					case "+":
						result = operant1 + operant2;
						break;
					case "-":
						result = operant1 - operant2;
						break;
					case "*":
						result = operant1 * operant2;
						break;
					case "/":
						result = operant1 / operant2;
						break;
				}
				outputPrint.println(result);
				outputPrint.flush();

			}
			socket.close();
			System.out.println("Socket closed : "+socket.getPort());
		}catch(IOException ex){
			System.out.println("Loi thread "+ex.toString());
		}
		
	}

	public void tachYeuCau(String request){
		request = request.trim();
		int firstSpace = request.indexOf(" ");
		int secondSpace = request.lastIndexOf(" ");

		op = request.substring(0,firstSpace);
		String op1 = request.substring(firstSpace+1,secondSpace);
		operant1 = Double.parseDouble(op1);
		String op2 = request.substring(secondSpace+1);
		operant2 = Double.parseDouble(op2);
	}
}
