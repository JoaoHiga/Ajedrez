package Piezas;

import Otros.Color;
import Otros.Herramientas;
import Tablero.Movimiento;
import Tablero.Movimiento.MovimientoPacífico;
import Tablero.Tablero;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Peón extends Pieza{
    
    private final static int[] CoordenadasMovimientosPosibles = {8,16};

    public Peón(final int posiciónPieza, final Color colorPieza) {
        super(posiciónPieza, colorPieza);
    }

    @Override
    public Collection<Movimiento> calcularMovimientosLegales(final Tablero tablero) {
        final List<Movimiento> movimientosLegales = new ArrayList<>();
        for(final int coordenadaSeleccionada : CoordenadasMovimientosPosibles){
            int coordenadaDeDestino = this.posiciónPieza + (coordenadaSeleccionada)*this.getColorPieza().getDirección();
            
            if(!Herramientas.esCoordenadaVálida(coordenadaDeDestino)){
                continue;
            }
            if(coordenadaSeleccionada == 8 && tablero.getCasilla(coordenadaDeDestino).estáOcupadoPorPieza()){
                movimientosLegales.add(new MovimientoPacífico(tablero, this,coordenadaDeDestino));
            }else if(coordenadaSeleccionada == 16 && this.esPrimerMovimiento()&&
                    (Herramientas.segundaFila[this.posiciónPieza]&&this.getColorPieza().esNegro())||
                    (Herramientas.séptimaFila[this.posiciónPieza]&&this.getColorPieza().esBlanco())){
                final int detrásDeCoordenadaDeDestino = this.posiciónPieza + (this.colorPieza.getDirección()*8);
                if(!tablero.getCasilla(detrásDeCoordenadaDeDestino).estáOcupadoPorPieza()&& 
                   !tablero.getCasilla(coordenadaDeDestino).estáOcupadoPorPieza()){
                    movimientosLegales.add(new MovimientoPacífico(tablero, this,coordenadaDeDestino));
                }
            }
        }
        return Collections.unmodifiableList(movimientosLegales);
    }
}
