package com.tw.exercise.util;

/**
 * Created by jjliu on 11/17/15.
 */
public class NumberUtil {

	public static int parseScoring(String scoring) {
		boolean isNumber = scoring.matches("\\d+");
		if (isNumber) {
			return Integer.parseInt(scoring);
		}

		switch (scoring) {
			case "X":
				return 10;
			case "-":
				return 0;

		}
		return 0;
	}
}
