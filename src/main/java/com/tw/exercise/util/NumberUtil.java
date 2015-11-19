package com.tw.exercise.util;

/**
 * Created by jjliu on 11/17/15.
 */
public class NumberUtil {

	public static int parseScoring(String scoring) {
		if (scoring == null || !scoring.matches("\\d+")) {
			return 0;
		}

		return Integer.parseInt(scoring);
	}
}
