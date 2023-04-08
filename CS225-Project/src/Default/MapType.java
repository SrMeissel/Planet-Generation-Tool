//Look into using ArrayList subList AND iterator 

package Default;

import java.util.ArrayList;

public class MapType {
	private int Number = 5000;
		
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
				point.setDv(Dv);
				point.setDy(Dy);
				points.add(point);
				
				if(Count > 1000 && Count < 2000) {
					System.out.println("point " + Count + " made at: V= " + points.get(Count).getLocation()[0] + " Y= " + points.get(Count).getLocation()[1] );
				}
				Count++;
			}	
		}
		return points;
	}
	
	public ArrayList<GenericData> findNeighbors(ArrayList<GenericData> data, GenericData point) {
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


	//Make better with ArrayList.subList()
	public ArrayList<ArrayList<GenericData>> seperatePoints(ArrayList<GenericData> data){
		ArrayList<ArrayList<GenericData>> seperatedPoints = new ArrayList<ArrayList<GenericData>>(); 
		int size = 0;
		double savedValue = data.get(0).getLocation()[0];
		
		for(int i=0; i < data.size(); i++) {
			if(data.get(i).getLocation()[0] == savedValue) {
				try {
				seperatedPoints.get(size).add(data.get(i));
				} catch(Exception e) {
					seperatedPoints.add(new ArrayList<GenericData>());
					seperatedPoints.get(size).add(data.get(i));
				}
			}else {
				savedValue = data.get(i).getLocation()[0];
				size++;
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
			case "Designation":
				values[i] = data.get(i).getDesignation();
				break;
			default:
				return null;
			}
		}
		return values;
	}
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
			neighbors.add(data.get(targetRow-1).get(lowerColumn));
			 upperColumn = closestBinarySearch(dataToValue(data.get(targetRow-1), "Y"), targetValue);
			neighbors.add(data.get(targetRow+1).get(upperColumn));
		}
		return neighbors;
	}
	public ArrayList<GenericData> getHorizontalNeighbors(ArrayList<ArrayList<GenericData>> data, ArrayList<GenericData> targetColumn){
		int rows = targetColumn.size();
		ArrayList<GenericData> points = new ArrayList<GenericData>();
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<data.size(); j++) {
				if(data.get(j).contains(targetColumn.get(i))) {
					if(data.get(j).indexOf(targetColumn.get(i)) == 0) {
						points.add(data.get(j).get(data.size()-1));
						points.add(data.get(j).get(1));
					}else if(data.get(j).indexOf(targetColumn.get(i)) == data.get(j).size()-1){
						points.add(data.get(j).get(0));
						points.add(data.get(j).get(targetColumn.size()-2));
					}else {
						points.add(data.get(j).get(data.get(j).indexOf(targetColumn.get(i))-1));
						points.add(data.get(j).get(data.get(j).indexOf(targetColumn.get(i))+1));
					}
				}
			}
		}
		return points;
	}
}
