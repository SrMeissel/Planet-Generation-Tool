package Default;
import java.util.ArrayList; 

public class Planet {
	ArrayList<GenericLayer> Layers = new ArrayList<GenericLayer>();
	int timeElapsed = 0;
	int timeDesired = 0;
	
	public static void main(String[] args){
		Planet ctrl = new Planet();
		ctrl.launch();
	}
	
	public void launch() {
		GenericLayer initialLayer = new GenericLayer();
		Layers.add(initialLayer);
		
		
		
	}
}
