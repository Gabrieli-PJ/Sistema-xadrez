package chess;

import boardGame.Board;

public class ChessMatch {
	private Board tabuleiro;
	
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
	}
	
	public ChessPiece[][] getPeças() {
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i<tabuleiro.getLinhas(); i++) {
			for (int j = 0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPiece) tabuleiro.peça(i, j);
			}
		}
		
		return mat;
	}
}
