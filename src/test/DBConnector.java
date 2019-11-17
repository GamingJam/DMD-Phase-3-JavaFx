package test;

import java.sql.*;

public class DBConnector {
    
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:identifier.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            if(conn == null)
                throw new SQLException("No DB connection established!");
            else
                System.out.println("Connection to SQLite has been established.");
            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
        }
        return null;
    }

    public static void query(Connection conn, String sql){
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            ResultSet answer = stmt.executeQuery(sql);

            System.out.println("Query succeed");

            while (answer.next()) {
                System.out.println(answer.getInt("id") +  "\t" +
                        answer.getString("name") + "\t" +
                        answer.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
