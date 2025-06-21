package RestaurantSystem.utils;
import RestaurantSystem.service.FoodService;
import RestaurantSystem.view.customerView;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class MyUtity {

    public static FoodService cookAndAddStock(FoodService foodService) {
        Map<Integer, FoodService.Food> foodStock = foodService.getFoodStock();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您想要炒的菜名的id:");
        int dishName = scanner.nextInt();
        if (!foodStock.containsKey(dishName)) {
            System.out.println("没有找到该id，请确认id是否正确。");
            return foodService;
        }
        int quantity;
        while (true) {
            try {
                System.out.println("请输入要炒的份数（必须大于0）:");
                quantity = scanner.nextInt();
                if (quantity <= 0) {
                    System.out.println("输入的份数必须大于0，请重新输入。");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("输入的数据类型不是整数，请重新输入。");
                scanner.nextLine();
            }
        }

        FoodService.Food food = foodStock.get(dishName);
        food.setStoke(food.getStoke() + quantity);
        foodStock.put(food.getId(),food);
        foodService.setFoodStock(foodStock);
        System.out.println("已成功增加库存，当前库存: " + food.getStoke());
        return foodService;
    }

    public static FoodService cookAndSubStock(FoodService foodService) {
        Map<Integer,FoodService.Food> foodStock = foodService.getFoodStock();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您想要扔的菜的id:");
        int dishName = scanner.nextInt();

        if (!foodStock.containsKey(dishName)) {
            System.out.println("没有找到该id，请确认id是否正确。");
            return foodService;
        }
        FoodService.Food food = foodStock.get(dishName);
        int quantity;
        while (true) {
            try {
                System.out.println("请输入要扔的份数");
                quantity = scanner.nextInt();
                if (quantity <= 0) {
                    System.out.println("您的输入有误，请重新输入。");
                } else if (quantity > food.getStoke()) {
                    System.out.println("库存里没有这么多");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("输入的数据类型不是整数，请重新输入。");
                scanner.nextLine();
            }
        }
        System.out.println("您真的要扔掉吗 y/n");
        char key_ = Utility.readChar();
        if ((key_ == 'y') || (key_ == 'Y') ) {
            food.setStoke(food.getStoke() - quantity);
            System.out.println("已扔掉，当前库存: " + food.getStoke());
        }
        return foodService;
    }
    public static FoodService revise(FoodService foodService) {
        Map<Integer, FoodService.Food> foodStock = foodService.getFoodStock();
        Scanner sc = new Scanner(System.in);

        boolean validinput = true;
        while (validinput) {
            int id = 0;
            boolean validinput1 = false;
            while (!validinput1) {
                try {
                    System.out.println("请输入您要修改的食品id");
                    id = sc.nextInt();
                    sc.nextLine();
                    validinput1 = true;
                } catch (InputMismatchException e) {
                    System.out.println("输入无效，请输入一个有效的选项");
                    sc.nextLine();
                }
            }
            FoodService.Food food = foodStock.get(id);
            if (foodStock.containsKey(id)){
                String name1 = null;
                System.out.println("---------------修改食品---------------");
                System.out.println("请输入食品名称：");
                name1 = sc.nextLine();

                double price = 0;
                boolean validinput2 = false;
                while (!validinput2) {
                    try {
                        System.out.println("请输入食品价格：");
                        price = sc.nextDouble();
                        sc.nextLine();
                        validinput2 = true;
                    } catch (InputMismatchException e) {
                        System.out.println("输入无效，请输入一个有效的选项");
                        sc.nextLine();
                    }
                }
                System.out.println("请输入描述：");
                String description = sc.nextLine();
                foodStock.put(id,new FoodService.Food(id,name1,price,description,food.getStoke()));
                System.out.println("您已经修改成功！");
                validinput = false;
            }
        }
        return foodService;
    }

    public static FoodService SubFood(FoodService foodService) {
        Map<Integer, FoodService.Food> foodStock = foodService.getFoodStock();
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("请输入您想删除的菜品id：");
            int id = sc.nextInt();
            System.out.println("您真的要删掉吗 y/n");
            char key_ = Utility.readChar();
            if ((key_ == 'y') || (key_ == 'Y') ) {
                foodStock.remove(id);
                System.out.println("删除成功!");
                loop = false;
            } else {
                loop = false;
            }
        }
        return foodService;
    }


    public static FoodService AddFood(FoodService foodService) {
        Map<Integer, FoodService.Food> foodStock = foodService.getFoodStock();
        Scanner sc = new Scanner(System.in);
        boolean loop1 = true;
        System.out.println("-------------------- 新 增 菜 单 --------------------");
        while (loop1) {
            int id = 0;
            try {
                System.out.println("请输入id：");
                 id = sc.nextInt();
                 sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("输入无效，请输入一个有效值:");
                sc.nextLine();
            }
            FoodService.Food food = foodStock.get(id);
            if (foodStock.containsKey(id)) {
                System.out.println("id重复了！");
            } else {
                boolean loop2 = true;
                while (loop2) {
                    System.out.println("请输入菜名：");
                    String name = sc.nextLine().trim(); // 读取菜名并去掉前后空格

                    // 检查菜名是否重复
                    boolean isDuplicate = false;
                    for (FoodService.Food food1 : foodStock.values()) {
                        if (food1.getName().equalsIgnoreCase(name)) { // 忽略大小写比较
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        System.out.println("菜名重复了，请重新输入！");
                    } else {
                        double price = 0;
                        try {
                            System.out.println("请输入价格：");
                            price = sc.nextDouble();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("输入无效，请输入一个有效值：");
                            sc.nextLine();
                        }
                        System.out.println("请输入描述：");
                        String description = sc.nextLine();
                        foodStock.put(id,new FoodService.Food(id,name,price,description,0));
                        System.out.println("添加成功！");
                        loop1 = false;
                        loop2 = false;
                    }
                }
            }
        }
        return foodService;
    }
}
