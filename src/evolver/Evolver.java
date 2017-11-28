package evolver;

import java.time.Instant;

public class Evolver {
	
	static String target = "Hello, World!";
	static int popsize = 5000000;
	
	public static void main(String[] args) throws InterruptedException {
		
		//SET UP POPULATIONS
		Population population = new Population(popsize);
		double mutationRate = 0.03;
		
		//SET UP STATS STUFF
		int generation = 0;
		/**
		 * the start time
		 */
		Instant starttime = Instant.now();
		float millisElapsed = 0;
		 
			//MAIN ALGORITHM LOOP
			generation = 0;
			population = new Population(popsize);
			starttime = Instant.now();
			do {
				generation++;
				population.breed();
				population.mutate(mutationRate);
				population.displayBest(starttime);
			} while (population.bestScore() > 0);
		
			//DISPLAY STATS STUFF
			Instant stoptime = Instant.now();
			millisElapsed = stoptime.minusMillis(starttime.toEpochMilli()).toEpochMilli();
			System.out.println("DONE IN " + (millisElapsed / 1000) + "s:\tgeneration " + generation + ":\tmutation rate is " + mutationRate * 100 + "%");
	}
	
}
