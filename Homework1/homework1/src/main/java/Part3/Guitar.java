package Part3;

public class Guitar {
    private String serialNumber;
    private double price;
    String builder;
    String model;
    String type;
    String backWood;
    String topWood;



    public Guitar(String serialNumber, double price) {
        this.serialNumber = serialNumber;
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(float newPrice) {
        this.price = newPrice;
    }
    public String getBuilder() {
        return builder;
    }
    public String getModel() {
        return model;
    }
    public String getType() {
        return type;
    }
    public String getBackWood() {
        return backWood;
    }
    public String getTopWood() {
        return topWood;
    }

}
