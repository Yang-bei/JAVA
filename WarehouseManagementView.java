package RestaurantSystem.view;
import RestaurantSystem.service.FoodService;
import RestaurantSystem.utils.MyUtity;
import RestaurantSystem.utils.Utility;
import java.util.Scanner;

public class WarehouseManagementView {
    private boolean loop1_1 = true;
    private boolean loop_ = true;
    private char s = ' ';
    private char key1_1 = ' ';


    public void WarehouseManagementMenu(FoodService foodService) {
        Scanner sc1_1 = new Scanner(System.in);
        do {
            System.out.println("---------- (●'◡'●) 仓 库 管 理 模 块 ----------");
            System.out.println("1.查看库存");
            System.out.println("2.炒菜");
            System.out.println("3.扔菜");
            System.out.println("4.返回上一级");
            System.out.println();
            System.out.println(" 请 输 入 您 的 选 择：");
            key1_1 = Utility.readChar();
            switch (key1_1) {
                case '1':
                    new CookView().listFood(foodService);
                    System.out.println("返回请输入1");

                    do {
                        s = Utility.readChar();
                        if (s != '1') {
                            System.out.println("您输入错误，请重新输入：");
                        } else {
                            loop_ = false;
                        }
                    } while (loop_);
                    break;
                case '2':
                    new CookView().listFood(foodService);
                    foodService = MyUtity.cookAndAddStock(foodService);
                    break;
                case '3':
                    new CookView().listFood(foodService);
                    foodService = MyUtity.cookAndSubStock(foodService);
                    break;
                case '4':
                    loop1_1 = false;
                    break;
            }
        }while (loop1_1);
    }
}

