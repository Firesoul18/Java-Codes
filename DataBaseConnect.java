import java.sql.*;
public class DataBaseConnect {
    Connection con;

    DataBaseConnect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servjsp","root","simsim");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception{
        DataBaseConnect dbc = new DataBaseConnect();
        Connection c = dbc.con;
        Statement st = c.createStatement();
        ResultSet rst = st.executeQuery("show tables;");
        System.out.println(rst.getString(0));
    }
}
