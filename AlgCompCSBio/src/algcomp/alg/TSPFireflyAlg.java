
package algcomp.alg;
import java.util.Random;
import algcomp.util.*;

public class TSPFireflyAlg {
	TSPfly[] current_generation;
	double[][] Attractive;
	Graph gr;
	int gensize;
	double Abscoeff;
	double Attcoeff;
	int EvolutionTime;
	TSPfly BestSoFar;
	int [][] PathDistance;
	
	public TSPFireflyAlg(Graph _gr,int _gensize, int _evTime, double _Abscoeff,double _Attcoeff){
	gr=_gr;
	gensize=_gensize;
	Attcoeff=_Attcoeff;
	Abscoeff=_Abscoeff;
	EvolutionTime=_evTime;
	current_generation = new TSPfly[gensize];
	for(int i=0;i<gensize;i++){
		current_generation[i] = new TSPfly(gr.size());
		}
	InitAttractive();
	
	}
	public void ComputeAtt(){
		int i;
		CompPathDist();
		for (i=0;i<gensize;i++){
			Evaluate(current_generation[i], i);
		}
	}
	
	public void Evaluate(TSPfly fly, int index){ //before calling this function - call CompPathDist()
		int j;
		int id=index;
		fly.Intensity= evaluatedist(fly);
		for (j=0;j<gensize;j++){
			if(j==id){
				Attractive[id][j]=0;
				}
			Attractive[id][j]=Attcoeff*Math.exp(-1*Abscoeff*PathDistance[id][j]);
				
			}
		
		}
	public double evaluatedist(TSPfly fp) {
		double ev = 0.0;
		//printArray(path);
		for(int i=0; i<gensize-1;i++){
			ev = ev + gr.getPoint(fp.path[i]).distanceTo(gr.getPoint(fp.path[i+1]));
		}
		return ev;
	}
	
	private void Moveitoj(int i, int j){
		Random rand=new Random();
		int k;
		int r=rand.nextInt(gensize);
		int oldval= current_generation[i].path[r];
		int newval = current_generation[j].path[r];
		for (k=0;k<gensize;k++){
			if (current_generation[i].path[k]==newval){
				current_generation[i].path[r]=newval;   //swap between the two
				current_generation[i].path[k]=oldval;
				return;
			}
			
		}
	}
	private void CompPathDist(){
		int i,j;
		for (i=0;i<gensize;i++){
			for (j=0;j<gensize;j++){
				PathDistance[i][j]=SinglePathDist(current_generation[i],current_generation[j]);
				PathDistance[j][i]=PathDistance[i][j];
			}
		}
	}
	private int SinglePathDist(TSPfly flyA,TSPfly flyB){
		int i,j;
		int Dist=0;
		for(i=0;i<gensize-1;i++){
			for (j=0;j<gensize-1;j++){
				if(flyA.path[i]!=flyB.path[j] || flyA.path[i+1]!=flyB.path[j+1] ){
					Dist=Dist+1;
				}
					
			}
		} 
		return Dist;
	}
	private TSPfly FindBestSofar(){
		int i;
		TSPfly Best=current_generation[1];
		for (i=0;i<gensize; i++){
			if (current_generation[i].Intensity > Best.Intensity)
				Best=current_generation[i];
		}
		return Best;
		
	}
	public TSPfly Step(){
		int i,j;
		for (i=0;i< gensize;i++){
			for(j=0;j<gensize;j++){
				if (current_generation[j].Intensity>current_generation[i].Intensity){
					Moveitoj(i,j);
				}
			}
		}
		ComputeAtt();
		
		return FindBestSofar();
	}
	
	private void InitAttractive(){
		int i,j;
		for (i=1;i<=gensize; i++){
			for(j=1;j<=gensize;j++){
				Attractive[i][j]=0;
			}	
		}
	}
}

