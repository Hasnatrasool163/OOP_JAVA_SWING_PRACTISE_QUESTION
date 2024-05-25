package main;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static java.lang.System.*;

public class Test {
    static Product[] p;
    public static void main(String[] args) {
        p = new Product[5];
        p[0] = new Food("bread", 120, 5, LocalDate.now(),LocalDate.of(2024,6,5));
        p[1] = new Food("juice", 160, 3, LocalDate.now(),LocalDate.of(2024,7,31));
        p[2] = new Clothing("jeans",1800,6,"Large","Blue","Cotton");
        p[3] = new Electronics("iron",4500,2,100,"V1","Dawlance");
        p[4] = new Electronics("lamp",1200,1,50,"v5","Henta's");


        Arrays.sort(p);
        out.println("List of Products sorted by price");
//        out.println(Arrays.toString(p));
        // normal writing to file

//        try{
//            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("products.dat")));
//            writer.write(Arrays.toString(p));
//            writer.close();
//
//        }
//         catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // using serialize interface to save data

        try {
            FileOutputStream fileStream = new FileOutputStream("product1.dat");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream); // object
            objectStream.writeObject(p); // serialize and write
            objectStream.close(); // close object
            fileStream.close(); // close
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // using serialize interface to read data  again

        try {
            FileInputStream fileStream = new FileInputStream("product1.dat");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            Product[] p = (Product[]) objectStream.readObject();
            objectStream.close();
            fileStream.close();
            System.out.println(Arrays.toString(p)); // Print the deserialized array
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
