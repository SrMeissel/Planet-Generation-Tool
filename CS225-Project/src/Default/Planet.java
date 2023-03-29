package Default;
import java.util.ArrayList; 

public class Planet {
	ArrayList<GenericLayer> Layers = new ArrayList<GenericLayer>();
	int timeElapsed = 0;
	int timeDesired;
	
	public static void main(String[] args){
		Planet ctrl = new Planet();
		ctrl.launch();
	}
	
	public void launch() {
		GenericLayer initialLayer = new GenericLayer();
		Layers.add(initialLayer);
		while(timeElapsed < timeDesired) {
			for(int i=0; i < Layers.size(); i++) {
				Layers.get(i).update();
			}
			timeElapsed++;
		}
	}
}
