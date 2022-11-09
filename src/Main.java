import customer.CustomerManagement;
import customer.CustomerManagementMenu;
import house.HouseManagementMenu;
import order.OrderManagementMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("\t\tOrder Management Menu");
        System.out.println("======================================");
        System.out.println("1. House Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Order Management");
        System.out.println("0. Exit");
        HouseManagementMenu houseManagementMenu = new HouseManagementMenu();
        CustomerManagementMenu customerManagementMenu = new CustomerManagementMenu();
        OrderManagementMenu orderManagementMenu = new OrderManagementMenu();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt(); scanner.nextLine();
        switch(choice) {
            case 1 -> houseManagementMenu.menu();
            case 2 -> customerManagementMenu.menu();
            case 3 -> orderManagementMenu.menu();
            case 0 -> System.exit(0);
        }
    }
}
