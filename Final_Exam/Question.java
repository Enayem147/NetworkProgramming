import java.util.Scanner;

public class Question{
	String question;
	String [] answer = new String[4];
	String solution;
	public Question(String question , String answer1 , String answer2 , String answer3 , String answer4,String solution){
		this.question = question;
		answer[0] = answer1;
		answer[1] = answer2;
		answer[2] = answer3;
		answer[3] = answer4;
		this.solution = solution;
	}

	// public void initQuestion(){
	// 	Scanner scn = new Scanner(System.in);
	// 	System.out.print("Nhap cau hoi :");
	// 	question = scn.nextLine();
	// 	for(int i = 0 ; i < 4 ; i++){
	// 		System.out.print("Nhap cau tra loi thu "+(i+1)+" :");
	// 		answer[i] = scn.nextLine();
	// 	}
	// }

}