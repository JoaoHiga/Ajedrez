package Otros;

public class Herramientas {
    
    public static boolean[] primeraColumna = iniciarColumna(0);
    public static boolean[] segundaColumna = iniciarColumna(1);
    public static boolean[] séptimaColumna = iniciarColumna(6);
    public static boolean[] octavaColumna = iniciarColumna(7);
    
    public static boolean[] segundaFila = null;
    public static boolean[] séptimaFila = null;
    
    public static final int numeroCasillas = 64;
    public static final int numeroCasillasPorColumna = 8;
    
    private Herramientas(){
        throw new RuntimeException();
    }
    
    private static boolean[] iniciarColumna(int numeroColumna){
        final boolean[] columna = new boolean[64];
        do{
            columna[numeroColumna] = true;
            numeroColumna +=numeroCasillasPorColumna;
        }while(numeroColumna < numeroCasillas);
        return columna;
    }

    public static boolean esCoordenadaVálida(int coordenada) {
        return coordenada >= 0 && coordenada < 64;
    }
    
}
