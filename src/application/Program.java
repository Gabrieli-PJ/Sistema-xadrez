package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
	
		
		
		Scanner sc = new Scanner(System.in);
		ChessMatch partida = new ChessMatch();
		
		while (true) {
			try {
				final String WHITE_BOLD = "\033[1;37m";
				final String ANSI_RESET = "\u001B[0m";
				UI.clearScreen();
				UI.printBoard(partida.getPeças());
				System.out.println();
				System.out.println( WHITE_BOLD + "Origem: " + ANSI_RESET);
				ChessPosition origem = UI.readChessPosition(sc);
				
				System.out.println();
				System.out.println(WHITE_BOLD + "Destino: " + ANSI_RESET);
				
				ChessPosition destino = UI.readChessPosition(sc);
				
				ChessPiece peçaCapturada = partida.performChessMove(origem, destino);
				
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
		

	}

}
