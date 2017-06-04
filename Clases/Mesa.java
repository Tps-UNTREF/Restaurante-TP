package Clases;

import java.util.HashMap;

import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExcepcion;
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

	/**
	 * post: Devuelve el estado de la mesa.
	 */
	public Estados getEstado() {
		return estado;
	}

	/**
	 * post: Devuelve el numero de mesa.
	 */
	public int getNumeroDeMesa() {
		return numeroDeMesa;
	}

	/**
	 * pre: Se le pasa el producto y la cantidad que el usuario esta
	 * consumiendo. post: Ingresa el producto junto con su cantidad en un map.
	 * 
	 * @throws ProductoNoEncontradoException
	 * @throws MesaNoOcupadaExcepcion
	 */
	public void setConsumisiones(Producto producto, Integer cantidad)
			throws ProductoNoEncontradoException, MesaNoOcupadaExcepcion {
		if (getEstado() != Estados.Ocupada) {
			throw new MesaNoOcupadaExcepcion("La mesa tiene que estar ocupada");
		} else if (!(ModuloPrecios.getModuloPrecios().existeProducto(producto))) {
			throw new ProductoNoEncontradoException("Producto no encontrado en la lista de precios");
		} else {
			this.consumiciones.put(producto, consumiciones.getOrDefault(producto, 0) + cantidad);
		}
	}

	public HashMap<Producto, Integer> getConsumisiones() {
		return this.consumiciones;
	}

	/**
	 * pre: Se le pasa un estado por parametro post: Si la mesa ya tiene el
	 * estado pasado tira error.
	 */
	public void setEstado(Estados estado) throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		if (getEstado() == estado) {
			throw new MesaEstadoInvalidoExcepcion("La mesa ya esta " + estado.toString());
		} else if ((estado == Estados.Ocupada || estado == Estados.Cerrada) && getEstado() != Estados.Disponible) {
			throw new MesaNoDisponibleExcepcion(
					"La mesa tiene que estar disponible pasarla al estado " + estado.toString());
		} else {
			this.estado = estado;
		}
	}

	/**
	 * pre: Se le pasa 2 fechas post: Busca todos los tickets entre las 2 fechas
	 * dadas y calcula los ingresos totales entre esas 2 fechas. Tambien muestra
	 * por consola el codigo del ticket y su valor.
	 */
	public enum Estados {
		Disponible, Cerrada, Ocupada
	}

}
