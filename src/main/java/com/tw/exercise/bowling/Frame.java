package com.tw.exercise.bowling;

import static com.tw.exercise.util.NumberUtil.parseScoring;

/**
 * Created by jjliu on 11/17/15.
 */
public class Frame {
	private String firstRoll;
	private String secondRoll;
	private String extendRoll;
	private Frame nextRound;


	public Frame(String firstRoll, String secondRoll) {
		this.firstRoll = firstRoll;
		this.secondRoll = secondRoll;
	}

	public String getFirstRoll() {
		return firstRoll;
	}

	public String getSecondRoll() {
		return secondRoll;
	}

	public String getExtendRoll() {
		return extendRoll;
	}

	public void setExtendRoll(String extendRoll) {
		this.extendRoll = extendRoll;
	}

	public boolean isBasicScoring() {
		return getCurrentFramePins() < ScoringCard.MAX_PIN_SIZE;

	}

	public boolean isStrikeBonusScoring() {
		return parseScoring(firstRoll) == ScoringCard.MAX_PIN_SIZE;
	}

	public boolean isSpareBonusScoring() {
		return !isStrikeBonusScoring() && getCurrentFramePins() == ScoringCard.MAX_PIN_SIZE;
	}


	public void setNextRound(Frame nextRound) {
		this.nextRound = nextRound;
	}

	public Frame getNextRound() {
		return nextRound;
	}

	public boolean hasNextRound() {
		return nextRound != null;
	}

	public boolean isLast() {
		return extendRoll != null;
	}


	public int getScoring() {
		int currentScoring = getCurrentFramePins();
		if (isLast()) {
			return currentScoring + parseScoring(getExtendRoll());
		}
		if (isStrikeBonusScoring()) {
			currentScoring = currentScoring + getNextTwoRollScoring();
		} else if (isSpareBonusScoring()) {
			currentScoring = currentScoring + getNextOneRollScoring();
		}

		return currentScoring;
	}

	public int getCurrentFramePins() {
		return parseScoring(getFirstRoll()) + parseScoring(getSecondRoll());
	}

	public int getNextOneRollScoring() {
		Frame nextRound = getNextRound();
		if (nextRound == null) {
			return 0;
		}
		return parseScoring(nextRound.getFirstRoll());
	}

	public int getNextTwoRollScoring() {
		int scoringNextTwo = 0;
		Frame nextRound = getNextRound();
		if (nextRound == null) {
			return scoringNextTwo;
		}
		scoringNextTwo = nextRound.getCurrentFramePins();
		if (nextRound.isStrikeBonusScoring() && nextRound.hasNextRound()) {
			scoringNextTwo = scoringNextTwo + parseScoring(nextRound.nextRound.getFirstRoll());
		}
		return scoringNextTwo;
	}

}
