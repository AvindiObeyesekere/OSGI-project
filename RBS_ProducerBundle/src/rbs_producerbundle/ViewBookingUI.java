package rbs_producerbundle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ViewBookingUI {
    private RoomBookingServiceImpl bookingService;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewBookingUI(RoomBookingServiceImpl service) {
        this.bookingService = service;
        createUI();
    }

    private void createUI() {
        frame = new JFrame("View All Bookings");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table Setup
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Room ID", "Room No", "Room Type", "People", "Rooms", "Days", "Check-In", "Check-Out"});

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");

        // Refresh Button Action
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBookings();
            }
        });

        buttonPanel.add(refreshButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Load Bookings Initially
        loadBookings();

        frame.setVisible(true);
    }

    private void loadBookings() {
        // Clear existing table data
        tableModel.setRowCount(0);

        HashMap<Integer, Room> bookings = bookingService.getBookedRooms();
        for (Map.Entry<Integer, Room> entry : bookings.entrySet()) {
            Room room = entry.getValue();
            tableModel.addRow(new Object[]{
                    room.getRoomId(), room.getRoomNo(), room.getRoomType(), room.getNoOfPeople(),
                    room.getNoOfRooms(), room.getNoOfDays(), room.getCheckInDate(), room.getCheckOutDate()
            });
        }
    }
}
