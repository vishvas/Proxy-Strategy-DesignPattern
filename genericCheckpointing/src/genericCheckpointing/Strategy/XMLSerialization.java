package genericCheckpointing.Strategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;


public class XMLSerialization implements SerStrategy{
	private FileProcessor fp;
	ArrayList<String> list;
	
	public XMLSerialization(FileProcessor fpIn) {
		fp = fpIn;
	}
	/**
	 * Take Serializable object as argument 
	 * convert into wire Format
	 */
	public void processInput(SerializableObject  sObject){
		list = new ArrayList<String>();
		String fieldName;
		Class<? extends Object> className = sObject.getClass();
		Method myMethod = null;
		String signature =null;
		Object value=null;
		String fieldType;
		
		Field[] fields = className.getDeclaredFields();
		
		list.add("<DPSerialization>\n");
		list.add(" <complexType xsi:type=\"" + className.getCanonicalName() + "\">\n");
		
		for (Field field : fields) {
			fieldType = field.getType().getSimpleName().toLowerCase();
			fieldName = field.getName();
			signature = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); //getMyInt
			
			/**
			 * Getting method for given class
			 */
			try {
				myMethod = className.getMethod(signature);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
			/**
			 * Invoking the method for object
			 */
			try {
				value = myMethod.invoke(sObject);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			/**
			 * creating serialize string
			 * the value which got by invoking the method
			 */
//			if(fieldType.equals("class java.lang.String")){
//				list.add(serialize(fieldName,"string",value));
//			}else{
				list.add(serialize(fieldName,fieldType,value));
			//}
		}

		list.add(" </complexType>\n");
		list.add("</DPSerialization>\n");
		
		for (String string : list) {
			//bw.write(string);
			fp.storeLine(string);
		}
		
	}
	
	/**
	 * Make a String Format
	 * @param field
	 * @param fieldType
	 * @param value
	 * @return
	 */
	private String serialize(String field,String fieldType,Object value){
		String s;
		s="  <"+field+" xsi:type=\"xsd:"+fieldType+"\">"+value.toString() +"</"+field+">\n";
		return s;
	}
}
