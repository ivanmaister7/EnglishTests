package sample;

import java.sql.*;

public class Database {
    private final String userName = "root";
    private final String password = "Q1W2E3rtyuiop";
    private final String connectUrl = "jdbc:mysql://localhost:3306/test";
    private final String tableName = "words";

    public Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){

            System.out.println("Connected");
            //statement.executeUpdate("drop table words");
            statement.executeUpdate("create table if not exists words(id MEDIUMINT auto_increment, question char (30) not null, answer char (30) not null, primary key (id))");
            //statement.execute("insert into temp set name = 'Harry Potter2'");
            //statement.execute("insert into temp set name = 'Harry Potter2'");
//            ResultSet resultSet = statement.executeQuery("select * from temp");
//            while(resultSet.next()){
//                System.out.println(resultSet.getInt("id"));
//                System.out.println(resultSet.getString("name"));
//            }
        }
    }

    public void put(String question, String answer) throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){
            statement.execute("insert into words set question = '"+ question +"', answer = '"+ answer +"'");
        }
    }

    public void update(int idn, String question, String answer) throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){
            statement.executeUpdate("update words set question = '"+ question +"', answer = '"+ answer +"' where id = '"+ idn +"' ");
        }
    }

    public String getQuestion(int idn) throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from words where id = '"+ idn +"' ");
            while(resultSet.next()){
                return resultSet.getString("question");
            }
        }
        return "-1";
    }

    public String getAnswer(int idn) throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from words where id = '"+ idn +"' ");
            while(resultSet.next()){
                return resultSet.getString("answer");
            }
        }
        return "-1";
    }

    public int getId(String question) throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from words where question = '"+ question +"'");
            while(resultSet.next()){
                return resultSet.getInt("id");
            }
        }
        return -1;
    }

    public int size() throws SQLException {
        try(Connection connection = DriverManager.getConnection(connectUrl,userName,password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = statement.executeQuery("select * from words");
            if (rs != null) {
                rs.last();
                return rs.getRow();
            }
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database db = new Database();
        db.put("black","чорний");
        db.put("car","машина");
        db.put("table","стыл");
        db.update(3,"table","стiл");
        System.out.println(db.getQuestion(3));
        System.out.println(db.getAnswer(3));
        System.out.println(db.getId(db.getQuestion(3)));
        System.out.println(db.size());
    }
}
