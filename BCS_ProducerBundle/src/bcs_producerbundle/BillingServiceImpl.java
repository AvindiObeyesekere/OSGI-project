package bcs_producerbundle;

import java.util.HashMap;

public class BillingServiceImpl implements BillingService {
    private final HashMap<String, Integer> roomPrices = new HashMap<>();

    public BillingServiceImpl() {
        // 🔹 Predefined room prices
        roomPrices.put("Single Room", 10000);
        roomPrices.put("Double Room", 20000);
        roomPrices.put("Twin Room", 25000);
        roomPrices.put("Suite", 50000);
        roomPrices.put("Deluxe", 100000);
        roomPrices.put("Standard Room", 35000);
    }

    @Override
    public int getRoomPrice(String roomType) {
        return roomPrices.getOrDefault(roomType, 0); // Return 0 if room type is unknown
    }
}
