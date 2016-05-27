package genericCheckpointing.Strategy;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLDeserialization implements DeserStrategy{
	private FileProcessor fp=null;
	public XMLDeserialization(FileProcessor fpIn) {
		fp = fpIn;
	}
	public SerializableObject getDeserObject(){
		
		String line = null;
		Method myMethod = null;
		try {
			Class<?> c = null;
			Object obj = null;
			Pattern p = null;
			Matcher m = null;
			String typeName;
			String typeValue;
			String signature;
			String tagName;
			line = fp.readLineFromFile();
			while (line!= null) {

				if (line.contains("xsi:type")) { 

					if (line.contains("complexType")) {  // creating an Object for givenString
						p = Pattern.compile("\"([^\"]*)\"");
						m = p.matcher(line);
						if (m.find()) {

							String objectName = m.group(1);
							try {
								c = Class.forName(objectName);
								obj = c.newInstance();

							} catch (ClassNotFoundException e) {
								System.err.println(objectName + " class is not found");
								e.printStackTrace();
								System.exit(0);
							}
						}

					}
 
					/**
					 * Set value for fields of an object instance
					 */
					if (line.contains("xsd:")) { 
						p = Pattern.compile("\"xsd:([^\"]*)\">(s*.*s*)<\\/(s*.*s*)>");
						m = p.matcher(line);
						if (m.find()) {
							
							typeName = m.group(1).toString(); 
							typeValue = m.group(2).toString(); 
							tagName = m.group(3).toString();
							signature = "set" + tagName.substring(0, 1).toUpperCase() + tagName.substring(1); //getMyInt
							switch (typeName) {
							case "int":
								myMethod = c.getMethod(signature, Integer.TYPE);
								myMethod.invoke(obj, Integer.parseInt(typeValue));
								break;
							case "string":
								myMethod = c.getMethod(signature, String.class);
								myMethod.invoke(obj, typeValue);
								break;
							case "long":
								myMethod = c.getMethod(signature, Long.TYPE);
								myMethod.invoke(obj, Long.parseLong(typeValue));
								break;
							case "boolean":
								Boolean b = new Boolean(typeValue);
								myMethod = c.getMethod(signature, Boolean.TYPE);
								myMethod.invoke(obj, b);
								break;
							case "double":
								myMethod = c.getMethod(signature, Double.TYPE);
								myMethod.invoke(obj, Double.parseDouble(typeValue));
								break;
							case "float":
								myMethod = c.getMethod(signature, Float.TYPE);
								myMethod.invoke(obj, Float.parseFloat(typeValue));
								break;
							case "char":
								myMethod = c.getMethod(signature, Character.TYPE);
								myMethod.invoke(obj, typeValue.charAt(0));
								break;
							case "short":
								myMethod = c.getMethod(signature, Short.TYPE);
								myMethod.invoke(obj, Short.parseShort(typeValue));
								break;
							default:
								break;
							}
						}

					}
				} else if (line.equals("</DPSerialization>")) {
					return (SerializableObject) obj;
				}
				line = fp.readLineFromFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
