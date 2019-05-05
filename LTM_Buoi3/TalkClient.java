import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TalkClient{
	private static final int PORT = 9000;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket();
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			Scanner scn = new Scanner(System.in);
			while(true){
				// Gữi dữ liệu
				System.out.print("Ban noi : ");
				String outputStr = scn.nextLine();

				DatagramPacket outputPack = new DatagramPacket(outputStr.getBytes(),outputStr.length(),serverAddress,PORT);
				socket.send(outputPack);
				// Nhận dữ liệu

				byte []inputByte = new byte[60000];
				DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
				socket.receive(inputPack);

				String inputStr = new String(inputPack.getData(),0,inputPack.getLength());
				System.out.println("Thang hang xom noi : "+inputStr);
			}
		}catch(IOException ex){
			System.out.println("Loi client : "+ex.toString());
		}
		
	}
}