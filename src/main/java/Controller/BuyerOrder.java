package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.login2;
@WebServlet("/orderde")
public class BuyerOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("insert method");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        login2 neworder = g.fromJson(requestData, login2.class);
        System.out.println(neworder.getId() + " " + neworder.getOrdername() + " " + neworder.getBuyername() + " "
                + neworder.getRes() + "," + neworder.getLocation() + "," + neworder.getdate() + ","
                + neworder.getPrice());
        int id = neworder.getId();
        String ordername = neworder.getOrdername();
        String buyername = neworder.getBuyername();
        String res = neworder.getRes();
        String location = neworder.getLocation();
        String date = neworder.getdate();
        Float price = neworder.getPrice();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", ""); 
            PreparedStatement pstm = conn.prepareStatement("insert into serve values(?,?,?,?,?,?,?)");
            System.out.println(id);
            System.out.println(ordername);
            System.out.println(buyername);
            System.out.println(res);
            System.out.println(location);
            System.out.println(date);
            System.out.println(price);

            pstm.setLong(1, id);
            pstm.setString(2, ordername);
            pstm.setString(3, buyername);
            pstm.setString(4, res);
            pstm.setString(5, location);
            pstm.setString(6, date);
            pstm.setFloat(7, price);




            int status = pstm.executeUpdate();
            System.out.println("successfully inserted ");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
