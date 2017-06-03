package Clases;

import java.util.HashMap;

import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExepcion;
import Excepciones.ProductoNoEncontradoException;


public class Mesa {

	private Estados estado = null;
	private int numeroDeMesa;
	private HashMap<Producto, Integer> consumiciones;

	public Mesa(int numeroDeMesa, Estados estado) {
		this.estado = estado;
		this.numeroDeMesa = numeroDeMesa;
		this.consumiciones = new HashMap<Producto, Integer>();
	}

	public Estados getEstado() {
		return estado;
	}

	public int getNumeroDeMesa() {
		return numeroDeMesa;
	}
	
	public HashMap<Producto,Integer> getConsumisiones(){
		return this.consumiciones;
		
	}

	public void setConsumisiones(Producto producto, Integer cantidad) throws MesaNoOcupadaExepcion, ProductoNoEncontradoException {
		if(getEstado() != Estados.Ocupada) {
			throw new MesaNoOcupadaExepcion("La mesa " + getNumeroDeMesa() + " tiene que estar ocupada");
		} else if (!(ModuloPrecios.getModuloPrecios().existeProducto(producto))){
			throw new ProductoNoEncontradoException("Producto " + producto.getDescripcion() + " no encontrado en el Menu");
		} else {
			this.consumiciones.put(producto, cantidad);
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
