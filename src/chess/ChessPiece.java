package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece {
	private Color cor;
	private int movimentos;

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}
	
	public int getMovimentos() {
		return movimentos;
	}
	
	public void aumentarMovimentos() {
		movimentos++;
	}
	
	public void diminuirMovimentos() {
		movimentos--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(posicao);
	}
	
	protected boolean isThereOpponentPiece(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peça(posicao);
		return p != null && p.getCor() != cor;
		
	}
	
	
}
