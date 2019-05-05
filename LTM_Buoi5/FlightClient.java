import java.rmi.*;
import java.net.MalformedURLException;
import java.util.Scanner;
public class FlightClient{
	public static void main(String[] args) {
		try{
			FlightInterface flightReference1 = (FlightInterface) Naming.lookup("rmi://127.0.0.1/Flight1");
			FlightInterface flightReference2 = (FlightInterface) Naming.lookup("rmi://127.0.0.1/Flight2");
			Scanner scn = new Scanner(System.in);
			String inf1 = flightReference1.getFlightInformation();
			String inf2 = flightReference2.getFlightInformation();
			System.out.println("===Thong tin chuyen bay thu 1===");
			System.out.println(inf1);
			System.out.println("============================");
			System.out.println("===Thong tin chuyen bay thu 2===");
			System.out.println(inf2);
			System.out.println();
			while(true){
				System.out.print("Nhap so hieu may bay : ");
				int soHieu = scn.nextInt();
				if(soHieu == 10001){
					System.out.println("Dat ve : 1");
					System.out.println("Tra ve : 2");
					System.out.print("Nhap lua chon : ");
					int flag = scn.nextInt();
					if(flag == 1){
						System.out.print("Nhap so ve can dat : ");
						int soVe = scn.nextInt();
						if(flightReference1.buyTicket(soVe))
							System.out.println("Dat thanh cong");
						else
							System.out.println("Dat khong thanh cong");
					}else if (flag == 2){
						System.out.print("Nhap so ve can tra: ");
						int soVe = scn.nextInt();
						if(flightReference1.refundTicket(soVe))
							System.out.println("Tra ve thanh cong");
						else
							System.out.println("Tra ve khong thanh cong");
					}
					System.out.println("===Thong tin chuyen bay thu 1===");
					System.out.println(flightReference1.getFlightInformation());

				}else if (soHieu == 10002){
					System.out.println("Dat ve : 1");
					System.out.println("Tra ve : 2");
					System.out.print("Nhap lua chon : ");
					int flag = scn.nextInt();
					if(flag == 1){
						System.out.print("Nhap so ve can dat : ");
						int soVe = scn.nextInt();
						if(flightReference2.buyTicket(soVe))
							System.out.println("Dat thanh cong");
						else
							System.out.println("Dat khong thanh cong");
					}else if (flag == 2){
						System.out.print("Nhap so ve can tra: ");
						int soVe = scn.nextInt();
						if(flightReference2.refundTicket(soVe))
							System.out.println("Tra ve thanh cong");
						else
							System.out.println("Tra ve khong thanh cong");
					}
					System.out.println("===Thong tin chuyen bay thu 2===");
					System.out.println(flightReference2.getFlightInformation());
				}else if(soHieu == 0){
					System.out.println("END");
					break;
				}else{
					System.out.println("Chuyen bay khong ton tai");
				}
			}
		}catch(NotBoundException ex){
			System.out.println(ex.toString());
		}catch(RemoteException ex){
			System.out.println(ex.toString());
		}catch(MalformedURLException ex){
			System.out.println(ex.toString());
		}
		
	}
}