package model;

public class order {
    private String idOrder;
    private  String dateOrder;
    private  String idEmployee;
    private  double priceTotal;
    private  String idTable;

    public order(String idOrder, String dateOrder, String idEmployee, double priceTotal, String idTable) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.idEmployee = idEmployee;
        this.priceTotal = priceTotal;
        this.idTable = idTable;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }
}
