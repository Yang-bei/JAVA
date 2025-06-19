package RestaurantSystem.view;
import RestaurantSystem.service.FoodService;
import RestaurantSystem.service.OrderService;
import RestaurantSystem.service.UserService;
import RestaurantSystem.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.*;


public class customerView {
    private double cookprice;
    private customerView price;

    public void initializePrice() {
        price = new customerView();
        price.setCookprice(10000.0 + getCookprice());
    }

    public customerView getPrice() {
        return price;
    }

    public double getCookprice() {
        return cookprice;
    }

    public void setCookprice(double cookprice) {
        this.cookprice = cookprice;
    }


    private boolean loop2 = true;
    private boolean loop3 = true;
    private boolean loop4 = true;
    private boolean loop5 = true;
    private boolean loop6 = true;
    private boolean loop2_1 = true;
    private char key2 = ' ';
    private char key2_1 = ' ';


    public void customerMenu(FoodService foodService, UserService userService){
//        map= (HashMap) foodService.getFoodStock();
        Map<String, UserService> userMap = userService.getUserMap();
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        System.out.println("请输入你的用户名：");
        String methodName = sc.next();
        if (methodName != null && !methodName.trim().isEmpty()) {
            UserService.User user = (UserService.User) userMap.get(methodName);
            if (user != null) {

                if (user.getVip() == 0) {
                    System.out.println("欢迎老用户到来");
                } else if (user.getVip() == 1) {
                    System.out.println("欢迎VIP用户到来");
                }
            } else {
                System.out.println("欢迎新用户到来");
                userMap.put(methodName, new UserService.User(methodName, 0, 0));
            }
        } else {
            System.out.println("用户名不能为空");
        }

        do {
            System.out.println("---------- 顾 客 模 板 ----------");
            System.out.println("1.查看菜单");
            System.out.println("2.点菜");
            System.out.println("3.充钱");
            System.out.println("4.注册VIP用户");
            System.out.println("5.查看个人信息");
            System.out.println("6.返回上一级");
            System.out.println();
            System.out.println(" 请 输 入 你 的 选 择：");
            key2 = Utility.readChar();
            switch (key2) {
                case '1':
                    new CookView().listFood(foodService);
                    System.out.println("返回请输入1");
                    key2_1 = Utility.readChar();
                    if (key2_1 == '1') {
                        break;
                    }  else {
                        do {
                            System.out.println("您的输入错误，请重新输入：");
                            key2_1 = Utility.readChar();
                            if (key2_1 == '1') {
                                loop2_1 = false;
                            }
                        } while (loop2_1);
                        break;
                    }
                case '2':
                    new CookView().listFood(foodService);
                    double money = 0;
                    double pay = 0;
                    int id = 0;
                    Map<String,Integer> foodQuantityMap = new HashMap<>();

                    while (loop6) {
                        boolean asd = true;
                        while (asd) {
                            try {
                                System.out.println("请输入菜名的id：");
                                id = sc.nextInt();
                                asd = false;
                            } catch (InputMismatchException e) {
                                System.out.println("您的输入不正确，请重新输入：");
                                sc.nextLine();
                            }
                        }

                        sc.nextLine();
                        if(foodService.getFoodStock().containsKey(id)){
//                            FoodService foodService1 = (FoodService) map1.get(id);
                            int age=0;
                            loop3 = true;
                            while(loop3) {
                                boolean validInput3 = false;
                                while (!validInput3) {
                                    try {
                                        System.out.print("请输入购买的数量：");
                                        age = sc.nextInt();
                                        validInput3 = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("输入无效，请输入一个有效的选项");
                                        sc.nextLine(); // 清除无效输入
                                    }
                                }
                                UserService.User user = (UserService.User) userMap.get(methodName);
                                if (age > 0) {
                                    String foodName = foodService.getFoodStock().get(id).getName();
                                    foodQuantityMap.put(foodName,foodQuantityMap.getOrDefault(foodName,0) + age);
                                    money = age * foodService.getFoodStock().get(id).getPrice();
                                    pay = pay + money;
                                } else {
                                    System.out.println("您输入的数字应该大于零");
                                }
                                System.out.println("请问您是否还要继续购买？（Y/N）");
                                String key_ = null;
                                while (true) {
                                    sc.nextLine();
                                    key_ = sc.nextLine();
                                    if ("y".equals(key_) || "Y".equals(key_)) {
                                        loop3 = false;
                                        break;
                                    } else if ( "n".equals(key_) || "N".equals(key_)) {
                                        if (user.getVip() == 0){
                                            System.out.println("下单成功，您的购物车如下：");
                                            System.out.println("正在付款，请稍等...");
                                            if (user.getAmount() >= pay) {
                                                user.setAmount(user.getAmount() - pay);
                                                setCookprice(getCookprice() + pay);
                                                System.out.println("...付款成功");
                                                    String uuid = UUID.randomUUID().toString();
                                                    StringBuffer foodDetails = new StringBuffer();
                                                    for (Map.Entry<String,Integer> entry : foodQuantityMap.entrySet()) {
                                                        foodDetails.append(entry.getKey()).append("*").append(entry.getValue()).append(", ");
                                                    }
                                                    foodDetails.setLength(foodDetails.length() - 2);
                                                    OrderService.Order order = new OrderService.Order(id, uuid, foodDetails.toString(),
                                                            foodQuantityMap.values().stream().mapToInt(Integer::intValue).sum(), pay,
                                                            sdf.format(new Date()), user.getVip() == 1 ? "【VIP用户】" : "【普通用户】", "未处理");
                                                    OrderService orderService = OrderService.getInstance();
                                                    orderService.addOrderMap(order);

                                                    String consumptionRecord = "点菜：" + foodDetails + ", 支付"
                                                            + pay + ", 时间：" + sdf.format(new Date());
                                                    user.addConsumptionRecord(consumptionRecord);
                                                    System.out.println(order);


                                            } else {
                                                System.out.println("...付款失败，您的余额不足");
                                            }
                                        } else if (user.getVip() == 1){
                                            System.out.println("下单成功，您的购物车如下：");
                                            System.out.println("正在付款，请稍等...");
                                            pay = pay * 0.5;
                                            if (user.getAmount() >= pay) {
                                                user.setAmount(user.getAmount() - pay);
                                                setCookprice(getCookprice() + pay);
                                                System.out.println("...付款成功");

                                                String uuid = UUID.randomUUID().toString();
                                                StringBuffer foodDetails = new StringBuffer();
                                                for (Map.Entry<String,Integer> entry : foodQuantityMap.entrySet()) {
                                                    foodDetails.append(entry.getKey()).append("*").append(entry.getValue()).append(", ");
                                                }
                                                foodDetails.setLength(foodDetails.length() - 2);
                                                OrderService.Order order = new OrderService.Order(id,uuid,foodDetails.toString(),
                                                        foodQuantityMap.values().stream().mapToInt(Integer::intValue).sum(),pay,
                                                        sdf.format(new Date()),user.getVip() == 1 ? "【VIP用户】" : "【普通用户】","未处理");

                                                String consumptionRecord = "点菜：" + foodDetails + ", 支付"
                                                        + pay + ", 时间：" + sdf.format(new Date()) + order.getState();

                                                user.addConsumptionRecord(consumptionRecord);
                                                OrderService orderService = OrderService.getInstance();
                                                orderService.addOrderMap(order);
                                                System.out.println("当前 orderMap 内容：" + orderService.getOrderMap());
                                                System.out.println(order);
                                            }
                                        }
                                        loop3 = false;
                                        loop6 = false;
                                        break;
                                    } else {
                                        System.out.println("您的输入错误，请重新输入：");
                                    }
                                }
                            }
                        }
                    }
                    break;
                case '3':
                    UserService.User user = (UserService.User) userMap.get(methodName);
                    double recharge = 0;
                    do {
                        try {
                            System.out.println("请输入充的钱数（限制冲入的钱数在0~500元范围之间）");
                            recharge = sc.nextInt();
                            if (recharge <= 0 || recharge > 500) {
                                System.out.println("请输入正确的范围！");
                                sc.nextLine();
                            } else {
                                user.setAmount(user.getAmount() + recharge);
                                System.out.println("充值成功！");
                                String rechargeRecord = "充值金额: " + recharge + ", 时间: " + sdf.format(new Date());
                                loop4 = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("请输入正确的格式！");
                            sc.nextLine();
                        }
                    } while (loop4);
                    break;
                case '4':
                    String key__ = null;
                    user = (UserService.User) userMap.get(methodName);
                    do {
                        if (user.getVip() == 1) {
                            System.out.println("您已经是VIP了！");
                        } else {
                            System.out.println("您真的要注册VIP吗？(Y/N) (注册需花费200元)");
                            sc.nextLine();
                            key__ = sc.nextLine();
                            if ( "y".equals(key__) || "Y".equals(key__)) {
                                if (user.getAmount() >= 200) {
                                    user.setAmount(user.getAmount() - 200);
                                    user.setVip(1);
                                    System.out.println("正在授权中...");
                                    System.out.println("授权成功！");
                                    loop5 = false;
                                } else {
                                    System.out.println("您的余额不足...");
                                }
                            } else if ("n".equals(key__) || "N".equals(key__)) {
                                loop5 = false;
                            } else {
                                System.out.println("请输入正确的选项(Y/N)");
                            }
                        }
                    } while (loop5);
                    break;
                case '5':
                    user = (UserService.User) userMap.get(methodName);
                    System.out.println("用户名：" + user.getMethodName());
                    System.out.println("余额：" + user.getAmount());
                    System.out.println("身份：" + (user.getVip() == 1 ? "VIP用户" : "普通用户"));
                    System.out.println("充值记录：" + user.getRechargeRecords());
                    System.out.println("消费记录：" + user.getConsumptionRecords());
                    System.out.println();
                    System.out.println("返回请输入1");
                    sc.nextLine();
                    int a = 0;
                    do {
                        try {
                            a = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("您输入的类型错误，请输入数字1：");
                        }
                        if (a == 1) {
                            break;
                        } else {
                            System.out.println("想返回输入1哦");
                        }
                    } while (true);
                    break;
                case '6':
                    loop2 = false;
                    break;
            }
        } while (loop2);
    }
}
