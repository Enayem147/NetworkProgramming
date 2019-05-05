import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPFileServer{
	private static final int PORT = 9000;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket(PORT);
			byte []inputByte = new byte[60000];
			while(true){
				// nhận tên file từ client
				DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
				socket.receive(inputPack);
				String inputStr = new String(inputPack.getData(),0,inputPack.getLength());
				String fileName = inputStr.substring(5);
				// doc file
				File file = new File(fileName);
				int fileLength = (int)file.length();
				byte [] outputByte = new byte[fileLength];
				FileInputStream fis = new FileInputStream(file);
				fis.read(outputByte);
				// gữi dữ liệu cho client
				DatagramPacket outputPack = new DatagramPacket(outputByte,outputByte.length,inputPack.getAddress(),inputPack.getPort());
				socket.send(outputPack);

			}
		}catch(IOException ex){
			System.out.println("Loi Server : "+ex.toString());
		}
	}
}