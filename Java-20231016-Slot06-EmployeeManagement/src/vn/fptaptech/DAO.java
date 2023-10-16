package vn.fptaptech;

import java.sql.SQLException;

public interface DAO<T> {
    public T getById(String id) throws SQLException;
    public void getAll() throws SQLException;
    public T add(T t) throws SQLException;
    public T delete(String id) throws SQLException;
    public T update(String id, String name) throws SQLException;
}
