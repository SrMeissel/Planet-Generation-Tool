//*********************************************************
//Class: GenericData
//Author: Ethan Mizer
//Created: 3/31/23
//Modified: 4/8/23
//
//Purpose: Provides optimal way to access data that is stored on each point. better than having a array of excessive dimensions
//			
//
//Attributes: 
//			location
//			designation
//			Dv
//			Dy
//			Vector
//			GeoHeight
//			TempHeight
//			ClassHeight
//			Composition
//
//Methods: 
//		   Getters and Setters
//		   
//
//*********************************************************
package Default;

import java.util.LinkedList;
import java.util.Arrays; 


//height/s (x) double OR int

class GenericData {
	private double[] location;
	
	private int designation;
	
	private double[] Vector = new double[3];
	private double GeoHeight;
	private double TempHeight;
	private int ClassHeight;
	
	LinkedList<GenericMass> Composition = new LinkedList<GenericMass>();
	
	public int getDesignation() {
		return designation;
	}
	public void setDesignation(int designation) {
		this.designation = designation;
	}
	public double[] getLocation() {
		return Arrays.copyOf(location, 2);
	}
	public void setLocation(double[] location) {
		this.location = Arrays.copyOf(location, 2);
	}
	public double[] getVector() {
		return Arrays.copyOf(Vector, 3);
	}
	public void setVector(double[] vector) {
		this.Vector = Arrays.copyOf(vector, 3);
	}
	public double getGeoHeight() {
		return GeoHeight;
	}
	public void setGeoHeight(double geoHeight) {
		GeoHeight = geoHeight;
	}
	public double getTempHeight() {
		return TempHeight;
	}
	public void setTempHeight(double tempHeight) {
		TempHeight = tempHeight;
	}
	public int getClassHeight() {
		return ClassHeight;
	}
	public void setClassHeight(int classHeight) {
		ClassHeight = classHeight;
	}
	// Make copies of array before returning 
	public LinkedList<GenericMass> getComposition() {
		return Composition;
	}
	public void setComposition(LinkedList<GenericMass> composition) {
		Composition = composition;
	}
}