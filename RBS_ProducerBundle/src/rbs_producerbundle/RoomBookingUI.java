package rbs_producerbundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomBookingUI {
    private RoomBookingServiceImpl service; // Reference to the booking service

    public RoomBookingUI(RoomBookingServiceImpl service) {
        this.service = service;
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Room Booking System");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 2, 5, 5));

        // UI Components
        JLabel roomNoLabel = new JLabel("Room No:");
        JTextField roomNoField = new JTextField();

        JLabel roomTypeLabel = new JLabel("Room Type:");
        String[] roomTypes = {"Single Room", "Double Room", "Twin Room", "Suite", "Deluxe", "Standard Room"};
        JComboBox<String> roomTypeBox = new JComboBox<>(roomTypes);

        JLabel noOfPeopleLabel = new JLabel("No. of People:");
        JTextField noOfPeopleField = new JTextField();

        JLabel noOfRoomsLabel = new JLabel("No. of Rooms:");
        JTextField noOfRoomsField = new JTextField();

        JLabel noOfDaysLabel = new JLabel("No. of Days:");
        JTextField noOfDaysField = new JTextField();

        JLabel checkInLabel = new JLabel("Check-in Date (DD/MM/YYYY):");
        JTextField checkInField = new JTextField();

        JLabel checkOutLabel = new JLabel("Check-out Date (DD/MM/YYYY):");
        JTextField checkOutField = new JTextField();

        JButton submitButton = new JButton("Book Room");
        JButton viewBookingsButton = new JButton("View All Bookings"); // New Button

        // Adding components to frame
        frame.add(roomNoLabel);
        frame.add(roomNoField);
        frame.add(roomTypeLabel);
        frame.add(roomTypeBox);
        frame.add(noOfPeopleLabel);
        frame.add(noOfPeopleField);
        frame.add(noOfRoomsLabel);
        frame.add(noOfRoomsField);
        frame.add(noOfDaysLabel);
        frame.add(noOfDaysField);
        frame.add(checkInLabel);
        frame.add(checkInField);
        frame.add(checkOutLabel);
        frame.add(checkOutField);
        frame.add(new JLabel());
        frame.add(submitButton);
        frame.add(new JLabel()); 
        frame.add(viewBookingsButton); // Add View Bookings Button

        // Submit Button Action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNo = Integer.parseInt(roomNoField.getText());
                    String roomType = (String) roomTypeBox.getSelectedItem();
                    int noOfPeople = Integer.parseInt(noOfPeopleField.getText());
                    int noOfRooms = Integer.parseInt(noOfRoomsField.getText());
                    int noOfDays = Integer.parseInt(noOfDaysField.getText());
                    String checkInDate = checkInField.getText();
                    String checkOutDate = checkOutField.getText();

                    // Validate inputs
                    if (roomNo <= 0 || noOfPeople <= 0 || noOfRooms <= 0 || noOfDays <= 0 ||
                        checkInDate.isEmpty() || checkOutDate.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Create Room Object
                    Room room = new Room(roomNo, roomType, noOfPeople, noOfRooms, noOfDays, checkInDate, checkOutDate);
                    service.bookRoom(room);
                    
                    JOptionPane.showMessageDialog(frame, "Room Booking Successful!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter correct values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View Bookings Button Action
        viewBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewBookingUI(service); // Open ViewBookingUI
            }
        });

        frame.setVisible(true);
    }
}
