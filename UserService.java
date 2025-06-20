package RestaurantSystem.service;

import java.text.SimpleDateFormat;
import java.util.*;

public  class UserService {
    public  Map<String,UserService> userMap = new TreeMap<>();

    public UserService() {
        userMap = new TreeMap<>();

    }


    public  Map<String, UserService> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, UserService> userMap) {
        this.userMap = userMap;
    }

    public static class User extends UserService {
        private  String methodName;
        private double amount;
        private  int vip;
        private List<String> rechargeRecords;
        private List<String> consumptionRecords;

        public User(String methodName, double amount, int vip) {
            this.methodName = methodName;
            this.amount = amount;
            this.vip = vip;
            this.rechargeRecords = new ArrayList<>();
            this.consumptionRecords = new ArrayList<>();
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public  int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public void addRechargeRecord(double amount) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String record = "充值金额: " + amount + ", 时间: " + sdf.format(new Date());
            rechargeRecords.add(record);
        }

        public void addConsumptionRecord(String record) {
            consumptionRecords.add(record);
        }

        public List<String> getRechargeRecords() {
            return rechargeRecords;
        }

        public List<String> getConsumptionRecords() {
            return consumptionRecords;
        }

        @Override
        public String toString() {
            return "姓名='" + methodName + '\'' +
                    ", 余额=" + amount +
                    ", VIP状态: " + (vip == 1 ? "VIP" : "普通用户") +
                    ", 充值记录: " + rechargeRecords +
                    ", 消费记录: " + consumptionRecords;
        }
    }
}
