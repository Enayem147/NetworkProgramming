public class Question {
	String question;
	String []answer = new String[4];
	String solution;

	public Question(String question,String a,String b , String c , String d , String solution){
		this.question = question;
		answer[0] = "A : "+a;
		answer[1] = "B : "+b;
		answer[2] = "C : "+c;
		answer[3] = "D : "+d;
		this.solution = solution;
	}
}