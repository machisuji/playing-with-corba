package StockObjects;

/**
* StockObjects/UnknownHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from stock.idl
*/

public final class UnknownHolder implements org.omg.CORBA.portable.Streamable
{
  public StockObjects.Unknown value = null;

  public UnknownHolder ()
  {
  }

  public UnknownHolder (StockObjects.Unknown initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = StockObjects.UnknownHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    StockObjects.UnknownHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return StockObjects.UnknownHelper.type ();
  }

}
