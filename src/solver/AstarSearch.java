package solver;

import java.awt.Point;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AstarSearch extends AbstractSolver {
	
	private final Random randomiser = new Random();

	@Override
	public List<Point> solve(int[][] maze, Point start, Point goal) {
//		LinkedList<Point> path = new LinkedList<Point>();
//		List<Point> nextPoints = new LinkedList<Point>();
//		
//		boolean found = false;
//		
//		path.add(start);
		
		LinkedList<Point> foundPath = new LinkedList<Point>();
		LinkedList<Point> opensList = new LinkedList<Point>();
		LinkedList<Point> closedList = new LinkedList<Point>();
		Hashtable<Point, Integer> gscore = new Hashtable<Point, Integer>();
		Hashtable<Point, Point> cameFrom = new Hashtable<Point,Point>();
		Point x = new Point();
		gscore.put(start, 0);
		opensList.add(start);
		
		while(!opensList.isEmpty()) {
			int min = -1;
			
			for(Point f : opensList) {
				if(min == -1) {
					min = gscore.get(f) + getH(f, goal);
					x = f;
				}
				else {
					int currf = gscore.get(f) + getH(f, goal);
					if(min > currf) {
						min = currf;
						x = f;
					}
				}
			}
			if(x == goal) {
				Point curr = goal;
				while(curr != start) {
					foundPath.addFirst(curr);
					curr = cameFrom.get(curr);
				}
				return foundPath;
			}
			opensList.remove(x);
			closedList.add(x);
			for(Point y : getNeighbours(x, maze)) {
				if(closedList.contains(y)) {
					continue;
				}
				int tentGScore = gscore.get(x) + getDist(x,y);
				boolean distIsBetter = false;
				if(!opensList.contains(y)) {
					opensList.add(y);
					distIsBetter = true;
				}
				else if(tentGScore < gscore.get(y)) {
					distIsBetter = true;
				}
				if(distIsBetter) {
					cameFrom.put(y, x);
					gscore.put(y, tentGScore);
				}
			}
		}
		
		return null;
		
		
		
		
//		double g_score = 0;
//		double f_score = g_score + heuristic_cost_estimate(start, goal);
		
//		while(!found) {
//			Point current = path.getLast();
//			if(current.equals(goal)) {
//				return path;
//			}
//			nextPoints.clear();
//			for(Point neighbour : getNeighbours(current, maze)) {
//				if(!path.contains(neighbour)) {
//					nextPoints.add(neighbour);
//				}
//			}
//			if(nextPoints.isEmpty()) {
//				path.clear();
//				path.add(start);
//			}
//			else {
//				Point next = nextPoints.get(randomiser.nextInt(nextPoints.size()));
//				path.add(next);
//			}
//		}
//		return null;
	}
	
	private int getH(Point start, Point end) {
		int x;
		int y;
		x = (int) (start.getX()-end.getX());
		y = (int) (start.getY()-end.getY());
		if(x<0) {
			x = x * (-1);
		}
		if(y<0) {
			y = y * (-1);
		}
		return x+y;
	}
	
	private int getDist(Point start, Point end) {
		int ret = 0;
		if(start.getX() == end.getX() || start.getY() == end.getY()) {
			ret = 10;
		}
		else {
			ret = 14;
		}
		return ret;
	}

}
