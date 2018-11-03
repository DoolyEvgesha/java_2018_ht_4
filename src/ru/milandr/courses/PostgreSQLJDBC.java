package ru.milandr.courses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PostgreSQLJDBC {
    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/usera_database",
                            "dooly", "WakeMeUp_inside");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
//-------------------------------------------------------------------------
            stmt = c.createStatement();
            ResultSet rs1 = stmt.executeQuery( "SELECT * FROM users;" );
            ArrayList<Integer> id_list=new ArrayList<Integer>();

            while ( rs1.next() ) {

                int id = rs1.getInt("id");
                id_list.add(id);

            }

            Integer i = Collections.max(id_list);
            System.out.println("Maximum id is "+ i);
//--------------------------------------------------------------------------
            ResultSet rs2 = stmt.executeQuery( "SELECT * FROM users WHERE address_id = 2;" );
            System.out.println("\nPeople living at certain address:");
            while(rs2.next()){

                String first_name = rs2.getString("first_name");
                String last_name = rs2.getString("last_name");
                System.out.println(first_name+" "+last_name+"");

            }
//----------------------------------------------------------------------------
            ResultSet rs3 = stmt.executeQuery( "SELECT * FROM users ;" );
            ArrayList<String> nameList = new ArrayList<>();
            while(rs3.next()){
                String name = rs3.getString("last_name");
                nameList.add(name);
            }

            nameList.sort(String::compareToIgnoreCase);
            //sorry, I didn't know if it was necessary to print surnames
            //so I just printed in an ArrayList
            //hope, it won't be bad :3
            System.out.println(nameList);
//----------------------------------------------------------------------------

            rs1.close();
            rs2.close();
            rs3.close();
            stmt.close();
            c.close();



        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("COULD NOT OPEN DATABASE\n");
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}