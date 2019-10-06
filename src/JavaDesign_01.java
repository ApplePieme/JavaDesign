import java.sql.*;
import java.util.Scanner;

public class JavaDesign_01 {
    public static void main(String[] args) {
        Inquire inquire = new Inquire();
        inquire.link();
        inquire.find();
    }
}

class Inquire {
    protected Connection connection;

    void link() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person_data_table?useUnicode=true&characterEncoding=utf-8", "root", "123456");
            //?useUnicode=true&characterEncoding=utf-8很关键，如果没有则不能利用jdbc实现中文的查询
            Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);//获取数据库表的数据
            ResultSet rs = s.executeQuery("select * from personal_data");
            System.out.println("个人信息表：");
            showRecord(rs);
            s.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }//连接数据库并打印表记录

    void showRecord(ResultSet rs) {
        try {
            rs.last();
            rs.beforeFirst();
            while (rs.next()) {
                System.out.print("\t序号:" + rs.getString("id"));
                System.out.print("\t姓名:" + rs.getString("name"));
                System.out.print("\t电话:" + rs.getString("phone_number"));
                System.out.print("\t地址:" + rs.getString("address"));
                System.out.print("\t邮箱:" + rs.getString("email"));
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }//遍历结果集，并输出

    void find() {
        System.out.print("输入姓名关键字进行查询：");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from personal_data where name like ?");
            ps.setString(1, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            System.out.println("关于“" + str + "”的查找结果：");
            if (!rs.next())//若结果集为空，则输出查无此人
                System.err.println("查无此人!");
            else
                showRecord(rs);//结果集不为空就把对象传给showRecord方法输出
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//查找
}