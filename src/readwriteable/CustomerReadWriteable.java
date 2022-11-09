package readwriteable;
import customer.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerReadWriteable implements ReadWriteable<Customer>{
    List<Customer> list = new ArrayList<>();
    @Override
    public List<Customer> read(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(lineHandle(line));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public void save(String file, List<Customer> list) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Customer h: list) {
                bw.write(h.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer lineHandle(String line)  {
        String[] str = line.split(",");
        return new Customer(Integer.parseInt(str[0]), str[1]);
    }
}
