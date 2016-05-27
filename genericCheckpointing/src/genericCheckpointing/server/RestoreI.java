package genericCheckpointing.server;



import genericCheckpointing.util.SerializableObject;
/**
 * Restore Interface for Serializable Object 
 * conver wireFormat into Object
 * @author Vishvas
 *
 */
public interface RestoreI extends StoreRestoreI {
    SerializableObject readObj(String wireFormat);
}