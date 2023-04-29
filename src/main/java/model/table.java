package model;

public class table {
    private String idTable;
    private String nameTable;
    private int status;

    public table(String idTable, String nameTable, int status) {
        this.idTable = idTable;
        this.nameTable = nameTable;
        this.status = status;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
