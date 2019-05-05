
import java.net.*;
import java.io.*;
public class BigFileClient{
	private static final int PORT = 9000;
	public static void main(String[] args) {
		try{
			MulticastSocket socket = new MulticastSocket(PORT);
			InetAddress address = InetAddress.getByName("225.1.1.1");
			socket.joinGroup(address);
			File file = new File("result");
			FileOutputStream fos = new FileOutputStream(file);
			FileInputStream fis = new FileInputStream(file);
			int count = 0;
			
			while(true){
				// nhận gói bắt đầu (rỗng)
				byte []firstByte = new byte[1];
				DatagramPacket firstPack = new DatagramPacket(firstByte,1);
				socket.receive(firstPack);
				int start = firstPack.getLength();
				boolean complete = false;
				while(start == 0 ){
					System.out.println("Dang ghi file");
					byte []inputByte = new byte [60000];
					DatagramPacket pack = new DatagramPacket(inputByte,inputByte.length);
					socket.receive(pack);
					if(pack.getLength() == 0){
						complete = true;
						break;
					}
					fos.write(pack.getData());
					count += pack.getLength();

				}
				if(complete){
					byte []copyByte = new byte[count];
					fis.read(copyByte);
					FileOutputStream out = new FileOutputStream("final");
					out.write(copyByte,0,count);
					break;
				}
			}
			socket.close();
			fis.close();
			fos.close();
			file.delete();
			System.out.println("Da luu file thanh cong");
		}catch(IOException ex){
			System.out.println(ex.toString());
		}
		
	}
}