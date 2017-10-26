package evolver;

import java.time.Instant;

public class Evolver {
	
	static String target = "Hello, I am malcolm.";
	static int popsize = 1000;
	
	public static void main(String[] args) throws InterruptedException {
		
		//SET UP POPULATIONS
		Population population = new Population(popsize);
		int bestscore = 0;
		double mutationRate = 0.01;
		double lastMutationRate = 0.1;
		boolean lastInc = true;
		
		//SET UP STATS STUFF
		int generation = 0;
		Instant starttime = Instant.now();
		boolean stopOnFalse = true;
		float millisElapsed = 0;
		float lastMillisElapsed;
		
		/*while (stopOnFalse) {
			generation = 0;
			population = new Population(popsize);
			starttime = Instant.now();*/
			//MAIN ALGORITHM LOOP
		while(true) {
			generation = 0;
			population = new Population(popsize);
			starttime = Instant.now();
			do {
				generation++;
				population.breed();
				population.mutate(mutationRate);
				//population.displayBest();
			} while (population.bestScore() > 0);
		
			//DISPLAY STATS STUFF
			Instant stoptime = Instant.now();
			lastMillisElapsed = millisElapsed;
			millisElapsed = stoptime.minusMillis(starttime.toEpochMilli()).toEpochMilli();
			System.out.println("DONE IN " + (millisElapsed / 1000) + "s:\tgeneration " + generation + ":\tmutation rate is " + mutationRate * 100 + "%");
			
			if (mutationRate > lastMutationRate && millisElapsed < lastMillisElapsed) {
				lastMutationRate = mutationRate;
				mutationRate += 0.001;
			} else if (mutationRate > lastMutationRate && millisElapsed > lastMillisElapsed) {
				lastMutationRate = mutationRate;
				mutationRate -= 0.001;
			} else if (mutationRate < lastMutationRate && millisElapsed < lastMillisElapsed) {
				lastMutationRate = mutationRate;
				mutationRate -= 0.001;
			} else {
				lastMutationRate = mutationRate;
				mutationRate += 0.001;
			}
		}
			/*
			if (mutationRate > lastMutationRate && millisElapsed < lastMillisElapsed) {
				mutationRate += 0.005;
			} else if (mutationRate > lastMutationRate && millisElapsed > lastMillisElapsed) {
				mutationRate -= 0.005;
			} else if (mutationRate < lastMutationRate && millisElapsed < lastMillisElapsed) {
				mutationRate -= 0.005;
			} else {
				mutationRate += 0.005;
			}
		}*/
	}
	
}
