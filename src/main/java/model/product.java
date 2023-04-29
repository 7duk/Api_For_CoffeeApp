package model;

public class product {
    private String id;
     private String  name;
     private  double salePrice;
     private  int quantity;
     private String image;
     private  String idCatelory;

    public product(String id, String name, double salePrice, int quantity, String image, String idCatelory) {
        this.id = id;
        this.name = name;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.image = image;
        this.idCatelory = idCatelory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdCatelory() {
        return idCatelory;
    }

    public void setIdCatelory(String idCatelory) {
        this.idCatelory = idCatelory;
    }
}
