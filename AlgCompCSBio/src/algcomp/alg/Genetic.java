package algcomp.alg;

import java.util.Random;

import algcomp.util.*;

//Will only work for minimization problems.... shortest tour or minimum of a function.
public class Genetic {
	Graph gr;
	int gensize;
	double mutprob;
	boolean sbs;
	int timer;
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
	public Genetic(Graph _gr,int _gensize, double _mutprob, boolean _sbs,int _timer){
		gr = _gr;
		gensize = _gensize;
		mutprob = _mutprob;
		sbs = _sbs;
		timer = _timer;
		//if a graph is sent as a parameter, it is a graph problem...
		graphprob = true;
		//just a random chromosome... probably not the best thing to do.
		bestsofar = new PathChromosome(gr.size());
		
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
		
		//create next generation
		Random r = new Random();
		int created = 0;
		double probscore;
		Chromosome[] newgen = new PathChromosome[gensize];
		
		while(created < gensize-1){
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
		
		//add a random guy every generation, to keep things moving
		newgen[gensize-1]=new PathChromosome(gr.size());
		current_generation = newgen;
		return bestsofar;
	}
	
	//evaluate each chromosome and set selection probabilities.
	public void evaluateGeneration(){
		double fitness_sum = 0.0;
		double prob_counter = 0.0;
		for(int i = 0;i<current_generation.length;i++){
			fitness_sum = fitness_sum +evaluate((PathChromosome) current_generation[i]);
			if(current_generation[i].getEval() < bestsofar.getEval()){
				bestsofar = current_generation[i];
			}
		}
		
		for(int i = 0;i<current_generation.length;i++){
			current_generation[i].setProb(current_generation[i].getEval()/fitness_sum);
			current_generation[i].setFrom(prob_counter);
			prob_counter = prob_counter + current_generation[i].getProb();
			current_generation[i].setTo(prob_counter);
		}
	}
	
	
	public double evaluate(PathChromosome pc) {
		double ev = 0.0;
		int[] path = pc.getPath();
		for(int i=0; i<path.length-1;i++){
			ev = ev + gr.getPoint(i).distanceTo(gr.getPoint(i+1));
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
	
}

