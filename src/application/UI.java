package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";

	public static final String GREEN_BRIGHT = "\033[0;92m";
	public static final String WHITE_BOLD = "\033[1;37m";
	 public static final String BLACK_BOLD = "\033[1;30m";
	public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
	public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";
	public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";
	public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";

	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new ChessPosition(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posição. Valores validos são de A1 à H8");
		}

	}

	public static void printBoard(ChessPiece[][] peças) {
		for (int i = 0; i < peças.length; i++) {
			System.out.print(GREEN_BRIGHT + (8 - i) + "  " + ANSI_RESET);

			for (int j = 0; j < peças.length; j++) {
				printPiece(peças[i][j], false);

			}
			System.out.println();
		}
		System.out.println(GREEN_BRIGHT + "   a  b  c  d  e  f  g  h" + ANSI_RESET);
	}

	public static void printBoard(ChessPiece[][] peças, boolean[][] possibleMoves) {
		for (int i = 0; i < peças.length; i++) {
			System.out.print(GREEN_BRIGHT + (8 - i) + "  " + ANSI_RESET);

			for (int j = 0; j < peças.length; j++) {
				printPiece(peças[i][j], possibleMoves[i][j]);

			}
			System.out.println();
		}
		System.out.println(GREEN_BRIGHT + "   a  b  c  d  e  f  g  h" + ANSI_RESET);
	}
	
	private static void printPiece(ChessPiece peça, boolean background) {
		if (background) {
			System.out.print(BLACK_BACKGROUND_BRIGHT);
		}
		
		if (peça == null) {
			if (background == true) {
			System.out.print(BLACK_BOLD + "-" + ANSI_RESET); 
			}
			else {
				System.out.print(BLACK_BOLD_BRIGHT + "-" + ANSI_RESET); 
			}

		} else {
			if (peça.getCor() == Color.WHITE) {
				System.out.print(WHITE_BOLD + peça + ANSI_RESET);
			} else {
				System.out.print(BLUE_BOLD_BRIGHT + peça + ANSI_RESET);
			}
		}
		System.out.print("  ");
	}
}
