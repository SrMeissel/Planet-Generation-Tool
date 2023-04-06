package Default;
import java.util.LinkedList;

//Vector (x,y,m) double
//height/s (x) double OR int

class GenericData {
	private double[] location;
	private double Dv;
	private double Dy;
	
	private double[] Vector = new double[3];
	private double GeoHeight;
	private double TempHeight;
	private int ClassHeight;
	
	LinkedList<GenericMass> Composition = new LinkedList<GenericMass>();
	
	public double getDv() {
		return Dv;
	}
	public void setDv(double dv) {
		Dv = dv;
	}
	public double getDy() {
		return Dy;
	}
	public void setDy(double dy) {
		Dy = dy;
	}
	public double[] getLocation() {
		return location;
	}
	public void setLocation(double[] location) {
		this.location = location;
	}
	public double[] getVector() {
		return Vector;
	}
	public void setVector(double[] vector) {
		Vector = vector;
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
	public LinkedList<GenericMass> getComposition() {
		return Composition;
	}
	public void setComposition(LinkedList<GenericMass> composition) {
		Composition = composition;
	}
	
}