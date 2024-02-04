package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
	
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
			System.out.print((8 - i) + "  ");

			for (int j = 0; j < peças.length; j++) {
				printPiece(peças[i][j]);

			}
			System.out.println();
		}
		System.out.println("   a  b  c  d  e  f  g  h");
	}

	private static void printPiece(ChessPiece peça) {
		if (peça == null) {
			System.out.print("-");

		} else {
			System.out.print(peça);
		}
		System.out.print("  ");
	}
}
