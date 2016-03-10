package algcomp.alg;

import java.util.Random;

import algcomp.util.Function;

public class FunctionChromosome{

	double eval;
	
	//for probability of selection (roulette method)
	double from;
	double to;
	double prob;
	
	char[] fullch; //signx x decx signy y decy
	Function f;
	int bitsfordecimals = 14; // 9999
	double decdiv = 10000.0;
	int bitsforx;
	int bitsfory;
	double x;
	double y;
	Random r;
	
	boolean feasible;
	
	public FunctionChromosome(Function _f){
		r = new Random();
		f = _f;
		bitsforx = bitsneeded(f.getRangex());
		bitsfory = bitsneeded(f.getRangey());
		fullch = new char[2+bitsforx+bitsfory+2*bitsfordecimals];
		for(int i = 0; i<fullch.length; i++){
			int c =  Math.abs(r.nextInt()%2);
			fullch[i] = Integer.toString(c).charAt(0);
		}
		chToDecimal();
		
	}
	
	private FunctionChromosome(char[] _fullch, Function _f){
		r = new Random();
		f = _f;
		bitsforx = bitsneeded(f.getRangex());
		bitsfory = bitsneeded(f.getRangey());
		fullch = _fullch;
		chToDecimal();
	}
	

	public void mutate() {
		int mutpoint = r.nextInt(fullch.length);
		if(fullch[mutpoint]==Integer.toString(1).charAt(0)){
			fullch[mutpoint]=Integer.toString(0).charAt(0);
		}else{
			fullch[mutpoint]=Integer.toString(1).charAt(0);
		}
		chToDecimal();
	}


	public FunctionChromosome crossover(FunctionChromosome c) {
		
		int xpoint = r.nextInt(fullch.length);
		char[] newch = new char[fullch.length];
		
		for(int i=0 ; i<xpoint; i++){
			newch[i] = fullch[i];
		}
		char[] cch = c.getFullch();
		
		for(int i=xpoint ; i<cch.length; i++){
			newch[i] = cch[i];
		}
		
		return new FunctionChromosome(newch,f);
	}
	
	public char[] getFullch() {
		return fullch;
	}

	void chToDecimal(){
		feasible = true;
		String s = new String(fullch);
		int accum = 0;
		int accum2 = 0;
		String signx = s.substring(accum,++accum);
		accum2 = accum + bitsforx;
		String sx = s.substring(accum,accum2);
		accum = accum2;
		accum2 = accum + bitsfordecimals;
		String sdx = s.substring(accum,accum2);
		
		int xp1 = Integer.parseInt(sx, 2);
		//solution out of range
		if(xp1+(Integer.parseInt(sdx, 2)/decdiv)>f.getRangex()){
//			sx = "0"+sx.substring(1);
//			xp1 = Integer.parseInt(sx, 2);
			feasible = false;
		}

		accum = accum2;
		String signy = s.substring(accum,++accum);
		accum2 = accum + bitsfory;
		String sy = s.substring(accum,accum2);
		accum = accum2;
		accum2 = accum +bitsfordecimals;
		String sdy = s.substring(accum,accum2);

		int yp1 = Integer.parseInt(sy, 2);
		//solution out of range
		if(yp1+(Integer.parseInt(sdy, 2)/decdiv)>f.getRangey()){
//			sy = "0"+sy.substring(1);
//			yp1 = Integer.parseInt(sy, 2);
			feasible = false;
		}
		
		x = xp1 +(Integer.parseInt(sdx, 2)/decdiv);
		y = yp1+(Integer.parseInt(sdy, 2)/decdiv);
		
		if(signx.equals("1")){
			x = x*-1;
		}
		if(signy.equals("1")){
			y = y*-1;
		}
		
	}

	public boolean isFeasible() {
		return feasible;
	}

	static int bitsneeded(double x){
		double log = Math.log(x) / Math.log(2);
		
		return (int)Math.ceil(log);
	}
	
	public String toString(){
		return "x: " + x + " , y: " + y+ " , ev: " + eval+" , from: " + from+" , to: " + to;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
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
	
	
/*	public static void main(String[] args){
//		System.out.println(bitsneeded(9999));
//		System.out.println(bitsneeded(512));
//		System.out.println(bitsneeded(100));
		Function fu = new Function("Booth");
		for(int i = 0; i<100;i++)
			System.out.println((new FunctionChromosome(fu)).toString());
	}*/
}
