package chess.pieces;

import boardGame.Board;
import chess.ChessPiece;
import chess.Color;

public class Torre extends ChessPiece {

	public Torre(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		return mat;
	}

}
