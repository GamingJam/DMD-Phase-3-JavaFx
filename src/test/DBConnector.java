package test;

import java.sql.*;

public class DBConnector {
    private String db_url;
    private Connection connection;

    public DBConnector(String db_url) {
        this.db_url = db_url;

        this.hold_invariant();
    }

    /**
     * The function guarantees that class will be connected to the database
     */
    private void hold_invariant(){
        boolean isConnected = false;

        while(!isConnected){
            try {
                if(connection == null || !connection.isValid(5)){
                    this.connect();
                }
                isConnected = true;
            }catch (SQLException ex){
                isConnected = false;
                System.err.println("Error message: " + ex.getLocalizedMessage());
                System.err.println("State: "+ex.getSQLState());
            }

            if(!isConnected){
                System.err.println("DB connection failed, retrying");
            }
        }
    }

    private void connect() throws SQLException {
        // create a connection to the database
        this.connection = DriverManager.getConnection(this.db_url);
        System.err.println("Connected to DB successfully");
    }

    public String query(String sql){
        this.hold_invariant();
        StringBuilder result = new StringBuilder();

        try (Statement stmt = this.connection.createStatement()) {
            // create a new table
            ResultSet resultSet = stmt.executeQuery(sql);
            System.err.println("Query succeed");
            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for(int i = 1; i <= columnCount; ++i){
                    if (i > 1) result.append(",  ");
                    result.append(resultSet.getString(i));
                }
                result.append("\n");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result.toString();
    }

}
