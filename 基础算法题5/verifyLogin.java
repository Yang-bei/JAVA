import java.util.Scanner;
public class verifyLogin{
	public static void main(String[] args){
		Scanner myScanner = new Scanner(System.in);
		String name = "";
		String passwd = "";
		int chance = 3;
		for(int i = 1;i <= 3;i++){
			System.out.println("����������");
			name = myScanner.next();
			System.out.println("����������");
			passwd = myScanner.next();
			if("����".equals(name)&&"666".equals(passwd)){
				System.out.println("��ϲ�㣬��½�ɹ�~");
				break;
			}
			chance--;
			System.out.println("�㻹��" + chance + "�ε�½����");
		}
	}
}