import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
public class QuizServer{
	private static final int udpPort = 3333;
	private static final int tcpPORT = 4444;
	private static final String server = "127.0.0.1";

	public static class QuizService extends Thread{
		DatagramSocket udpSocket;
		Socket socket;
		public QuizService(Socket socket,DatagramSocket udpSocket){
			this.socket = socket;
			this.udpSocket = udpSocket;
		}

		@Override
		public void run(){
			Question []questionList  = new Question[5];
			questionList[0] = new Question("Em an com chua ? ","A : Da roi a.","B : Chua anh oi","C : An hay chua ke bo may","D : Em khong biet","A");
			questionList[1] = new Question("Em thich an rau den khong ? ","A : Da roi a.","B : Chua anh oi","C : An hay chua ke bo may","D : Em khong biet","B");
			questionList[2] = new Question("Em ngu chua ? ","A : Da roi a.","B : Chua anh oi","C : Dang ngu nen khong tra loi duoc ","D : Ke tao","C");
			questionList[3] = new Question("Bo em co phai la an trom ? ","A : Sao anh lai hoi nhu vay ???","B : Anh bi ngao a ??","C : Chac chan la khong roi anh ","D : Em khong biet","C");
			questionList[4] = new Question("Anh thich em em co biet hay khong ? ","A : Khong","B : Thiet la nhu vay luon","C : Em thua biet","D : Hihi","D");
			try{
				
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				Scanner inputScanner = new Scanner(is);
				PrintWriter printWriter = new PrintWriter(os);

				// nhan thong tin nguoi choi
				byte [] playerByte = new byte[60000];
				DatagramPacket playerPack = new DatagramPacket(playerByte,playerByte.length);
				udpSocket.receive(playerPack);
				String playerInformation = new String(playerPack.getData(),0,playerPack.getLength());
				String playerPassword = "passwordla6consosau";
				if(!playerInformation.equals("")){
					System.out.println("Xin chao : "+playerInformation);
					// gui password nguoi choi
					DatagramPacket passwordPack = new DatagramPacket(playerPassword.getBytes(),playerPassword.length(),
																	 playerPack.getAddress(),playerPack.getPort());
					udpSocket.send(passwordPack);
					// nhan password va kiem tra
					String receivePassword = inputScanner.nextLine();
					int count = 0 ;
					if(receivePassword.equals(playerPassword)){
						// gui cau hoi va cau tra loi
						for(int i = 0 ; i < 5 ; i++){
							String question = questionList[i].question;
							String fullQuestion = "Cau "+(i+1)+" : "+question;
							printWriter.println(fullQuestion);
							printWriter.flush();
							for(int j = 0 ; j < 4 ; j++){
								String answer = questionList[i].answer[j];
								printWriter.println(answer);
								printWriter.flush();
							}
							// nhan cau tra loi tu client
							String playerAnswer = inputScanner.nextLine();
							String correctAnswer = questionList[i].solution;
							// kiem tra dap an
							if(playerAnswer.equals(correctAnswer))
								count++;
						}
						// gui ket qua
						String result = "Ban da tra loi dung "+count+"/5 cau";
						printWriter.println(result);
						printWriter.flush();

						socket.close();
						System.out.println("Client \""+playerInformation+"\" da hoan thanh bai kiem tra");
					}
					
				}
				

			}catch(IOException ex){
				System.out.println(ex.toString());
			}catch(NoSuchElementException ex){
			System.out.println("Co client ngat ngang");
			}
		}
	}

	public static void main(String[] args) {
		try{
			DatagramSocket udpSocket = new DatagramSocket(udpPort);
			ServerSocket serverSocket = new ServerSocket(tcpPORT);
			System.out.println("Khoi dong server thanh cong");
			while(true){
				Socket tcpSocket = serverSocket.accept();
				QuizService service = new QuizService(tcpSocket,udpSocket);
				service.start();
			}	
		}catch(IOException ex){
			System.out.println(ex.toString());
		}catch(NoSuchElementException ex){
			System.out.println("Co client ngat ngang");
		}
	}
}