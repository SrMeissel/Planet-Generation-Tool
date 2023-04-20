//*********************************************************
//Class: FileIO
//Author: Ethan Mizer
//Created: 3/27/23
//Modified: 4/8/23
//
//Purpose: Manage the formatting of data output
//			
//
//Attributes: 
//			loyerTotal
//	
//Methods: 
//		   writeToFile
//		   
//
//*********************************************************
package Default;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;

public class FileIO {
	public void writeToFile(GenericLayer Layer, int index){
		File layerTotal = new File("Layer" + index + ".csv");
		try {
			FileWriter fw = new FileWriter(layerTotal);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Point number,Theta Value, Alpha Value, Vector Magnitude, Temprature");
			bw.newLine();
			
			ArrayList<GenericData> data = Layer.getSurface();
			for(int i=0; i<data.size(); i++) {
				GenericData point = data.get(i);
				bw.write(point.getDesignation() + "," + point.getLocation()[0] + "," + point.getLocation()[1] + "," + point.getVector()[0] + "," + point.getTempHeight());
				bw.newLine();
			}
			
			bw.close();
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
