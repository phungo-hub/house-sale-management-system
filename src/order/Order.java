package order;

import customer.Customer;
import customer.CustomerManagement;
import house.House;
import house.HouseManagement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    private HouseManagement houseManagement = HouseManagement.getHouseManagement();
    private int custId;
    private LocalDate buyDate;
    private static int count = 0;
    private int orderId;
    private double total;

    private Map<Integer, Integer> prodQty;

    public Order(int orderId, LocalDate buyDate, int custId, double total) {
        this.orderId = orderId;
        this.custId = custId;
        this.buyDate = buyDate;
        this.total = total;
        prodQty = new HashMap<>();
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public Map<Integer, Integer> getProdQty() {
        return prodQty;
    }
    public void setProdQty(int prodId, int qty) {
        prodQty.put(prodId, qty);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Order (int custId, LocalDate buyDate) {
        orderId = ++count;
        this.custId = custId;
        this.buyDate = buyDate;
        prodQty = new HashMap<>();
    }

    public void addProduct(int prodId, int qty) {
        prodQty.put(prodId, qty);
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public double getSubTotal(int prodId, int qty) {
        House h = houseManagement.searchById(prodId);
        double subTotal = 0;
        subTotal = h.getPrice() * qty;
        return subTotal;
    }
    public double getTotal() {
        double total = 0;
        for (Integer prodId: prodQty.keySet()) {
            total += getSubTotal(prodId, prodQty.get(prodId));
        }
        return total;
    }
    @Override
    public String toString() {
        Customer c = customerManagement.searchById(custId);
        String str = "";
        //1
        str += "Order ID: " + orderId + "- " + "Buy Date: " + buyDate + "- " +
                "Cust ID: " + custId + "- " + "CustName: " + c.getName() + "\n";
        //2 for
        for (Integer prodId: prodQty.keySet()) {
            House h = houseManagement.searchById(prodId);
            str += "Product Id: " + prodId + "-" + "Quantity: " +
                    prodQty.get(prodId) + " - Subtotal: " +
                    getSubTotal(prodId, prodQty.get(prodId));
        }
        //3: in total
        str += "\n" + "Total: " + getTotal();
        return str;
    }

    public String toFile() {
        //1, 1/1/2000, c001, p01, 3, p02, 5, p04, 3
        String out = "";
        out += orderId + "," + buyDate + "," + custId + "," + getTotal();
        for (Integer prodId: prodQty.keySet()) {
            out += "," + prodId + "," + prodQty.get(prodId);
        }
        return out;
    }
}
