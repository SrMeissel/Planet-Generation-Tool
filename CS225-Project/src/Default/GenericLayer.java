package Default;
//import java.util.ArrayList; 

public class GenericLayer {
	MapType mapper = new MapType();
	VectorType vectorMath = new VectorType();
	
	public GenericLayer() {
		GenericData[] surface = mapper.plotPoints();
	}
}
