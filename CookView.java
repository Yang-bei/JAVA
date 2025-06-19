package RestaurantSystem.view;
import RestaurantSystem.service.ChefService;
import RestaurantSystem.service.FoodService;
import RestaurantSystem.service.OrderService;
import RestaurantSystem.utils.Utility;
import java.util.*;

public class CookView {
    private boolean loop1 = true;
    private char key1 = ' ';

    public void listFood(FoodService service){
        System.out.println("--------------- 食 品 列 表 ---------------");
        for (FoodService.Food foodService : service.foodStock.values()) {
            System.out.println(foodService);
        }
        System.out.println("--------------- 食 品 列 表 ---------------");
    }


    public void cookMenu(FoodService foodService, ChefService chefService){
        Scanner sc1 = new Scanner(System.in);

        do {
            System.out.println("---------- 后 厨 管 理 模 块 ----------");
            System.out.println("1.仓库管理");
            System.out.println("2.菜单管理");
            System.out.println("3.订单管理");
            System.out.println("4.查看余额");
            System.out.println("5.查看用户");
            System.out.println("6.返回上一级");
            System.out.println();
            System.out.println(" 请 输 入 你 的 选 择：");
            key1 = Utility.readChar();
            switch (key1) {
                case '1':
                    new WarehouseManagementView().WarehouseManagementMenu(foodService);
                    break;
                case '2':
                    new MenuManagementView().MenuManageMenu(foodService);
                    break;
                case '3':
                    new OrderView().OrderMnue(new OrderService(), chefService, foodService);
                    break;
                case '4':

//                    customerView another = new customerView();
//                    another.initializePrice();
//                    double price = another.getCookprice();
                    System.out.println("您当前的余额为" + chefService.getBalance() + "元！");
                    break;
                case '5':

                    break;
                case '6':
                    loop1 = false;
                    break;
            }
        } while (loop1);
    }
}
