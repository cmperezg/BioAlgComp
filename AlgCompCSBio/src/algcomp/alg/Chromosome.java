package algcomp.alg;

public abstract class Chromosome {
	
	double eval;
	
	//for probability of selection (roulette method)
	double from;
	double to;
	double prob;
	
	public abstract void mutate();
	public abstract Chromosome crossover(Chromosome c);
	public double getFrom() {
		return from;
	}
	public void setFrom(double from) {
		this.from = from;
	}
	public double getTo() {
		return to;
	}
	public void setTo(double to) {
		this.to = to;
	}
	public double getProb() {
		return prob;
	}
	public void setProb(double prob) {
		this.prob = prob;
	}
	public double getEval() {
		return eval;
	}
	public void setEval(double eval) {
		this.eval = eval;
	}
	
	
}
