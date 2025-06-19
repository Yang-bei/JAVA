package RestaurantSystem.view;

import RestaurantSystem.service.FoodService;
import RestaurantSystem.utils.MyUtity;
import RestaurantSystem.utils.Utility;

public class MenuManagementView {
    public boolean loop1_2 = true;
    public boolean loop1_3 = true;
    public char key1_2 = ' ';
    public void MenuManageMenu(FoodService foodService) {
        do {
            System.out.println("---------- (●'◡'●) 菜 单 模 块 ----------");
            System.out.println("1.查看菜单");
            System.out.println("2.修改菜单");
            System.out.println("3.删除特色菜");
            System.out.println("4.增加特色菜");System.out.println("5.返回上一级");
            System.out.println();
            System.out.println(" 请 输 入 您 的 选 择：");
            key1_2 = Utility.readChar();
            switch (key1_2) {
                case '1':
                    new CookView().listFood(foodService);
                    break;
                case '2':
                    new CookView().listFood(foodService);
                    foodService = MyUtity.revise(foodService);
                    break;
                case '3':
                    new CookView().listFood(foodService);
                    foodService = MyUtity.SubFood(foodService);
                    break;
                case '4':
                    new CookView().listFood(foodService);
                    foodService = MyUtity.AddFood(foodService);
                    break;
                case '5':
                    loop1_2 = false;
                    break;
            }
        } while (loop1_3);
    }
}
