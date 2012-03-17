package StockObjects;

/**
* StockObjects/StockFactoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from stock.idl
*/

public final class StockFactoryHolder implements org.omg.CORBA.portable.Streamable
{
  public StockObjects.StockFactory value = null;

  public StockFactoryHolder ()
  {
  }

  public StockFactoryHolder (StockObjects.StockFactory initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = StockObjects.StockFactoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    StockObjects.StockFactoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return StockObjects.StockFactoryHelper.type ();
  }

}
