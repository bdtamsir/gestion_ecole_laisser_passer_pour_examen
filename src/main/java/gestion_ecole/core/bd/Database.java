package gestion_ecole.core.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    void openConnection();
    void closeConnection();
    ResultSet executeQuery(String query) throws SQLException;
    int executeUpdate(String query) throws SQLException;
    void initCreateStatement(String query) throws SQLException;
}
