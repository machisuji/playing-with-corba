package stock;

import StockObjects.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManagerPackage.State;

public class Client {
    public void run() {
        try {
            ORB orb = StockORB.getInstance();
            org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "StockFactory";
            StockFactory factory = StockFactoryHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtained a handle on server object");

            Stock stock = factory.create_stock("DE", "Deutschland?");

            System.out.println("got stock: " + stock);
        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }
}