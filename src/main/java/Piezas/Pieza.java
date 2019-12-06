package Piezas;
import Otros.Color;
import Tablero.Movimiento;
import Tablero.Tablero;
import java.util.Collection;

public abstract class Pieza {

    protected final int posiciónPieza;
    protected final Color colorPieza;
    protected final boolean esPrimerMovimiento = false;
     
    Pieza(final int posiciónPieza, final Color colorPieza){
        this.posiciónPieza = posiciónPieza;
        this.colorPieza = colorPieza;
    }
    
    public Color getColorPieza(){
        return this.colorPieza;
    }
    
    public boolean esPrimerMovimiento(){
        return this.esPrimerMovimiento;
    }
    
    public abstract Collection<Movimiento> calcularMovimientosLegales(final Tablero tablero);
    
}
