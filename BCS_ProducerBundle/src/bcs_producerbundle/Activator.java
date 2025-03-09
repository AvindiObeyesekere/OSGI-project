package bcs_producerbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    private BillingServiceImpl service;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("[BCS Producer] Registering Billing Service...");
        service = new BillingServiceImpl();
        context.registerService(BillingService.class.getName(), service, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("[BCS Producer] Stopping Billing Service...");
    }
}
