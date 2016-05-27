package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	
	public MyAllTypesFirst(){
		
	}
	/**
	 * MyAllTypesFirst Constructor
	 * @param myIntIn
	 * @param myLongIn
	 * @param myStringIn
	 * @param myBoolIn
	 */
	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn){
		myInt = myIntIn;
		myLong = myLongIn;
		myString = myStringIn;
		myBool = myBoolIn;
	}

	

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public boolean getMyBool() {
		return myBool;
	}

	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}

	@Override
    public boolean equals(Object objIn){
		if(objIn==null){
			return false;
		}
		if(!(objIn instanceof MyAllTypesFirst)){
			return false;
		}
		MyAllTypesFirst obj =(MyAllTypesFirst)objIn;
		
		if(this.hashCode()==obj.hashCode()){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s;
		s="Int: "+ this.myInt +"\nLong: "+ this.myLong+"\nString:"+ this.myString +"\nBool:"+ this.myBool;
		return s;
	}
	
	@Override
	public int hashCode(){
		int hashValue;
		String s;
		s=String.valueOf(this.myInt)+ String.valueOf(this.myLong)+ this.myString + String.valueOf(this.myBool);
		hashValue = s.hashCode();
		return hashValue;
	}
	
	
}

