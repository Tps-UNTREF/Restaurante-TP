package Clases;

import java.util.ArrayList;

import Clases.Mesa.Estados;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExepcion;
import Excepciones.MesaYaDisponibleExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.ProductoNoEncontradoException;

public class ModuloMesa {
	private static ModuloMesa moduloMesa;
	private ArrayList<Mesa> mesas;
	private boolean seGeneraronMesas;
	/**
	 * post: Constructor privado por que es un singleton, crearlo a partir del metodo getModuloMesa().
	 */
	private ModuloMesa(){
		this.mesas = new ArrayList<Mesa>();
		this.seGeneraronMesas = false;
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
		if(!seGeneraronMesas){
			for(int i=0; i<cantidadMesas; i++){
				mesas.add(new Mesa(i,Estados.Disponible));
			}
			this.seGeneraronMesas = true;
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
	public boolean getSeGeneraronMesas(){
		return this.seGeneraronMesas;
	}
	/**
	 * pre: Se le pasa por parametro el numero de mesa a buscar.
	 * post: Devuelve una mesa.
	 */
	public Mesa getMesa(int numeroDeMesa){
		return this.mesas.get(numeroDeMesa);
	}
	
	/**
	 * pre: Se le pasa por parametro el numero de mesa a ocupar.
	 * post: La mesa es ocupada en el caso de que su estado sea disponible.
	 */
	public void ocuparMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Disponible){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Ocupada);
		}else{
			throw new MesaNoDisponibleExcepcion("La mesa tiene que estar disponible para poder ocuparla.");
		}
	}
	/**
	 * pre: Se le pasa por parametro el numero de mesa a cerrar.
	 * post: La mesa es cerrada en el caso de que su estado sea disponible.
	 */
	public void cerrarMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Disponible){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Cerrada);
		}else{
			throw new MesaNoDisponibleExcepcion("La mesa tiene que estar disponible para poder cerrarla.");
		}
	}
	/**
	 * pre: Se le pasa por parametro el numero de mesa.
	 * post: La mesa es pasada a disponible en el caso de que su estado sea cerrada o ocupada.
	 */
	public void pasarMesaADisponible(int numeroDeMesa) throws MesaYaDisponibleExcepcion {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Cerrada || this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Disponible);
		}else{
			throw new MesaYaDisponibleExcepcion("La mesa ya esta disponible");
		}
	}
	/**
	 * pre: Se le ingresa el numero de mesa, y un unico producto que este consumiendo la mesa.
	 * @throws MesaNoOcupadaExepcion 
	 * @throws ProductoNoEncontradoException 
	 * 
	 * 
	 */
	public void registrarConsumision(int numeroDeMesa,Producto producto) throws MesaNoOcupadaExepcion, ProductoNoEncontradoException{
		boolean existe = false;
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada && (existe = ModuloPrecios.getModuloPrecios().existeProducto(producto))){
			this.mesas.get(numeroDeMesa).setConsumisiones(producto, 1);
		}else if(!existe){
			throw new ProductoNoEncontradoException("Producto no encontrado en la lista de precios");
		}else{
			throw new MesaNoOcupadaExepcion("La mesa tiene que estar ocupada");
		}
	}
	/**
	 * pre: Se le ingresa el numero de mesa, el producto y la cantidad de el producto a agregar.
	 * @throws MesaNoOcupadaExepcion 
	 * @throws ProductoNoEncontradoException 
	 */
	public void registrarConsumision(int numeroDeMesa,Producto producto,Integer cantidad) throws MesaNoOcupadaExepcion, ProductoNoEncontradoException{
		boolean existe = false;
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada && (existe = ModuloPrecios.getModuloPrecios().existeProducto(producto))){
			this.mesas.get(numeroDeMesa).setConsumisiones(producto, cantidad);
		}else if(!existe){
			throw new ProductoNoEncontradoException("Producto no encontrado en la lista de precios");
		}else{
			throw new MesaNoOcupadaExepcion("La mesa tiene que estar ocupada");
		}
	}
	
	
	

	


	
	
}
