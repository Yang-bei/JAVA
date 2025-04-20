public class BubbleSort{
	public static void main(String[] args){
		int[] arr = {24,35,92,56,38};
		int num = 0;
		for(int j = 0;j < 4;j++){
			if(arr[j] > arr[j + 1]){
				num = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = num;
			}
		}
		System.out.println("===第一次转换===");
		for(int j = 0;j < arr.length;j++){
			System.out.print(arr[j] + "\t");
		}
	}
}