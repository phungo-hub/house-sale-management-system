package house;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HouseManagementMenu {
    HouseManagement houseManagement = HouseManagement.getHouseManagement();
    public void displayMenu() {
        System.out.println("=====================");
        System.out.println("1. Add new house");
        System.out.println("2. Update info");
        System.out.println("3. Search house by ID");
        System.out.println("4. remove (careful! house will be gone after using this)");
        System.out.println("5. check almost out of stock");
        System.out.println("6. Display All");
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
                case 5 -> checkAlmostOutOfStock();
                case 6 -> displayAll();
                case 0 -> System.exit(0);
            }
        }
    }

    public void add() {
        int id = checkId();
        double width = checkAddWidth();
        double price = checkAddPrice();
        int qty = checkAddQty();
        String direction = checkAddDirection();

        House house = new House(id, width, price,qty, direction);
        houseManagement.add(house);
    }

    public int checkId() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input house ID");
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

    public double checkAddWidth() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter width");
        double inputWidth = 0;
        try {
            inputWidth = input.nextDouble();
        } catch (Exception e) {
            System.out.println("Please kindly input number");
        }
        return inputWidth;
    }

    public double checkAddPrice() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter price");
        double inputWidth = 0;
        try {
            inputWidth = input.nextDouble();
        } catch (Exception e) {
            System.out.println("Please kindly input number");
        }
        return inputWidth;
    }

    public int checkAddQty() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input quantity");
        String inputQty = input.nextLine();
        try {
            Pattern pattern = Pattern.compile("[0-9]*", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputQty);
            boolean matchFound = matcher.matches();
            if (!matchFound)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Please only enter number ");
        }
        int qty = Integer.parseInt(inputQty);
        return qty;
    }
    public String checkAddDirection() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please kindly choose direction");
        System.out.println("==============================");
        System.out.println("1. North");
        System.out.println("2. Est");
        System.out.println("3. South");
        System.out.println("4. West");

        int choice = input.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    return "North";
                case 2:
                    return "Est";
                case 3:
                    return "South";
                case 4:
                    return "West";
                default:
                    System.out.println("Please choose correct option");
                    break;
            }
        }
    }
    public void displayAll() {
        System.out.println(houseManagement.display());
    }

    public void update() {
        int id = checkId();
        double width = checkAddWidth();
        double price = checkAddPrice();
        String direction = checkAddDirection();
        int qty = checkAddQty();
        House house = new House(id, width, price, qty, direction);
        houseManagement.update(id, house);
    }
    public void searchById() {
        int id = checkId();
        System.out.println(houseManagement.searchById(id));
    }

    public void remove() {
        int id = checkId();
        houseManagement.remove(id);
    }

    public void checkAlmostOutOfStock() {
        System.out.println("Products that are almost out of stock: \n");
        System.out.println(houseManagement.checkAlmostOutOfStock().toString());
    }
}
