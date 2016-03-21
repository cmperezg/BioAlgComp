package algcomp.alg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import algcomp.util.*;

//Will only work for minimization problems.... shortest tour or minimum of a function.
public class GeneticFunc{

	Function f;
	int gensize;
	double mutprob;
	int immigrants;
	double tol;
	int gencounter;

	
	FunctionChromosome bestsofar;
	
	FunctionChromosome[] current_generation;
	

	
	public GeneticFunc(Function _f,int _gensize, double _mutprob,int _immi, double _tol){
		f = _f;
		gensize = _gensize;
		mutprob = _mutprob;
		immigrants = _immi;
		tol = _tol;
		
		//just a random chromosome... probably not the best thing to do.
		bestsofar = new FunctionChromosome(f);
		evaluate(bestsofar);
		//initialize first generation
		current_generation = new FunctionChromosome[gensize];
		for(int i=0;i<gensize;i++){
			current_generation[i] = new FunctionChromosome(f);
		}
	}
	
	/*
	 * This will actually return the best chromosome for the previous generation.
	 * Generation created in this step will not be evaluated until next step is run.
	 */
	public FunctionChromosome step(){
		evaluateGeneration();
		//System.out.println("BEST NOW: " + bestsofar.toString());
		
		//create next generation
		Random r = new Random();
		int created = 0;
		double probscore;
		FunctionChromosome[] newgen;
		
		newgen = new FunctionChromosome[gensize];
		
		 
		//System.out.println("NEWGEN");
		while(created < gensize-immigrants){
			//select first parent
			 probscore= r.nextDouble();
			 FunctionChromosome p1 = getChByProb(probscore);
			 probscore= r.nextDouble();
			 FunctionChromosome p2 = getChByProb(probscore);
			 FunctionChromosome nc = p1.crossover(p2);
			 
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
			
			newgen[gensize-i]=new FunctionChromosome(f);
			
			
		}
		
		current_generation = newgen;
		//printArray(((PathChromosome)bestsofar).getPath());
		//System.out.println("STEP OVER");
		
		return bestsofar;
	}
	
	
	
	public FunctionChromosome fullrun(){
		System.out.println("Genetic run");
		long startTime = System.nanoTime();    
			while(Math.abs(bestsofar.eval-f.getOptev()) > tol) {
				//System.out.println(bestsofar.eval-f.getOptev());
				step();
			}
		long estimatedTime = System.nanoTime() - startTime;
		double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
		System.out.println("Function: " + f.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + tol+", Ev: " + bestsofar.getEval());
		return bestsofar;
	}
	
	//evaluate each chromosome and set selection probabilities.
	public void evaluateGeneration(){
		double fitness_sum = 0.0;
		double prob_counter = 0.0;
		double fmax = 0.0; //worst feasible solution

		for(int i = 0;i<current_generation.length;i++){
			
				evaluate(current_generation[i]);
			
			if(current_generation[i].getEval() > fmax & current_generation[i].isFeasible()){
				fmax = current_generation[i].getEval();
			}
			
			//printArray(((PathChromosome)current_generation[i]).getPath());
			//System.out.println(current_generation[i].getEval());
			if(current_generation[i].getEval() < bestsofar.getEval() & current_generation[i].isFeasible()){
				bestsofar = current_generation[i];
			}
		}
		double totmax;
		totmax = 2*fmax;
		//extra step needed to process for minimization function. roulette method is natural for maximization.
		for(int i = 0;i<current_generation.length;i++){
			if(!current_generation[i].isFeasible()){
				current_generation[i].setEval(current_generation[i].getEval()+1000);
			}
			double x = current_generation[i].getEval();
			double fp = totmax-x;
			fitness_sum = fitness_sum + fp;

		}
		
		for(int i = 0;i<current_generation.length;i++){
			current_generation[i].setProb((totmax-current_generation[i].getEval())/fitness_sum);
			current_generation[i].setFrom(prob_counter);
			prob_counter = prob_counter + current_generation[i].getProb();
			current_generation[i].setTo(prob_counter);
			
			//System.out.println(current_generation[i].toString());
		}
		
		
	}

	
	public double evaluate(FunctionChromosome fc) {
		double ev = f.eval(fc.getX(), fc.getY());
		fc.setEval(ev);
		return ev;
	}
	
	public FunctionChromosome getChByProb(double prob){
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

