package CRUD;

import java.io.File;
import java.sql.*;

public class ConnStmt
{
    public static Connection conn;
    public static Statement stmt;
    private final File file = new File("Admins.db");

    public void createORconnectToDB()
    {
        try
        {
            if (!file.exists())
            { System.out.println("Creating the DataBase"); }

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:Admins.db");
            stmt = conn.createStatement();

            System.out.println("DataBase Accessed!");

            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getColumns(null, null, "CREDENTIALS", "ADMINISTRATOR");
            if (!rs.next()) { createTable(); }
        }
        catch (Exception e)
        { e.printStackTrace(); System.exit(0); }

        closeStatement();
    }

    private void createTable()
    {
        try
        {
            stmt.executeUpdate("CREATE TABLE CREDENTIALS " +
                "(ID INT PRIMARY KEY NOT NULL," +
                " ADMINISTRATOR TEXT NOT NULL," +
                " PASSWORD TEXT NOT NULL);");
        }
        catch (Exception e)
        { e.printStackTrace(); System.exit(0); }

        System.out.println("The table has been created");
    }

    private void closeStatement()
    {
        try { stmt.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public void closeConnection()
    {
        try { conn.close(); System.out.println("Connection Terminated!");}
        catch (SQLException e) { e.printStackTrace(); }
    }
}
