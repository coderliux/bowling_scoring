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
		String allScoring[] = input.split(SPLIT_TEXT);
		ScoringCard scoringCard = new ScoringCard();

		Frame tempFrame = null;
		int rollCount = 0;
		for (int i = 0; i < allScoring.length; i++) {
			String firstRoll = allScoring[i];
			String secondRoll = null;
			if (String.valueOf(ScoringCard.MAX_PIN_SIZE).equals(firstRoll) && rollCount < ScoringCard.LAST_FRAME_START_ROLL_INDEX) {
				secondRoll = SCORING_ZERO;
			} else {
				secondRoll = allScoring[++i];
			}
			tempFrame = new Frame(firstRoll, secondRoll);
			if (rollCount >= ScoringCard.LAST_FRAME_START_ROLL_INDEX && i < allScoring.length) {
				String extendRoll = allScoring[++i];
				tempFrame.setExtendRoll(extendRoll);
			}
			scoringCard.addFrame(tempFrame);
			rollCount += ScoringCard.FRAME_ROLL_SIZE;
		}

		return scoringCard;
	}
}
