package RestaurantSystem.service;

import java.util.List;

public  class ListService {
    private List<String> itemList;

    public ListService(List<String> itemList) {
        this.itemList = itemList;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return itemList + "\n";
    }
}
