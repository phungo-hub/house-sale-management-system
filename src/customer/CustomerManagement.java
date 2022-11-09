package customer;

import readwriteable.CustomerReadWriteable;
import readwriteable.ReadWriteable;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagement {
    List<Customer> customers;
    private ReadWriteable readWriteable = new CustomerReadWriteable();
    private final String FILE_PATH = "customers.csv";
    private static CustomerManagement customerManagement = new CustomerManagement();
    public static CustomerManagement getCustomerManagement() {
        return customerManagement;
    }
    public CustomerManagement() {
        customers = new ArrayList<>();
        readFile();
    }

    public void add(Customer c) {
        customers.add(c);
        saveFile();
    }
    public Customer searchById(int id) {
        for (Customer c: customers)
            if (c.getCustomerId() == id)
                return c;
        return null;
    }

    public List<Customer> searchByName(String name) {
        List<Customer> list = new ArrayList<>();
        for (Customer c: customers)
            if (c.getName().equals(name)) {
                list.add(c);
            }
        return list;
    }

    public void remove(int id) {
        Customer searchCustomer = searchById(id);
        if (searchCustomer != null)
            customers.remove(searchCustomer);
        saveFile();
    }

    public void update(int id, Customer c) {
        Customer searchCustomer = searchById(id);
        if (searchCustomer != null) {
            searchCustomer.setName(c.getName());
        }
    }

    public String display() {
        String str = "";
        for (Customer c: customers)
            str += c.toString() + "\n";
        return str;
    }

    public void saveFile() {
        readWriteable.save(FILE_PATH, customers);
    }
    public void readFile() {
        customers.clear();
        customers = readWriteable.read(FILE_PATH);
    }
}
