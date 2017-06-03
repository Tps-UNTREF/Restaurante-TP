package Clases;

import java.util.ArrayList;

import Clases.Mesa.Estados;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExcepcion;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.ProductoNoEncontradoException;

public class ModuloMesa {
	private static ModuloMesa moduloMesa;
	private static ArrayList<Mesa> mesas = new ArrayList<Mesa>();

	/**
	 * post: Constructor privado por que es un singleton, crearlo a partir del
	 * metodo getModuloMesa().
	 */
	private ModuloMesa() {
	}

	/**
	 * post: Si el moduloMesa no esta instanciado, lo instancia y lo devuelve.
	 */
	public static ModuloMesa getModuloMesa() {
		if (null == moduloMesa) {
			moduloMesa = new ModuloMesa();
		}
		return moduloMesa;
	}

	/**
	 * pre: Se le ingresa la cantidad de mesas que quiero para mi local. post:
	 * Si no se generaron mesas, genera la cantidad de mesas ingresadas y las
	 * almacena en la lista.
	 * 
	 * @throws MesasYaGeneradasExcepcion
	 */
	public static void generarMesas(int cantidadMesas) throws MesasYaGeneradasExcepcion {
		if (mesas.size() == 0) {
			for (int i = 0; i < cantidadMesas; i++) {
				mesas.add(new Mesa(i, Estados.Disponible));
			}
		} else {
			throw new MesasYaGeneradasExcepcion("Ya se generaron las mesas");
		}
	}

	/**
	 * post: Devuelve la cantidad de mesas que tiene el restaurante.
	 */
	public int getCantidadMesas() {
		return mesas.size();
	}

	/**
	 * pre: Se le pasa por parametro el numero de mesa a buscar. post: Devuelve
	 * una mesa.
	 */
	public Mesa getMesa(int numeroDeMesa) {
		return mesas.get(numeroDeMesa);
	}

	/**
	 * pre: Se le pasa por parametro el numero de mesa a ocupar. post: La mesa
	 * es ocupada en el caso de que su estado sea disponible.
	 */
	public void ocuparMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		mesas.get(numeroDeMesa).setEstado(Estados.Ocupada);
	}

	/**
	 * pre: Se le pasa por parametro el numero de mesa a cerrar. post: La mesa
	 * es cerrada en el caso de que su estado sea disponible.
	 */
	public void cerrarMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		mesas.get(numeroDeMesa).setEstado(Estados.Cerrada);
	}

	/**
	 * pre: Se le pasa por parametro el numero de mesa. post: La mesa es pasada
	 * a disponible en el caso de que su estado sea cerrada o ocupada.
	 */
	public void pasarMesaADisponible(int numeroDeMesa) throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		mesas.get(numeroDeMesa).setEstado(Estados.Disponible);
	}

	/**
	 * pre: Se le ingresa el numero de mesa, y un unico producto que este
	 * consumiendo la mesa.
	 * 
	 * @throws MesaNoOcupadaExcepcion
	 * @throws ProductoNoEncontradoException
	 */
	public void registrarConsumision(int numeroDeMesa, Producto producto)
			throws MesaNoOcupadaExcepcion, ProductoNoEncontradoException {
		mesas.get(numeroDeMesa).setConsumisiones(producto, 1);
	}

	/**
	 * pre: Se le ingresa el numero de mesa, el producto y la cantidad de el
	 * producto a agregar.
	 * 
	 * @throws MesaNoOcupadaExcepcion
	 * @throws ProductoNoEncontradoException
	 */
	public void registrarConsumision(int numeroDeMesa, Producto producto, Integer cantidad)
			throws MesaNoOcupadaExcepcion, ProductoNoEncontradoException {
		mesas.get(numeroDeMesa).setConsumisiones(producto, cantidad);
	}

}
