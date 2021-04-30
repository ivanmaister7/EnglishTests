package sample;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String userName = "root";
        String password = "Q1W2E3rtyuiop";
        String connectUrl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){

            System.out.println("Connected");
            ResultSet resultSet = statement.executeQuery("select * from temp");
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));

            }
        }

    }
}
