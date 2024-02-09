package chess.pieces;

import boardGame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece {

	public Rei(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		return mat;
	}

}
