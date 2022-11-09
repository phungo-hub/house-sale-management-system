package house;

public class House {
    private int houseId;
    private double width;
    private double price;
    private int qty;
    private String direction;

    public House(int houseId, double width, double price, int qty, String direction) {
        this.houseId = houseId;
        this.width = width;
        this.price = price;
        this.direction = direction;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty += qty;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return houseId + "," +
                width + "," +
                price + "," +
                qty + "," +
                direction;
    }
}
