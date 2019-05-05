import java.rmi.*;
import java.net.MalformedURLException;

public class FlightServer{
	public static void main(String[] args) {
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		try{
			Flight flight1 = new Flight(10001 , "20-11-2019 8h30" , "Can Tho" , "Ha Noi",200);
			Flight flight2 = new Flight(10002 , "11-11-2019 13h30" , "Can Tho", "Phu Quoc",300);

			Naming.rebind("Flight1",flight1);
			Naming.rebind("Flight2",flight2);
			
			System.out.println("Da khoi tao 2 chuyen bay thanh conggggg");
		}catch(RemoteException ex){
			System.out.println(ex.toString());
		}catch(MalformedURLException ex){
			System.out.println(ex.toString());
		}
	}
}