package utils;

import java.sql.SQLException;
import java.util.List;

public interface Utils<T, S> {
    boolean create(T obj) throws SQLException;
    boolean delete(String id) throws SQLException;
    boolean update(T obj) throws SQLException;
    List<T> get(S obj) throws SQLException;
}
