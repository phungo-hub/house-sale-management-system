package order;

import house.House;
import house.HouseManagement;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderManagementMenu {
    Scanner scanner = new Scanner(System.in);
    OrderManagement orderManagement = OrderManagement.getOrderManagement();
    HouseManagement houseManagement = HouseManagement.getHouseManagement();
    public void displayMenu() {
        System.out.println("===================");
        System.out.println("1. Add new order");
        System.out.println("2. Search by ID");
        System.out.println("3. Update quantity");
        System.out.println("4. Check most bought houses in a month");
        System.out.println("5. Check least bought houses");
        System.out.println("6. Display all");
        System.out.println("0. Exit");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt(); scanner.nextLine();
            switch(choice) {
                case 1 -> add();
                case 2 -> searchById();
                case 3 -> updateProdQty();
                case 4 -> checkMostBoughtHouseInAMonth();
                case 5 -> checkLeastBoughtHouse();
                case 6 -> displayAll();
                case 0 -> System.exit(0);
            }
        }
    }
    public void add() {
        System.out.println("please input Customer Id");
        int custId = scanner.nextInt(); scanner.nextLine();
        System.out.println("please input buyDate");
        String buyDateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(buyDateStr);
        Order o1 = new Order(custId, date);
        int inputPID;
        int choice = -1;
        while (choice != 0) {
            System.out.println("Please input house Id");
            inputPID = scanner.nextInt(); scanner.nextLine();
            if (checkProdId(inputPID)){
                System.out.println("Please input quantity");
                int qty = scanner.nextInt(); scanner.nextLine();
                House h = houseManagement.searchById(inputPID);
                if ((h.getQty() - qty) >= 0) {
                    h.setQty(-qty);
                    o1.addProduct(inputPID, qty);
                    houseManagement.saveFile();
                } else System.out.println("Not enough stock. quantity left: " + h.getQty());
            } else {
                System.out.println("Not found product");
            }
            System.out.println("========================");
            System.out.println("1. Continue adding products");
            System.out.println("0. Exit");
            choice = scanner.nextInt(); scanner.nextLine();
        }
        if (o1.getProdQty().size() > 0){
            orderManagement.add(o1);
        }
    }
    public boolean checkProdId(int id) {
        House h = HouseManagement.getHouseManagement().searchById(id);
        if (h != null)
            return true;
        else return false;
    }

    public void displayAll() {
        System.out.println(orderManagement.display());
    }
    public void searchById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input order ID");
        int id = scanner.nextInt(); scanner.nextLine();
        Order o1 = orderManagement.searchById(id);
        System.out.println(o1.toString());
    }
    public void updateProdQty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input order ID to update");
        int id = scanner.nextInt(); scanner.nextLine();

        Order o1 = orderManagement.searchById(id);
        int prodId;
        if (o1 != null) {
            int choice = -1;
            while (choice != 0) {
                System.out.println("Please input House ID to update");
                prodId = scanner.nextInt(); scanner.nextLine();
                if (checkProdId(prodId)){
                    System.out.println("Please input quantity");
                    int qty = scanner.nextInt(); scanner.nextLine();
                    House h = houseManagement.searchById(prodId);
                    int dif = o1.getProdQty().get(prodId) - qty;
                    if (dif < 0) {
                        if (h.getQty() - dif >= 0) {
                            h.setQty(dif);
                            orderManagement.updateProdQty(id, prodId, qty);
                            houseManagement.saveFile();
                        } else {System.out.println("Not enough stock. quantity left: " + h.getQty());}
                    } else {
                        h.setQty(-dif);
                        orderManagement.updateProdQty(id, prodId, qty);
                        houseManagement.saveFile();
                    }
                } else {
                    System.out.println("Not found product");
                }
                System.out.println("========================");
                System.out.println("1. Continue adding products");
                System.out.println("0. Exit");
                choice = scanner.nextInt(); scanner.nextLine();
            }
        } else System.out.println("Order ID not found");

    }

    public void checkMostBoughtHouseInAMonth() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter month");
        int month = scanner.nextInt(); scanner.nextLine();
        List<Integer> result = orderManagement.checkMostBoughtHouseInAMonth(month);
        System.out.println("House ids that were bought the most: " );
        System.out.println(result.toString());
    }

    public void checkLeastBoughtHouse() {
        System.out.println("House Ids that are bought the least: ");
        System.out.println(orderManagement.checkLeastBoughtHouse().toString());
    }
}
