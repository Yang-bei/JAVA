package RestaurantSystem.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public  class FoodService {
    public Map<Integer, Food> foodStock = new TreeMap<>();
    @SuppressWarnings({"all"})

    public FoodService() {
        foodStock = new TreeMap<>();
        foodStock.put(1,new Food(1,"招牌仙芋", 10, "加冰超好吃", 10));
        foodStock.put(2,new Food(2, "肥牛冒菜", 38, "配米饭好吃", 15));
        foodStock.put(3,new Food(3, "麻婆豆腐", 28, "味道非常棒", 5));
        foodStock.put(4,new Food(4, "王婆大虾", 60, "微辣刚刚好", 10));
    }

    public Map<Integer, Food> getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(Map<Integer, Food> foodStock) {
        this.foodStock = foodStock;
    }

    public Food getFoodByName(String name) {
        for (Food food : foodStock.values()) {
            if (food.getName().equals(name)) {
                return food;
            }
        }
        return null;
    }

    public static class Food implements Comparable<Food>{
        public static int getPrice;
        private int id;
        private String name;
        private double price;
        private String describe;
        private int stoke;


        public Food(int id, String name, double price, String describe, int stoke) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.describe = describe;
            this.stoke = stoke;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getStoke() {
            return stoke;
        }

        public void setStoke(int stoke) {
            this.stoke = stoke;
        }

        @Override
        public String toString() {
            return "id: " + id + "," + '\t' +
                    " 菜名: " + name + "," + '\t' +
                    " 价格: " + price + "," + '\t' + '\t' +
                    " 描述: " + describe + "," + '\t' +
                    " 库存: " + stoke;
        }
        @Override
        public int compareTo(Food other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Food food = (Food) o;
            return id == food.id && Double.compare(price, food.price) == 0 && stoke == food.stoke && Objects.equals(name, food.name) && Objects.equals(describe, food.describe);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, price, describe, stoke);
        }
    }
}

