package readwriteable;

import order.Order;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderReadWriteable implements ReadWriteable<Order> {
    @Override
    public List<Order> read(String file) {
        List<Order> array = new ArrayList<>();
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                array.add(lineHandle(line));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return array;
    }

    @Override
    public void save(String file, List<Order> list) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Order o: list) {
                bw.write(o.toFile());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Order lineHandle(String line) {
        String[] arr = line.split(",");
        Order o1 = new Order(Integer.parseInt(arr[0]), LocalDate.parse(arr[1]), Integer.parseInt(arr[2]), Double.parseDouble(arr[3]));
        for (int i = 4; i < arr.length; i+=2) {
            o1.addProduct(Integer.parseInt(arr[i]), Integer.parseInt(arr[i+1]));
        }
        return o1;
    }
}
