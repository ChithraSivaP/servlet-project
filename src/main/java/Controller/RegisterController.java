package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/register") 
public class RegisterController extends HttpServlet {
    PrintWriter out;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("REGISTERFORM");
        try{
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
           "root", "");
           String email = req.getParameter("emailid");
           String pass = req.getParameter("password");
           System.out.println(email);
           System.out.println(pass);
       


           System.out.println("register");
               PreparedStatement stmt = conn.prepareStatement("select * from order_user where email=? and password=?");
               stmt.setString(1,email);
               stmt.setString(2, pass);
               ResultSet rset = stmt.executeQuery();


           if(rset.next()) {
               HttpSession session = req.getSession();
           session.setAttribute("Emailid", email);
           session.setAttribute("Password", pass);
           resp.sendRedirect("regform.html");
         
           }
           else {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", ""); 
                PreparedStatement pstm = conn.prepareStatement("INSERT INTO order_user(email,PASSWORD) values (?,?)");
             
                System.out.println(email);
                System.out.println(pass);
    
               
                pstm.setString(1, email);
                pstm.setString(2, pass);
    
                int status = pstm.executeUpdate();
                System.out.println("successfully inserted ");
                resp.sendRedirect("userform.html");
           
                }catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
                catch(SQLException e){
                System.out.println(e);
                }

                }

        }
    