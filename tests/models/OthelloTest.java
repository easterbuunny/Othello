package models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.Othello;

public class OthelloTest {
	private Othello o;
	private Player blackPlayer;
	private Player whitePlayer;

	@BeforeEach
	public void beforeEach() {
		blackPlayer = new HumanPlayer("J1", TypePiece.BLACK);
		whitePlayer = new HumanPlayer("J2", TypePiece.WHITE);

		o = new Othello(blackPlayer, whitePlayer);
	}

	@AfterEach
	public void afterEach() {
		o = null;
	}

	@Test
	public void getCoupValideTest_01() {
		assertEquals(4, o.getValidMoves(o.getBlackPlayer().getTypePiece()).size());
	}

	@Test
	public void getCoupValideTest_02() {
		assertEquals(4, o.getValidMoves(o.getWhitePlayer().getTypePiece()).size());
	}

	@Test

	// Les Noirs commencent par jouer
	public void getTourTest_01() {
		assertEquals(blackPlayer,o.getTurn());
	}

	@Test
	// Si le jNoir joue un coup invalide , alors c'est encore a lui de jouer
	public void getTourTest_02() {
		o.play(2);
		assertEquals(blackPlayer,o.getTurn());
	}
	
	@Test
	// Si les Noir viennent de jouer et que les blanc ont un coup valide , alors
	// c'est au blanc de jouer
	public void getTourTestSup_022() {
		o.play(19); // Le joueur noir joue un coup valide
		assertEquals(whitePlayer,o.getTurn());
		
	}

	@Test
	// Si le joueur Noir vient de jouer mais que les blancs n'ont pas de coup valide
	// et que les noirs ont un coup valide alors c'est au noir de jouer
	public void getTourTest_03() {
		for(int i = 1 ; i < 64 ; i++) {
			if(i!= 7 || i != 56 || i !=63) {
				o.getBoard().setSquare(TypePiece.WHITE,i);
			}
		}
		o.getBoard().setSquare(TypePiece.BLACK, 63);
		// Les blancs n'ont plus de coups valide sur 2 coups.
		o.play(7);
		assertEquals(blackPlayer,o.getTurn());
	}

	// SI les Noires et les blancs n'ont pas de coup valdie NUll
	public void getTourTest_04() {

	}

	// Si les blancs viennent de jouer et que les noires ont un coup valdie alors
	// c'est au noir de jouer
	public void getTourTest_05() {
		
	}

	@Test
	// Si les blancs viennent de jouer et que les noirs n'ont pas de coup valide et
	// que les blanc ont un coup valide alors c'est au blanc de jouer
	public void getTourTest_06() {
		for(int i = 1 ; i < 64 ; i++) {
			if(i!= 7 || i != 56 || i !=63) {
				o.getBoard().setSquare(TypePiece.BLACK,i);
			}
		}
		o.getBoard().setSquare(TypePiece.WHITE, 63);
		// Les blancs n'ont plus de coups valide sur 2 coups.
		o.play(7);
		assertEquals(blackPlayer,o.getTurn());
	}

}
