package rbs_producerbundle;

public class Room {
    private static int roomIdCounter = 1; // Auto-incrementing Room ID
    private final int roomId;  // Unique room identifier
    private int roomNo;        // Actual room number
    private String roomType;   // Directly storing room type name
    private int noOfPeople;    // Number of guests
    private int noOfRooms;     // Number of rooms booked
    private int noOfDays;      // Duration of stay
    private String checkInDate;
    private String checkOutDate;

    public Room(int roomNo, String roomType, int noOfPeople, int noOfRooms, int noOfDays, String checkInDate, String checkOutDate) {
        this.roomId = roomIdCounter++; // Auto-incrementing Room ID
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.noOfPeople = noOfPeople;
        this.noOfRooms = noOfRooms;
        this.noOfDays = noOfDays;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public int getRoomId() { return roomId; }  // Returns auto-generated Room ID
    public int getRoomNo() { return roomNo; }
    public String getRoomType() { return roomType; }
    public int getNoOfPeople() { return noOfPeople; }
    public int getNoOfRooms() { return noOfRooms; }  // Returns user-inputted noOfRooms
    public int getNoOfDays() { return noOfDays; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }

    @Override
    public String toString() {
        return "[Room ID: " + roomId + ", Room No: " + roomNo + ", Type: " + roomType +
               ", People: " + noOfPeople + ", Rooms: " + noOfRooms + ", Days: " + noOfDays + 
               ", Check-In: " + checkInDate + ", Check-Out: " + checkOutDate + "]";
    }
}
