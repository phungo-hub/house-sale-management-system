package readwriteable;

import house.House;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HouseReadWriteable implements ReadWriteable<House>{
    List<House> list = new ArrayList<>();
    @Override
    public List<House> read(String file) {
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
    public void save(String file, List<House> list) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (House h: list) {
                bw.write(h.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public House lineHandle(String line)  {
        String[] str = line.split(",");
        return new House(Integer.parseInt(str[0]), Double.parseDouble(str[1]), Double.parseDouble(str[2]), Integer.parseInt(str[3]), str[4]);
     }
}
