package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Model.login2;
@WebServlet("/new")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Login");
        try{
             Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
            String user = req.getParameter("username");
            String pass = req.getParameter("password");
            System.out.println("hii");
                PreparedStatement stmt=conn.prepareStatement("select * from login where username=? and password=?");
                stmt.setString(1,user);
                stmt.setString(2, pass);
                ResultSet rset = stmt.executeQuery();

            if(rset.next()) {
                HttpSession session = req.getSession();
            session.setAttribute("Username", user);
            session.setAttribute("Password", pass);
            resp.sendRedirect("mainform.html");
            }
            else {
            resp.sendRedirect("index.html");
            }
            }
            catch(SQLException e){
                System.out.println(e);
            }
    }
  




    //------------------------doGet in cards-----------------------
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Report");

        ArrayList<Object> stList3 = new ArrayList<Object>();
        stList3.clear();


        //-----------------------------TOP RESTAURANTS IN DATEWISE------------------------------------------------------
        ArrayList<login2> stList = new ArrayList<login2>();
        stList.clear();
      
        try {
           
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
                java.sql.Statement stmt = conn.createStatement();
                

                String strSelect = " SELECT id,ordername,buyername,res,location,date,price,user_id FROM serve where date= '2022-08-02' GROUP BY res ORDER BY COUNT(*) DESC LIMIT 1";
                System.out.println("The SQL statement is: " + strSelect + "\n");
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("The records selected are:");
                
                while(rset.next()) {   // Repeatedly process each row
                     
                    int id = rset.getInt("id"); 
                    String ordername = rset.getString("ordername");  
                     String buyername  = rset.getString("buyername");   
                    String res = rset.getString("res");  
                    String location  = rset.getString("location");  
                    String date  = rset.getString("date");    
                    Float price  = rset.getFloat("price"); 
                    int user_id = rset.getInt("user_id"); 

                    System.out.println(id + ", " + ordername + ", " + buyername+","+res+","+location+","+date+","+price +","+user_id);  
                   stList.add(new login2(id, ordername, buyername,res,location,date,price,user_id));
                }
               

   
        } catch (Exception e) {
            // TODO: handle exception
        }


        //--------------------------------------TOP CUSTOMER  ORDER IN DATEWISE---------------------------------
        ArrayList<login2> stList1 = new ArrayList<login2>();

        stList1.clear();
       
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
                java.sql.Statement stmt = conn.createStatement();
                

                String strSelect = " SELECT id, ordername, buyername,res,location,date,price,user_id FROM serve WHERE price=(SELECT MAX(price) FROM serve GROUP BY date HAVING DATE ="+" '2022-08-02' )";
                System.out.println("The SQL statement is: " + strSelect + "\n");
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("The records selected are:");
                
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

               
        } catch (Exception e) {
            // TODO: handle exception
        }


        //---------------------------------DATEWISE NO OF ORDERS----------------------------------

        ArrayList<Object> stList2 = new ArrayList<Object>();

        stList2.clear();
       
        try {
           
           
     
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
                java.sql.Statement stmt = conn.createStatement();
                

                String strSelect = " SELECT id, ordername, buyername,res,location,date,price FROM serve WHERE DATE = '2022-08-02'";
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
    
                     System.out.println(id + ", " + ordername + ", " + buyername+","+res+","+location+","+date+","+price);
                     ++rowCount;
                   //stList2.add(new login2(id, ordername, buyername,res,location,date,price));
                   

                }
                stList2.add(rowCount);
                

               
        } catch (Exception e) {
            // TODO: handle exception
        }

    

      
      
       


    //    stList3.add(new (stList,stList1) );

        stList3.add(stList);
        stList3.add(stList1);
        stList3.add(stList2);
        
        System.out.println(stList3);



        
        resp.setContentType("text/plain");
        String g1= new Gson().toJson(stList3);
        resp.getWriter().print(g1);

    
        }
     




}
