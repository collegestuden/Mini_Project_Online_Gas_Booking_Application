package BookingGas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gas {
    private static List<Customer> customers = new ArrayList<>();
    private static List<GasAgency> gasAgencies = new ArrayList<>();
    private static List<GasBooking> gasBookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----- Online Gas Booking System -----");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Gas Agency");
            System.out.println("3. Book Gas");
            System.out.println("4. Display Customers");
            System.out.println("5. Display Gas Agencies");
            System.out.println("6. Display Bookings");
            System.out.println("7. Add Payment");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    addGasAgency(scanner);
                    break;
                case 3:
                    bookGas(scanner);
                    break;
                case 4:
                    displayCustomers();
                    break;
                case 5:
                    displayGasAgencies();
                    break;
                case 6:
                    displayBookings();
                    break;
                case 7:
                    addPayment(scanner);
                    break;
                case 8:
                    System.out.println("Thank you for using the Online Gas Booking System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 8);

        scanner.close();
    }

    private static void addCustomer(Scanner scanner) {
        int customerID = customers.size() + 1;
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer phone number: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(customerID, name, phone);
        customers.add(customer);
        Connection_Class.addCustomerToDB(customer);
    }
    private static void addPayment(Scanner scanner) {
        System.out.print("Enter booking ID for the payment: ");
        int bookingID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter payment type (Cash or Card): ");
        String paymentType = scanner.nextLine();

        String cardNumber = null;
        if ("Card".equalsIgnoreCase(paymentType)) {
            System.out.print("Enter card number: ");
            cardNumber = scanner.nextLine();
        }

        Payment payment = new Payment(generatePaymentID(), bookingID, paymentType, cardNumber);
        Connection_Class.addPaymentToDB(payment);
    }
    private static int generatePaymentID() {
        // You can implement your logic to generate a unique payment ID here.
        // For simplicity, we will just return a random number.
        return (int) (Math.random() * 100000);
    }

    private static void addGasAgency(Scanner scanner) {
        int agencyID = gasAgencies.size() + 1;
        System.out.print("Enter gas agency name: ");
        String name = scanner.nextLine();
        System.out.print("Enter gas agency address: ");
        String address = scanner.nextLine();
        System.out.print("Enter gas agency phone number: ");
        String phone = scanner.nextLine();

        GasAgency gasAgency = new GasAgency(agencyID, name, address, phone);
        gasAgencies.add(gasAgency);
        Connection_Class.addGasAgencyToDB(gasAgency);
    }

    private static void bookGas(Scanner scanner) {
        int bookingID = gasBookings.size() + 1;
        System.out.print("Enter customer phoneNumber: ");
        long phone = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter gas agency Name: ");
        String name = scanner.nextLine();
//        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter quantity of gas: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

       
		GasBooking gasBooking = new GasBooking(bookingID, phone, name, quantity);
        gasBookings.add(gasBooking);
        Connection_Class.addGasBookingToDB(gasBooking);
    }

    private static void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("\n----- Customers -----");
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getCustomerID() + " Name: " + customer.getName() + ", Phone: " + customer.getPhone());
            }
        }
    }

    private static void displayGasAgencies() {
        System.out.println("\n----- Gas Agencies -----");
        for (GasAgency agency : gasAgencies) {
            System.out.println("ID: " + agency.getAgencyID() + ", Phone: " + agency.getPhone() + ", Address: " + agency.getAddress() + ", Phone: " + agency.getPhone());
        }
    }

    private static void displayBookings() {
        System.out.println("\n----- Gas Bookings -----");
        for (GasBooking booking : gasBookings) {
            System.out.println("Booking ID: " + booking.getBookingID() + ", Customer Phone: " + booking.getPhone() + ", Agency : " + booking.getAgencyName() + ", Quantity: " + booking.getQuantity());
        }
    }
}
