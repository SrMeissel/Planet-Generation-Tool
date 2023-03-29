package Default;
//import java.math.*;
import java.util.ArrayList; 

public class MapType {
	int Number = 5000;
	int Count;
		
	public double[][] plotPoints() {
		ArrayList<double[]> points = new ArrayList<>();
		// points[i][j][k]
		//i = point #
		//j = V
		//k = Y
		
		Count = 0;
		double radius = 1;
		double a = 4*Math.PI*((radius*radius)/Number);
		double d = Math.sqrt(a);
		int Mv = (int) Math.round(Math.PI/d);
		double Dv = Math.PI/Mv;
		double Dy = a/Dv; 
		
		for(int i=0; i < Mv; i++) {
			double V = Math.PI*(i+0.5)/Mv;
			int My = (int) Math.round(2*Math.PI*Math.sin(V)/Dy);
			for(int j = 0; j < My; j++ ) {
				double Y = 2*Math.PI*j/My;
				double[] point = {Count, V, Y};
				points.add(point);
				System.out.println("point " + points.get(Count)[0] + " made at: V= " + points.get(Count)[1] + " Y= " + points.get(Count)[2] );
				Count++;
			}	
		}
		
		double[][] finalPoints = new double[points.size()][3];
		for(int i=0; i < points.size(); i++) {
			finalPoints[i] = points.get(i);
		}
		
		return finalPoints;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}
	
}
