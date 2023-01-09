package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Model.login2;



@WebServlet("/order")

public class OrderController extends HttpServlet{
   
    ArrayList<login2> list1 = new ArrayList<login2>();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order Controller");
        list1.clear();
        try {
               Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
                Statement stmt = conn.createStatement();
                

                String strSelect = "select id, ordername, buyername,res,location,date,price,user_id from serve ";
                System.out.println("The SQL statement is: " + strSelect + "\n");
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("The records selected are:");
                int rowCount = 0;
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
                   ++rowCount;
                   list1.add(new login2(id, ordername, buyername,res,location,date,price,user_id));
                }
                System.out.println(rowCount);
                String g = new Gson().toJson(list1);
                resp.getWriter().print(g);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Login");
        try{
             Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
            String email = req.getParameter("emailid");
            String pass = req.getParameter("password");
            System.out.println("hii");
                PreparedStatement stmt=conn.prepareStatement("select * from order_user where email=? and password=?");
                stmt.setString(1,email);
                stmt.setString(2, pass);
                ResultSet rset = stmt.executeQuery();

            if(rset.next()) {
                HttpSession session = req.getSession();
            session.setAttribute("Emailid", email);
            session.setAttribute("Password", pass);
            resp.sendRedirect("userform.html");

            }
            else {
            resp.sendRedirect("index.html");
            }
            }
            catch(SQLException e){
                System.out.println(e);
            }
    }
    
    
}
