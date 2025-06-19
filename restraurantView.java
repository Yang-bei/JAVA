package RestaurantSystem.view;

import RestaurantSystem.service.ChefService;
import RestaurantSystem.service.FoodService;
import RestaurantSystem.service.OrderService;
import RestaurantSystem.service.UserService;
import RestaurantSystem.utils.Utility;

import java.util.Scanner;

public class restraurantView extends customerView{
    private boolean loop = true;
    private boolean loop_ = true;
    private char key = ' ';

    public void mainMenu(FoodService foodService, OrderService orderService, UserService userService, ChefService chefService){
        Scanner sc = new Scanner(System.in);//你需要就可以传

        do {
            System.out.println("欢迎来到起点炒个菜，请选择你身份前面的数字：");
            System.out.println("1.厨师" + '\t' + "2.顾客" + '\t' + "3.退出");
            do {
                key = Utility.readChar();
                if (key != '1' && key != '2' && key != '3') {
                    System.out.println("该选项不存在，请重新输入：");
                    break;
                } else {
                    loop_ = false;
                    break;
                }
            } while (loop_);
            switch (key){
                case '1':
                    System.out.println("欢迎回厨！");
                    System.out.println("注意你有三次登入管理系统的机会");
                    int i = 3;
                    while (true) {
                        if (i == 0) {
                            i = 3;
                            System.out.println("三次机会用完，休眠5秒才能重新进入系统...");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("请输入账号：");
                            String account = sc.next();
                            System.out.println("请输入密码");
                            String password = sc.next();
                            if (account.equals("3") && password.equals("3")){
                                System.out.println("登入成功，欢迎大厨◝(⑅•ᴗ•⑅)◜..°♡！");
                                new CookView().cookMenu(foodService, chefService);
                                break;
                            } else {
                                i--;
                                System.out.println("账户或密码错误，你还有" + i + "次机会");
                            }
                        }
                    }
                    break;
                case '2':
                    new customerView().customerMenu(foodService,userService);
                    break;
                case '3':
                    loop = false;
                    break;
            }
        }while (loop);
    }
}
