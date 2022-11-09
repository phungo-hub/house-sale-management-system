package order;

import readwriteable.OrderReadWriteable;
import readwriteable.ReadWriteable;

import java.util.*;

public class OrderManagement {
    private static OrderManagement orderManagement = new OrderManagement();
    private final String FILE_PATH = "orders.csv";
    private ReadWriteable readWriteable = new OrderReadWriteable();

    private List<Order> orders;

    public static OrderManagement getOrderManagement() {
        return orderManagement;
    }

    public OrderManagement() {
        orders = new ArrayList<>();
        readFile();
        setCount();
    }
    public Order searchById(int id) {
        for (Order o: orders)
            if (o.getOrderId() == id)
                return o;
        return null;
    }
    public void updateProdQty(int id, int productId, int qty) {
        Order o1 = searchById(id);
        if (o1 != null) {
            Map<Integer,Integer> prodQty = o1.getProdQty();
            for (Integer prodId: prodQty.keySet()) {
                if (prodId == productId)
                    o1.setProdQty(prodId, qty);
            }
        }
        saveFile();
    }
    public void setCount() {
        int max = 0;
        for (Order o: orders) {
            if (o.getOrderId() > max)
                max = o.getOrderId();
        }
        Order.setCount(max);
    }

    public void add(Order o) {
        orders.add(o);
        saveFile();
    }

    public String display() {
        String out = "";
        for (Order o: orders)
            out += o.toString() + "\n";
        return out;
    }

    public List<Integer> checkMostBoughtHouseInAMonth(int month) {
        List<Order> newList = new ArrayList<>();
        Map<Integer, Integer> productBoughtMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (Order o:orders)
            if (o.getBuyDate().getMonthValue() == month)
                newList.add(o);
        for (Order o: newList) {
            for (Integer prodId: o.getProdQty().keySet()) {
                if (productBoughtMap.containsKey(prodId)) {
                    int value = productBoughtMap.get(prodId) + o.getProdQty().get(prodId);
                    productBoughtMap.put(prodId, value);
                } else {
                    productBoughtMap.put(prodId, o.getProdQty().get(prodId));
                }
            }
        }
        int max = Collections.max(productBoughtMap.values());
        for (Map.Entry<Integer, Integer> entry: productBoughtMap.entrySet()) {
            if (entry.getValue() == max)
                result.add(entry.getKey());
        }
        return result;
    }

    public List<Integer> checkLeastBoughtHouse() {
        Map<Integer, Integer> productQty = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (Order o: orders)
            for (Integer prodId: o.getProdQty().keySet()) {
                if (productQty.containsKey(prodId)) {
                    int qty = o.getProdQty().get(prodId) + productQty.get(prodId);
                    productQty.put(prodId, qty);
                } else productQty.put(prodId, o.getProdQty().get(prodId));
            }
        int min = Collections.min(productQty.values());
        for (Map.Entry<Integer, Integer> entry : productQty.entrySet()) {
            if (min == entry.getValue())
                result.add(entry.getKey());
        }
        return result;
    }

    public void saveFile() {
        readWriteable.save(FILE_PATH, orders);
    }
    public void readFile() {
        orders.clear();
        orders = readWriteable.read(FILE_PATH);
    }
}
