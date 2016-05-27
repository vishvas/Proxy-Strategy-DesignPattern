package genericCheckpointing.Strategy;

import genericCheckpointing.util.SerializableObject;

/**
 * Serialization Interface 
 * Return String of an Object
 * @author Vishvas
 *
 */
public interface SerStrategy{
	public void processInput(SerializableObject sObject);
}
