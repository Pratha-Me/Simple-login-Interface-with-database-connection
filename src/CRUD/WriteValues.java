package CRUD;

import java.util.Scanner;

import static CRUD.ConnStmt.conn;
import static CRUD.ConnStmt.stmt;

public class WriteValues
{
    private final static Scanner in = new Scanner(System.in);

    public void write()
    {
        System.out.println("\nEnter ID, Username and password" +
            "\nHit enter once more after an entry to make an exit.");

        String line = in.nextLine();
        while (!line.isEmpty())
        {
            String[] str = line.trim().split("\\s+");
            try
            {
                stmt = conn.createStatement();
                stmt.executeUpdate(String.format("INSERT INTO CREDENTIALS (ID, ADMINISTRATOR, PASSWORD) " +
                    "VALUES (%1$d, '%2$s', '%3$s');", Integer.parseInt(str[0]), str[1], str[2]));
            }
            catch (Exception e)
            { e.printStackTrace(); System.exit(0); }

            line = in.nextLine();
        }
    }
}
