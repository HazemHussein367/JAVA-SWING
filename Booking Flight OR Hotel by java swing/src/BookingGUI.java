import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Booking {
    protected static int idCounter = 100;
    protected int bookingID;
    protected String customerName;
    protected String bookingDate;

    public Booking(String customerName, String bookingDate) {
        this.bookingID = idCounter++;
        this.customerName = customerName;
        this.bookingDate = bookingDate;
    }

    public String getDetails() {
        return "Booking ID: " + bookingID + "\nCustomer: " + customerName + "\nDate: " + bookingDate;
    }
}

class FlightBooking extends Booking {
    private String flightNumber;
    private String seatClass;

    public FlightBooking(String customerName, String bookingDate, String flightNumber, String seatClass) {
        super(customerName, bookingDate);
        this.flightNumber = flightNumber;
        this.seatClass = seatClass;
    }

    public String getDetails() {
        return super.getDetails() + "\nFlight: " + flightNumber + "\nClass: " + seatClass + "\n----------------------";
    }
}

class HotelBooking extends Booking {
    private String hotelName;
    private String roomType;

    public HotelBooking(String customerName, String bookingDate, String hotelName, String roomType) {
        super(customerName, bookingDate);
        this.hotelName = hotelName;
        this.roomType = roomType;
    }

    public String getDetails() {
        return super.getDetails() + "\nHotel: " + hotelName + "\nRoom: " + roomType + "\n----------------------";
    }
}

public class BookingGUI {
    private List<Booking> bookings = new ArrayList<>();
    private JTextArea displayArea;

    public BookingGUI() {
        JFrame frame = new JFrame("Booking System");
        frame.setSize(950, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text area to display bookings
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Buttons
        JButton addFlightButton = new JButton("Add Flight Booking");
        JButton addHotelButton = new JButton("Add Hotel Booking");
        JButton viewBookingsButton = new JButton("View All Bookings");
        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addFlightButton);
        buttonPanel.add(addHotelButton);
        buttonPanel.add(viewBookingsButton);
        // Button Actions
        addFlightButton.addActionListener(e -> addFlightBooking());
        addHotelButton.addActionListener(e -> addHotelBooking());
        viewBookingsButton.addActionListener(e -> viewBookings());
        // Add components to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    private void addFlightBooking() {
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField flightField = new JTextField();
        JTextField classField = new JTextField();
        Object[] message = {
                "Customer Name:", nameField,
                "Booking Date (YYYY-MM-DD):", dateField,
                "Flight Number:", flightField,
                "Seat Class:", classField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Add Flight Booking", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            bookings.add(new FlightBooking(nameField.getText(), dateField.getText(), flightField.getText(), classField.getText()));
            JOptionPane.showMessageDialog(null, "Flight Booking Added!");
        }
    }
    private void addHotelBooking() {
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField hotelField = new JTextField();
        JTextField roomField = new JTextField();
        Object[] message = {
                "Customer Name:", nameField,
                "Booking Date (YYYY-MM-DD):", dateField,
                "Hotel Name:", hotelField,
                "Room Type:", roomField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Add Hotel Booking", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            bookings.add(new HotelBooking(nameField.getText(), dateField.getText(), hotelField.getText(), roomField.getText()));
            JOptionPane.showMessageDialog(null, "Hotel Booking Added!");
        }
    }
    private void viewBookings() {
        if (bookings.isEmpty()) {
            displayArea.setText("No bookings available.");
        } else {
            StringBuilder sb = new StringBuilder("\n--- All Bookings ---\n");
            for (Booking b : bookings) {
                sb.append(b.getDetails()).append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }
    public static void main(String[] args) {
        new BookingGUI();
    }
}