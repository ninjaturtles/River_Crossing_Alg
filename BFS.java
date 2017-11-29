package com.amalik;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.amalik.State;

public class BFS {
	
	/**
	 * Performs BFS to find the optimal next state in 
	 * @param initialState - Initial state (ML, CL, BP, MR, CR) = (3, 3, L, 0, 0)
	 * @return the optimal next state, or null on error
	 */
	public State startBFSSearch(State initialState) {
		if (initialState.goalTest()) {
			return initialState;
		}
		
		Queue<State> frontier = new LinkedList<State>();
		Set<State> exploredStates = new HashSet<State>();
		frontier.add(initialState);
		
		while (true) {
			if (frontier.isEmpty()) {
				return null;	
			}
			
			State state = frontier.poll();
			exploredStates.add(state);
			List<State> nextValidStates = state.generateNextValidStates();
			
			for (State current : nextValidStates) {
				if (!exploredStates.contains(current) || !frontier.contains(current)) {
					if (current.goalTest()) {
						return current;
					}
					frontier.add(current);
				}
			}
		}
	}

}
