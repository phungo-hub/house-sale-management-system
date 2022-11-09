package customer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerManagementMenu {
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    public void displayMenu() {
        System.out.println("=====================");
        System.out.println("1. Add new customer");
        System.out.println("2. Update info");
        System.out.println("3. Search customer by ID");
        System.out.println("4. remove");
        System.out.println("5. Display All");
        System.out.println("0. Exit");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt(); scanner.nextLine();
            switch (choice) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> searchById();
                case 4 -> remove();
                case 5 -> displayAll();
                case 0 -> System.exit(0);
            }
        }
    }
    public void add() {
        int id = checkId();
        String name = checkAddName();
        Customer customer = new Customer(id, name);
        customerManagement.add(customer);
    }

    public int checkId() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input customer ID");
        String inputId = input.nextLine();
        try {
            Pattern pattern = Pattern.compile("[0-9]*", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputId);
            boolean matchFound = matcher.matches();
            if (!matchFound)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Please only enter number ");
        }
        int id = Integer.parseInt(inputId);
        return id;
    }

    public String checkAddName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input customer's name");
        String input = scanner.nextLine();
        try {
            Pattern pattern = Pattern.compile("[A-Z][a-z]*");
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.matches();
            if (!matchFound)
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Name must be written in Camelcase");
        }
        return input;
    }

    public void update() {
        int id = checkId();
        String name = checkAddName();
        Customer c = new Customer(id, name);
        customerManagement.update(id, c);
    }

    public void searchById() {
        int id = checkId();
        System.out.println(customerManagement.searchById(id).toString());
    }

    public void remove() {
        int id = checkId();
        customerManagement.remove(id);
    }
    public void displayAll() {
        System.out.println(customerManagement.display());
    }
}
