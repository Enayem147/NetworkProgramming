import java.net.*;
import java.io.*;
import java.util.Scanner;

public class QuizClient{
	private final static int udpPort = 5555 , tcpPort = 4444;
	private final static String server = "127.0.0.1";
	public static void main(String[] args) {
		try{
			Socket tcpSocket = new Socket(server,tcpPort);
			DatagramSocket udpSocket = new DatagramSocket();
			InetAddress serverAddress = InetAddress.getByName(server);
			InputStream is = tcpSocket.getInputStream();
			OutputStream os = tcpSocket.getOutputStream();
			Scanner scn = new Scanner(System.in);
			Scanner inputScanner = new Scanner(is);
			PrintWriter printWriter = new PrintWriter(os);

			// nhap user name , password
			System.out.print("Nhap username : ");
			String username = scn.nextLine();
			System.out.print("Nhap password : ");
			String password = scn.nextLine();
			String request = username + " " + password;
			// gui username - password cho server
			DatagramPacket outputPack = new DatagramPacket(request.getBytes(),request.length(),serverAddress,udpPort);
			udpSocket.send(outputPack);
			// nhan password tro choi
			byte []gamePasswordByte = new byte[60000];
			DatagramPacket inputPack = new DatagramPacket(gamePasswordByte,gamePasswordByte.length);
			udpSocket.receive(inputPack);
			String gamePassword = new String(inputPack.getData(),0,inputPack.getLength());

			// gui password tro choi cho server
			printWriter.println(gamePassword);
			printWriter.flush();

			// nhan cau hoi va dap an
			for(int i = 0 ; i < 5 ; i++){
				String question = inputScanner.nextLine();
				System.out.println(question);
				for(int j = 0 ; j < 4 ; j++){
					String answer = inputScanner.nextLine();
					System.out.println(answer);
				}
				// gui cau tra loi cho server
				System.out.print("Dap an cua ban : ");
				String solution = scn.nextLine();

				printWriter.println(solution);
				printWriter.flush();
			}
			String result = inputScanner.nextLine();
			System.out.println(result);

			tcpSocket.close();
			udpSocket.close();



		}catch(IOException ex){
			System.out.println(ex.toString());
		}
	}
}