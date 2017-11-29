package com.amalik;

import java.util.ArrayList;
import java.util.List;


enum Position {RIGHT, LEFT}

public class State {

	private State previousState;
	private int missionariesLeft;
	private int cannibalsLeft;
	private Position boatPosition;
	private int missionariesRight;
	private int cannibalsRight;

	/**
	 * Class Constructor - initialize new State object
	 * @param missionariesLeft - number of missionaries on the left bank of the river.
	 * @param cannibalsLeft - number of cannibals on the left bank of the river.
	 * @param boatPosition - position of the boat
	 * @param missionariesRight - number of missionaries on the right bank of the river.
	 * @param cannibalsRight - number of cannibals on the right bank of the river.
	 */
	public State (int missionariesLeft, int cannibalsLeft, Position boatPosition,
			int missionariesRight, int cannibalsRight) {

		this.missionariesLeft = missionariesLeft;
		this.cannibalsLeft = cannibalsLeft;
		this.boatPosition = boatPosition;
		this.missionariesRight = missionariesRight;
		this.cannibalsRight = cannibalsRight;
	}

	@Override
	public String toString() {
		if (boatPosition == Position.LEFT) {
			return "(" + missionariesLeft + ", " +  cannibalsLeft + ", L, "
					+  missionariesRight + ", " + cannibalsRight + ")";
		} else {
			return "(" + missionariesLeft + ", " +  cannibalsLeft + ", R, "
					+  missionariesRight + ", " + cannibalsRight + ")";
		}
	}

	@Override
	public boolean equals(Object object) {
		State state = (State) object;
		return (state.missionariesLeft == missionariesLeft 
				&& state.cannibalsLeft == cannibalsLeft
				&& state.boatPosition == boatPosition 
				&& state.missionariesRight == missionariesRight
				&& state.cannibalsRight == cannibalsRight);
	}

	/**
	 * checks if state is goal state.
	 * @return true if state is goal state, false otherwise.
	 */
	public boolean goalTest() {
		return missionariesLeft == 0 && cannibalsLeft == 0 && boatPosition == Position.RIGHT
				&& missionariesRight == 3 && cannibalsRight == 3;
	}

	/**
	 * checks if state is a valid state, i.e. missionaries are not out-numbered by cannibals 
	 * at any given time.
	 * @return true if state is valid, false otherwise.
	 */
	public boolean isValidState() {
		if (missionariesLeft >= 0 && cannibalsLeft >= 0 
				&& missionariesRight >= 0 && cannibalsRight >= 0
				&& (missionariesLeft == 0 || missionariesLeft >= cannibalsLeft)
				&& (missionariesRight == 0 || missionariesRight >= cannibalsRight)) {
			return true;
		}
		return false;
	}

	/**
	 * Actions - generates a List of all possible and valid next states, given current state.
	 * @return List of all all possible and valid next states.
	 */
	public List<State> generateNextValidStates() {
		List<State> nextValidStates = new ArrayList<State>();
		if (boatPosition == Position.LEFT) {
			// Two missionaries cross left to right.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft - 2, cannibalsLeft, 
					Position.RIGHT, missionariesRight + 2, cannibalsRight)); 
			// Two cannibals cross left to right.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft, cannibalsLeft - 2, 
					Position.RIGHT, missionariesRight, cannibalsRight + 2)); 
			// One missionary and one cannibal cross left to right.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft - 1, cannibalsLeft - 1, 
					Position.RIGHT, missionariesRight + 1, cannibalsRight + 1)); 
			// One missionary crosses left to right.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft - 1, cannibalsLeft, 
					Position.RIGHT, missionariesRight + 1, cannibalsRight));
			// One cannibal crosses left to right.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft, cannibalsLeft - 1,
					Position.RIGHT, missionariesRight, cannibalsRight + 1));
		} else {
			// Two missionaries cross right to left.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft + 2, cannibalsLeft, 
					Position.LEFT, missionariesRight - 2, cannibalsRight)); 
			// Two cannibals cross right to left.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft, cannibalsLeft + 2, 
					Position.LEFT, missionariesRight, cannibalsRight - 2));
			// One missionary and one cannibal cross right to left.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft + 1, cannibalsLeft + 1, 
					Position.LEFT, missionariesRight - 1, cannibalsRight - 1));
			// One missionary crosses right to left.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft + 1, cannibalsLeft, 
					Position.LEFT, missionariesRight - 1, cannibalsRight)); 
			// One cannibal crosses right to left.
			validateAndAddtoList(nextValidStates, new State(missionariesLeft, cannibalsLeft + 1, 
					Position.LEFT, missionariesRight, cannibalsRight - 1));
		}
		return nextValidStates;
	}

	/**
	 * helper method that verifies if a state is a valid state, if so it adds it to the 
	 * list of valid next states.
	 * @param nextValidStates - List of all all possible and valid next states
	 * @param newState - new state to be validated and added.
	 */
	private void validateAndAddtoList(List<State> nextValidStates, State newState) {
		if (newState.isValidState()) {
			newState.setPreviousState(this);
			nextValidStates.add(newState);
		}
	}

	public State getPreviousState() {
		return previousState;
	}

	public int getMissionariesLeft() {
		return missionariesLeft;
	}

	public int getCannibalsLeft() {
		return cannibalsLeft;
	}

	public Position getBoatPosition() {
		return boatPosition;
	}

	public int getMissionariesRight() {
		return missionariesRight;
	}

	public int getCannibalsRight() {
		return cannibalsRight;
	}

	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}

	public void setMissionariesLeft(int missionariesLeft) {
		this.missionariesLeft = missionariesLeft;
	}

	public void setCannibalsLeft(int cannibalsLeft) {
		this.cannibalsLeft = cannibalsLeft;
	}

	public void setBoatPosition(Position boatPosition) {
		this.boatPosition = boatPosition;
	}

	public void setMissionariesRight(int missionariesRight) {
		this.missionariesRight = missionariesRight;
	}

	public void setCannibalsRight(int cannibalsRight) {
		this.cannibalsRight = cannibalsRight;
	}
}
