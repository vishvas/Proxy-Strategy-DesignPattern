package genericCheckpointing.Strategy;

import genericCheckpointing.util.SerializableObject;

/**
 * Deserialization Interface 
 * Return Serializable Object
 * @author Vishvas
 *
 */
public interface DeserStrategy{
	public SerializableObject getDeserObject();
}
