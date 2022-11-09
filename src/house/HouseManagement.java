package house;

import readwriteable.HouseReadWriteable;
import readwriteable.ReadWriteable;

import java.util.ArrayList;
import java.util.List;

public class HouseManagement {
    private List<House> houses;
    private static final String FILE_PATH_AVAILABLE = "available.csv";
    private static HouseManagement houseManagement = new HouseManagement();
    private ReadWriteable readWriteable = new HouseReadWriteable();

    public static HouseManagement getHouseManagement() {
        return houseManagement;
    }
    public HouseManagement() {
        houses = new ArrayList<>();
        readFile();
    }

    public void add(House h) {
        houses.add(h);
        saveFile();
    }

    public void remove(int id) {
        House searchHouse = searchById(id);
        if (searchHouse != null)
            houses.remove(searchHouse);
        saveFile();
    }

    public House searchById(int id) {
        for (House h: houses)
            if (h.getHouseId() == id)
                return h;
        return null;
    }

    public List<House> searchByDirections(String direction) {
        List<House> houseList = new ArrayList<>();
        for (House h: houses) {
            if (h.getDirection().equals(direction))
                houseList.add(h);
        }
        return houseList;
    }
    public void update(int id, House h) {
        House searchHouse = searchById(id);
        if (searchHouse != null) {
            searchHouse.setPrice(h.getPrice());
            searchHouse.setDirection(h.getDirection());
            searchHouse.setWidth(h.getWidth());
        }
        saveFile();
    }

    public List<Integer> checkAlmostOutOfStock() {
        List<Integer> outOfStocks = new ArrayList<>();
        for (House h:houses) {
            if (h.getQty() <= 5)
                outOfStocks.add(h.getHouseId());
        }
        return outOfStocks;
    }
    public String display() {
        String houseStr = "";
        for (House h: houses) {
            houseStr += h.toString() + "\n";
        }
        return houseStr;
    }

    public void saveFile() {
        readWriteable.save(FILE_PATH_AVAILABLE, houses);
    }

    private void readFile() {
        houses.clear();
        houses = readWriteable.read(FILE_PATH_AVAILABLE);
    }
}
