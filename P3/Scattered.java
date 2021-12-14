//ALUMNA ANA SIESTO PÉREZ

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		boolean entradaValida = false;

		Scanner in = new Scanner(System.in);

		System.out.println("Bienvenido a 'Dispersas'.\n");

		while (!entradaValida) {

			System.out.println(
					"A continuación deberá elegir el tipo de entrada que desea para el funcionamiento del programa.");
			System.out.println("(Si introduce 1, deberá introducir una matriz)");
			System.out.println("(Si introduce 2, deberá introducir el formato-C de una matriz)\n");
			System.out.print("Elija el tipo de entrada (1 o 2): ");

			String r = in.nextLine();
			int respuesta = Integer.parseInt(r);

			if (respuesta == 1) { // Modo 1
				System.out.println("\nAhora introduzca los siguientes datos.");

				System.out.print("Dimensiones de la matriz (número de filas, 'espacio' y el número de columnas): ");
				r = in.nextLine();
				String[] resp = r.split(" ");
				int filas = Integer.parseInt(resp[0]);
				int columnas = Integer.parseInt(resp[1]);

				System.out
						.println("Y ahora el contenido de la matriz (por filas, y cada dato separado por un espacio).");
				int[][] matriz = new int[filas][columnas]; // Inicializamos

				// Pedimos fila a fila
				for (int i = 0; i <= filas - 1; i++) {
					System.out.print("Fila " + (i + 1) + ": ");
					r = in.nextLine();
					resp = r.split(" ");

					for (int j = 0; j <= columnas - 1; j++) {
						matriz[i][j] = Integer.parseInt(resp[j]);
					}

				}

				int[][] matrizC = formatoC(matriz); // Convertimos la matriz introducida a formato-C

				// Inicializamos los vectores
				int[] v = new int[matrizC[3][0]];
				int[] iFilas = new int[matrizC[3][0]];
				int[] iColum = new int[matrizC[3][0]];

				// Guardamos los vectores a partir de la matriz de formato-C
				for (int j = 0; j <= matrizC[3][0] - 1; j++) {
					v[j] = matrizC[0][j];
					iFilas[j] = matrizC[1][j];
					iColum[j] = matrizC[2][j];
				}

				// Imprimimos resultados
				System.out.println("\n********** RESULTADOS **********");
				System.out.println("El formato-C de la matriz introducida es: ");

				System.out.print("v");
				imprimeFormatoC(v);
				System.out.print("\ni");
				imprimeFormatoC(iFilas);
				System.out.print("\nj");
				imprimeFormatoC(iColum);

				entradaValida = true; // Salimos del bucle

			} else if (respuesta == 2) { // Modo 2
				System.out.println("\nAhora introduzca los siguientes datos.");

				// Pedimos datos
				System.out.print("Longitud de las siguientes entradas: ");
				r = in.nextLine();
				@SuppressWarnings("unused")
				int tamaño = Integer.parseInt(r); // No necesitamos el tamaño pero es un requisito

				System.out.print("Valores no nulos: ");
				r = in.nextLine();
				String[] v = r.split(" ");

				System.out.print("Indices de las filas (>0): ");
				r = in.nextLine();
				String[] iFilas = r.split(" ");

				System.out.print("Indices de las columnas (>0): ");
				r = in.nextLine();
				String[] iColum = r.split(" ");

				// Pasamos los vectores de tipo String a tipo int
				int[] vF = stringToInt(v);
				int[] iFilasF = stringToInt(iFilas);
				int[] iColumF = stringToInt(iColum);

				int[][] matriz = creaMatriz(vF, iFilasF, iColumF); // Creamos la matriz

				System.out.print("Y por último, la posición del valor que quiere obtener: ");
				r = in.nextLine();
				String[] pos = r.split(" ");
				int filaValor = Integer.parseInt(pos[0]); // Indice de la fila del valor a encontrar
				int columValor = Integer.parseInt(pos[1]); // Indice de la columna del valor a encontrar

				System.out.println("\n********** RESULTADOS **********");
				System.out.println("Número de filas de la matriz: " + numMax(iFilasF));
				System.out.println("Número de columnas de la matriz: " + numMax(iColumF));
				System.out.println("La matriz resultante es: ");
				imprimeMatriz(matriz);
				System.out.println("Valor en la posicion dada: " + encuentraValor(matriz, filaValor, columValor));

				entradaValida = true; // Salimos del bucle

			} else {
				System.out.println(
						"\n*************************************************\n¡Entrada no válida! La respuesta debe ser 1 o 2.\n*************************************************\n");
			}

		}
		in.close();

	}

	public static int[][] formatoC(int[][] m) {

		int[][] mC = new int[100][100]; // Inicializamos
		int tamaño = 0;

		for (int i = 0; i <= m.length - 1; i++) {
			for (int j = 0; j <= m[0].length - 1; j++) {
				if (m[i][j] != 0) { // Valor de la matriz distinto a 0, lo guardamos en el formato-C
					mC[0][tamaño] = m[i][j];
					mC[1][tamaño] = i + 1;
					mC[2][tamaño] = j + 1;
					tamaño++;
				}
			}
		}

		mC[3][0] = tamaño;

		return mC;
	}

	public static void imprimeMatriz(int[][] m) {

		for (int i = 0; i <= m.length - 1; i++) {
			for (int j = 0; j <= m[0].length - 1; j++) {
				System.out.print(m[i][j] + " "); // Imprime valor a valor

			}
			System.out.print("\n");
		}
	}

	public static void imprimeFormatoC(int[] m) {

		// Imprime con el formato requerido del tipo "x = [_,_,_,_,...]
		System.out.print(" = [");

		for (int i = 0; i <= m.length - 1; i++) {
			if (i != m.length - 1) {
				System.out.print(m[i] + ",");
			} else {
				System.out.print(m[i] + "]");
			}
		}
	}

	public static int numMax(int[] f) {
		int mayor = 0; // El mayor inicial siempre será 0 al principio

		for (int i = 0; i <= f.length - 1; i++) {
			if (mayor < f[i]) { // Si encuentra un numero mayor
				mayor = f[i]; // Lo guarda
			}
		}

		return mayor;
	}

	public static int[][] creaMatriz(int[] v, int[] f, int[] c) {

		int filas = numMax(f), colum = numMax(c), contador = 0;
		int[][] matriz = new int[filas][colum];

		while (contador != v.length) { // Cuando haya introducido en la matriz todos los valores del vector v saldrá
										// del bucle
			for (int i = 0; i <= filas; i++) {
				if (contador != v.length) {
					if (Arrays.asList(f[contador]).contains(i) == true) { // Si i coincidice con algun valor de f
						matriz[f[contador] - 1][c[contador] - 1] = v[contador]; // Guarda el valor en la matriz en la
																				// fila y columna que corresponde
						contador++;
					}
				}
			}
		}

		return matriz;
	}

	public static int encuentraValor(int[][] m, int iFila, int iColum) {
		int num = 0; // Inicializamos

		for (int i = 0; i <= m.length - 1; i++) {
			for (int j = 0; j <= m[0].length - 1; j++) {
				if (i == (iFila - 1) && j == (iColum - 1)) { // Cuando i y j coinciden con iFila e iColum
					num = m[i][j]; // Guardamos el número
				}
			}
		}

		return num;
	}

	public static int[] stringToInt(String[] x) {
		int[] y = new int[x.length]; // Inicializamos con el mismo tamaño

		for (int i = 0; i <= x.length - 1; i++) {
			y[i] = Integer.parseInt(x[i]); // Guardamos el valor en tipo entero
		}

		return y;
	}
}
