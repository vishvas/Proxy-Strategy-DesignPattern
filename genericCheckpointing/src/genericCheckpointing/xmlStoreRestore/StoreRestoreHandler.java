package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.Strategy.*;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {

	FileProcessor fp=null;

	public StoreRestoreHandler(FileProcessor fpIn) {
		fp = fpIn;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String strategyType;
		String methodName = method.getName();
		if (methodName.equals("writeObj")) {
			strategyType = args[1].toString();
			if (strategyType.equals("XML")) {
				serializeData((SerializableObject)args[0], new XMLSerialization(fp));
			} else if (strategyType.equals("json")) {

			}

		} else if (methodName.equals("readObj")) {
			strategyType = args[0].toString();
			SerializableObject obj=null;
			if (strategyType.equals("XML")) {
				obj = (SerializableObject) DeserializeData(new XMLDeserialization(fp));
			} else if (strategyType.equals("json")) {

			}
			return obj;
		} else {
			System.out.println("Wrong function selection for object, either can be writeObj or readObj");
			System.exit(0);
		}

		return null;
	}

	/**
	 * Serialize Object into Given strategy
	 * @param sObject
	 * @param sStrategy
	 */
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject);
	}
	
	/**
	 * Deserialize Wire Format into Object using 
	 * Given Strategy
	 * @param DeStrategy
	 * @return
	 */
	public SerializableObject DeserializeData(DeserStrategy DeStrategy) {
		SerializableObject obj = DeStrategy.getDeserObject();
		return obj;
	}
	
}
