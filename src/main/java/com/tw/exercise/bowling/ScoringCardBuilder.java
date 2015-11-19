package com.tw.exercise.bowling;

/**
 * Created by jjliu on 11/17/15.
 */
public class ScoringCardBuilder {


	public static final String SPLIT_TEXT = " ";
	public static final String SCORING_ZERO = "0";

	public ScoringCard buildScoringCard(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Not given scoring input!");
		}
		String pinsForEachRoll[] = input.split(SPLIT_TEXT);
		ScoringCard scoringCard = new ScoringCard();

		Frame tempFrame = null;
		int scoredRoll = 0;
		for (int i = 0; i < pinsForEachRoll.length; i++) {
			String firstRoll = pinsForEachRoll[i];
			String secondRoll = SCORING_ZERO;

			if (!isStrikeRoll(firstRoll) || isLastFrameRoll(scoredRoll)) {
				secondRoll = pinsForEachRoll[++i];
			}

			tempFrame = new Frame(firstRoll, secondRoll);
			if (hasExtendRoll(pinsForEachRoll.length, scoredRoll, i)) {
				String extendRoll = pinsForEachRoll[++i];
				tempFrame.setExtendRoll(extendRoll);
			}
			scoringCard.addFrame(tempFrame);

			scoredRoll += ScoringCard.FRAME_ROLL_SIZE;
		}

		return scoringCard;
	}

	private boolean hasExtendRoll(int allScoringLength, int rollCount, int currentRollIndex) {
		return isLastFrameRoll(rollCount) && currentRollIndex < allScoringLength;
	}

	public boolean isLastFrameRoll(int rollCount) {
		return rollCount >= ScoringCard.LAST_FRAME_START_ROLL_INDEX;
	}

	public boolean isStrikeRoll(String firstRoll) {
		return String.valueOf(ScoringCard.MAX_PIN_SIZE).equals(firstRoll);
	}
}
