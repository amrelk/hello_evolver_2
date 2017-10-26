package evolver;

import java.time.Instant;

public class Evolver {
	
	static String target = "Hello, I am malcolm.";
	static int popsize = 100;
	
	public static void main(String[] args) throws InterruptedException {
		
		//SET UP POPULATIONS
		Population population = new Population(popsize);
		int bestscore = 0;
		double mutationRate = 0.1;
		double lastMutationRate = 0.05;
		boolean lastInc = true;
		
		//SET UP STATS STUFF
		int generation = 0;
		Instant starttime = Instant.now();
		boolean stopOnFalse = true;
		float millisElapsed = 0;
		float lastMillisElapsed;
		
		while (stopOnFalse) {
			//MAIN ALGORITHM LOOP
			do {
				generation++;
				population.breed();
				population.mutate(0.2);
				//population.displayBest();
			} while (population.bestScore() > 0);
		
			//DISPLAY STATS STUFF
			Instant stoptime = Instant.now();
			lastMillisElapsed = millisElapsed;
			millisElapsed = stoptime.minusMillis(starttime.toEpochMilli()).toEpochMilli();
			System.out.println("DONE IN " + (millisElapsed / 1000) + "s: generation " + generation);
			
			if (mutationRate > lastMutationRate && millisElapsed < lastMillisElapsed) {
			}
		}
	}
	
}
