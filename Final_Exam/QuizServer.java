import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
public class QuizServer{
	private final static int udpPort = 5555 , tcpPort = 4444;
	private final static String server = "127.0.0.1";
	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(tcpPort);
			DatagramSocket udpSocket = new DatagramSocket(udpPort);
			System.out.println("Server da duoc khoi tao thanh cong");
			while(true){
				Socket tcpSocket = serverSocket.accept();
				System.out.println("Co mot client vua ket noi : "+tcpSocket.getPort());
				QuizService service = new QuizService(tcpSocket,udpSocket);
				service.start();
			}
		}catch(IOException ex){
			System.out.println(ex.toString());
		}catch(NoSuchElementException ex){
			System.out.println("client out");
		}
	}

	public static class QuizService extends Thread {
		Socket tcpSocket ;
		DatagramSocket udpSocket;
		public QuizService(Socket tcpSocket,DatagramSocket udpSocket){
			this.tcpSocket = tcpSocket;
			this.udpSocket = udpSocket;
		}

		@Override
		public void run(){
			try{
				Question []questionList = new Question[5];
				questionList[0] = new Question("Em an com chua ? ","Em an roi","Em chua a.","An roi hay chua ke tao","hihi","C");
				questionList[1] = new Question("Em co thich an rau den khong ? ","Da co","Da khong a.","Ke tao","hihi","A");
				questionList[2] = new Question("Di xem ENDGAME khong ? ","OK","Dut","Mua ve di","Het tien oi","D");
				questionList[3] = new Question("Gia dinh em co biet em bi dong tinh khong ? ","Co","Khong","????","Vaiiiiii","B");
				questionList[4] = new Question("Em co thich anh khong? ","hihi","hmmmmm em cung k biet nua","Co","Khong","A");

				InetAddress serverAddress = InetAddress.getByName(server);
				InputStream is = tcpSocket.getInputStream();
				OutputStream os = tcpSocket.getOutputStream();
				Scanner scn = new Scanner(System.in);
				Scanner inputScanner = new Scanner(is);
				PrintWriter printWriter = new PrintWriter(os);
				// khong can kiem tra
				byte []playerInformationByte = new byte[60000];
				DatagramPacket inputPack = new DatagramPacket(playerInformationByte,playerInformationByte.length);
				udpSocket.receive(inputPack);
				// String playerInfor = new String(inputPack.getData(),0,inputPack.getLength());
				// System.out.println(playerInfor);
				// gui password tro choi
				String gamePassword = "soso6666so6";
				DatagramPacket outputPack = new DatagramPacket(gamePassword.getBytes(),gamePassword.length(),inputPack.getAddress(),inputPack.getPort());
				udpSocket.send(outputPack);
				// nhan password tro choi

				String receivePassword = inputScanner.nextLine();
				int result = 0 ;
				if(receivePassword.equals(gamePassword)){
					// gui cau hoi
					for(int i = 0 ; i < 5 ; i++){
						String question = "Cau hoi "+(i+1)+" : "+questionList[i].question;
						printWriter.println(question);
						printWriter.flush();
						for(int j = 0 ; j < 4 ; j++){
							String answer = questionList[i].answer[j];
							printWriter.println(answer);
							printWriter.flush();
						}
						// thong ke cau tra loi
						String solution = inputScanner.nextLine();
						if(solution.equals(questionList[i].solution))
							result++;
					}
				}
				String resultStr = "Ban da tra loi duoc "+result+"/5 cau ";
				printWriter.println(resultStr);
				printWriter.flush();

				System.out.println("Client o port "+tcpSocket.getPort()+" da hoan thanh bai kiem tra");
				tcpSocket.close();
			}catch(IOException ex){
				System.out.println(ex.toString());
			}catch(NoSuchElementException ex){
				System.out.println("client out");
			}
		}
	}
}