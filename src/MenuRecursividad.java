import java.util.Scanner;

/**
 * Menu interactivo con cinco ejemplos de recursividad.
 */
public class MenuRecursividad {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final int LIMITE_RECURSION = 10_000;

    public static void main(String[] args) {
        boolean continuar = true;

        System.out.println("=== EJERCICIOS DE RECURSIVIDAD ===");
        System.out.println("Elija una opcion. Escriba 0 para terminar.");

        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Opcion: ");

            try {
                switch (opcion) {
                    case 1 -> ejecutarFactorial();
                    case 2 -> ejecutarSuma();
                    case 3 -> ejecutarPotencia();
                    case 4 -> ejecutarContarDigitos();
                    case 5 -> ejecutarInvertirCadena();
                    case 0 -> {
                        continuar = false;
                        System.out.println("Programa finalizado. Hasta luego.");
                    }
                    default -> System.out.println("Opcion no valida. Elija un numero entre 0 y 5.");
                }
            } catch (ArithmeticException e) {
                System.out.println("El resultado no cabe en el tipo long. Intente con valores menores.");
            }

            System.out.println();
        }
        ENTRADA.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Factorial de N");
        System.out.println("2. Sumar desde 1 hasta N");
        System.out.println("3. Potencia: base elevado a exponente");
        System.out.println("4. Contar digitos de un numero");
        System.out.println("5. Invertir una cadena");
        System.out.println("0. Salir");
    }

    private static void ejecutarFactorial() {
        int n = leerEnteroEnRango("Ingrese N (0 a 20): ", 0, 20);
        System.out.println(n + "! = " + factorial(n));
    }

    private static void ejecutarSuma() {
        int n = leerEnteroEnRango(
                "Ingrese N (0 a " + LIMITE_RECURSION + "): ", 0, LIMITE_RECURSION);
        System.out.println("La suma de 1 hasta " + n + " es " + sumaHasta(n));
    }

    private static void ejecutarPotencia() {
        long base = leerLong("Ingrese la base (entero): ");
        int exponente = leerEnteroEnRango(
                "Ingrese el exponente (0 a " + LIMITE_RECURSION + "): ", 0, LIMITE_RECURSION);
        System.out.println(base + "^" + exponente + " = " + potencia(base, exponente));
    }

    private static void ejecutarContarDigitos() {
        long numero = leerLong("Ingrese un numero entero: ");
        System.out.println("El numero tiene " + contarDigitos(numero) + " digito(s).");
    }

    private static void ejecutarInvertirCadena() {
        System.out.print("Ingrese el texto que desea invertir: ");
        String texto = ENTRADA.nextLine();
        if (texto.isEmpty()) {
            System.out.println("No se puede invertir una cadena vacia.");
            return;
        }

        int[] puntosCodigo = texto.codePoints().toArray();
        if (puntosCodigo.length > LIMITE_RECURSION) {
            System.out.println("El texto es demasiado largo. Maximo: " + LIMITE_RECURSION + " caracteres.");
            return;
        }
        System.out.println("Texto invertido: " + invertir(puntosCodigo, puntosCodigo.length - 1));
    }

    // Caso base: 0! es 1. Caso recursivo: n! = n * (n - 1)!.
    private static long factorial(int n) {
        return n <= 1 ? 1 : Math.multiplyExact(n, factorial(n - 1));
    }

    // Caso base: la suma hasta 0 es 0. Caso recursivo: n + sumaHasta(n - 1).
    private static long sumaHasta(int n) {
        return n == 0 ? 0 : n + sumaHasta(n - 1);
    }

    // Caso base: cualquier numero elevado a 0 es 1.
    private static long potencia(long base, int exponente) {
        return exponente == 0 ? 1 : Math.multiplyExact(base, potencia(base, exponente - 1));
    }

    // Dividir entre 10 elimina un digito, incluso para numeros negativos.
    private static int contarDigitos(long numero) {
        return numero > -10 && numero < 10 ? 1 : 1 + contarDigitos(numero / 10);
    }

    // Se usan puntos de codigo para conservar correctamente emojis y caracteres Unicode.
    private static String invertir(int[] puntosCodigo, int indice) {
        return indice < 0
                ? ""
                : new String(Character.toChars(puntosCodigo[indice])) + invertir(puntosCodigo, indice - 1);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = ENTRADA.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Escriba un numero entero.");
            }
        }
    }

    private static long leerLong(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = ENTRADA.nextLine().trim();
            try {
                return Long.parseLong(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Escriba un numero entero valido.");
            }
        }
    }

    private static int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.println("El valor debe estar entre " + minimo + " y " + maximo + ".");
        }
    }
}
