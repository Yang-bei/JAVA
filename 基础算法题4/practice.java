public class practice{
	public static void main(String[] args){
		Book book = new Book("Ц������",300);
		book.info();
		book.updatePrice();
		book.info();
	}
}
class Book{
	String name;
	double price;
	public Book(String name,double price){
		this.name = name;
		this.price = price;
	}
	public void updatePrice(){
		if(this.price > 150){
			this.price = 150;
		}else if(this.price > 100){
			this.price = 100;
		}
	}
	public void info(){
		System.out.println("����=" + this.name + " " + "�۸�=" + this.price);
	}
}