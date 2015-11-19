package com.tw.exercise.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberUtilTest {

	@Test
	public void testParseScoring() throws Exception {
		assertThat(NumberUtil.parseScoring("0"), is(0));
		assertThat(NumberUtil.parseScoring("5"), is(5));
		assertThat(NumberUtil.parseScoring(""), is(0));
		assertThat(NumberUtil.parseScoring("-"), is(0));
	}
}