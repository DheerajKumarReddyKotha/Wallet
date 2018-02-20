package com.project.wallet;
import java.sql.*;
import java.util.List;

import com.project.wallet.model.Access;

public class JDBC {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/test";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   
   public void insertData(List<Access> accessList) {
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
      String sql = "select * from access_log";
      rs = stmt.executeQuery(sql);
      System.out.println(rs);
      
      for(Access access:accessList) {
      sql = "INSERT INTO access_log (start_epoch,ip_address,request,user_agent,status)" +
                   "VALUES ("
                   +access.getStartDate()+","+"\""
                   +access.getIp()+"\""+","
                   +access.getRequest()+","
                   +access.getUserAgent()+","
                   +access.getStatus()+")"+" ON DUPLICATE KEY UPDATE "
                   +"status = VALUES(status)";
      System.out.println(sql);
      stmt.executeUpdate(sql);
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
