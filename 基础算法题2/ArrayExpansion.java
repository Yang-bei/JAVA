public class ArrayExpansion{
	public static void main(String[] args){
		int[] arr = {10,12,45,90};
		int num = 23;
		int index = -1;
		for(int i = 0;i < arr.length; i++){
			if(num < arr[i]){
				index = i;
				break;
			}
		}
		if(index == -1){
			index = arr.length;
		}
		int[] arrNew = new int[arr.length + 1];
		for(int i = 0,j = 0;i < arrNew.length;i++){
			if(i != index){
				arrNew[i] = arr[j];
				j++;
			}else{
				arrNew[i] = num;
			}
		}
		arr = arrNew;
		System.out.println("====插入后，arr数组的元素情况====");
		for(int i = 0;i < arr.length;i++){
			System.out.print(arr[i] + " ");
		}
	}
}