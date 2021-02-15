package models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaptureEvaluatorTest {
	private Board board;
	private ArrayList<Integer> capturedPieces;

	@BeforeEach
	public void beforeEach() {
		board = new Board();
		capturedPieces = new ArrayList<Integer>();
	}

	@AfterEach
	public void afterEach() {
		board = null;
		capturedPieces = null;
	}

	@Test
	// Capture en hauteur d'une seule piece
	public void testCaptureNorth_01() {
		capturedPieces.add(36);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 44, CaptureEnum.NORTH));
		capturedPieces.clear();
		capturedPieces.add(35);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 43, CaptureEnum.NORTH));
	}

	@Test
	// Capture en hauteur de plusieurs pieces
	public void testCaptureNorth_02() {
		board.setSquare(TypePiece.WHITE, 44);
		capturedPieces.add(44);
		capturedPieces.add(36);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 52, CaptureEnum.NORTH));
	}

	@Test
	// Capture une rangee complete en hauteur
	public void testCaptureNorth_03() {
		Integer pieceACap[] = { 48, 40, 32, 24, 16, 8 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.WHITE, 0);
		board.setSquare(TypePiece.BLACK, 8);
		board.setSquare(TypePiece.BLACK, 16);
		board.setSquare(TypePiece.BLACK, 24);
		board.setSquare(TypePiece.BLACK, 32);
		board.setSquare(TypePiece.BLACK, 40);
		board.setSquare(TypePiece.BLACK, 48);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 56, CaptureEnum.NORTH));
	}

	@Test
	// Pas de capture s'il existe une case vide entre les pieces
	public void testCaptureNorth_04() {
		assertTrue(CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 52, CaptureEnum.NORTH).isEmpty());
		assertTrue(CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 51, CaptureEnum.NORTH).isEmpty());
	}

	@Test
	// Capture de bas en haut d'une seul piece pour les deux joueurs
	public void testCaptureSouth_01() {
		capturedPieces.add(27);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 19, CaptureEnum.SOUTH));
		capturedPieces.clear();
		capturedPieces.add(28);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 20, CaptureEnum.SOUTH));
	}

	@Test
	// Capture de toute la colonne de bas en haut
	public void testCaptureSouth_02() {
		Integer pieceACap[] = { 8, 16, 24, 32, 40, 48 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.BLACK, 8);
		board.setSquare(TypePiece.BLACK, 16);
		board.setSquare(TypePiece.BLACK, 24);
		board.setSquare(TypePiece.BLACK, 32);
		board.setSquare(TypePiece.BLACK, 40);
		board.setSquare(TypePiece.BLACK, 48);
		board.setSquare(TypePiece.WHITE, 56);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 0, CaptureEnum.SOUTH));
	}

	@Test
	// Pas de capture s'il y a une case vide entre les pieces
	public void testCaptureSouth_03() {
		assertTrue(CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 11, CaptureEnum.SOUTH).isEmpty());
		assertTrue(CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 12, CaptureEnum.SOUTH).isEmpty());
	}

	@Test

	public void testCaptureWest_01() {
		capturedPieces.add(36);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 37, CaptureEnum.WEST));
		capturedPieces.clear();
		capturedPieces.add(28);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 29, CaptureEnum.WEST));
	}

	@Test
	public void testCaptureWest_02() {
		Integer pieceACap[] = { 30, 29, 28, 27, 26, 25 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.WHITE, 24);
		board.setSquare(TypePiece.BLACK, 25);
		board.setSquare(TypePiece.BLACK, 26);
		board.setSquare(TypePiece.BLACK, 27);
		board.setSquare(TypePiece.BLACK, 28);
		board.setSquare(TypePiece.BLACK, 29);
		board.setSquare(TypePiece.BLACK, 30);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 31, CaptureEnum.WEST));
	}

	@Test
	public void testCaptureWest_03() {
		assertTrue(CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 38, CaptureEnum.WEST).isEmpty());
		assertTrue(CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 30, CaptureEnum.WEST).isEmpty());
	}

	@Test
	public void testCaptureEast_02() {
		Integer pieceACap[] = { 25, 26, 27, 28, 29, 30 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.BLACK, 25);
		board.setSquare(TypePiece.BLACK, 26);
		board.setSquare(TypePiece.BLACK, 27);
		board.setSquare(TypePiece.BLACK, 28);
		board.setSquare(TypePiece.BLACK, 29);
		board.setSquare(TypePiece.BLACK, 30);
		board.setSquare(TypePiece.WHITE, 31);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 24, CaptureEnum.EAST));
	}

	@Test
	public void testCaptureNorthWest_01() {
		Integer pieceACap[] = { 54, 45, 36, 27, 18, 9 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.BLACK, 0);
		board.setSquare(TypePiece.WHITE, 9);
		board.setSquare(TypePiece.WHITE, 18);
		board.setSquare(TypePiece.WHITE, 45);
		board.setSquare(TypePiece.WHITE, 54);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 63, CaptureEnum.NORTHWEST));
	}

	@Test
	public void testCaptureNorthEast_01() {
		Integer pieceACap[] = { 49, 42, 35, 28, 21, 14 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.WHITE, 7);
		board.setSquare(TypePiece.BLACK, 14);
		board.setSquare(TypePiece.BLACK, 21);
		board.setSquare(TypePiece.BLACK, 42);
		board.setSquare(TypePiece.BLACK, 49);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 56, CaptureEnum.NORTHEAST));
	}

	@Test
	public void testCaptureSouthEast_01() {
		Integer pieceACap[] = { 9, 18, 27, 36, 45, 54 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.BLACK, 63);
		board.setSquare(TypePiece.WHITE, 9);
		board.setSquare(TypePiece.WHITE, 18);
		board.setSquare(TypePiece.WHITE, 45);
		board.setSquare(TypePiece.WHITE, 54);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.BLACK, 0, CaptureEnum.SOUTHEAST));
	}

	@Test
	public void testCaptureSouthWest_01() {
		Integer pieceACap[] = { 14, 21, 28, 35, 42, 49 };
		capturedPieces.addAll(Arrays.asList(pieceACap));
		board.setSquare(TypePiece.WHITE, 56);
		board.setSquare(TypePiece.BLACK, 14);
		board.setSquare(TypePiece.BLACK, 21);
		board.setSquare(TypePiece.BLACK, 42);
		board.setSquare(TypePiece.BLACK, 49);
		assertEquals(capturedPieces, CaptureEvaluator.captureEvaluator(board, TypePiece.WHITE, 7, CaptureEnum.SOUTHWEST));
	}

}
