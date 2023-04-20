//*********************************************************
//Class: VectorType
//Author: Ethan Mizer
//Created: 3/27/23
//Modified: 4/8/23
//
//Purpose: Provides math need to manipulate and interact with vectors
//			
//
//Attributes: 
//			
//	
//Methods: 
//		   addVectors
//		   
//
//*********************************************************
package Default;
public class VectorType {
	
	//double[] Vector = new double[3];
	// (M, i, j)
	//M = magnitude 
	//i = Y Component 
	//j = V Component
	// I am debating leaving out the magnitude component just leaving it to be recalculated each time,
	// I dont like risking it becoming out of date.
	
	public double recalculateMagnitude(double[] Vector) {
		return Math.sqrt((Vector[1]*Vector[1]) + (Vector[2]*Vector[2]));
	}
	public double[] addVectors(double[] vectorA, double[] vectorB, double modifier) {
		double[] sum = new double[3];
		sum[1] = vectorA[1]*modifier + vectorB[1];
		sum[2] = vectorA[2]*modifier + vectorB[2];
		sum[0] = Math.sqrt(sum[1]*sum[1] + sum[2]*sum[2]);
		
		return sum;
	}	
	public double componentToAngle(double[] vector) {
		return Math.atan2(vector[2], vector[1]);
	}
	public double dotProduct(double[] vectorA, double[] vectorB) {
		return (vectorA[1]*vectorB[1]) + (vectorA[2]*vectorB[2]);
	}
	public double angleBetweenVectors(double[] vectorA, double[] vectorB) {
		double magnitudeSum = vectorA[0]*vectorB[0];
		double scalarProduct = this.dotProduct(vectorA, vectorB);
		return Math.acos(magnitudeSum/scalarProduct);
	}
	
	// Tail, head
	public double[] pointToVector(double[] locationA, double[] locationB) {
		double[] vector = new double[2];
		vector[1] = locationB[0]-locationA[0];
		vector[2] = locationB[1]-locationA[1];
		vector[0] = this.recalculateMagnitude(vector);
		return vector;
	}
}