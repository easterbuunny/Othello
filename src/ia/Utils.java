package ia;

public class Utils {
	public static boolean isCornerSquare(int indexSquare) {
		return indexSquare == 0 || indexSquare == 7 || indexSquare == 56 || indexSquare == 63;
	}

	public static boolean isNextToaCornerSquare(int indexSquare) {
		int nextTocornerSquare[] = { 1, 8, 9, 6, 14, 13, 57, 48, 49, 54, 55, 62 };
		for (int i = 0; i < nextTocornerSquare.length; i++) {
			if (indexSquare == nextTocornerSquare[i])
				return true;
		}
		return false;
	}

	public static boolean isBorderSquare(int indexSquare) {
		return indexSquare <= 7 || indexSquare % 8 == 0 || indexSquare % 7 == 0 || indexSquare >= 56;
	}

	public static boolean isNextToaBorderSquare(int indexSquare) {
		return isBorderSquare(indexSquare + 8) || isBorderSquare(indexSquare - 8) || isBorderSquare(indexSquare - 1)
				|| isBorderSquare(indexSquare + 1);
	}
}
