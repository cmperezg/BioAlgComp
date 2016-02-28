package algcomp.alg;

import algcomp.util.Function;

public class FunctionChromosome extends Chromosome {

	int[] ygene;
	int[] xgene;
	
	Function f;
	
	public FunctionChromosome(Function _f){
		
	}
	
	@Override
	public void mutate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Chromosome crossover(Chromosome c) {
		// TODO Auto-generated method stub
		return null;
	}

	static int bitsneeded(double x){
		double log = Math.log(x) / Math.log(2);
		if(log % 1 == 0){
			return (int)Math.ceil(log)+1;
		}
		return (int)Math.ceil(log);
	}
	
	public static void main(String[] args){
		System.out.println(bitsneeded(9999));
		System.out.println(bitsneeded(512));
		System.out.println(bitsneeded(100));
	}
}
