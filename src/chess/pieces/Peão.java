package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Peão extends ChessPiece {
	
	private ChessMatch partida;

	public Peão(Board tabuleiro, Color cor, ChessMatch partida) {
		super(tabuleiro, cor);
		this.partida = partida;
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
			
			//en passant
			if (posicao.getLinha() == 3) {
				Position esquerda = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().positionExists(esquerda) && isThereOpponentPiece(esquerda) && getTabuleiro().peça(esquerda) == partida.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
			}
			
			if (posicao.getLinha() == 3) {
				Position direita = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().positionExists(direita) && isThereOpponentPiece(direita) && getTabuleiro().peça(direita) == partida.getEnPassantVulneravel()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
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
			
			//en passant
			if (posicao.getLinha() == 4) {
				Position esquerda = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().positionExists(esquerda) && isThereOpponentPiece(esquerda) && getTabuleiro().peça(esquerda) == partida.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
			}
			
			if (posicao.getLinha() == 4) {
				Position direita = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().positionExists(direita) && isThereOpponentPiece(direita) && getTabuleiro().peça(direita) == partida.getEnPassantVulneravel()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}

		}

		return mat;

	}
	

}
