package genericCheckpointing.util;

import java.util.Random;

public class RandomGenerator {
	
	private Random rand=null;
	public RandomGenerator() {
		rand = new Random();
	}
	public int randomInt(){  //generate Random int
		return (int)(rand.nextInt(100)+1);
	}
	
	
	public float randomFloat(){ //generate Random float
		return (float)(Math.random() * 50 + 1);
	}
	
	public double randomDouble(){ //generate Random double
		return (double)(Math.random() * 500 + 1);
	}
	
	public short randomShort(){ //generate Random short
		return (short)(rand.nextInt(100)+1);
	}
	
	public long randomLong(){ //generate Random long
		return (long)(rand.nextInt(1000)+1);
	}
	
	public boolean randomBool(){ //generate Random boolean
		return rand.nextBoolean();
	}
}
