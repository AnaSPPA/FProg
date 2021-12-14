//ALUMNA ANA SIESTO PÉREZ

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		/*
		 * Calcular los divisores esenciales de los números pertenecientes a un
		 * intervalo cerrado y detectar el menor junto con su número del intervalo
		 * 
		 * Entrada: dos enteros positivos
		 * 
		 * Salida: el menor divisor esencial y su correspondiente número en el intervalo
		 * 
		 * Observaciones: 
		 * 			-se valida que la entrada sea positiva volviendo a pedir el
		 * 			dato si es necesario 
		 * 			-si el intervalo de números se da al revés (primero el
		 *	 		número mayor y después el menor) se le dará la vuelta 
		 *			-se ha añadido un caso especial si el primer número introducido es 1
		 *			ya que su único divisor es sí mismo
		 */

		Scanner in = new Scanner(System.in);
		boolean positivo = false;
		int n1 = 0, n2 = 0, tmp = 0;

		while (!positivo) {
			System.out.print("Introduzca dos números enteros positivos: ");
			String respuesta = in.nextLine();

			String[] resp = respuesta.split(" ");

			n1 = Integer.parseInt(resp[0]); // Primer número
			n2 = Integer.parseInt(resp[1]); // Segundo número

			positivo = true;

			if (n1 <= 0 || n2 <= 0) {
				System.out.println("¡Error! Ambos números deben ser estricatemente positivos (>0)\n");
				positivo = false;
			}
		}

		if (n1 > n2) { // Primer número introducido mayor que el segundo (intervalo inverso)
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		}

		in.close();

		int[][] matrizResultado = calculaDivisores(n1, n2);

		System.out.println("El número con menor divisor esencial (y el divisor esencial) es: "
				+ menorEsencialyDivisor(matrizResultado)); // Imprimimos el resultado
	}

	/**
	 * Calcula todos los divisores primos de el intervalo cerrado entre los números
	 * enteros introducidos como parámetros y los guada en una matriz
	 * 
	 * @param num1 número entero (int) que indicará el principio del intervalo
	 *             (incluido)
	 * @param num2 número entero (int) que indicará el final del intervalo
	 *             (incluido)
	 * @return array multidimensional de enteros (int[][]) que reserva la primera
	 *         columna para los divisores esenciales que calculará otra función, en
	 *         la segunda columna aparecerán todos los números del intervalo
	 *         cerrado, y las siguientes serán los divisores primos del número del
	 *         intervalo de esa fila
	 */
	public static int[][] calculaDivisores(int num1, int num2) {

		int indice1 = 0, indice2 = 2, // Empezamos a partir de la tercera columna (incluida)
				filas = (num2 - num1) + 1, tmp = num1; // Necesitamos un temporal para los cálculos
		int[][] divisores = new int[filas][15];
		int d = 2; // Primer número primo

		for (int i = 0; i <= (num2 - num1); i++) {
			divisores[i][1] = tmp; // Segunda columna con los números del intervalo cerrado
			tmp++;
			if (num1 == 1) {
				divisores[0][2] = num1;
			}
		}

		tmp = num1; // Reset

		while ((num1 - 1) != num2) {
			while (tmp != 1) {
				while (tmp % d == 0) {

					if (esPrimo(d) == true) {
						divisores[indice1][indice2] = d; // Guardamos el divisor
						indice2++;
						tmp /= d;
					}
				}
				d++;
			}

			indice1++;
			num1++; // Siguiente número en el intervalo
			indice2 = 2; // Reset
			d = 2; // Reset
			tmp = num1; // Reset con el número actual
		}

		return divisores;
	}

	/**
	 * Calcula los divisores esenciales de los números de la segunda columna de
	 * enteros de la matriz introducida como parámetro (gracias a los divisores
	 * guardados en las columnas posteriores a estos), y guarda los divisores
	 * esenciales calculados en la primera columna. Una vez hecho esto, detecta el
	 * menor de los divisores esenciales y el número de la segunda columna al que
	 * pertenece
	 * 
	 * @param m array multidimensional de enteros (int[][])
	 * @return String que contiene el menor divisor esencial y el número de la
	 *         segunda columna al que pertenece
	 */
	public static String menorEsencialyDivisor(int[][] m) {

		int divisorEsencial = 1, // Inicializamos al 1 ya que siempre será divisor y no nos dará problemas al
									// multiplicar
				menorEsencial = m[0][1]; // Inicializamos al primer número del intervalo ya que su divisor esencial
											// siempre será menor que él
		String menorYdivisor = "";

		for (int i = 0; i <= m.length - 1; i++) {
			for (int j = 2; j <= m[0].length - 1; j++) { // Recorremos la matriz a partir de la segunda columna (donde
															// se encuentran los divisores)
				if (m[i][j] != 0 && m[i][j] != m[i][j + 1]) { // Distinto de 0 y del divisor siguiente
					divisorEsencial *= m[i][j];
					m[i][0] = divisorEsencial;
				}
			}
			if (menorEsencial >= m[i][0]) {
				menorEsencial = m[i][0];
				menorYdivisor = m[i][1] + " " + menorEsencial; // Actualizamos el resultado final
			}
			divisorEsencial = 1; // Reset para la siguiente fila
		}

		return menorYdivisor;
	}

	/**
	 * Comprueba si el número introducido como parámetro es primo
	 * 
	 * @param numero entero positivo
	 * @return "true" si el número introducido es primo, "false" si no lo es
	 */
	public static boolean esPrimo(int numero) {
		int contador = 2; // Primer número primo
		boolean primo = true;

		while ((primo) && (contador != numero)) {
			if (numero % contador == 0)
				primo = false;
			contador++;
		}
		return primo;
	}
}
