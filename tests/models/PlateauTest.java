package models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlateauTest {
	Board board;

	public void remplirPlateau(Board board, int nbRow, int nbColumn) {
		if (nbRow > 8 || nbColumn > 8 || nbRow < 0 || nbColumn < 0)
			return;

		for (int i = 0; i < nbRow; i++) {
			for (int j = 0; j < nbColumn; j++) {
				if (j % 2 == 0) {
					board.getSquare(i,j).setPiece(TypePiece.BLACK);
				} else {
					board.getSquare(i,j).setPiece(TypePiece.WHITE);
				}
			} // Fin for colonne
		} // Fin for ligne
	}

	@BeforeEach
	public void beforeEach() {
		board = new Board();
	}

	@AfterEach
	public void afterEach() {
		board = null;
	}

	@Test
	void testisFull_01() {
		assertFalse(board.isFull());
	}

	@Test
	void testisFull_02() {
		remplirPlateau(board, 5, 6);
		assertFalse(board.isFull());
	}

	@Test
	void testisFull_03() {
		remplirPlateau(board, 2, 4);
		assertFalse(board.isFull());
	}

	@Test
	void testisFull_04() {
		remplirPlateau(board, 8, 8);
		assertTrue(board.isFull());
	}

	@Test
	void testInit_01() {
		// Verifie que les pieces sont bien ajoute a la bonne position
		assertFalse(board.getSquare(4,4).isEmpty());
		assertFalse(board.getSquare(3, 3).isEmpty());
		assertFalse(board.getSquare(4, 3).isEmpty());
		assertFalse(board.getSquare(3, 4).isEmpty());
	}

	void testInit_02() {
		// Verifie que les bonnes pieces sont bien ajoutees a la bonne position
		assertTrue(board.getSquare(4, 4).getPiece().getTypePiece().equals(TypePiece.WHITE));
		assertTrue(board.getSquare(3, 3).getPiece().getTypePiece().equals(TypePiece.WHITE));
		assertTrue(board.getSquare(4, 3).getPiece().getTypePiece().equals(TypePiece.BLACK));
		assertTrue(board.getSquare(3, 4).getPiece().getTypePiece().equals(TypePiece.BLACK));
	}

	@ParameterizedTest
	@CsvSource({ "1, 1", "2, 2", "6, 7", "1, 5", "4, 5", "5, 5", "0 , 4" })
	void testInit_03(int indexLigne, int indexCol) {
		assertTrue(board.getSquare(indexLigne, indexCol).isEmpty());
	}
	
	@Test
	void testGetIndicePiece_01() {
		ArrayList<Integer> pieceNoire = new ArrayList<Integer>();
		pieceNoire.add(28);
		pieceNoire.add(35);
		assertEquals(pieceNoire,board.getPiecesIndex(TypePiece.BLACK));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {5,12,22,37,24,9,8,62})
	void testGetIndicePiece_02 (int value) {
		board.setSquare(TypePiece.WHITE,value);
		assertTrue(board.getPiecesIndex(TypePiece.WHITE).contains(value));
	}
	
	@Test
	// Cas initial
	void testGetNbPiece_01() {
		assertEquals( board.getNbPiece(TypePiece.BLACK),2);
		assertEquals( board.getNbPiece(TypePiece.WHITE),2);
	}
	@Test
	// On enleve des pieces
	void testGetNbPiece_02() {
		board.setSquare(TypePiece.WHITE, 4,3);
		board.setSquare(TypePiece.WHITE, 3,4);
		assertEquals(board.getNbPiece(TypePiece.BLACK),0);
		assertEquals(board.getNbPiece(TypePiece.WHITE),4);
	}
	

}
