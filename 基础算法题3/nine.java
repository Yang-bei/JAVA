public class nine{
	public static void main(String[] args){
		int i = 0;
		int count = 0;
		for(i = 1;i<=100;i++){
			if(i % 9 == 0){
				System.out.println(i);
				count++;
			}
		}
		System.out.println(count);
	}
}