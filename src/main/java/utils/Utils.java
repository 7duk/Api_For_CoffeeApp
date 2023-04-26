package utils;

import java.sql.SQLException;
import java.util.List;

public interface Utils<T, S> {
    void create();
    boolean deleteProduct(String id) throws SQLException;
//    T getItem(S obj)throws SQLException;
    void update(int id);
    List<T> get(S obj) throws SQLException;
}
