package com.tw.exercise.bowling;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FrameTest {

	@Test
	public void shouldReturnTrueWhenBasicScoring() throws Exception {
		Frame frame=new Frame("1", "2");
		assertThat(frame.isBasicScoring(), is(true));
		assertThat(frame.isStrikeBonusScoring(), is(false));
		assertThat(frame.isSpareBonusScoring(), is(false));

		frame=new Frame("9", "1");
		assertThat(frame.isBasicScoring(), is(false));
		assertThat(frame.isStrikeBonusScoring(), is(false));
		assertThat(frame.isSpareBonusScoring(), is(true));

		frame=new Frame("10", "0");
		assertThat(frame.isBasicScoring(), is(false));
		assertThat(frame.isStrikeBonusScoring(), is(true));
		assertThat(frame.isSpareBonusScoring(), is(false));
	}

	@Test
	public void shouldReturnCurrentScoringWhenIsBasicScoringNoNextRound() throws Exception {
		Frame frame=new Frame("1", "2");
		assertThat(frame.getScoring(), is(3));

		frame=new Frame("8", "2");
		assertThat(frame.getScoring(), is(10));
	}

	@Test
	public void shouldReturnCurrentScoringWhenIsStrikeBonusScoringHasNextTwoRound() throws Exception {
		Frame frame=new Frame("10", "0");
		Frame next=new Frame("10", "0");
		Frame nextNext=new Frame("10", "0");

		next.setNextRound(nextNext);
		frame.setNextRound(next);
		assertThat(frame.getScoring(), is(30));

	}

	@Test
	public void shouldReturnCurrentScoringWhenIsStrikeBonusScoringHasNextOneRound() throws Exception {
		Frame frame=new Frame("10", "0");
		Frame next=new Frame("8", "2");
		frame.setNextRound(next);

		assertThat(frame.getScoring(), is(20));
	}


	@Test
	public void shouldReturnCurrentScoringWhenIsSpareBonusScoringHasNextRound() throws Exception {
		Frame frame=new Frame("9", "1");
		Frame next=new Frame("2", "3");

		frame.setNextRound(next);
		assertThat(frame.getScoring(), is(12));
	}

	@Test
	public void shouldReturn30WhenIsLastIsAllStrike() throws Exception {
		Frame frame=new Frame("10", "10");
		frame.setExtendRoll("10");

		assertThat(frame.getScoring(), is(30));
	}
}