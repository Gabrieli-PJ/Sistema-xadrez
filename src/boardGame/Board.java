package boardGame;

public class Board {

	private int linhas;
	private int colunas;
	private Piece[][] peças;
	
	public Board(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
}
