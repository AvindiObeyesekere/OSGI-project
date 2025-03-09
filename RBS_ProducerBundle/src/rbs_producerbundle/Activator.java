package rbs_producerbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    private RoomBookingServiceImpl service;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("[RBS Producer] Starting Room Booking Service...");
        service = new RoomBookingServiceImpl();
        context.registerService(RoomBookingService.class.getName(), service, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("[RBS Producer] Stopping Room Booking Service...");
    }
}
