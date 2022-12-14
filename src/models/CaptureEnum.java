package models;

public enum CaptureEnum {
	NORTH(-8),SOUTH(8),WEST(-1),EAST(1),NORTHWEST(-8 - 1),NORTHEAST(-8 + 1),SOUTHWEST(8 - 1),SOUTHEAST(8 + 1);
	private int nextSquare;
	// Nombre indiquant de combien de case on doit se decaler dans le tableau pour atteindre la case suivante selon la direction
	
	CaptureEnum(int nextSquare) {
		this.nextSquare = nextSquare;
	}
	
	public int getNextSquare() {
		return nextSquare;
	}
	
	/**
	 * Determine si une capture peut avoir lieu dans une certaine direction
	 * @param squareIndex indice de la case a tester
	 * @return True si une capture NE PEUT PAS etre envisagee dans la direction , false sinon
	 */
	public boolean cannotCaptureInThisDirection(int squareIndex) {
		if(this.equals(NORTH)){
			return squareIndex <= 7;
		}
		if(this.equals(SOUTH)) {
			return squareIndex >= 56;
		}
		if(this.equals(WEST)) {
			return (squareIndex % 8 == 0);
		}
		if(this.equals(EAST)) {
			return squareIndex % 8 == 7;
		}
		if(this.equals(NORTHWEST)) {
			return squareIndex <= 7 || squareIndex % 8 == 0;
		}
		if(this.equals(NORTHEAST)) {
			return squareIndex <= 7 || squareIndex % 8 == 7;
		}
		if(this.equals(SOUTHWEST)) {
			return squareIndex >= 56 || squareIndex % 8 == 0;
		}
		if(this.equals(SOUTHEAST)) {
			return squareIndex >= 56 || squareIndex % 8 == 7; 
		}
		return true;
	}
}
