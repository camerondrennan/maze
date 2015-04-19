package solver;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class CameronDrennanSearch extends AbstractSolver {

	private Point goal;
	
	@Override
	public List<Point> solve(int[][] maze, Point start, Point goal) {
		this.goal = goal;
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
				if(!visitedNodes.contains(currentPoint)) {
					visitedNodes.add(currentPoint);
					//extending path
					PriorityQueue<Point> neighbours = new PriorityQueue<Point>(linkedListComparator);
					for(Point neighbour : getNeighbours(currentPoint, maze)) {
						//path checking
						if(!currentPath.contains(neighbour)) {
							neighbours.add(neighbour);
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
	
	Comparator<Point> linkedListComparator = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			return Double.compare(o1.distance(goal), o2.distance(goal));
		} 
	};

}


