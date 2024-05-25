package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CalculateBill extends JFrame {


    JComboBox<String>  productsList;
    JRadioButton food,Clothing,electronics;
    ButtonGroup btn;
    JTextField quantityField;
    Product[] p;
    JLabel Bill;

    public CalculateBill(){
        setTitle("CalculateBill");
        setSize(380,350);
        setLocation(350,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel category = new JLabel("Category");
        category.setBounds(20,50,80,20);
        add(category);
        food = new JRadioButton("Food");
        food.setBounds(90,50,60,20);
        add(food);
        Clothing = new JRadioButton("Clothing");
        Clothing.setBounds(160,50,100,20);
        add(Clothing);
        electronics = new JRadioButton("Electronics");
        electronics.setBounds(260,50,100,20);
        add(electronics);
        btn = new ButtonGroup();
        btn.add(food);
        btn.add(Clothing);
        btn.add(electronics);

        food.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        Clothing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        electronics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        JLabel product = new JLabel("Product");
        product.setBounds(20,90,80,20);
        add(product);
        productsList = new JComboBox<>();
        productsList.setBounds(100,90,100,20);
        add(productsList);

        JLabel quantity = new JLabel("Quantity");
        quantity.setBounds(20,150,100,20);
        add(quantity);

        quantityField = new JTextField();
        quantityField.setBounds(100,150,100,20);
        add(quantityField);

        JButton calButton = new JButton("CalculateBill");
        calButton.setBounds(80,190,150,20);
        add(calButton);

        Bill = new JLabel("");
        Bill.setBounds(80,220,150,20);
        add(Bill);

        loadData();

        calButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
                String quantity = quantityField.getText();
                String productName = productsList.getSelectedItem().toString();
                Product selectedProduct = findProductByName(productName);
                if (selectedProduct != null) {
                    double bill = calculateBill(selectedProduct, quantityField.getText());
                    double bill1 = Double.parseDouble(quantityField.getText())*bill;
                    JOptionPane.showMessageDialog(null, "Bill: " + bill1);
                    Bill.setText("Total bill= " + bill1);
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found!");
                }
            }
        });

        setVisible(true);

    }

    // simple efficient way

    public void loadData(){
        try {
            FileInputStream fileStream = new FileInputStream("product1.dat");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            p = (Product[]) objectStream.readObject();
            objectStream.close();
            fileStream.close();

            String selectedCategory = getSelectedCategory();
            Product[] filteredProducts = new Product[p.length];
            int index = 0;
            for (Product product : p) {
                if (selectedCategory == null || product.getClass().getSimpleName().equals(selectedCategory)) {
                    filteredProducts[index] = product;
                    index++;
                }
            }

            productsList.removeAllItems();
            for (Product product : filteredProducts) {
                if (product != null) {
                    productsList.addItem(product.getName());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    // simple long way using for loop

//    public void loadData(){
//        try {
//            FileInputStream fileStream = new FileInputStream("product1.dat");
//            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
//            p = (Product[]) objectStream.readObject();
//            objectStream.close();
//            fileStream.close();
//
//            Product[] filteredProducts;
//            if (food.isSelected()) {
//                filteredProducts = new Product[p.length];
//                int index = 0;
//                for (Product product : p) {
//                    if (product instanceof Food) {
//                        filteredProducts[index] = product;
//                        index++;
//                    }
//                }
//            } else if (Clothing.isSelected()) {
//                filteredProducts = new Product[p.length];
//                int index = 0;
//                for (Product product : p) {
//                    if (product instanceof Clothing) {
//                        filteredProducts[index] = product;
//                        index++;
//                    }
//                }
//            } else if (electronics.isSelected()) {
//                filteredProducts = new Product[p.length];
//                int index = 0;
//                for (Product product : p) {
//                    if (product instanceof Electronics) {
//                        filteredProducts[index] = product;
//                        index++;
//                    }
//                }
//            } else {
//                filteredProducts = p;
//            }
//
//            productsList.removeAllItems();
//            for (Product product : filteredProducts) {
//                if (product != null) {
//                    productsList.addItem(product.getName());
//                }
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // lambada expression

//    public void loadData(){
//        try {
//            FileInputStream fileStream = new FileInputStream("product1.dat");
//            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
//            p = (Product[]) objectStream.readObject();
//            objectStream.close();
//            fileStream.close();
//
//            Product[] filteredProducts;
//            if (food.isSelected()) {
//                filteredProducts = Arrays.stream(p)
//                        .filter(product -> product instanceof Food)
//                        .toArray(Product[]::new);
//            } else if (Clothing.isSelected()) {
//                filteredProducts = Arrays.stream(p)
//                        .filter(product -> product instanceof Clothing)
//                        .toArray(Product[]::new);
//            } else if (electronics.isSelected()) {
//                filteredProducts = Arrays.stream(p)
//                        .filter(product -> product instanceof Electronics)
//                        .toArray(Product[]::new);
//            } else {
//                filteredProducts = p;
//            }
//
//            productsList.removeAllItems();
//            for (Product product : filteredProducts) {
//                productsList.addItem(product.getName());
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }



    private String getSelectedCategory() {
        if (food.isSelected()) {
            return "Food";
        } else if (Clothing.isSelected()) {
            return "Clothing";
        } else if (electronics.isSelected()) {
            return "Electronics";
        } else {
            return null;
        }
    }


    public Product findProductByName(String name) {
        for (Product product : p) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public double calculateBill(Product product, String quantity) {
        return product.CalculateBill(product.getPrice(), Integer.parseInt(quantity));
    }


    public static void main(String[] args) {

        new CalculateBill().setVisible(true);
    }
}
