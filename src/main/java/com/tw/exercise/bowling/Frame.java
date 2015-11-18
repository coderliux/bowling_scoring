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
		return (parseScoring(firstRoll) + parseScoring(secondRoll)) < ScoringCard.MAX_PIN_SIZE;

	}

	public boolean isStrikeBonusScoring() {
		return "X".equals(firstRoll) || parseScoring(firstRoll) == ScoringCard.MAX_PIN_SIZE;
	}

	public boolean isSpareBonusScoring() {
		return "/".equals(secondRoll) ||
					   (!isStrikeBonusScoring() && (parseScoring(firstRoll)
															+ parseScoring(secondRoll)) == ScoringCard.MAX_PIN_SIZE);
	}


	public void setNextRound(Frame nextRound) {
		this.nextRound = nextRound;
	}

	public Frame getNextRound() {
		return nextRound;
	}

	public boolean isLast() {
		return extendRoll != null;
	}


	public int getScoring() {
		int currentScoring = 0;
		if (isLast()) {
			return parseScoring(getFirstRoll()) + parseScoring(getSecondRoll()) + parseScoring(getExtendRoll());
		}
		if (isStrikeBonusScoring()) {
			currentScoring = parseScoring(getFirstRoll()) + getNextTwoRollScoring();
		} else if (isSpareBonusScoring()) {
			currentScoring = parseScoring(getFirstRoll()) + parseScoring(getSecondRoll()) + getNextOneRollScoring();
		} else {
			currentScoring = parseScoring(getFirstRoll()) + parseScoring(getSecondRoll());
		}
		return currentScoring;
	}

	private int getNextOneRollScoring() {
		Frame nextRound = getNextRound();
		if (nextRound == null) {
			return 0;
		}
		return parseScoring(nextRound.getFirstRoll());
	}

	private int getNextTwoRollScoring() {
		int currentScoring = 0;
		Frame nextRound = getNextRound();
		if (nextRound == null) {
			return currentScoring;
		}
		if (nextRound.isStrikeBonusScoring()) {
			currentScoring += parseScoring(nextRound.getFirstRoll());
			Frame nextNextRound = nextRound.getNextRound();
			if (nextNextRound != null) {
				currentScoring += parseScoring(nextNextRound.getFirstRoll());
			} else if (nextRound.isLast()) {
				currentScoring += parseScoring(nextRound.getSecondRoll());
			}
		} else {
			currentScoring = parseScoring(nextRound.getFirstRoll()) + parseScoring(nextRound.getSecondRoll());
		}
		return currentScoring;
	}
}
