package main;

import java.time.LocalDate;
public class Food extends Product{

    private LocalDate manufacturingdDate;
    private LocalDate ExpiryDate;

    public Food(String name, double price, int quantity, LocalDate manufacturingdDate, LocalDate expiryDate) {
        super(name, price, quantity);
        this.manufacturingdDate = manufacturingdDate;
        ExpiryDate = expiryDate;
    }

    @Override
    public double CalculateBill(double price, int quantity) {
        return super.CalculateBill(price,quantity)*0.5;
    }

    public LocalDate getManufacturingdDate() {
        return manufacturingdDate;
    }

    public void setManufacturingdDate(LocalDate manufacturingdDate) {
        this.manufacturingdDate = manufacturingdDate;
    }

    public LocalDate getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        ExpiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return super.toString()+
                "manufacturingDate=" + manufacturingdDate +"\t"+
                "ExpiryDate=" + ExpiryDate +"\n";
    }
}
