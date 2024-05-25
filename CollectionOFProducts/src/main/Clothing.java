package main;

public class Clothing extends Product{

    private String size;
    private String color;
    private String material;

    public Clothing(String name, double price, int quantity, String size, String color, String material) {
        super(name, price, quantity);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    @Override
    public double CalculateBill(double price, int quantity) {
        return super.CalculateBill(price,quantity)*0.10;
    }

    @Override
    public String toString() {
        return super.toString() +
                "size=" + size +"\t"+
                "color=" + color + "\t"+
                "material=" + material +"\n";
    }
}
