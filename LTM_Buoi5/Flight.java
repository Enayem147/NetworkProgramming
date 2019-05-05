import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Flight extends UnicastRemoteObject implements FlightInterface{
	private int soHieu;
	private String thoiGianBay;
	private String noiDi;
	private String noiDen;
	private int tongSoGhe;
	private int soGheDaBan;
	private int soGheConTrong;

	public Flight() throws RemoteException{
		super();
	}

	public Flight(int soHieu , String thoiGianBay , String noiDi ,String noiDen,int tongSoGhe) throws RemoteException{
		super();
		this.soHieu = soHieu;
		this.thoiGianBay = thoiGianBay;
		this.noiDi = noiDi;
		this.noiDen = noiDen;
		this.tongSoGhe = tongSoGhe;
		this.soGheDaBan = 0;
		this.soGheConTrong = tongSoGhe;
	}

	public String getFlightInformation(){
		String result = "";
		result+="So hieu may bay : "+soHieu;
		result+="\nThoi gian bay : "+thoiGianBay;
		result+="\nNoi di : "+noiDi;
		result+="\nNoi den : "+noiDen;
		result+="\nTong so ghe : "+tongSoGhe;
		result+="\nSo ghe da ban: "+soGheDaBan;
		result+="\nSo ghe con trong : "+soGheConTrong;

		return result;
	}

	public boolean buyTicket(int numberOfTicket){
		boolean complete = false;
		if((soGheConTrong - numberOfTicket) >= 0 ){
			soGheConTrong -= numberOfTicket;
			soGheDaBan += numberOfTicket;
			complete = true;
		}
		return complete;
	}

	public boolean refundTicket(int numberOfTicket){
		boolean complete = false;
		if((soGheDaBan - numberOfTicket) >= 0 ){
			soGheConTrong += numberOfTicket;
			soGheDaBan -= numberOfTicket;
			complete = true;
		}
		return complete;
	}
}