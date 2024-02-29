package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece {
	
	private ChessMatch partida;

	public Rei(Board tabuleiro, Color cor, ChessMatch partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peça(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testTorreRoque(Position posição) {
		ChessPiece p = (ChessPiece)getTabuleiro().peça(posição);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getMovimentos() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// Acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Noroeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Sudoeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Movimento especial Roque
		if (getMovimentos() == 0 && !partida.getXeque()) {
			//roque pequeno (lado do rei)
			Position posiçãoT1 = new Position(posicao.getLinha(), posicao.getColuna() + 3);
			if (testTorreRoque(posiçãoT1)) {
				Position p1 = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				Position p2 = new Position(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null ) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				} 
			}
			// roque grande (lado da rainha)
			Position posiçãoT2 = new Position(posicao.getLinha(), posicao.getColuna() - 4);
			if (testTorreRoque(posiçãoT2)) {
				Position p1 = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				Position p2 = new Position(posicao.getLinha(), posicao.getColuna() - 2);
				Position p3 = new Position(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null && getTabuleiro().peça(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				} 
			}
		}

		return mat;
	}

}
