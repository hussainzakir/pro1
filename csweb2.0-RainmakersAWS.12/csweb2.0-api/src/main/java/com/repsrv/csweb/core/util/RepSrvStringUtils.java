package com.repsrv.csweb.core.util;


public class RepSrvStringUtils {

	/**
	 * Notes by line
	 * 
	 * @param text
	 * @param noOfLines
	 * @param lineLenght
	 * @return
	 * @version 2.0
	 */
	public final static String[] textToArrayByLine(String text, int noOfLines, int lineLenght){
		String textArray[] = new String[noOfLines];
		if (text != null)
		{
			// textArray = ;
			int currentLine = 1;
			int endIndex = 0;
			int initialIndex = 0;
			int textLenght = text.trim().length();
			int index = 0;
			while (endIndex < textLenght){
				initialIndex = (currentLine - 1) * lineLenght;
				endIndex = currentLine++ * lineLenght;
				if (endIndex >= textLenght){
					endIndex = textLenght;
				}
				textArray[index] = text.substring(initialIndex, endIndex);
				index++;
			}
		}
		// Make sure there are no null values. The db doesn't take null values.
		for (int i = 0; i < noOfLines; i++) {
			if (textArray[i] == null) {
				textArray[i] = "";
			}
		}
		return textArray;
	}

	public final static String[] textToArrayByLine(String text, int noOfLines, int lineLenght, char delimiter)
	{
		// No delimiter has been passed
		if (delimiter == '\u0000') {
			return textToArrayByLine(text, noOfLines, lineLenght);
		}
		String textArray[] = new String[noOfLines];
		StringBuffer strBuffer = null;
		char textCharacters[] = null;
		if (text != null) {
			textCharacters = text.toCharArray();
			strBuffer = new StringBuffer();
			int index = 0;
			for (int i = 0; i < textCharacters.length; i++) {
				char currentCharacter = textCharacters[i];

				if (currentCharacter == '\r') {
					continue;
				}
				if (currentCharacter == delimiter) {
					// System.out.println("space"+currentCharacter);
					textArray[index] = strBuffer.toString();
					strBuffer.delete(0, strBuffer.length());
					index++;
					continue;
				}
				// System.out.println("other"+currentCharacter);
				if (strBuffer.length() < lineLenght) {
					strBuffer.append(textCharacters[i]);
					if (i == textCharacters.length - 1 || strBuffer.length() >= lineLenght) {
						textArray[index] = strBuffer.toString();
						strBuffer.delete(0, strBuffer.length());
						index++;

					}
				}
			}
		}
		// Make sure there are no null values
		for (int i = 0; i < noOfLines; i++) {
			if (textArray[i] == null) {
				textArray[i] = "";
			}
		}
		return textArray;
	}
}
