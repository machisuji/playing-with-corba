package stock;

import org.omg.CORBA.ORB;
import StockObjects.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAManagerPackage.State;

public class StockORB {
    public static String[] args = {
        "-ORBInitialPort", "1050",
        "-ORBInitialHost", "localhost"
    };

    public static ORB getInstance() {
        ORB orb = org.omg.CORBA.ORB.init(args, null);

        return orb;
    }

    public static void register(org.omg.CORBA.Object object, String name, ORB orb) throws Exception {
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt nc = NamingContextExtHelper.narrow(objRef);
        NameComponent path[] = nc.to_name(name);

        nc.rebind(path, object);
    }
}