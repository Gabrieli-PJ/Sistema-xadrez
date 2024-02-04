package chess;

import boardGame.Board;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	private Board tabuleiro;
	
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		initialSetup();
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
	
	private void placeNewPiece(char coluna, int linha, ChessPiece peça) {
		tabuleiro.placePiece(peça, new ChessPosition(coluna, linha).toPosition());
	}
	
	private void initialSetup() {
	placeNewPiece('a', 8, new Torre(tabuleiro, Color.WHITE));
	placeNewPiece('h', 8, new Torre(tabuleiro, Color.WHITE));
	placeNewPiece('e', 8, new Rei(tabuleiro, Color.WHITE));
	}
}
