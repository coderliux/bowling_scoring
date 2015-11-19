package com.tw.exercise.bowling;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FrameTest {

	@Test
	public void shouldReturnTrueWhenBasicScoring() throws Exception {
		Frame frame = new Frame("1", "2");
		assertThat(frame.isBasicScoring(), is(true));
	}

	@Test
	public void shouldReturnTrueWhenSpareBonusScoring() throws Exception {
		Frame frame = new Frame("9", "1");
		assertThat(frame.isSpareBonusScoring(), is(true));
	}

	@Test
	public void shouldReturnTrueWhenStrikeBonusScoring() throws Exception {
		Frame frame = new Frame("10", "0");
		assertThat(frame.isStrikeBonusScoring(), is(true));
	}

	@Test
	public void shouldReturnScoringWhenIsBasicRollNoNextRound() throws Exception {
		Frame frame = new Frame("1", "2");
		assertThat(frame.getScoring(), is(3));
	}

	@Test
	public void shouldReturnScoring10WhenIsStrikeRollNoNextRound() throws Exception {
		Frame frame = new Frame("10", "0");
		assertThat(frame.getScoring(), is(10));
	}

	@Test
	public void shouldReturnScoring10WhenIsSpareRollNoNextRound() throws Exception {
		Frame frame = new Frame("8", "2");
		assertThat(frame.getScoring(), is(10));
	}

	@Test
	public void shouldReturnScoring30WhenIsStrikeRollHasNextTwoStrikeRound() throws Exception {
		Frame frame = new Frame("10", "0");
		Frame next = new Frame("10", "0");
		Frame nextNext = new Frame("10", "0");

		next.setNextRound(nextNext);
		frame.setNextRound(next);
		assertThat(frame.getScoring(), is(30));

	}

	@Test
	public void shouldReturnScoring20WhenIsStrikeRollHasNextOneSpareRound() throws Exception {
		Frame frame = new Frame("10", "0");
		Frame next = new Frame("8", "2");
		frame.setNextRound(next);

		assertThat(frame.getScoring(), is(20));
	}

	@Test
	public void shouldReturnScoringWhenIsSpareRollHasNextBasicRound() throws Exception {
		Frame frame = new Frame("9", "1");
		Frame next = new Frame("2", "3");

		frame.setNextRound(next);
		assertThat(frame.getScoring(), is(12));
	}

	@Test
	public void shouldReturnScoring30WhenIsLastIsAllStrike() throws Exception {
		Frame frame = new Frame("10", "10");
		frame.setExtendRoll("10");

		assertThat(frame.getScoring(), is(30));
	}

	@Test
	public void shouldReturnNextOneRollScoring0WhenNextIsNull() throws Exception {
		Frame frame = new Frame("1", "2");
		frame.setNextRound(null);

		assertThat(frame.getNextOneRollScoring(), is(0));
	}

	@Test
	public void shouldReturnNextOneRollScoringWhenNextIsNotNull() throws Exception {
		Frame frame = new Frame("1", "2");
		Frame next = new Frame("1", "2");
		frame.setNextRound(next);

		assertThat(frame.getNextOneRollScoring(), is(1));
	}

	@Test
	public void shouldReturnNextTwoRollScoring0WhenNextIsNull() throws Exception {
		Frame frame = new Frame("1", "2");
		frame.setNextRound(null);

		assertThat(frame.getNextTwoRollScoring(), is(0));
	}

	@Test
	public void shouldReturnNextCurrentPingWhenNextRollIsBasic() throws Exception {
		Frame frame = new Frame("1", "2");
		frame.setNextRound(new Frame("1", "2"));

		assertThat(frame.getNextTwoRollScoring(), is(3));
	}

	@Test
	public void shouldReturnNextTwoRollScoringWhenNextRollIsStrike() throws Exception {
		Frame frame = new Frame("1", "2");
		Frame next = new Frame("10", "0");
		next.setNextRound(new Frame("1", "2"));
		frame.setNextRound(next);

		assertThat(frame.getNextTwoRollScoring(), is(11));
	}


	@Test
	public void shouldReturnTotalPinsWheneverAnyRoll() throws Exception {
		Frame frame = new Frame("1", "2");
		assertThat(frame.getCurrentFramePins(), is(3));

		frame = new Frame("10", "0");
		assertThat(frame.getCurrentFramePins(), is(10));

		frame = new Frame("8", "2");
		assertThat(frame.getCurrentFramePins(), is(10));
	}


}