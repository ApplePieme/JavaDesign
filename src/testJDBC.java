import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testJDBC {
    public static void main(String[] args){
        String Driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/myba";
        String user="root";
        String password="123456";
        try{
            Class.forName(Driver);
            try{
                Connection connection= DriverManager.getConnection(url,user,password);
                System.out.println(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
