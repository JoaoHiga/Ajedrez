package Piezas;

import Otros.Color;
import Otros.Herramientas;
import Tablero.Casilla;
import Tablero.Movimiento;
import Tablero.Tablero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Caballo extends Pieza {
    
    private final static int[] CoordenadasMovimientosPosibles = {-17,-15,-10,-6,6,10,15,17};
            
    public Caballo(int posiciónPieza, Color colorPieza) {
        super(posiciónPieza, colorPieza);
    }

    @Override
    public List<Movimiento> calcularMovimientosLegales(Tablero tablero) {
        
        final List<Movimiento> movimientosLegales = new ArrayList<>();
        
        for(final int coordenadaSeleccionada : CoordenadasMovimientosPosibles){
            final int coordenadaDeDestino = this.posiciónPieza + coordenadaSeleccionada;
            if(Herramientas.esCoordenadaVálida(coordenadaDeDestino)){
                if(esExcepciónDePrimeraColumna(this.posiciónPieza, coordenadaSeleccionada)||
                        esExcepciónDeSegundaColumna(this.posiciónPieza, coordenadaSeleccionada)||
                        esExcepciónDeSéptimaColumna(this.posiciónPieza, coordenadaSeleccionada)||
                        esExcepciónDeOctavaColumna(this.posiciónPieza, coordenadaSeleccionada)){
                    continue;
                }
                final Casilla casillaDeDestino = tablero.getCasilla(coordenadaDeDestino);
                if(!casillaDeDestino.estáOcupadoPorPieza()){
                    movimientosLegales.add(new Movimiento.MovimientoPacífico(tablero,this,coordenadaDeDestino));
                }else{
                    final Pieza piezaEnLaCoordenadaDeDestino = casillaDeDestino.getPieza();
                    final Color colorPieza = piezaEnLaCoordenadaDeDestino.getColorPieza();
                    if(this.colorPieza != colorPieza){
                        movimientosLegales.add(new Movimiento.MovimientoAtaque(tablero,this,piezaEnLaCoordenadaDeDestino,coordenadaDeDestino));
                    }
                }
            }
        }
        return  Collections.unmodifiableList(movimientosLegales);
    }
    
    private static boolean esExcepciónDePrimeraColumna(final int posiciónActual, final int coordenadaSeleccionada){
        return Herramientas.primeraColumna[posiciónActual] && (coordenadaSeleccionada == -17||coordenadaSeleccionada == -10||
                coordenadaSeleccionada == 6||coordenadaSeleccionada == 15);  
    }
    private static boolean esExcepciónDeSegundaColumna(final int posiciónActual, final int coordenadaSeleccionada){
        return Herramientas.segundaColumna[posiciónActual] && (coordenadaSeleccionada == -10||coordenadaSeleccionada == 6);  
    }
    private static boolean esExcepciónDeSéptimaColumna(final int posiciónActual, final int coordenadaSeleccionada){
        return Herramientas.séptimaColumna[posiciónActual] && (coordenadaSeleccionada == -6||coordenadaSeleccionada == 10);  
    }
    private static boolean esExcepciónDeOctavaColumna(final int posiciónActual, final int coordenadaSeleccionada){
        return Herramientas.octavaColumna[posiciónActual] && (coordenadaSeleccionada == 17||coordenadaSeleccionada == 10||
                coordenadaSeleccionada == -6||coordenadaSeleccionada == -15);  
    }
}
