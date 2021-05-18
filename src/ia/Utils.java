package ia;

public class Utils {
	// Verifie si la case est un coin de l'othellier
	// true si c'est un coin sinon false
	public static boolean isCornerSquare(int indexSquare) {
		return indexSquare == 0 || indexSquare == 7 || indexSquare == 56 || indexSquare == 63;
	}

	//Verifie si les case sont a cote d'un coin de l'othellier
	public static boolean isNextToaCornerSquare(int indexSquare) {
		int nextTocornerSquare[] = { 1, 8, 9, 6, 14, 15, 57, 48, 49, 54, 55, 62 };
		for (int i = 0; i < nextTocornerSquare.length; i++) {
			if (indexSquare == nextTocornerSquare[i])
				return true;
		}
		return false;
	}

	//Verifie si la case est sur le bord de l'othellier
	//true si une case sur le bord, sinon false
	public static boolean isBorderSquare(int indexSquare) {
		return indexSquare <= 7 || indexSquare % 8 == 0 || indexSquare % 7 == 0 || indexSquare >= 56;
	}
	//Verifie si la case est a cote d'une case au bord de l'othellier
	public static boolean isNextToaBorderSquare(int indexSquare) {
		return isBorderSquare(indexSquare + 8) || isBorderSquare(indexSquare - 8) || isBorderSquare(indexSquare - 1)
				|| isBorderSquare(indexSquare + 1);
	}
	
	public static boolean isCenterSquare(int indexSquare) {
		int centerSquares[] = {27,28,35,36};
		for (int i = 0; i < centerSquares.length; i++) {
			if (indexSquare == centerSquares[i])
				return true;
		}
		return false;
	}
}
