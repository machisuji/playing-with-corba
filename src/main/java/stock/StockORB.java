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

    public static NamingContextExt getNameService(ORB orb) {
        try {
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt nc = NamingContextExtHelper.narrow(objRef);

            return nc;
        } catch (org.omg.CORBA.ORBPackage.InvalidName e) {
            throw new RuntimeException("Could not get NameService", e);
        }
    }

    public static NamingContextExt getNameService() {
        return getNameService(getInstance());
    }

    public static void register(org.omg.CORBA.Object object, String name, ORB orb) throws Exception {
        NamingContextExt nc = getNameService();
        NameComponent path[] = nc.to_name(name);

        nc.rebind(path, object);
    }

    public static void register(org.omg.CORBA.Object object, String name) throws Exception {
        register(object, name, getInstance());
    }

    public static org.omg.CORBA.Object lookup(String name, ORB orb) {
        NamingContextExt nc = getNameService();
        try {
            return nc.resolve_str(name);
        } catch (org.omg.CosNaming.NamingContextPackage.NotFound e) {
            System.err.println("Not found: " + name);
        } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed e) {
            throw new RuntimeException("Que? " + e.getMessage());
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
            throw new RuntimeException("Invalid Name: " + name + " (" + e.getMessage() + ")");
        }
        return null;
    }

    public static org.omg.CORBA.Object lookup(String name) {
        return lookup(name, getInstance());
    }
}