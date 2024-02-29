package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Peão extends ChessPiece {

	public Peão(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// branco
		if (getCor() == Color.WHITE) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Position p2 = new Position(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2)
					&& getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			p.setValues(posicao.getLinha() - 1, posicao.getColuna() -1);
			if (getTabuleiro().positionExists(p) &&  isThereOpponentPiece(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() +1);
			if (getTabuleiro().positionExists(p) &&  isThereOpponentPiece(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

		}
		// preto
		else {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Position p2 = new Position(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2)
					&& getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			p.setValues(posicao.getLinha() + 1, posicao.getColuna() -1);
			if (getTabuleiro().positionExists(p) &&  isThereOpponentPiece(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() +1);
			if (getTabuleiro().positionExists(p) &&  isThereOpponentPiece(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

		}

		return mat;

	}
	

}
