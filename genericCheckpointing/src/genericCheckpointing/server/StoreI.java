package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * Store Interface Convert Object into 
 * String
 * @author Vishvas
 *
 */
public interface StoreI extends StoreRestoreI{
	  void writeObj(MyAllTypesFirst aRecord, String wireFormat);
      void writeObj(MyAllTypesSecond aRecord, String wireFormat);

}
