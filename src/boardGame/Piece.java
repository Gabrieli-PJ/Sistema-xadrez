package boardGame;

public abstract class Piece {
	protected Position posicao;
	private Board tabuleiro;
	
	
	public Piece(Board tabuleiro) {
			this.tabuleiro = tabuleiro;
			posicao = null;
	}


	protected Board getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position posicao) {
		return possibleMoves()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean isTheAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
			}
			}
			
		}
		return false;
	}
	
}
