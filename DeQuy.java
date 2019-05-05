


public class DeQuy{
	public static void main(String[] args) {
		// int [] numArr = {1,2,3,4};
		// int result = tinhTongTrongMang(numArr,numArr.length-1);

		for(int i = 0 ; i<=38 ; i++){
			int result = tinhSoFibonaci(i);
			System.out.println(i +" ---- "+result);
		}
		
	}


	public static int tinhTong(int num){
		if(num == 0 )
			return 0;
		return num + tinhTong(num - 1);
	}

	public static int tinhDayThua(int num){
		if(num == 0)
			return 1;
		return num * tinhDayThua(num - 1);
	}

	public static int tinhTongTrongMang(int []arr,int num){
		if(num == 0)
			return arr[0];
		return arr[num] + tinhTongTrongMang(arr,num-1);
	}

	public static int tinhSoFibonaci(int num){
		if(num == 0)
			return 0;
		if (num < 3)
			return 1;
			
		return tinhSoFibonaci(num-1) + tinhSoFibonaci(num-2);
	}
}