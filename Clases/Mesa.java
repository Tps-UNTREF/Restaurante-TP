package Clases;

import java.util.HashMap;
import java.util.Map;
import Excepciones.MesaNoOcupadaExepcion;
import Excepciones.ProductoNoEncontradoException;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesaNoDisponibleExcepcion;

public class Mesa {

	private Estados estado = null;
	private int numeroDeMesa;
	private Map<Integer, Integer> consumiciones;

	public Mesa(int numeroDeMesa, Estados estado) {
		this.estado = estado;
		this.numeroDeMesa = numeroDeMesa;
		this.consumiciones = new HashMap<Integer, Integer>();
	}

	public Estados getEstado() {
		return estado;
	}

	public int getNumeroDeMesa() {
		return numeroDeMesa;
	}

	public void setConsumisiones(Integer codigoDeProducto, Integer cantidad) throws MesaNoOcupadaExepcion {
		if(getEstado() != Estados.Ocupada){
			throw new MesaNoOcupadaExepcion("La mesa " + this.getNumeroDeMesa() + " tiene que estar ocupada");
		} else if (!(ModuloPrecios.getModuloPrecios().existeProducto(codigoDeProducto))) { 
			new ProductoNoEncontradoException("Codigo de producto (" + codigoDeProducto + ") no encontrado en menu");
		}else{
			consumiciones.put(codigoDeProducto, consumiciones.getOrDefault(codigoDeProducto, 0) + cantidad);
		}
	}

	public void setEstado(Estados estado) throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		if(getEstado() == estado){
			throw new MesaEstadoInvalidoExcepcion("La mesa ya esta " + estado.toString());
		} else if ((estado == Estados.Ocupada || estado == Estados.Cerrada ) && getEstado() != Estados.Disponible) {
			throw new MesaNoDisponibleExcepcion("La mesa tiene que estar disponible pasarla al esta " + estado.toString());
		} else {
			this.estado = estado;
		}
	}

	public enum Estados {
		Disponible, Cerrada, Ocupada
	}
}
