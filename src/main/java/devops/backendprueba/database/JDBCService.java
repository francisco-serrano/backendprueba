package devops.backendprueba.database;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class JDBCService {

    Connection conn = null;

    public void makeConnection(){
        String url = "jdbc:sqlite:./dolar.db";
        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (isDBEmpty())
            initializeDB();
        else
            System.out.println("La base de datos ya fue inicializada");
    }

    private void initializeDB(){
        createTable();
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            String someshit = "INSERT INTO Dolar " +
                    "VALUES('0000/00/00', 00.0000);";
            stmt.executeUpdate(someshit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Dolar" +
                    "(date TEXT NOT NULL, " +
                    " value DOUBLE NOT NULL); ";
            stmt.executeUpdate(sql);
            //add some shit
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        System.out.println("Table created successfully");
    }

    private boolean isDBEmpty(){
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql = "SELECT name FROM sqlite_master WHERE type='table';";
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            boolean tof = rs.next();
            return !(tof);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fall'e");
        }
        return false;
    }

    public boolean existDate(String date) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = "SELECT * FROM dolar WHERE date='" + date + "';";
            //String query = "SELECT * FROM dolar;";
            ResultSet rs = stmt.executeQuery(query);
            boolean tof = rs.next();
            stmt.close();
            return tof;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertValue(String date, double value){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = "INSERT INTO Dolar " +
                    "VALUES('" + date + "'," + value + ");";
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getLastValue() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            String query = "SELECT * " +
                    "FROM Dolar " +
                    "ORDER BY date DESC " +
                    "LIMIT 1;";
            ResultSet rs = stmt.executeQuery(query);
            JSONObject json = new JSONObject();
            if (rs.next()) {
                String d = rs.getString(("date"));
                double v = rs.getDouble("value");
                System.out.println(d);
                System.out.println(v);
                json.put("d", d);
                json.put("v", v);
                stmt.close();
                return json;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void show() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = "SELECT * " +
                    "FROM Dolar;";
            ResultSet rs = stmt.executeQuery(query);
            stmt.close();
            System.out.println(rs.getFetchSize());
            while (rs.next()) {
                String d = rs.getString(("date"));
                double v = rs.getDouble("value");
                System.out.println(d);
                System.out.println(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getNumber() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = "SELECT COUNT(*) " +
                    "FROM Dolar;";
            ResultSet rs = stmt.executeQuery(query);
            stmt.close();
            System.out.println(rs.getFetchSize());
            while (rs.next()) {
                String d = rs.getString(("date"));
                double v = rs.getDouble("value");
                System.out.println(d);
                System.out.println(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args){
        JDBCService db = new JDBCService();
        db.makeConnection();
        db.createTable();
        db.closeConnection();
    }
}