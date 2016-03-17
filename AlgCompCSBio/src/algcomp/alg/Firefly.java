package algcomp.alg;
import algcomp.util.Dpoint;
public class Firefly {
	Dpoint pos;
	int id;
	double Intensity;
	
		public Firefly(){
			pos= new Dpoint(0,0);

		}

		public Dpoint getPos() {
			return pos;
		}

		public void setPos(Dpoint pos) {
			this.pos = pos;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public double getIntensity() {
			return Intensity;
		}

		public void setIntensity(double intensity) {
			Intensity = intensity;
		}
		
		public String toString(){
			return "x: "+ pos.getX() + " y: " + pos.getY() + " eval: "+ Intensity;
		}

}
