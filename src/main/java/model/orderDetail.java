package model;

public class orderDetail {
    private  String idOrderDetail;
    private  String idProduct;
    private  String idOrder;
    private  int count;
    private  String note;

    public orderDetail(String idOrderDetail, String idProduct, String idOrder, int count, String note) {
        this.idOrderDetail = idOrderDetail;
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.count = count;
        this.note = note;
    }

    public String getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(String idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
