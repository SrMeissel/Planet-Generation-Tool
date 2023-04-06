package Default;
public class VectorType {
	
	//double[] Vector = new double[3];
	// (M, i, j)
	//M = magnitude 
	//i = Y Component 
	//j = V Component
	public double[] addVectors(double[] a, double[] b, double modifier) {
		double[] sum = new double[3];
		sum[1] = a[1]*modifier + b[1];
		sum[2] = a[2]*modifier + b[2];
		sum[0] = Math.sqrt(sum[1]*sum[1] + sum[2]*sum[2]);
		
		return sum;
	}	
}