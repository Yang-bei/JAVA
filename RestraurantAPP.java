package RestaurantSystem.view;

import RestaurantSystem.service.ChefService;
import RestaurantSystem.service.FoodService;
import RestaurantSystem.service.OrderService;
import RestaurantSystem.service.UserService;

public class RestraurantAPP {
    public static void main(String[] args) {
        ChefService chefService = new ChefService();
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        FoodService foodService = new FoodService();
        //你看下foodService的传的过程，同用的原理
        new restraurantView().mainMenu(foodService,orderService,userService, chefService);
        System.out.println("正在退出系统，谢谢光临~");
    }
}
