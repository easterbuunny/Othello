package ia;

import java.util.List;

import models.Board;
import models.TypePiece;

public class AIEvaluator {

    /**
     * 
     * @param board Le plateau a evaluer.
     * @param tp Type de piece du joueur appelant la methode.
     * @return La difference de nombre de coup disponible entre le joueur appelant et l'adversaire.
     */
    public static int mobility_evaluator(Board board, TypePiece tp) {
        int mobilityAIPlayer = board.getValidMoves(tp).size();
        int mobilityOppositePlayer = board.getValidMoves(TypePiece.getOpposite(tp)).size();
        return mobilityAIPlayer - mobilityOppositePlayer;
    }
    /**
     * 
     * @param board Le plateau a evaluer.
     * @param tp Type de piece du joueur appelant la methode.
     * @return La difference de piece entre le joueur appelant et l'adversaire dans la position.
     */
    public static int material_evaluator(Board board, TypePiece tp) {
        return board.getNbPiece(tp) - board.getNbPiece(TypePiece.getOpposite(tp));
    }

    public static int position_evaluator(Board board, TypePiece tp) {
        List < Integer > playerPieces = board.getPiecesIndex(tp);
        List < Integer > opponentPieces = board.getPiecesIndex(TypePiece.getOpposite(tp));
        int scorePlayer = 0;
        int scoreOpponentPlayer = 0;
        for (int pieceIndex: playerPieces) {
            scorePlayer += getValueSquare(pieceIndex);
        }
        for (int pieceIndex: opponentPieces) {
            scoreOpponentPlayer += getValueSquare(pieceIndex);
        }
        return scorePlayer - scoreOpponentPlayer;
    }

    public static int getValueSquare(int indexSquare) {
        if (Utils.isCornerSquare(indexSquare))
            return 100;
        if (Utils.isNextToaCornerSquare(indexSquare))
            return -40;
        if (Utils.isBorderSquare(indexSquare))
            return 20;
        if (Utils.isNextToaBorderSquare(indexSquare))
            return -10;
        return 5;

    }



}