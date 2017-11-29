package com.amalik;

import java.util.ArrayList;
import java.util.List;

import com.amalik.BFS;
import com.amalik.Position;
import com.amalik.State;

public class Main {
	public static void main(String[] args) {
		System.out.println("Missionaries and Cannibals - River Crossing");

		State initialState = new State (3, 3, Position.LEFT, 0, 0);

		BFS bfs = new BFS();

		State solution = bfs.startBFSSearch(initialState);

		printSolution(solution);

	}

	/**
	 * helper method to print out the results
	 * @param solution - the solution if one is available
	 */
	private static void printSolution(State solution) {
		if (null == solution) {
			System.out.print("\nUnable to find solution.");
		} else {
			System.out.println("\nSolution: \n(ML,CL,BP,MR,CR) ");
			List<State> path = new ArrayList<State>();
			State state = solution;
			
			while(null!=state) {
				path.add(state);
				state = state.getPreviousState();
			}

			int pathCost = path.size() - 1;

			for (int i = pathCost; i >= 0; i--) {
				state = path.get(i);
				System.out.println(state.toString());
			}
			
			System.out.println("\nPath Cost = " + pathCost);
		}
	}

}
