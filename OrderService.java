package RestaurantSystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderService {
//    public Map<String, Order> orderMap = new TreeMap<>();

    private static final OrderService instance = new OrderService();

    private Map<String,Order> orderMap;

    public OrderService() {
        orderMap = new HashMap<>();
    }

    public static OrderService getInstance(){
        return instance;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
    }

    public void addOrderMap(Order order) {
        orderMap.put(order.getUuid(),order);
    }


    public Order processOrder(String uuid) {
        return orderMap.remove(uuid);
    }
    public Map<String, Order> getPendingOrders() {
        Map<String, Order> pendingOrders = new TreeMap<>();
        for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
            if ("未处理".equals(entry.getValue().getState())) {
                pendingOrders.put(entry.getKey(), entry.getValue());
            }
        }
        return pendingOrders;
    }

    // 根据 UUID 获取订单
    public Order getOrder(String uuid) {
        return orderMap.get(uuid);
    }

    // 更新订单状态
    public void updateOrder(Order order) {
        orderMap.put(order.getUuid(), order);
    }


    public static class Order{
        private int id;
        private String uuid;
        private String food;
        private int quantity;
        private double pay;
        private String time;
        private String vip;
        private String state;

        public Order(int id,String uuid,String food,int quantity,double pay,
                     String time,String vip,String state) {
            this.id = id;
            this.uuid = uuid;
            this.food = food;
            this.quantity = quantity;
            this.pay = pay;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.time = sdf.format(new Date());
            this.vip = vip;
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity() {
            this.quantity = quantity;
        }

        public double getPay() {
            return pay;
        }

        public void setPay(double pay) {
            this.pay = pay;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "id=" + id +
                    ", 编码='" + uuid + '\'' +
                    ", 订单='" + food + '\'' +
                    ", 份数='" + quantity +
                    ", 支付=" + pay +
                    ", 点餐时间='" + time + '\'' + "," + vip + state;
        }        }


}