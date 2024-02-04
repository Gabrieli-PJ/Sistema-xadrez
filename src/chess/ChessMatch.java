package chess;

import boardGame.Board;
import boardGame.Position;
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
	
	private void initialSetup() {
		tabuleiro.placePiece(new Torre(tabuleiro, Color.WHITE), new Position(0, 0));
		tabuleiro.placePiece(new Torre(tabuleiro, Color.WHITE), new Position(0, 7));
		tabuleiro.placePiece(new Rei(tabuleiro, Color.WHITE), new Position(0, 4));
	}
}
