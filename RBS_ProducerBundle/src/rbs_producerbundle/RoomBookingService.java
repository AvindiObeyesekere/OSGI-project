package rbs_producerbundle;

public interface RoomBookingService {
    void bookRoom(Room room);  
    java.util.HashMap<Integer, Room> getBookedRooms();  
    String getRoomType(int roomId);  
    int getNoOfRooms(int roomId);  
    int getNoOfDays(int roomId);  
    int getLatestRoomId();  // New method to retrieve the latest booked room ID
    int getTotalPrice(int roomId);
    void setTotalPrice(int roomId, int totalPrice);
}
