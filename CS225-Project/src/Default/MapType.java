//*********************************************************
//Class: MapType
//Author: Ethan Mizer
//Created: 3/27/23
//Modified: 4/8/23
//
//Purpose: Provide math needed to place and navigate across points equidistant on a shell
//			
//
//Attributes: 
//			Number
//	
//Methods: 
//		   plotPoints
//		   findNeighbors
//			seperatePoints
//			closestBinarySearch
//			Compare
//			dataToValue
//			getVerticalNeighbors
//			getHorizontalNeighbors
//
//*********************************************************
//Checkout subList()

package Default;

import java.util.ArrayList;

public class MapType {
	private int Number = 5000;
		
	//https://www.cmu.edu/biolphys/deserno/pdf/sphere_equi.pdf
	// radius of earth is 6378100 meters
	public ArrayList<GenericData> plotPoints() {
		ArrayList<GenericData> points = new ArrayList<GenericData>();
		// points[i][j][k]
		//i = point #
		//j = V
		//k = Y
		
		int Count = 0;
		double radius = 1;
		double a = 4*Math.PI*((radius*radius)/Number);
		double d = Math.sqrt(a);
		int Mv = (int) Math.round(Math.PI/d);
		double Dv = Math.PI/Mv;
		double Dy = a/Dv; 
		
		for(int i=0; i < Mv; i++) {
			double V = Math.PI*(i+0.5)/Mv;
			int My = (int) Math.round(2*Math.PI*Math.sin(V)/Dy);
			for(int j = 0; j < My; j++ ) {
				double Y = 2*Math.PI*j/My;
				GenericData point = new GenericData();
				double[] location = {V, Y};
				point.setLocation(location);
				point.setDesignation(Count);
				points.add(point);
				
				//System.out.println("point " + Count + " made at: V= " + points.get(Count).getLocation()[0] + " Y= " + points.get(Count).getLocation()[1] );
				Count++;
			}	
		}
		return points;
		// 0,V is Vertical
		// 1,Y is Horizontal
	}
	
	public ArrayList<GenericData> getAllNeighbors(ArrayList<GenericLayer> layers, GenericLayer layer, GenericData point){
		ArrayList<GenericData> neighbors = new ArrayList<GenericData>();
		neighbors.addAll(getLayerNeighbors(layer.getSurface(), point));
		neighbors.addAll(getHeightNeighbors(layers, layer, point));
		
		return neighbors;
	}
	
	public ArrayList<GenericData> getLayerNeighbors(ArrayList<GenericData> data, GenericData point) {
		ArrayList<ArrayList<GenericData>> seperatedData = seperatePoints(data);
		ArrayList<GenericData> neighbors = new ArrayList<GenericData>();
		neighbors.add(point);
		neighbors.addAll(getVerticalNeighbors(seperatedData, point));
		neighbors.addAll(getHorizontalNeighbors(seperatedData, neighbors));
		//is done? really? no
		neighbors.remove(point);
		//returning neighbors, NOT INCLUDING THE ORIGINAL POINT
		
		return neighbors;
	}

	//Find neighbors at heights above and below the selected point if they exist. not diagonal corners
	public ArrayList<GenericData> getHeightNeighbors(ArrayList<GenericLayer> layers, GenericLayer layer, GenericData point){
		ArrayList<GenericData> neighbors = new ArrayList<GenericData>();
		int level = layers.indexOf(layer);
		boolean topLayer = true;
		boolean bottomLayer = true;
		
		if(layers.size() > 1) {
			if(level == 0) {
				//only up
				bottomLayer = false;
			}else if(level == (layers.size()-1)){
				// only down 
				topLayer = false;
			}
			if(topLayer) {
				ArrayList<GenericData> topPoint = new ArrayList<GenericData>();
				topPoint.add(getClosestPoint(point, layers.get(level-1).getSurface()));
				ArrayList<GenericData> topSurface = layers.get(level-1).getSurface();
				ArrayList<ArrayList<GenericData>> seperatedTopSurface = seperatePoints(topSurface);
				neighbors.addAll(getHorizontalNeighbors(seperatedTopSurface, topPoint));
				neighbors.addAll(getVerticalNeighbors(seperatedTopSurface, topPoint.get(0)));
				neighbors.add(topPoint.get(0));
			}
			if(bottomLayer) {
				ArrayList<GenericData> bottomPoint = new ArrayList<GenericData>();
				bottomPoint.add(getClosestPoint(point, layers.get(level+1).getSurface()));
				ArrayList<GenericData> bottomSurface = layers.get(level+1).getSurface();
				ArrayList<ArrayList<GenericData>> seperatedBottomSurface = seperatePoints(bottomSurface);
				neighbors.addAll(getHorizontalNeighbors(seperatedBottomSurface, bottomPoint));
				neighbors.addAll(getVerticalNeighbors(seperatedBottomSurface, bottomPoint.get(0)));
				neighbors.add(bottomPoint.get(0));
			}
			return neighbors;
		} else {
			return null;
		}
	}
	
	//secondary methods

	//Make better with ArrayList.subList()
	// This method uses the fact that all points on the sphere a plotted with consistent height angles. 
	// It cycles through the entire arrayList and seperated the points based on their height angles lowest to highest.
	// if the next element being considered has the same height angle as the last one, it get added to the same list,
	// Otherwise, if the height angle is different is creates a new list for that new height value.
	public ArrayList<ArrayList<GenericData>> seperatePoints(ArrayList<GenericData> data){
		ArrayList<ArrayList<GenericData>> seperatedPoints = new ArrayList<ArrayList<GenericData>>(); 
		seperatedPoints.add(new ArrayList<GenericData>());
		int size = 0;
		double savedValue = data.get(0).getLocation()[0];
		
		for(int i=0; i < data.size(); i++) {
			if(data.get(i).getLocation()[0] == savedValue) {
				seperatedPoints.get(size).add(data.get(i));

			}else {
				seperatedPoints.add(new ArrayList<GenericData>());
				savedValue = data.get(i).getLocation()[0];
				size++;
				seperatedPoints.get(size).add(data.get(i));
			}
		}
		return seperatedPoints;
	}
	
	//https://www.geeksforgeeks.org/find-closest-number-array/
	public int closestBinarySearch(double[] array, double target) {
		int length = array.length;
	
		//edge cases 
		if(target <= array[0]) {
			return 0;
		}
		if(target >= array[length-1]) {
			return length-1;
		}
		
		
		int leftBound = 0;
		int rightBound = length;
		int middle = 0;
		while(leftBound < rightBound) {
			middle = (leftBound+rightBound)/2;
			if(array[middle] == target) {
				return middle;
			}
			
			if(target < array[middle]) {
				if(middle > 0 && target > array[middle-1]) {
					return Compare(array[middle-1], middle-1, array[middle], middle, target);
				}
				rightBound = middle;
			}else {
				if(middle < length-1 && target < array[middle+1]) {
					return Compare(array[middle], middle, array[middle+1], middle+1, target);
				}
				leftBound = middle+1;
			}
		}
		return middle;
	}
	
	public int Compare(double value1, int Index1, double value2, int Index2, double target) {
		if(target - value1 >= value2 - target) {
			return Index2;
		}else {
			return Index2;
		}
	}
	public double[] dataToValue(ArrayList<GenericData> data, String type) {
		double[] values = new double[data.size()];
		for(int i=0; i<data.size(); i++) {
			switch(type){
			case "Y":
				values[i] = data.get(i).getLocation()[1];
				break;
			case "V":
				values[i] = data.get(i).getLocation()[0];
				break;
			case "Designation":
				values[i] = data.get(i).getDesignation();
				break;
			default:
				return null;
			}
		}
		return values;
	}
	
	// Finds points above and below a given points. In the case that the point is at the bottom, it only looks above it and vice
	//versa. Since the values have a inconsitent horizontal angle value AND array length, it uses a binary search to find
	// the index of the point with the closest horizontal angle
	public ArrayList<GenericData> getVerticalNeighbors(ArrayList<ArrayList<GenericData>> data, GenericData point){
		ArrayList<GenericData> neighbors = new ArrayList<GenericData>();
		int lowerColumn;
		int upperColumn;
		int targetRow = 0;
		double targetValue = point.getLocation()[1];
		
		while(!data.get(targetRow).contains(point)) {
			targetRow++;
		}
		
		if(targetRow == 0) {
			 lowerColumn = closestBinarySearch(dataToValue(data.get(targetRow+1), "Y"), targetValue);
			neighbors.add(data.get(targetRow+1).get(lowerColumn));
		}else if(targetRow == data.size()-1) {
			 upperColumn = closestBinarySearch(dataToValue(data.get(targetRow-1), "Y"), targetValue);
			neighbors.add(data.get(targetRow-1).get(upperColumn));
		}else {
			 lowerColumn = closestBinarySearch(dataToValue(data.get(targetRow+1), "Y"), targetValue);
			neighbors.add(data.get(targetRow+1).get(lowerColumn));
			 upperColumn = closestBinarySearch(dataToValue(data.get(targetRow-1), "Y"), targetValue);
			neighbors.add(data.get(targetRow-1).get(upperColumn));
		}
		return neighbors;
	}
	
	// The majority of this method just tests edge cases otherwise, It just find the values to the left and right of the points given
	public ArrayList<GenericData> getHorizontalNeighbors(ArrayList<ArrayList<GenericData>> data, ArrayList<GenericData> targetColumn){
		int rows = targetColumn.size();
		ArrayList<GenericData> points = new ArrayList<GenericData>();
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<data.size(); j++) {
				if(data.get(j).contains(targetColumn.get(i))) {
					if(data.get(j).indexOf(targetColumn.get(i)) == 0) {
						points.add(data.get(j).get(data.get(j).size()-1));
						points.add(data.get(j).get(1));
					}else if(data.get(j).indexOf(targetColumn.get(i)) == data.get(j).size()-1){
						points.add(data.get(j).get(0));
						points.add(data.get(j).get(data.get(j).size()-2));
					}else {
						points.add(data.get(j).get(data.get(j).indexOf(targetColumn.get(i))-1));
						points.add(data.get(j).get(data.get(j).indexOf(targetColumn.get(i))+1));
					}
				}
			}
		}
		return points;
	}
	public GenericData getClosestPoint(GenericData point, ArrayList<GenericData> data) {
		ArrayList<GenericData> closestHeights = new ArrayList<GenericData>();
		int closeHeight = closestBinarySearch(dataToValue(data, "V"), point.getLocation()[0]);
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).getLocation()[0] == data.get(closeHeight).getLocation()[0]) {
				closestHeights.add(data.get(i));
			}
		}
		// now we have a full row that has a Y value closest to the given point, now get V
		int closestPoint = closestBinarySearch(dataToValue(closestHeights, "Y"), point.getLocation()[1]);
		
		return data.get(data.indexOf(closestHeights.get(closestPoint)));
	}
}
