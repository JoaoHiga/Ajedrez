package Piezas;

import Otros.Color;
import Otros.Herramientas;
import Tablero.Casilla;
import Tablero.Movimiento;
import Tablero.Tablero;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Torre extends Pieza{

    public Torre(int posiciónPieza, Color colorPieza) {
        super(posiciónPieza, colorPieza);
    }
    
    private final static int[] CoordenadasMovimientosPosibles = {-8,-1,1,8};
    
    @Override
    public Collection<Movimiento> calcularMovimientosLegales(Tablero tablero) {
        
        final List<Movimiento> movimientosLegales = new ArrayList<>();
        
        for(final int coordenadaSeleccionada : CoordenadasMovimientosPosibles){
            int coordenadaDeDestino = this.posiciónPieza;
            while(Herramientas.esCoordenadaVálida(coordenadaDeDestino)){
                if(esExcepciónDePrimeraColumna(coordenadaDeDestino, coordenadaSeleccionada)||
                        esExcepciónDeOctavaColumna(coordenadaDeDestino, coordenadaSeleccionada)){
                    break;
                }
                coordenadaDeDestino += coordenadaSeleccionada;
                if(Herramientas.esCoordenadaVálida(coordenadaDeDestino)){
                    final Casilla casillaDeDestino = tablero.getCasilla(coordenadaDeDestino);
                    if(!casillaDeDestino.estáOcupadoPorPieza()){
                        movimientosLegales.add(new Movimiento.MovimientoPacífico(tablero,this,coordenadaDeDestino));
                    }else{
                        final Pieza piezaEnLaCoordenadaDeDestino = casillaDeDestino.getPieza();
                        final Color bandoPieza = piezaEnLaCoordenadaDeDestino.getColorPieza();
                        if(this.colorPieza != bandoPieza){
                            movimientosLegales.add(new Movimiento.MovimientoAtaque(tablero,this,piezaEnLaCoordenadaDeDestino,coordenadaDeDestino));
                        }
                        break;
                    } 
                }
            }
        }
        return  Collections.unmodifiableList(movimientosLegales);    
    }
    private static boolean esExcepciónDePrimeraColumna(final int posiciónActual, final int coordenadaSeleccionada){
        return Herramientas.primeraColumna[posiciónActual] && (coordenadaSeleccionada == -1);  
    }
    private static boolean esExcepciónDeOctavaColumna(final int posiciónActual, final int coordenadaSeleccionada){
        return Herramientas.octavaColumna[posiciónActual] && (coordenadaSeleccionada == 1);  
    }
}
