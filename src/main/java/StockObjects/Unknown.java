package StockObjects;


/**
* StockObjects/Unknown.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from stock.idl
*/

public final class Unknown extends org.omg.CORBA.UserException
{

  public Unknown ()
  {
    super(UnknownHelper.id());
  } // ctor


  public Unknown (String $reason)
  {
    super(UnknownHelper.id() + "  " + $reason);
  } // ctor

} // class Unknown
