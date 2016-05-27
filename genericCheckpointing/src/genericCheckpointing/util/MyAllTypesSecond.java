package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{
	
	private double myDoubleT;
	private float myFloatT;
	private char myCharT;
	private short myShortT;
	
	public MyAllTypesSecond(){
		
	}
	/**
	 * MyAllTypesSecond Constructor
	 * @param myIntIn
	 * @param myLongIn
	 * @param myStringIn
	 * @param myBoolIn
	 */
	public MyAllTypesSecond(double myDoubleIn, float myFloatIn, short myShortIn, char myCharIn){
		myDoubleT = myDoubleIn;
		myFloatT = myFloatIn;
		myCharT = myCharIn;
		myShortT = myShortIn;
	}


	public double getMyDoubleT() {
		return myDoubleT;
	}

	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	public float getMyFloatT() {
		return myFloatT;
	}

	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	public char getMyCharT() {
		return myCharT;
	}

	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	public short getMyShortT() {
		return myShortT;
	}

	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	@Override
    public boolean equals(Object objIn){
		if(objIn==null){
			return false;
		}
		if(!(objIn instanceof MyAllTypesSecond)){
			return false;
		}
		MyAllTypesSecond obj =(MyAllTypesSecond)objIn;
		
		if(this.hashCode()==obj.hashCode()){
			return true;
		}
    	return false;
	}
	
	@Override
	public String toString() {
		
		String s;
		s="Double: "+ this.myDoubleT +"\nFloat: "+ this.myFloatT+"\nShort:"+ this.myShortT +"\nChar:"+ this.myCharT;
		return s;
	}
	
	@Override
	public int hashCode(){
		int hashValue;
		String s;
		s=String.valueOf(this.myDoubleT)+String.valueOf(this.myFloatT)+ String.valueOf(this.myShortT)+String.valueOf(this.myCharT);
		hashValue = s.hashCode();
		return hashValue;
	}
}
