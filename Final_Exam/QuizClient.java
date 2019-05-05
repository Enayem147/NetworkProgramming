import java.net.*;
import java.io.*;
import java.util.Scanner;
public class QuizClient{
	private static final int udpPort = 3333;
	private static final int tcpPORT = 4444;
	private static final String server = "127.0.0.1";
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		try{
			DatagramSocket udpSocket = new DatagramSocket();
			InetAddress serverAddress = InetAddress.getByName(server);
			Socket socket = new Socket("127.0.0.1",tcpPORT);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner inputScanner = new Scanner(is);
			PrintWriter printWriter = new PrintWriter(os);
			// nhap username - password
			System.out.print("Nhap username : ");
			String username = scn.nextLine();
			System.out.print("Nhap password : ");
			String password = scn.nextLine();
			// gui username password cho server
			String request = username+" "+password;
			DatagramPacket requestPack = new DatagramPacket(request.getBytes(),request.length(),serverAddress,udpPort);
			udpSocket.send(requestPack);
			// nhan password tro choi
			byte[] passwordByte = new byte[60000];
			DatagramPacket passwordPack = new DatagramPacket(passwordByte,passwordByte.length);
			udpSocket.receive(passwordPack);
			String playerPassword = new String(passwordPack.getData(),0,passwordPack.getLength());
			// gui lai password tro choi cho server TCP
			printWriter.println(playerPassword);
			printWriter.flush();
			// nhan 5 cau hoi (moi cau hoi 4 cau tra loi) - va tra loi
			for(int i = 0 ; i < 5 ; i++){
				String question = inputScanner.nextLine();
				System.out.println(question);
				for(int j = 0 ; j < 4 ; j++){
					String answer = inputScanner.nextLine();
					System.out.println(answer);
				}
				System.out.print("Dap an cua ban : ");
				String solution = scn.nextLine();
				printWriter.println(solution);
				printWriter.flush();
			}
			// nhan ket qua
			String result = inputScanner.nextLine();
			System.out.print(result);

			socket.close();
			udpSocket.close();
		}catch(IOException ex){
			System.out.println(ex.toString());
		}
	}
}