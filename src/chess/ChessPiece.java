package chess;

import boardGame.Board;
import boardGame.Piece;

public abstract class ChessPiece extends Piece {
	private Color cor;

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}
	
	
}
