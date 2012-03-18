package stock;

import StockObjects.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManagerPackage.State;

public class StockFactoryImpl extends StockFactoryPOA {

    public Stock create_stock(String symbol, String description) {
        try {
            ORB orb = StockORB.getInstance();
            StockImpl newStock = new StockImpl(null, description);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            if (rootpoa.the_POAManager().get_state() != State.ACTIVE) {
                rootpoa.the_POAManager().activate();
            }
            byte[] id = rootpoa.activate_object(newStock);

            System.out.println("activated object: " + new String(id));

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(newStock);
            Stock stockRef = StockHelper.narrow(ref);

            StockORB.register(stockRef, "stock-" + symbol, orb);

            return stockRef;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Stock creation failed");
        }
    }
}