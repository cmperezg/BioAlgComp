package algcomp.util;
//This class will hold methods to evaluate different classical functions to evaluate optimization
public class Function {
	
	String type;
	int rangex;
	int rangey;
	double optx;
	double opty;
	double optev;
	
	
	public String getType() {
		return type;
	}



	public Function(String _type){
		
		type = _type;
		
		if(type.equals("Eggholder")){
			rangex = 512;
			rangey = 512;
			optx = 512;
			opty = 404.2319;
			optev = -959.6407;
		}else if(type.equals("Bohachevsky")){
			rangex = 100;
			rangey = 100;
			optx = 0;
			opty = 0;
			optev = 0;
		}else if(type.equals("Booth")){
			rangex = 10;
			rangey = 10;
			optx = 1;
			opty = 3;
			optev = 0;
		}else if(type.equals("Six Hump Camel")){
			rangex = 3;
			rangey = 2;
			optx = 0.0898;
			opty = -0.7126;
			optev = -1.0316;
		}else if(type.equals("Easom")){
			rangex = 100;
			rangey = 100;
			optx = Math.PI;
			opty = Math.PI;
			optev = -1;
		}
	}
	
	
	
	public int getRangex() {
		return rangex;
	}



	public int getRangey() {
		return rangey;
	}



	public double getOptx() {
		return optx;
	}



	public double getOpty() {
		return opty;
	}



	public double getOptev() {
		return optev;
	}



	public double eval(double x, double y){
		double ret = 0.0;
		if(type.equals("Eggholder")){
			ret = eggholder(x,y);
		}else if(type.equals("Bohachevsky")){
			ret = bohachevsky(x,y);
		}else if(type.equals("Booth")){
			ret = booth(x,y);
		}else if(type.equals("Six Hump Camel")){
			ret = sixHumpCamel(x,y);
		}else if(type.equals("Easom")){
			ret = easom(x,y);
		}
		return ret;
	}
	
	
	/*
	 * Eggholder function
	 * Many local minima
	 * Domain: square xi ∈ [-512, 512], for all i = 1, 2.
	 * Minimum: f(x*)=-959.6407 @ x* = [512,404.2319]
	 * Reference: http://www.sfu.ca/~ssurjano/egg.html
	 */
	private double eggholder(double x,double y){
		double ret = -(y+47)*Math.sin(Math.sqrt(Math.abs(y+(x/2)+47))) 
				- x*Math.sin(Math.sqrt(Math.abs(x-(y+47))));
		
		return ret;
	}
	
	/*
	 * Bohachevsky function
	 * Bowl shaped
	 * Domain: square xi ∈ [-100, 100], for all i = 1, 2.
	 * Minimum: f(x*)= 0 @ x* = [0,0]
	 * Reference: http://www.sfu.ca/~ssurjano/boha.html
	 */
	private double bohachevsky(double x,double y){
		double ret = x*x + 2*(y*y)-0.3*Math.cos(3*Math.PI*x)
				-0.4*Math.cos(4*Math.PI*y) + 0.7;
		return ret;
	}
	
	/*
	 * Booth function
	 * Plate shaped
	 * Domain: square xi ∈ [-10, 10], for all i = 1, 2. 
	 * Minimum: f(x*)= 0 @ x* = [1,3]
	 * Reference: http://www.sfu.ca/~ssurjano/booth.html
	 */
	private double booth(double x,double y){
		double ret = Math.pow(x+2*y-7, 2)+Math.pow(2*x+y-5, 2);
		return ret;
	}
	
	/*
	 * Six-hump camel function
	 * Valley shaped
	 * Domain: rectangle x1 ∈ [-3, 3], x2 ∈ [-2, 2]. 
	 * Minimum: f(x*)= -1.0316 @ x* = [0.0898,-0.7126] & [-0.0898,0.7126]
	 * Reference: http://www.sfu.ca/~ssurjano/camel6.html
	 */
	private double sixHumpCamel(double x,double y){
		double ret = (4-2.1*Math.pow(x,2)+Math.pow(x, 4)/3)*Math.pow(x, 2)+x*y
				+(-4+4*Math.pow(y, 2))*Math.pow(y, 2);
		return ret;
	}
	
	
	/*
	 * Easom function
	 * Steep drop
	 * Domain: square xi ∈ [-100, 100], for all i = 1, 2.
	 * Minimum: f(x*)= -1 @ x* = [PI,PI]
	 * Reference: http://www.sfu.ca/~ssurjano/easom.html
	 */
	private double easom(double x,double y){
		double ret = -Math.cos(x)*Math.cos(y)*Math.exp(-Math.pow((x-Math.PI), 2)-Math.pow((y-Math.PI), 2));
		return ret;
	}
	
//	public static void main(String[] args){
//		System.out.println(eggholder(512,404.2319));
////		System.out.println(bohachevsky(0,0));
////		System.out.println(booth(1,3));
////		System.out.println(sixHumpCamel(0.0898,-0.7126));
////		System.out.println(easom(Math.PI,Math.PI));
//	}

}
