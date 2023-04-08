package Default;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileIO {
	private File layerTotal = new File("OutPut.csv");
	public void writeToFile(GenericLayer Layer){
		try {
			FileWriter fw = new FileWriter(layerTotal);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.newLine();
		} catch(Exception e) {
			
		}
	}

}
