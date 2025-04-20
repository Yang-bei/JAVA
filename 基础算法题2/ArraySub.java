import java.util.Scanner;
public class ArraySub{
	public static void main(String[] args){
		
		Scanner myScanner = new Scanner(System.in);
		
		int[] arr = {1,2,3,4,5};
		
		do{
		int[] arrNew = new int[arr.length - 1];
		for(int i = 0; i < arrNew.length; i++){
			arrNew[i] = arr[i];
		}
		arr = arrNew;
		System.out.println("====arr缩减后元素情况====");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + "\t");
		}
		System.out.println("是否继续缩减 y/n");
		char key = myScanner.next().charAt(0);
		if(arr.length == 1){
			break;
		}else if( key == 'n'){
			break; 
		}
	}while(true);

	System.out.println("你已退出");
	}
}