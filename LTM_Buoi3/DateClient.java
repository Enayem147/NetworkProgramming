import java.util.Scanner;
import java.net.*;
import java.io.*;

public class DateClient{
	private static final int PORT = 13;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket();
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			Scanner scn = new Scanner(System.in);
			while(true){
				// Gữi dữ liệu qua server
				System.out.print("Nhap yeu cau :");
				String request = scn.nextLine();
				byte[] outputByte = request.getBytes();
				// 4 đối số : mảng byte , length , address , port
				DatagramPacket outputPack = new DatagramPacket(outputByte,outputByte.length,serverAddress,PORT);
				socket.send(outputPack);

				// Nhận dữ liệu từ server
				byte[] inputByte = new byte[60000];
				// 2 đối số : mảng byte , length
				DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
				socket.receive(inputPack);
				// 3 đối số : mảng byte , 0 , length
				String inputStr = new String(inputPack.getData(),0,inputPack.getLength());
				System.out.println("Ngay gio he thong la : "+inputStr);


			}
		}catch(IOException ex){
			System.out.println("Loi Client :"+ex.toString());
		}
		
	}
}