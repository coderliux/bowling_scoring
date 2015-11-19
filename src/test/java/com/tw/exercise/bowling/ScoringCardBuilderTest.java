package com.tw.exercise.bowling;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScoringCardBuilderTest {

	@Test
	public void shouldReturn10WhenGivenTwoBasicFrameRoll() throws Exception {
			String input="1 2 3 4";
		ScoringCard scoringCard=new ScoringCardBuilder().buildScoringCard(input);
		assertThat(scoringCard.getCurrentCount(),is(2));
		assertThat(scoringCard.calculateTotal(),is(10));
	}

	@Test
	public void shouldReturn29WhenGivenTwoSpareFrameRoll() throws Exception {
		String input="9 1 9 1";
		ScoringCard scoringCard=new ScoringCardBuilder().buildScoringCard(input);
		assertThat(scoringCard.getCurrentCount(),is(2));
		assertThat(scoringCard.calculateTotal(),is(29));
	}

	@Test
	public void shouldReturn18WhenGivenOneStrikeFrameRoll() throws Exception {
		String input="1 1 1 1 10 1 1";
		ScoringCard scoringCard=new ScoringCardBuilder().buildScoringCard(input);
		assertThat(scoringCard.getCurrentCount(),is(4));
		assertThat(scoringCard.calculateTotal(),is(18));
	}

	@Test
	public void shouldReturn300WhenGivenAllStrikeFrameRoll() throws Exception {
		String input="10 10 10 10 10 10 10 10 10 10 10 10";
		ScoringCard scoringCard=new ScoringCardBuilder().buildScoringCard(input);
		assertThat(scoringCard.getCurrentCount(),is(10));
		assertThat(scoringCard.calculateTotal(),is(300));
	}

	@Test
	public void shouldReturn74WhenGivenTwoStrikeAndOneSpareFrameRoll() throws Exception {
		String input="6 2 7 1 10 9 0 8 2 10";
		ScoringCard scoringCard=new ScoringCardBuilder().buildScoringCard(input);
		assertThat(scoringCard.calculateTotal(),is(74));
	}
}