
package genericCheckpointing.driver;

import java.lang.reflect.InvocationHandler;
import java.util.Vector;

import genericCheckpointing.util.*;
import genericCheckpointing.xmlStoreRestore.*;
import genericCheckpointing.server.*;

public class Driver {

	public static void main(String[] args) {
		/**
		 * Validation for the arguments
		 */
		if (args.length < 3 || args.length > 3) {
			System.err.println("No of argument should be 3. <1>Mode <2>No of Objects <3>Input/Output File Name");
			System.exit(0);
		}
		String mode = args[0].toString();
		if (!(mode.equals("serdeser") || mode.equals("deser"))) {
			System.err.println("Mode should be either \"serdeser\" or \"deser\"");
			System.exit(0);
		}
		int NUM_OF_OBJECTS = 0;
		try {
			NUM_OF_OBJECTS = Integer.parseInt(args[1].toString());
		} catch (NumberFormatException e) {
			System.err.println("No of objects should be an integer");
			System.exit(0);
		}

		String fileName = args[2].toString();
		FileProcessor fp = new FileProcessor(fileName); // creating fileProcessor instance
		ProxyCreator pc = new ProxyCreator(); //created ProxyCreator instance
		InvocationHandler handler = new StoreRestoreHandler(fp);  // created Invocation Handler
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class }, handler);
		RandomGenerator rand = new RandomGenerator(); //RandomGeneration of int.float,double
		SerializableObject myRecordRet = null;
		Vector<SerializableObject> serializeVector = null; //vectore for serialized Object
		Vector<SerializableObject> deserializeVector = null; //vector for deserialized Object
		MyAllTypesFirst myFirst = null;
		MyAllTypesSecond mySecond = null;
		
		if (mode.equals("serdeser")) {
			fp.createWriter();
			serializeVector = new Vector<>();
			deserializeVector = new Vector<>();
			for (int i = 0; i < NUM_OF_OBJECTS; i++) {
				myFirst = new MyAllTypesFirst(rand.randomInt(),rand.randomLong(),"Design Patterns",rand.randomBool());
				mySecond = new MyAllTypesSecond(rand.randomDouble(), rand.randomFloat(), rand.randomShort(), 'P');
				
				serializeVector.add(myFirst);
				serializeVector.add(mySecond);
				
				((StoreI) cpointRef).writeObj(myFirst, "XML");
				((StoreI) cpointRef).writeObj(mySecond, "XML");
			}
			
			fp.closeWriter();
		} 
		if (mode.equals("serdeser")||mode.equals("deser")) { // deserilzation part for both mode
			fp.createReader();
			deserializeVector = new Vector<>();
			for (int j = 0; j < 2 * NUM_OF_OBJECTS; j++) {
				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
				if(myRecordRet!=null){
					deserializeVector.addElement(myRecordRet);
				}
				
			}
			fp.closeReader();
		}
		
		
		/**
		 * Serdser mode object equalization and 
		 * printing of mismatched objects
		 */
		if(mode.equals("serdeser")){	
			if (serializeVector.size() != deserializeVector.size()) {
				System.out.println((2 * NUM_OF_OBJECTS) + " Mismatched Object");
				System.exit(0);
			} else {
				int matchedObject = 0;
				for (int i = 0; i < deserializeVector.size(); i++) {
					if (deserializeVector.get(i).equals(serializeVector.get(i))) {
						matchedObject++;
					}else{
						SerializableObject obj1 = deserializeVector.get(i);
						SerializableObject obj2 = serializeVector.get(i);
						System.out.println("Mismatched Serialize Object\n"+ obj2.toString());
						System.out.println("Mismatched Deserialize Object\n"+ obj1.toString());
					}
				}
				System.out.println((deserializeVector.size() - matchedObject) + " MissMatchedObject");

			}
		}
		/**
		 * Deserialize Mode object printing
		 */
		if(mode.equals("deser")){
			int objectNo=1;
			for (SerializableObject deserializableObject : deserializeVector) {
				System.out.println("Deserilized Object:"+ (objectNo++) +" \n"+deserializableObject.toString());
			}
		}

		
		
		
	}
	
	
}