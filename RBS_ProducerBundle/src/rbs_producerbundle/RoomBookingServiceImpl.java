package rbs_producerbundle;

import java.util.HashMap;

public class RoomBookingServiceImpl implements RoomBookingService {
    private final HashMap<Integer, Room> bookedRooms = new HashMap<>();
    private final HashMap<Integer, Integer> bookingPrices = new HashMap<>();
    private static int roomIdCounter = 1; // Auto-incrementing Room ID

    public RoomBookingServiceImpl() {
        new RoomBookingUI(this); // Open UI on startup
    }

    @Override
    public void bookRoom(Room room) {
        int roomId = roomIdCounter++; // Auto-generate room ID
        bookedRooms.put(roomId, room);
        System.out.println("[RBS Producer] Room Booked: " + room);
    }

    @Override
    public HashMap<Integer, Room> getBookedRooms() {
        return bookedRooms;
    }

    @Override
    public String getRoomType(int roomId) {
        return bookedRooms.containsKey(roomId) ? bookedRooms.get(roomId).getRoomType() : "Room Not Found";
    }

    @Override
    public int getNoOfRooms(int roomId) {
        return bookedRooms.containsKey(roomId) ? bookedRooms.get(roomId).getNoOfRooms() : 0;
    }

    @Override
    public int getNoOfDays(int roomId) {
        return bookedRooms.containsKey(roomId) ? bookedRooms.get(roomId).getNoOfDays() : 0;
    }

    // ✅ New method to get the latest booked room ID
    @Override
    public int getLatestRoomId() {
        if (bookedRooms.isEmpty()) {
            return -1;  // Return -1 if no rooms are booked
        }
        return bookedRooms.keySet().stream().max(Integer::compare).orElse(-1);
    }
    
    // ✅ Store total price when received from Consumer
    public void setTotalPrice(int roomId, int price) {
        bookingPrices.put(roomId, price);
    }
    // ✅ Fetch total price for UI display
    @Override
    public int getTotalPrice(int roomId) {
        return bookingPrices.getOrDefault(roomId, 0);
    }
    
}
