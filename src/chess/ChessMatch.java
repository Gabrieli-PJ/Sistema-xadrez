package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {

	private int rodada;
	private Color jogadorAtual;
	private Board tabuleiro;

	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		rodada = 1;
		jogadorAtual = Color.WHITE;
		initialSetup();
	}
	
	public int getRodada() {
		return rodada;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
	}

	public ChessPiece[][] getPeças() {
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPiece) tabuleiro.peça(i, j);
			}
		}

		return mat;
	}

	public boolean[][] possibleMoves(ChessPosition posicaoOrigem) {
		Position posicao = posicaoOrigem.toPosition();
		validateSourcePosition(posicao);
		return tabuleiro.peça(posicao).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition posicaoOrigem, ChessPosition posicaoDestino) {
		Position origem = posicaoOrigem.toPosition();
		Position destino = posicaoDestino.toPosition();
		validateSourcePosition(origem);
		validateTargetPosition(origem, destino);
		Piece peçaCapturada = makeMove(origem, destino);
		nextTurn();
		return (ChessPiece) peçaCapturada;
	}

	private Piece makeMove(Position origem, Position destino) {
		Piece p = tabuleiro.removePiece(origem);
		Piece peçaCapturada = tabuleiro.removePiece(destino);
		tabuleiro.placePiece(p, destino);
		return peçaCapturada;
	}

	private void validateSourcePosition(Position posicao) {
		if (!tabuleiro.thereIsAPiece(posicao)) {
			throw new ChessException("Não existe peças na posição de origem");
		}
		if (jogadorAtual != ((ChessPiece) tabuleiro.peça(posicao)).getCor()) {
			throw new ChessException("A peça escolhida não pertence a você");
		}
		if (!tabuleiro.peça(posicao).isTheAnyPossibleMove()) {
			throw new ChessException("Não existe movimentos possiveis para essa peça");
		}
	}

	private void validateTargetPosition(Position origem, Position destino) {
		if (!tabuleiro.peça(origem).possibleMove(destino)) {
			throw new ChessException("A peça não pode se mover para a posição de destino");
		}
	}
	
	private void nextTurn() {
		rodada++;
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private void placeNewPiece(char coluna, int linha, ChessPiece peça) {
		tabuleiro.placePiece(peça, new ChessPosition(coluna, linha).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('a', 8, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('b', 8, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('c', 8, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('d', 8, new Rei(tabuleiro, Color.WHITE));
		placeNewPiece('e', 8, new Rei(tabuleiro, Color.WHITE));
		placeNewPiece('f', 8, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('g', 8, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('h', 8, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('a', 7, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('b', 7, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('c', 7, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('d', 7, new Rei(tabuleiro, Color.WHITE));
		placeNewPiece('e', 7, new Rei(tabuleiro, Color.WHITE));
		placeNewPiece('f', 7, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('g', 7, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('h', 7, new Torre(tabuleiro, Color.WHITE));

		placeNewPiece('a', 1, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('b', 1, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('c', 1, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('d', 1, new Rei(tabuleiro, Color.BLACK));
		placeNewPiece('e', 1, new Rei(tabuleiro, Color.BLACK));
		placeNewPiece('f', 1, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('g', 1, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('h', 1, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('a', 2, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('b', 2, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('c', 2, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('d', 2, new Rei(tabuleiro, Color.BLACK));
		placeNewPiece('e', 2, new Rei(tabuleiro, Color.BLACK));
		placeNewPiece('f', 2, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('g', 2, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('h', 2, new Torre(tabuleiro, Color.BLACK));

	}
}
