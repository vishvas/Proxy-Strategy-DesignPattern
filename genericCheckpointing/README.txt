Author(s): Vishvas Patel

PURPOSE: Implement Proxy pattern with combination of strategy pattern to make object into serialization and deserialization.

FILES: 
-genericCheckpointing.driver
	--Driver.java 
-genericCheckpointing.server
	--StoreI.java
	-- RestoreI.java 
	--StoreRestoreI.java
-enericCheckpointing.Strategy	
	--DeserStrategy.java
	--SerStrategy.java
	--XMLDeserialization.java
	--XMLSerialization.java 
-genericCheckpointing.util
	--MyAllTypesFirst.java
	--MyAllTypesSecond.java
	--ProxyCreator.java
	--RandomGenerator.java
	--SerializableObject.java 
-genericCheckpointing.xmlStoreRestore
	--StoreRestoreHandler.java 

SAMPLE OUTPUT: 
--serdeser Mode:
0 MissMatchedObject

--deser Mode:
Deserilized Object:1 
Int: 4
Long: 416
String:Design Patterns
Bool:true
Deserilized Object:2 
Double: 382.72188565390684
Float: 5.7336745
Short:74
Char:P


Arguments:
	1.Mode
	2.No_Of_Object_Iteration
	3.InputFile(if mode deser) / OutputFile (if mode serdeser)

Operating The Code: From the vishvas_patel_assign5/genericCheckpointing/

COMPILE THE CODE: ant -buildfile build.xml all

RUN THE CODE: ant -buildfile build.xml run -Darg0=serdeser -Darg1=1 -Darg2=Input.txt

Clean Object Code: ant -buildfile build.xml clean

CHOICE OF DATA STRUCTURE: Vector
->Complexity of Data Structure in avg and worst case:
		
Space	O(n)
Search	O(n)
Insert	O(1) 
Delete	O(1)


CODE REFERENCE:
-Lecture slides of Reflection, Proxy Pattern
http://tutorials.jenkov.com/java-reflection/index.html
https://examples.javacodegeeks.com/core-java/reflection/java-reflection-example/
https://docs.oracle.com/javase/tutorial/reflect/

