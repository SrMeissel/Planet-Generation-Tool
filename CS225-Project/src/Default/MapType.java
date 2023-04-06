package Default;

public class MapType {
	private int Number = 5000;
		
	public GenericData[] plotPoints() {
		GenericData[] points = new GenericData[Number -1];
		// points[i][j][k]
		//i = point #
		//j = V
		//k = Y
		
		int Count = 0;
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
				double[] point = {V, Y};
				points[Count].setLocation(point);
				points[Count].setDv(Dv);
				points[Count].setDy(Dy);
				System.out.println("point " + Count + " made at: V= " + points[Count].getLocation()[0] + " Y= " + points[Count].getLocation()[1] );
				Count++;
			}	
		}
		return points;
	}
	public void findNeighbors(GenericData[] data, int point) {
		double V = data[point].getLocation()[0];
		double Dv = data[point].getDv();
		double Y = data[point].getLocation()[1];
		double Dy = data[point].getDy();
		double Vnew = (Math.round(V*100)/100) + (Math.round(Dv*100)/100);
		double Ynew = (Math.round(Y*100)/100) + (Math.round(Dy*100)/100);
		int Count = 0;
		while(!(data[Count].getLocation()[0] == Vnew)) {
			Count++;
		}
		//Count is now the neighbor, but this is really inefficent.
		//INSTEAD, Seperate them by rows, (may have index -1 problems) find point directly above and below
		// then finding points to the left and right of each of those is easy.
	}
}
