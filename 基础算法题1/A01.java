public class A01{
	public static void main(String[] args){
		A01 a = new A01();
		double[] arr = {};
		Double res = a.max(arr);
		if(res != null){
			System.out.println("arr的最大值=" + a.max(arr));
		}else{
			System.out.println("arr的输入有误，数组不能为null，或者{}");
		}
	}
}
class A01{
	public Double max(double[] arr){
		if(arr != null && arr.length > 0){
		double max = arr[0];
		for(int i = 1;i < arr.length;i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}
		return max;
		}else{
			return null;
		}
	}
}