package de;

public class GenerateRandomVariates {

	
    public static int getNextGroupSize(){
		
		//int randomint = (int)(Math.random()*(max) + min);
		double rando = Math.random();
		if(rando < .5){
		return 1;
		}
		if(rando >= .5 && rando < .75){
		return 2;
		}
		if(rando >= .75){
		return 3;
		}
		return 0;
	}
	
	public static double generateExponentialRandomVariable(double alpha){
		
		double randomVariable = Math.random();
			
			double erv = Math.log(1-randomVariable)/(-1/alpha);
			
			return erv;
		}
}
