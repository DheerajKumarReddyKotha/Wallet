package com.project.wallet;
import java.sql.*;
import java.util.List;

import com.project.wallet.model.Access;

public class SelectQuery {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   
   public void selectData(Long start,Long end, String threshold) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();
      ResultSet rs;
      /*
       * The tool will find any IPs that made more than 250 requests starting from 2017-01-
01.13:00:00 to 2017-01- 02.13:00:00 (24 hours) and print them to console AND also load them to
another MySQL table with comments on why it&#39;s blocked.
       */
      String sql = "select distinct(ip_address) from access_log where start_epoch >= "+start
      		+ " and start_epoch < "+end
      		+ " having count(request) > "
      		+ Integer.parseInt(threshold);
      System.out.println(sql);
      rs = stmt.executeQuery(sql);

      while (rs.next()) {
          String ip = rs.getString("ip_address");
          
          System.out.println(ip);
      }
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample
