package algcomp.alg;

import java.util.Random;

import algcomp.util.*;

//Will only work for minimization problems.... shortest tour or minimum of a function.
public class Genetic {
	Graph gr;
	int gensize;
	double mutprob;
	int immigrants;
	int timer;
	int gencounter;
	boolean graphprob;
	
	Chromosome bestsofar;
	
	Chromosome[] current_generation;
	
	

	/*
	 * Initialize algorithm:
	 * g = graph
	 * mutprob = probability of a chromosome muting
	 * sbs = step-by-step. 
	 * if true, algorithm will return one step at a time,
	 * otherwise, result of full run.
	 * if sbs is false and timer is not zero, timer will be the time in ms for which the alg. will run.
	 * otherwise it will run until the best path remains constant for constantcount generations.
	 */
	public Genetic(Graph _gr,int _gensize, double _mutprob,int _immi, int _timer){
		gr = _gr;
		gensize = _gensize;
		mutprob = _mutprob;
		immigrants = _immi;
		timer = _timer;
		//if a graph is sent as a parameter, it is a graph problem...
		graphprob = true;
		//just a random chromosome... probably not the best thing to do.
		bestsofar = new PathChromosome(gr.size());
		evaluate((PathChromosome)bestsofar);
		System.out.println("firstbest: " +bestsofar.getEval());
		//initialize first generation
		current_generation = new PathChromosome[gensize];
		for(int i=0;i<gensize;i++){
			current_generation[i] = new PathChromosome(gr.size());
		}
	}
	
	/*
	 * This will actually return the best chromosome for the previous generation.
	 * Generation created in this step will not be evaluated until next step is run.
	 */
	public Chromosome step(){
		evaluateGeneration();
		System.out.println("BEST NOW: " + bestsofar.toString());
		
		//create next generation
		Random r = new Random();
		int created = 0;
		double probscore;
		Chromosome[] newgen = new PathChromosome[gensize];
		//System.out.println("NEWGEN");
		while(created < gensize-immigrants){
			//select first parent
			 probscore= r.nextDouble();
			 Chromosome p1 = getChByProb(probscore);
			 probscore= r.nextDouble();
			 Chromosome p2 = getChByProb(probscore);
			 
			 Chromosome nc = p1.crossover(p2);
			 
			 //mutate with mutprob probability
			 probscore= r.nextDouble();
			 if(probscore<=mutprob){
				 nc.mutate();
			 }
			 //add new chromosome to generation
			 newgen[created] = nc;
			 created++;
		}
		//add a random guys every generation, to keep things moving
		for(int i =1;i<=immigrants;i++){
			newgen[gensize-i]=new PathChromosome(gr.size());
		}
		
		current_generation = newgen;
		//printArray(((PathChromosome)bestsofar).getPath());
		//System.out.println("STEP OVER");
		
		return bestsofar;
	}
	
	//full run stopped by timer
	public Chromosome fullrun(){
		final Thread thisThread = Thread.currentThread();
		final int timeToRun = timer; // 1200 = 2 minutes;

		new Thread(new Runnable() {
		    public void run() {
		        try {
					thisThread.sleep(timeToRun);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Problem with putting thread to sleep");
				}
		        thisThread.interrupt();
		    }
		}).start();

		while (!Thread.interrupted()) {
		    step();
		}
		
		return bestsofar;
	}
	
	//evaluate each chromosome and set selection probabilities.
	public void evaluateGeneration(){
		double fitness_sum = 0.0;
		double prob_counter = 0.0;
		double fmax = 0.0; 

		for(int i = 0;i<current_generation.length;i++){
			evaluate((PathChromosome) current_generation[i]);
			if(current_generation[i].getEval() > fmax){
				fmax = current_generation[i].getEval();
			}
			
			//printArray(((PathChromosome)current_generation[i]).getPath());
			//System.out.println(current_generation[i].getEval());
			if(current_generation[i].getEval() < bestsofar.getEval()){
				bestsofar = current_generation[i];
			}
		}
		
		//extra step needed to process for minimization function. roulette method is natural for maximization.
		for(int i = 0;i<current_generation.length;i++){
			double x = ((PathChromosome) current_generation[i]).getEval();
			double fp = fmax-x;
			fitness_sum = fitness_sum + fp;

		}
		
		for(int i = 0;i<current_generation.length;i++){
			current_generation[i].setProb((fmax-current_generation[i].getEval())/fitness_sum);
			current_generation[i].setFrom(prob_counter);
			prob_counter = prob_counter + current_generation[i].getProb();
			current_generation[i].setTo(prob_counter);
			
			//System.out.println(current_generation[i].toString());
		}
		
		
	}
	
	//single path evaluation
	public double evaluate(PathChromosome pc) {
		double ev = 0.0;
		int[] path = pc.getPath();
		//printArray(path);
		for(int i=0; i<path.length-1;i++){
			ev = ev + gr.getPoint(path[i]).distanceTo(gr.getPoint(path[i+1]));
		}
		
		pc.setEval(ev);
		return ev;
	}
	
	public Chromosome getChByProb(double prob){
		for( int i= 0;i<gensize;i++){
			if(prob >= current_generation[i].getFrom() & prob < current_generation[i].getTo()){
				return current_generation[i];
			}
		}
		System.out.println("something's wrong with the probabilities");
		return null;
	}
	
	//for testing
	private void printArray(int[] arr){
		System.out.println("");
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i]+",");
		}
	}
	
}

