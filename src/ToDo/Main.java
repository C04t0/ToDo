package ToDo;

import java.sql.*;

public class Main {
    public static void main (String[]args) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:todo.db");
            System.out.println("Opened database connection");
            try {
                deleteTable(conn);
                System.out.println("Table deleted");
            } catch (Exception e) {
                System.err.println(e);
            }

            createTable(conn);
            insertTodo(conn, "Java leren", "veel oefenen", "04-07-2023");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void insertTodo(Connection conn, String title, String description, String date) throws SQLException {
        String insertSql = "insert into todos (name, description, date) values (?, ?, ?)"; //het is illegaal om dit direct in te vullen met waarden (dit geeft mogelijkheid tot SQL-injections)
        PreparedStatement pstmt = conn.prepareStatement();
    }

    private static void deleteTable(Connection conn) throws SQLException {
        String deleteTableSQL = "drop table todos";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteTableSQL);
    }

    private static void createTable(Connection conn)  throws SQLException {
        String createTableSQL = "CREATE TABLE todos (id INTEGER UNIQUE,name TEXT, description TEXT,date TEXT,PRIMARY KEY(id AUTOINCREMENT))";
        Statement stmt = conn.createStatement();
        stmt.execute(createTableSQL);
    }
}
