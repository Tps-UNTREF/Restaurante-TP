package Clases;

import java.util.ArrayList;

import Clases.Mesa.Estados;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExepcion;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.ProductoNoEncontradoException;

public class ModuloMesa {
	private static ModuloMesa moduloMesa;
	private ArrayList<Mesa> mesas;
	/**
	 * post: Constructor privado por que es un singleton, crearlo a partir del metodo getModuloMesa().
	 */
	private ModuloMesa(){
		this.mesas = new ArrayList<Mesa>();
	}
	/**
	 * post: Si el moduloMesa no esta instanciado, lo instancia y lo devuelve.
	 */
	public static ModuloMesa getModuloMesa() {
		if(null == moduloMesa){
			moduloMesa = new ModuloMesa();
		}
		return moduloMesa;
	}
	
	/**
	 * pre: Se le ingresa la cantidad de mesas que quiero para mi local.
	 * post: Si no se generaron mesas, genera la cantidad de mesas ingresadas y las almacena en la lista.
	 * @throws MesasYaGeneradasExcepcion 
	 * 
	 */
	public void generarMesas(int cantidadMesas) throws MesasYaGeneradasExcepcion {
		if(mesas.size() == 0){
			for(int i=0; i<cantidadMesas; i++){
				mesas.add(new Mesa(i,Estados.Disponible));
			}
		}else{
			throw new MesasYaGeneradasExcepcion("Ya se generaron las mesas");
		}
	}
	/**
	 * post: Devuelve la cantidad de mesas que tiene el restaurante.
	 */
	public int getCantidadMesas(){
		return mesas.size();
	}
	/**
	 * post: Devuelve la cantidad de mesas que tiene el restaurante.
	 */
	public Mesa getMesa(int numeroDeMesa){
		return this.mesas.get(numeroDeMesa);
	}

	
	/**
	 * pre: Se le pasa por parametro el numero de mesa a ocupar.
	 * post: La mesa es ocupada en el caso de que su estado sea disponible.
	 */
	public void ocuparMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		mesas.get(numeroDeMesa).setEstado(Estados.Ocupada);
	}
	/**
	 * pre: Se le pasa por parametro el numero de mesa a cerrar.
	 * post: La mesa es cerrada en el caso de que su estado sea disponible.
	 */
	public void cerrarMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion {
		mesas.get(numeroDeMesa).setEstado(Estados.Cerrada);
	}
	/**
	 * pre: Se le pasa por parametro el numero de mesa.
	 * post: La mesa es pasada a disponible en el caso de que su estado sea cerrada o ocupada.
	 */
	public void pasarMesaADisponible(int numeroDeMesa) throws MesaEstadoInvalidoExcepcion, MesaNoDisponibleExcepcion {
		mesas.get(numeroDeMesa).setEstado(Estados.Disponible);
	}
	/**
	 * pre: Se le ingresa el numero de mesa, y un unico producto que este consumiendo la mesa.
	 * @throws MesaNoOcupadaExepcion 
	 * @throws ProductoNoEncontradoException 
	 */
	public void registrarConsumision(int numeroDeMesa,Integer codigoDeProducto) throws MesaNoOcupadaExepcion{
		if(ModuloPrecios.getModuloPrecios().existeProducto(codigoDeProducto)){
			mesas.get(numeroDeMesa).setConsumisiones(codigoDeProducto, 1);
		}else {
			throw new ProductoNoEncontradoException("Codigo de producto no encontrado en la lista de precios");
		}
	}
	/**
	 * pre: Se le ingresa el numero de mesa, el producto y la cantidad de el producto a agregar.
	 * @throws MesaNoOcupadaExepcion 
	 * @throws ProductoNoEncontradoException 
	 */
	public void registrarConsumision(int numeroDeMesa,Integer codigoDeProducto,Integer cantidad) throws MesaNoOcupadaExepcion{
		if(ModuloPrecios.getModuloPrecios().existeProducto(codigoDeProducto)){
			this.mesas.get(numeroDeMesa).setConsumisiones(codigoDeProducto, cantidad);
		} else {
			throw new ProductoNoEncontradoException("Codigo de producto no encontrado en la lista de precios");
		}
	}
	
	
	

	


	
	
}
