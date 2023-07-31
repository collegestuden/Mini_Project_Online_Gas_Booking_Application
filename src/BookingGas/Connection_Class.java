package BookingGas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
class Customer {
    private int customerID;
    private String name;
    private String phone;

    public Customer(int customerID, String name, String phone) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void insertIntoDB(Connection conn) throws SQLException {
        String query = "INSERT INTO Customer (name, phone) VALUES (?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, this.name);
        preparedStatement.setString(2, this.phone);
        preparedStatement.executeUpdate();
    }
}

class GasAgency {
    private int agencyID;
    private String name;
    private String address;
    private String phone;

    public GasAgency(int agencyID, String name, String address, String phone) {
        this.agencyID = agencyID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void insertIntoDB(Connection conn) throws SQLException {
        String query = "INSERT INTO GasAgency (name, address, phone) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, this.name);
        preparedStatement.setString(2, this.address);
        preparedStatement.setString(3, this.phone);
        preparedStatement.executeUpdate();
    }
}

class GasBooking {
    private int bookingID;
    private long phone;
    private String name;
    private int quantity;

    public GasBooking(int bookingID, long phone, String name, int quantity) {
        this.bookingID = bookingID;
        this.phone = phone;
        this.name = name;
        this.quantity = quantity;
    }

    public int getBookingID() {
        return bookingID;
    }

    public long getPhone() {
        return phone;
    }

    public String getAgencyName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void insertIntoDB(Connection conn) throws SQLException {
        String query = "INSERT INTO GasBooking (phone, name, quantity) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, this.phone);
        preparedStatement.setString(2, this.name);
        preparedStatement.setInt(3, this.quantity);
        preparedStatement.executeUpdate();
    }
}
class Payment {
    private int paymentID;
    private int bookingID;
    private String paymentType;
    private String cardNumber;

    public Payment(int paymentID, int bookingID, String paymentType, String cardNumber) {
        this.paymentID = paymentID;
        this.bookingID = bookingID;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
    }

    public void insertIntoDB(Connection conn) throws SQLException {
        String query = "INSERT INTO Payment (bookingID, paymentType, cardNumber) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, this.bookingID);
        preparedStatement.setString(2, this.paymentType);
        preparedStatement.setString(3, this.cardNumber);
        preparedStatement.executeUpdate();
    }
}

public class Connection_Class {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/gas_booking_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ab@2004";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver.", e);
        }
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }



    public static void addCustomerToDB(Customer customer) {
        try (Connection conn = getConnection()) {
            customer.insertIntoDB(conn);
            System.out.println("Customer added successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addGasAgencyToDB(GasAgency gasAgency) {
        try (Connection conn = getConnection()) {
            gasAgency.insertIntoDB(conn);
            System.out.println("Gas agency added successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addPaymentToDB(Payment payment) {
        try (Connection conn = getConnection()) {
            payment.insertIntoDB(conn);
            System.out.println("Payment added successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addGasBookingToDB(GasBooking gasBooking) {
        try (Connection conn = getConnection()) {
            gasBooking.insertIntoDB(conn);
            System.out.println("Gas booking added successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
