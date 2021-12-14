/* Calcula el tiempo transcurrido entre dos fechas dadas */

import java.util.Scanner;

//ALUMNA ANA SIESTO PÉREZ 

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Introduzca la primera fecha: "); // pedimos la primera fecha
		String fecha1 = in.nextLine();

		System.out.println("Ahora la segunda fecha: "); // pedimos la segunda
		String fecha2 = in.nextLine();

		in.close();

		System.out.println(calcularAños(fecha1, fecha2)); // llamamos a la funcion e imprimos el resultado una vez haya
															// terminado
	}

	public static String calcularAños(String f1, String f2) {
		String resultado = " "; // inicializamos la variable

		// dividimos las fechas en dia, mes y año
		String[] fech1 = f1.split(" ");
		String[] fech2 = f2.split(" ");

		int dia1 = Integer.parseInt(fech1[0]);
		int mes1 = Integer.parseInt(fech1[1]);
		int año1 = Integer.parseInt(fech1[2]);

		int dia2 = Integer.parseInt(fech2[0]);
		int mes2 = Integer.parseInt(fech2[1]);
		int año2 = Integer.parseInt(fech2[2]);

		if (año1 == año2) { // si son del mismo año
			resultado = "0";
			if (mes1 == mes2) { // mismo mes
				if (dia1 == dia2) { // y mismo dia
					resultado = resultado + " =";
				} else {
					if (dia1 < dia2) { // mismo mes pero el dia de la primera fecha es menor
						resultado = resultado + " A";
					} else {
						resultado = resultado + " B"; // y viceversa
					}
				}
			} else { // distinto mes pero mismo año
				if (mes1 < mes2) {
					resultado = resultado + " A";
				} else {
					resultado = resultado + " B";
				}
			}
		} else {
			// averiguamos qué fecha es la menor(más edad) y cual es la mayor(menos edad)
			int mayor;
			int menor;
			int contador = 0; // para contar los años que las separan

			if (año1 > año2) {
				mayor = año1;
				menor = año2;
				resultado = " B"; // primera fecha mayor (la segunda persona es mayor)
				if (mes1 < mes2) {
					contador--; // si el mes es menor, aunque el año sea mayor, quitamos 1 año (mínimo) al
								// contador
								// ya que si tenemos 1/1/1980 y 1/2/1979, no les separa un año sino 11 meses
				}
			} else {
				mayor = año2;
				menor = año1;
				resultado = " A"; // segunda fecha mayor (la primera persona es mayor)
				if (mes2 < mes1) {
					contador--; // repetimos el método al revés
				}
			}
			do { // bucle para saber cuantos años les separan
				mayor--;
				contador++;
			} while (mayor > menor);
			resultado = String.valueOf(contador) + resultado;
		}

		return resultado;
	}
}
