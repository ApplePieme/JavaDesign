import java.sql.*;

public class JavaDesign_01 {
    public static void main(String[] args) {
        Inquire inquire= new Inquire();
        inquire.link();
    }
}

class Inquire{
    void link(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/person_data_table","root","123456");
                    Statement s=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);//获取数据库表的数据
                    ResultSet r=s.executeQuery("select * from personal_data");
                    showRecord(r);
                    s.close();
                    connection.close();
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
    }
    void showRecord(ResultSet r){
        try{
            r.last();
            r.beforeFirst();
            while(r.next()){
                System.out.print("\t姓名:"+r.getString("name"));
                System.out.print("\t电话:"+r.getString("phone_number"));
                System.out.print("\t地址:"+r.getString("address"));
                System.out.print("\t邮箱:"+r.getString("email"));
                System.out.println();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}