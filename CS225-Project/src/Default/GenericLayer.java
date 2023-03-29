package Default;
//import java.util.ArrayList; 

public class GenericLayer {
	MapType mapper = new MapType();
	VectorType vectorMath = new VectorType();
	double[][] surface;
	double[][] vectors;
	double[] heights;
	
	public GenericLayer() {
		surface = mapper.plotPoints();
		vectors = new double[surface.length][3];
		heights = new double[surface.length];
	}
	public void initVectors() {
		
	}
	public void initHeights() {
		
	}
	public void update() {
		
	}
}
