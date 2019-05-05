import java.util.Scanner;
import java.util.Date;
import java.net.*;
import java.io.*;

public class DateServer{
	private static final int PORT = 13;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket(PORT);

			while(true){
				// Nhận dữ liệu từ client
				byte[] inputByte = new byte[1];
				DatagramPacket inputPack = new DatagramPacket(inputByte,0);
				socket.receive(inputPack);

				// xử lý
				Date date = new Date();
				String outputStr = date.toString();
				// Gữi dữ liệu cho client
				byte[] outputByte = outputStr.getBytes();
				DatagramPacket outputPack = new DatagramPacket(outputByte,outputByte.length,inputPack.getAddress(),inputPack.getPort());
				socket.send(outputPack);

			}
		}catch(IOException ex){
			System.out.println("Loi server : "+ex.toString());
		}
		
	}
}