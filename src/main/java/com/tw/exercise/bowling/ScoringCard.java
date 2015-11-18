package com.tw.exercise.bowling;

/**
 * Created by jjliu on 11/17/15.
 */
public class ScoringCard {
	public static final int MAX_FRAME_SIZE = 10;
	public static final int MAX_PIN_SIZE = 10;
	public static final int LAST_FRAME_START_ROLL_INDEX = 18;
	public static final int FRAME_ROLL_SIZE = 2;


	private Frame allFrame[] = new Frame[MAX_FRAME_SIZE];
	private int currentCount = 0;

	public Frame addFrame(Frame frame) {
		if (hasMoreThanOneFrame()) {
			allFrame[(currentCount - 1)].setNextRound(frame);
		}
		allFrame[currentCount++] = frame;
		return frame;
	}

	private boolean hasMoreThanOneFrame() {
		return currentCount > 0;
	}

	public int calculateTotal() {
		int total = 0;
		for (int idx = 0; idx < currentCount; idx++) {
			total += allFrame[idx].getScoring();
		}
		return total;
	}

	public int getCurrentCount() {
		return currentCount;
	}
}
