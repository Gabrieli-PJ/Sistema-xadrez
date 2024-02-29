package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.Bispo;
import chess.pieces.Cavalo;
import chess.pieces.Peão;
import chess.pieces.Rainha;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {

	private int rodada;
	private Color jogadorAtual;
	private Board tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	
	private List<Piece> peçasNoTabuleiro = new ArrayList<>();
	private List<Piece> peçasCapturadas = new ArrayList<>();

	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		rodada = 1;
		jogadorAtual = Color.WHITE;
		initialSetup();
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
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
		
		if (testCheck(jogadorAtual)) {
			undoMove(origem, destino, peçaCapturada);
			throw new ChessException("Você não pode se colocar em xeque");
		}
		
		xeque = (testCheck(oponente(jogadorAtual))) ? true : false;
		
		if (testCheckMate(oponente(jogadorAtual))) {
			xequeMate = true;
		}
		nextTurn();
		return (ChessPiece) peçaCapturada;
	}

	private Piece makeMove(Position origem, Position destino) {
		ChessPiece p = (ChessPiece)tabuleiro.removePiece(origem);
		p.aumentarMovimentos();
		Piece peçaCapturada = tabuleiro.removePiece(destino);
		tabuleiro.placePiece(p, destino);
		
		if (peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		
		return peçaCapturada;
	}
	
	private void undoMove(Position origem, Position destino, Piece peçaCapturada) {
		ChessPiece p = (ChessPiece)tabuleiro.removePiece(destino);
		p.diminuirMovimentos();
		tabuleiro.placePiece(p, origem);
		
		if (peçaCapturada != null) {
			tabuleiro.placePiece(peçaCapturada, destino);
			peçasCapturadas.remove(peçaCapturada);
			peçasNoTabuleiro.add(peçaCapturada);
		}
		
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
	
	private Color oponente(Color cor) {
		return (cor == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece Rei(Color cor) {
		List<Piece> lista = peçasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getCor() == cor).collect(Collectors.toList());
		for (Piece p : lista) {
			if (p instanceof Rei) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("Não existe nenhum rei da cor " + cor + "no tabuleiro");
	}
	
	private boolean testCheck(Color cor) {
		Position posiçaoRei = Rei(cor).getChessPosition().toPosition();
		List<Piece> peçasOponente = peçasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Piece p : peçasOponente) {
			boolean[][] mat = p.possibleMoves();
			if (mat[posiçaoRei.getLinha()][posiçaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color cor) {
		if (!testCheck(cor)) {
			return false;
		}
		
		List<Piece> lista = peçasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getCor() == cor).collect(Collectors.toList());
		for (Piece p : lista) {
			boolean [][] mat = p.possibleMoves();
			for(int i = 0; i<tabuleiro.getLinhas(); i++) {
				for (int j = 0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Position origem = ((ChessPiece)p).getChessPosition().toPosition();
						Position destino = new Position(i, j);
						
						Piece peçaCapturada = makeMove(origem, destino);
						boolean testarXeque = testCheck(cor);
						undoMove(origem, destino, peçaCapturada);
						
						if (!testarXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	

	private void placeNewPiece(char coluna, int linha, ChessPiece peça) {
		tabuleiro.placePiece(peça, new ChessPosition(coluna, linha).toPosition());
		peçasNoTabuleiro.add(peça);
	}

	private void initialSetup() {
		placeNewPiece('a', 1, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('b', 1, new Cavalo(tabuleiro, Color.WHITE));
		placeNewPiece('c', 1, new Bispo(tabuleiro, Color.WHITE));
		placeNewPiece('d', 1, new Rainha(tabuleiro, Color.WHITE));
		placeNewPiece('e', 1, new Rei(tabuleiro, Color.WHITE));
		placeNewPiece('f', 1, new Bispo(tabuleiro, Color.WHITE));
		placeNewPiece('g', 1, new Cavalo(tabuleiro, Color.WHITE));
		placeNewPiece('h', 1, new Torre(tabuleiro, Color.WHITE));
		placeNewPiece('a', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('b', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('c', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('d', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('e', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('f', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('g', 2, new Peão(tabuleiro, Color.WHITE));
		placeNewPiece('h', 2, new Peão(tabuleiro, Color.WHITE));

		placeNewPiece('a', 8, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('b', 8, new Cavalo(tabuleiro, Color.BLACK));
		placeNewPiece('c', 8, new Bispo(tabuleiro, Color.BLACK));
		placeNewPiece('d', 8, new Rainha(tabuleiro, Color.BLACK));
		placeNewPiece('e', 8, new Rei(tabuleiro, Color.BLACK));
		placeNewPiece('f', 8, new Bispo(tabuleiro, Color.BLACK));
		placeNewPiece('g', 8, new Cavalo(tabuleiro, Color.BLACK));
		placeNewPiece('h', 8, new Torre(tabuleiro, Color.BLACK));
		placeNewPiece('a', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('b', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('c', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('d', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('e', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('f', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('g', 7, new Peão(tabuleiro, Color.BLACK));
		placeNewPiece('h', 7, new Peão(tabuleiro, Color.BLACK));
	}
}
