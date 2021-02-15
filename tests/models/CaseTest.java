package models;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaseTest {
	private static Square square;
	@BeforeEach
	public void beforeEach() {
		 square = new Square();
	}
	@AfterEach
	public void afterEach() {
		square = null;
	}
	@Test
	// La Case est creer sans piece au depart
	public void testIsEmpty_01() {
		assertTrue(square.isEmpty());
	}
	
	@Test
	// L'ajout d'une piece de type PieceJ1 -> La case n'est plus vide
	public void testIsEmpty_02() {
		square.setPiece(TypePiece.BLACK);
		assertFalse(square.isEmpty());
	}
	@Test
	// L'ajout d'une piece de type PieceJ2 -> La case n'est plus vide
	public void testIsEmpty_03() {
		square.setPiece(TypePiece.WHITE);
		assertFalse(square.isEmpty());
	}
	@Test
	// L'ajout d'une piece de type NONE -> La case est vide
	public void testIsEmpty_04() {
		square.setPiece(TypePiece.NONE);
		assertTrue(square.isEmpty());
	}
	
	
}
