package stock;

import StockObjects.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManagerPackage.State;

public class Server {
    public void run() {
        org.omg.CORBA.ORB orb = StockORB.getInstance();
        StockFactory factory = createStockFactory(orb);

        System.out.println("Running Server ...");
        orb.run();
        System.out.println("Server stopped");
    }

    public StockFactory createStockFactory(org.omg.CORBA.ORB orb) {
        try {
            StockFactoryImpl factory = new StockFactoryImpl();

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            if (rootpoa.the_POAManager().get_state() != State.ACTIVE) {
                rootpoa.the_POAManager().activate();
            }
            byte[] id = rootpoa.activate_object(factory);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(factory);
            StockFactory factoryRef = StockFactoryHelper.narrow(ref);

            StockORB.register(factoryRef, "StockFactory", orb);

            System.out.println("registered StockFactory");

            return factoryRef;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("StockFactory creation failed");
        }
    }
}