import java.util.Scanner;
import java.net.*;
import java.io.*;

public class BigFileServer{
	private static final int PORT = 9000;
	public static void main(String[] args) {
		try{
			DatagramSocket socket = new DatagramSocket();
			InetAddress address = InetAddress.getByName("225.1.1.1");
			Scanner scn = new Scanner(System.in);
			System.out.print("Nhap ten file: ");
			String fileName = scn.nextLine();

			while(true){
				// đoc file
				File file = new File(fileName);
				int fileLength = (int)file.length();
				FileInputStream fis = new FileInputStream(file);
				byte [] fileByte = new byte[fileLength];
				fis.read(fileByte);

				//gữi gói đầu (rỗng)
				byte [] firstByte = new byte[0];
				DatagramPacket firstPack = new DatagramPacket(firstByte,0,address,PORT);
				socket.send(firstPack);
				System.out.println("Goi dau tien");
				//gữi các gói n-1
				int fileAmount = fileLength / 60000;
				if(fileLength % 60000 != 0 )
					fileAmount++;
				byte []midByte = new byte[60000];
				for(int i = 0 ;i < fileAmount - 1 ; i++){
					// copy các gói 
					for(int j = 0 ; j < 60000 ; j++){
						midByte[j] = fileByte[ i * 60000 + j];
					}
					DatagramPacket midPack = new DatagramPacket(midByte,60000,address,PORT);
					socket.send(midPack);
					System.out.println("Goi thu "+(i+1));
				}
				//gữi gói cuối
				byte []endByte = new byte[60000];
				int endAmount =  fileLength - ((fileAmount-1) * 60000);
				for(int y = 0 ; y<endAmount ; y++){
					endByte[y] = fileByte[((fileAmount-1) * 60000) + y];
				}
				DatagramPacket endPack = new DatagramPacket(endByte,endAmount,address,PORT);
				socket.send(endPack);
				System.out.println("Goi cuoi");
				Thread.sleep(1000);
			}
		}catch(IOException ex){
			System.out.println(ex.toString());
		}catch(InterruptedException ex){
			System.out.println(ex.toString());
		}
		
	}
}