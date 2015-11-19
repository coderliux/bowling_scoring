package com.tw.exercise.bowling;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScoringCardTest {

	@Test
	public void shouldReturnCurrentNot0AfterFrameAdded() throws Exception {
		ScoringCard card = new ScoringCard();
		Frame frame = new Frame("1", "2");
		Frame next = new Frame("2", "3");
		Frame returnFrame = card.addFrame(frame);
		Frame returnNextFrame = card.addFrame(next);

		assertThat(returnFrame, is(frame));
		assertThat(returnNextFrame, is(next));
		assertThat(card.getCurrentCount(), is(2));
	}

	@Test
	public void shouldReturnCurrent0WhenNoFrameAdded() throws Exception {
		ScoringCard card = new ScoringCard();

		assertThat(card.getCurrentCount(), is(0));
	}

	@Test
	public void shouldReturnTotalScoringWhenFrameAdded() throws Exception {
		ScoringCard card = new ScoringCard();
		Frame frame = new Frame("1", "2");
		Frame next = new Frame("2", "3");
		card.addFrame(frame);
		card.addFrame(next);

		assertThat(card.calculateTotal(), is(8));
	}

	@Test
	public void shouldReturnScoringZeroWhenNoFrameAdded() throws Exception {
		ScoringCard card = new ScoringCard();

		assertThat(card.calculateTotal(), is(0));
	}
}