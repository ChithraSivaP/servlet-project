package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Model.login2;

@WebServlet("/mycart")
public class Mycart extends HttpServlet{
    ServletRequest session;
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("myCArt");

     
       HttpSession ss = req.getSession();
       String m = (String) ss.getAttribute("Emailid");
        System.out.println(m);

        ArrayList<Object> stList3 = new ArrayList<Object>();
        stList3.clear();


        ArrayList<login2> stList = new ArrayList<login2>();
        stList.clear();
      try{
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
         PreparedStatement stmt=conn.prepareStatement("SELECT * FROM serve s WHERE user_id=(SELECT USER_id FROM order_user WHERE email=?)");
         stmt.setString(1,m);
         ResultSet rset = stmt.executeQuery();
          while(rset.next()) {   // Repeatedly process each row
            int id = rset.getInt("id"); 
           String ordername = rset.getString("ordername");  
            String buyername  = rset.getString("buyername");   
           String res = rset.getString("res");  
           String location  = rset.getString("location");  
           String date  = rset.getString("date");    
           Float price  = rset.getFloat("price"); 
           int user_id = rset.getInt("user_id");    

            System.out.println(id + ", " + ordername + ", " + buyername+","+res+","+location+","+date+","+price+","+user_id);
         
            stList.add(new login2(id, ordername, buyername,res,location,date,price,user_id));
          }
         
         } 
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<login2> stList1 = new ArrayList<login2>();
        stList1.clear();
      try{
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
         PreparedStatement stmt=conn.prepareStatement("SELECT * FROM serve where user_id = (SELECT USER_id FROM order_user WHERE email= ? )GROUP BY ordername ORDER BY COUNT(*) DESC LIMIT 1");
       
         stmt.setString(1,m);
         ResultSet rset = stmt.executeQuery();
          while(rset.next()) {   // Repeatedly process each row
            int id = rset.getInt("id"); 
           String ordername = rset.getString("ordername");  
            String buyername  = rset.getString("buyername");   
           String res = rset.getString("res");  
           String location  = rset.getString("location");  
           String date  = rset.getString("date");    
           Float price  = rset.getFloat("price"); 
           int user_id = rset.getInt("user_id");    

            System.out.println(id + ", " + ordername + ", " + buyername+","+res+","+location+","+date+","+price+","+user_id);
         
            stList1.add(new login2(id, ordername, buyername,res,location,date,price,user_id));
          }
         
         } 
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


       stList3.add(stList);
       stList3.add(stList1);





        Gson g = new Gson();
        String stds = g.toJson(stList3);
        resp.getWriter().print(stds);
    }

}
