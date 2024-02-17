package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
	
		
		
		Scanner sc = new Scanner(System.in);
		ChessMatch partida = new ChessMatch();
		List<ChessPiece> capturadas = new ArrayList<>();
		
		while (!partida.getXequeMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(partida, capturadas);
				System.out.println();
				System.out.println( UI.WHITE_BOLD + "Origem: " + UI.ANSI_RESET);
				ChessPosition origem = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = partida.possibleMoves(origem);
				UI.clearScreen();
				UI.printBoard(partida.getPeças(), possibleMoves);
				
				
				System.out.println();
				System.out.println(UI.WHITE_BOLD + "Destino: " + UI.ANSI_RESET);
				
				ChessPosition destino = UI.readChessPosition(sc);
				
				ChessPiece peçaCapturada = partida.performChessMove(origem, destino);
				
				if (peçaCapturada != null) {
					capturadas.add(peçaCapturada);
				}
				
			}
			catch (ChessException e) {
				System.out.println();
				System.out.println(e.getMessage());
				sc.nextLine();	
			}
			catch (InputMismatchException e) {
				System.out.println();
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(partida, capturadas);
		

	}

}
