package RestaurantSystem.view;

import RestaurantSystem.service.FoodService;
import RestaurantSystem.service.OrderService;
import RestaurantSystem.service.ChefService;
import RestaurantSystem.utils.Utility;

import java.util.Map;
import java.util.Scanner;

public class OrderView extends customerView{
    boolean loop = true;
    char key = ' ';

    OrderService orderService = OrderService.getInstance();
    Map<String, OrderService.Order> orderMap = orderService.getOrderMap();

    public void OrderMnue(OrderService orderService, ChefService chefService, FoodService foodService) {
        do {
            System.out.println("-------------------------订单管理-------------------------");
            System.out.println("1.查看订单");
            System.out.println("2.处理订单");
            System.out.println("3.返回上一级");
            System.out.println();
            System.out.println("请输入您的选择：");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    orderService.getOrderMap();
                    break;
                case '2':
                    showPendingOrders(orderService);
                    processOrder(this.orderService, chefService, foodService);
                    break;
                case '3':
                    loop = false;
                    break;
                default:
                    System.out.println("输入无效，请重新输入：");
            }
        } while (loop);
    }



    private void showPendingOrders(OrderService orderService) {

        Map<String, OrderService.Order> pendingOrders = orderService.getPendingOrders();
        if (pendingOrders.isEmpty()) {
            System.out.println("当前没有未处理订单！");
        } else {
            System.out.println("---------- 未处理订单列表 ----------");
            for (OrderService.Order order : pendingOrders.values()) {
                System.out.println(order);
            }
            System.out.println("----------------------------------");
        }
    }

    // 处理订单
    private void processOrder(OrderService orderService, ChefService chefService, FoodService foodService) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要处理的订单编号：");
        String uuid = sc.nextLine();

        System.out.println("Current orderMap:" + orderService.getOrderMap());

        // 获取订单
        OrderService.Order order = orderService.getOrder(uuid);
        if (order == null || !"已处理".equals(order.getState())) {
            System.out.println("未找到该订单或订单已处理！");
            return;
        }

        // 检查库存是否足够
        if (checkStock(foodService, order)) {
            // 扣除库存
            deductStock(foodService, order);
            // 更新厨师余额
            chefService.addBalance(order.getPay());
            // 更新订单状态为已处理
            order.setState("已处理");
            orderService.updateOrder(order); // 更新订单状态
            System.out.println("订单处理成功！厨师余额增加：" + order.getPay());
        } else {
            System.out.println("库存不足，订单处理失败！");
        }
    }

    // 检查库存是否足够
    private boolean checkStock(FoodService foodService, OrderService.Order order) {
        String[] foodItems = order.getFood().split(", ");
        for (String item : foodItems) {
            String[] parts = item.split(" x ");
            if (parts.length != 2) {
                System.out.println("订单格式错误，无法处理！");
                return false;
            }
            String foodName = parts[0];
            int requiredQuantity = Integer.parseInt(parts[1]);
            FoodService.Food food = foodService.getFoodByName(foodName);
            if (food == null || food.getStoke() < requiredQuantity) {
                System.out.println("菜品 " + foodName + " 库存不足！");
                return false;
            }
        }
        return true;
    }

    // 扣除库存
    private void deductStock(FoodService foodService, OrderService.Order order) {
        String[] foodItems = order.getFood().split(", ");
        for (String item : foodItems) {
            String[] parts = item.split(" x ");
            String foodName = parts[0];
            int requiredQuantity = Integer.parseInt(parts[1]);
            FoodService.Food food = foodService.getFoodByName(foodName);
            if (food != null) {
                food.setStoke(food.getStoke() - requiredQuantity);
            }
        }
    }

}
