package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece {
	private Color cor;

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(posicao);
	}
	
	protected boolean isThereOpponentPiece(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peça(posicao);
		return p != null && p.getCor() != cor;
		
	}
	
	
}
