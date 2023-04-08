package Default;
import java.util.ArrayList; 

public class GenericLayer {
	MapType mapper = new MapType();
	VectorType vectorMath = new VectorType();
	
	public GenericLayer() {
		ArrayList<GenericData> surface = mapper.plotPoints();
		
		//get random neighbors
		ArrayList<GenericData> localSurface = mapper.findNeighbors(surface, surface.get(1822));
		double[] localDesignations = mapper.dataToValue(localSurface, "Designation");
		System.out.println("the points closest to point 1822 are: ");
		for(int i=0; i<localDesignations.length; i++) {
			System.out.println((int) localDesignations[i]);
		}
	}
}
