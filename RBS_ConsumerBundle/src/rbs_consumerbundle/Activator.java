package rbs_consumerbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import rbs_producerbundle.RoomBookingService;
import bcs_producerbundle.BillingService;
import java.util.HashMap;

public class Activator implements BundleActivator {
    private RoomBookingService bookingService;
    private BillingService billingService;
    private boolean running = true;
    private static final HashMap<Integer, Integer> bookingPrices = new HashMap<>(); // ✅ Store total price per room

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("[RBS Consumer] Starting RBS Consumer Bundle...");

        while (running) {
            ServiceReference<?> rbRef = context.getServiceReference(RoomBookingService.class.getName());
            if (rbRef != null) {
                bookingService = (RoomBookingService) context.getService(rbRef);
                if (bookingService != null) {
                    int roomId = bookingService.getLatestRoomId();
                    if (roomId != -1) { 
                        String roomType = bookingService.getRoomType(roomId);
                        int noOfRooms = bookingService.getNoOfRooms(roomId);
                        int noOfDays = bookingService.getNoOfDays(roomId);

                        System.out.println("[RBS Consumer] Retrieved Booking Details:");
                        System.out.println("Room Type: " + roomType + ", No. of Rooms: " + noOfRooms + ", No. of Days: " + noOfDays);

                        ServiceReference<?> billRef = context.getServiceReference(BillingService.class.getName());
                        if (billRef != null) {
                            billingService = (BillingService) context.getService(billRef);
                            if (billingService != null) {
                                int pricePerRoom = billingService.getRoomPrice(roomType);
                                int totalBookingPrice = pricePerRoom * noOfRooms * noOfDays;

                                // ✅ Store total price in HashMap
                                bookingPrices.put(roomId, totalBookingPrice);

                                System.out.println("[RBS Consumer] ✅ Final Booking Price: ₹" + totalBookingPrice);
                            } else {
                                System.out.println("[RBS Consumer] ❌ BillingService is NULL.");
                            }
                        } else {
                            System.out.println("[RBS Consumer] ❌ BillingService NOT FOUND.");
                        }
                    } else {
                        System.out.println("[RBS Consumer] No bookings found yet...");
                    }
                } else {
                    System.out.println("[RBS Consumer] ❌ RoomBookingService is NULL.");
                }
            } else {
                System.out.println("[RBS Consumer] ❌ RoomBookingService NOT FOUND.");
            }

            Thread.sleep(5000); 
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        running = false;
        System.out.println("[RBS Consumer] Stopping RBS Consumer Bundle...");
    }
}
