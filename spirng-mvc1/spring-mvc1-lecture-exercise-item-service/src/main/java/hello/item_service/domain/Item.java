package hello.item_service.domain;

import lombok.Data;

@Data
public class Item {
    Long id;
    String name;
    Integer price;
    Integer count;

    public Item(){}

    public Item(String itemName, Integer price, Integer count) {
        this.name = itemName;
        this.price = price;
        this.count = count;
    }

    public void setItemName(String  itemName){
        name = itemName;
    }

    public void setQuantity(Integer quantity){
        count = quantity;
    }
}
