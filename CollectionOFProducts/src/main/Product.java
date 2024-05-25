package main;

import java.io.Serializable;

public class Product implements Comparable<Product> , Serializable {

    private String name;
    private double price;
    private int quantity;


    public double CalculateBill(double price, int quantity){
        return price * quantity;
    }
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }


    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  "\n"+
                "name=" + name +"\t"+
                "price=" + price +"\t"+
                "quantity=" + quantity +"\n";
    }
}
