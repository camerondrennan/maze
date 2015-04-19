package solver;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch extends AbstractSolver {
	
	@Override
	public List<Point> solve(int[][] maze, Point start, Point goal) {
		ArrayDeque<LinkedList<Point>> queue = new ArrayDeque<LinkedList<Point>>();
		LinkedList<Point> currentPath = new LinkedList<Point>(); 
		HashSet<Point> visitedNodes = new HashSet<Point>();
		
		currentPath.add(start);
		if(start.equals(goal)){
			return currentPath;
		}
		
		queue.add(currentPath);
		
		while(!queue.isEmpty()){
			
			currentPath = queue.poll();
			Point currentPoint = currentPath.get((currentPath.size()-1));
			if((currentPoint.equals(goal))){
				return currentPath;
			}
			else {
				//node checking
				if(!visitedNodes.contains(currentPoint)){
					visitedNodes.add(currentPoint);
					//extending path
					for(Point neighbour : getNeighbours(currentPoint, maze)) {
						//path checking
						if(!currentPath.contains(neighbour)) {
							LinkedList<Point> nextPath = new LinkedList<Point>();
							nextPath.addAll(currentPath);
							nextPath.add(neighbour);
							queue.add(nextPath);
						}
					}
				}
			}
			
		}
		return null;
	}

}
