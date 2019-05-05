import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TalkServer{
	private static final int PORT = 9000;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket(PORT);
			Scanner scn = new Scanner(System.in);
			byte []inputByte = new byte[60000];
			while(true){
				// Nhận dữ liệu	
				DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
				socket.receive(inputPack);
				String inpuStr = new String(inputPack.getData(),0,inputPack.getLength());
				System.out.println("Thang hang xom noi : "+inpuStr);

				// Gữi dữ liệu

				System.out.print("Ban noi : ");
				String outputStr = scn.nextLine();
				DatagramPacket outputPack = new DatagramPacket(outputStr.getBytes(),outputStr.length(),inputPack.getAddress(),inputPack.getPort());
				socket.send(outputPack);
			}
		}catch(IOException ex){
			System.out.println("Loi Server : "+ex.toString());
		}
		
	}
}