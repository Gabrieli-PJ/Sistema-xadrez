package boardGame;

public class Board {

	private int linhas;
	private int colunas;
	private Piece[][] peças;
	
	public Board(int linhas, int colunas) {
		if( linhas < 1 || colunas < 1) {
			throw new BoardException("Erro ao criar tabuleiro: é necessário pelo meno uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}	
	
	public Piece peça(int linha, int coluna) {
		if (!positionExists(linha, coluna)) {
			throw new BoardException("Essa posição não existe no tabuleiro");
		}
		return peças[linha][coluna];
	}
	
	public Piece peça(Position posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("Essa posição não existe no tabuleiro");
		}
		return peças[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void placePiece(Piece peça, Position posicao) {
		if (thereIsAPiece(posicao)) {
			throw new BoardException("Já existe uma peça na posição " + posicao );
		}
		peças[posicao.getLinha()][posicao.getColuna()] = peça;
		peça.posicao = posicao;
	}
	
	private boolean positionExists(int linha, int coluna) {
		return linha >=0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
	
	public boolean positionExists(Position posicao) {
		return positionExists(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean thereIsAPiece(Position posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("Essa posição não existe no tabuleiro");
		}
		return peça(posicao) != null;
	}
}
