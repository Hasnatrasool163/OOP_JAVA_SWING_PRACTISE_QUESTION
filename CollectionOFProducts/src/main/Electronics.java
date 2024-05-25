package main;

public class Electronics extends Product{

    private int storageCapacity;
    private String brand;
    private String model;

    public Electronics(String name, double price, int quantity, int storageCapacity, String model, String brand) {
        super(name, price, quantity);
        this.storageCapacity = storageCapacity;
        this.model = model;
        this.brand = brand;
    }

    @Override
    public double CalculateBill(double price, int quantity) {
        return super.CalculateBill(price,quantity)*0.25;
    }

    @Override
    public String toString() {
        return super.toString()+
                "storageCapacity=" + storageCapacity +"\t"+
                "brand=" + brand + "\t"+
                "model=" + model + "\n";
    }
}
