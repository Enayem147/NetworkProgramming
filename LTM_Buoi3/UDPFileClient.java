import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPFileClient{
	private static final int PORT = 9000;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket();
			InetAddress serverAddress = InetAddress.getByName("127.0.01");
			Scanner scn = new Scanner(System.in);
			String fileName = "";
			do{
				System.out.println("Nhap ten file : ");
				fileName = scn.nextLine();
			}while(checkFile(fileName));
			
			// gữi yêu cầu
			String request = "READ "+fileName;
			byte [] outputByte = request.getBytes();
			DatagramPacket outputPack = new DatagramPacket(outputByte,outputByte.length,serverAddress,PORT);
			socket.send(outputPack);

			// nhận yêu cầu và lưu ra file
			File file = new File("result");
			FileOutputStream fos = new FileOutputStream(file);
			byte []inputByte = new byte[60000];
			DatagramPacket inputPack = new DatagramPacket(inputByte,inputByte.length);
			socket.receive(inputPack);
			fos.write(inputPack.getData(),0,inputPack.getLength());

			System.out.println("Luu file thanh cong");



		}catch(IOException ex){
			System.out.println("Loi client : "+ex.toString());
		}
	}

	private static boolean checkFile(String fileName){
		boolean error = false;
		File file = new File(fileName);
		int maxLength = 64 * 1024;
		int fileLength = (int)file.length();

		if(fileLength > maxLength){
			error = true;
			System.out.println("File qua lon");
		}
		if(!file.exists()){
			error = true;
			System.out.println("File khong ton tai");
		}
		return error;
	}


}