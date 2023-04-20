//*********************************************************
//Class: GenericLayer
//Author: Ethan Mizer
//Created: 3/27/23
//Modified: 4/8/23
//
//Purpose: Hold a segment of data across a shell
//			
//
//Attributes: 
//			mapper
//			vectorMath
//	
//Methods: 
//		   GenericLayer
//		   
//
//*********************************************************
package Default;
import java.util.ArrayList; 

public class GenericLayer {
	private MapType mapper = new MapType();
	private VectorType vectorMath = new VectorType();
	private ArrayList<GenericData> surface;
	
	public GenericLayer() {
		
	}
	// takes all vectors on the surface and changes them based on the state of their neighbors
	//Note: all vectors currently are either constant or represent currents. so this is a fluid dynamics thing.

	public ArrayList<GenericData> getSurface() {
		return surface;
	}

	public void setSurface(ArrayList<GenericData> surface) {
		this.surface = surface;
	}
}
