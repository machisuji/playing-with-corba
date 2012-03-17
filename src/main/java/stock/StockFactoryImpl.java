package stock;

import StockObjects.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class StockFactoryImpl extends StockFactoryPOA {

    public Stock create_stock(String symbol, String description) {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
        StockImpl newStock = new StockImpl(null, description);

        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        if (rootpoa.the_POAManager().get_state() != POAManagerPackage.State.ACTIVE) {
            rootpoa.the_POAManager().activate();
        }
        newStock.setORB(orb);

        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
        Stock stockRef = StockHelper.narrow(ref);

        register(stockRef, "stock/" + symbol);

        return stockRef;
    }

    protected void register(Stock stock, String name) {
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt nc = NamingContextExtHelper.narrow(objRef);
        NameComponent path[] = nc.to_name(name);

        nc.rebind(path, stock);
    }
}