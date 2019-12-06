package Tablero;

import Piezas.Pieza;

public abstract class Movimiento {

    final Tablero tablero;
    final Pieza piezaEnMovimiento;
    final int coordenadaDeDestino;
    
    Movimiento(final Tablero tablero,final Pieza piezaEnMovimiento,final int coordenadaDeDestino){
        this.tablero = tablero;
        this.piezaEnMovimiento = piezaEnMovimiento;
        this.coordenadaDeDestino = coordenadaDeDestino;
    }
    
    public static final class MovimientoPacífico extends Movimiento{
        public MovimientoPacífico(final Tablero tablero, final Pieza piezaEnMovimiento, final int coordenadaDeDestino) {
            super(tablero, piezaEnMovimiento, coordenadaDeDestino);
        }
    }
    
    Pieza piezaAtacada;
    
    public static final class MovimientoAtaque extends Movimiento{
        public MovimientoAtaque(final Tablero tablero, final Pieza piezaEnMovimiento,final Pieza piezaAtacada, final int coordenadaDeDestino) {
            super(tablero, piezaEnMovimiento, coordenadaDeDestino);
            this.piezaAtacada = piezaAtacada;
        }
    }
}
