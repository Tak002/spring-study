package hello.core.singleton;

public class StatefulService {
    public int price;

    public void order(String name, int price) {
        this.price = price;
        System.out.println("name: "+ name + " price: "+ price);
    }

    public int getPrice() {
        return price;   
    }
}
