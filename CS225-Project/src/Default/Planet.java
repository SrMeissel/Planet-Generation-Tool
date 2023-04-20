//*********************************************************
//Class: Planet
//Author: Ethan Mizer
//Created: 3/27/23
//Modified: 4/8/23
//
//Purpose:
//			holding all Layers and facilating their interaction then providing output 
//
//Attributes: 
//			Layers
//			timeElapsed
//			timeDesired
//
//Methods: 
//		   main
//		   Launch
//
//*********************************************************

package Default;
import java.util.ArrayList; 

public class Planet {
	FileIO output = new FileIO();
	MapType mapper = new MapType();
	 VectorType vectorMath = new VectorType();
	
	ArrayList<GenericLayer> Layers = new ArrayList<GenericLayer>();
	
	int timeElapsed = 0;
	int timeDesired = 0;
	
	public static void main(String[] args){
		Planet ctrl = new Planet();
		ctrl.launch();
	}
	
	public void launch() {
		// Layer stacking Test
		GenericLayer firstLayer = new GenericLayer();
		GenericLayer secondLayer = new GenericLayer();
		GenericLayer thirdLayer = new GenericLayer();
		firstLayer.setSurface(mapper.plotPoints());
		secondLayer.setSurface(mapper.plotPoints());
		thirdLayer.setSurface(mapper.plotPoints());
		Layers.add(firstLayer);
		Layers.add(secondLayer);
		Layers.add(thirdLayer);
		
		this.process();		
	}
	public void process() {
		//testing all neighbors of point 3000 in the middle layer.
		ArrayList<GenericData> neighbors = new ArrayList<GenericData>();
		neighbors.addAll(mapper.getAllNeighbors(Layers, Layers.get(1), Layers.get(1).getSurface().get(1)));
		System.out.println("All neighbors of point 1 in the middle layer are: 	");
		for(int i=0; i<neighbors.size(); i++) {
			System.out.println(neighbors.get(i).getDesignation());
		}
	}
}
