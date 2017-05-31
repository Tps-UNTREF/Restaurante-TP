package Clases;

import java.util.ArrayList;

import Clases.Mesa.Estados;

public class ModuloMesa {
	private static ModuloMesa moduloMesa;
	private ArrayList<Mesa> mesas;
	private boolean seGeneraronMesas;
	
	private ModuloMesa(){
		this.mesas = new ArrayList<Mesa>();
		this.seGeneraronMesas = false;
	}
	
	public static ModuloMesa getModuloMesa(){
		if(null == moduloMesa){
			moduloMesa = new ModuloMesa();
			System.out.println("La mesa no estaba creada");
		}
		return moduloMesa;
	}
	
	/**
	 * pre: Se le ingresa la cantidad de mesas que quiero para mi local.
	 * post: Si no se generaron mesas, genera la cantidad de mesas ingresadas y las almacena en la lista.
	 * 
	 */
	public void generarMesas(int cantidadMesas) {
		if(!seGeneraronMesas){
			for(int i=0; i<cantidadMesas; i++){
				mesas.add(new Mesa(i,Estados.Disponible));
			}
			this.seGeneraronMesas = true;
		}else{
			throw new Error("Ya se generaron las mesas");
		}
	}
	
	public int getCantidadMesas(){
		return mesas.size();
	}
	
	public boolean getSeGeneraronMesas(){
		return this.seGeneraronMesas;
	}
	
	public Mesa getMesa(int numeroDeMesa){
		return this.mesas.get(numeroDeMesa);
	}

	
	public void ocuparMesa(int numeroDeMesa) {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Disponible){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Ocupada);
		}else{
			throw new Error("La mesa tiene que estar disponible para poder ocuparla.");
		}
	}
	
	public void cerrarMesa(int numeroDeMesa) {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Disponible){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Cerrada);
		}else{
			throw new Error("La mesa tiene que estar disponible para poder cerrarla.");
		}
	}
	
	public void pasarMesaADisponible(int numeroDeMesa) {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Cerrada || this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Disponible);
		}else{
			throw new Error("La mesa ya esta disponible");
		}
	}
	/**
	 * pre: Se le ingresa el numero de mesa, y un unico producto que este consumiendo la mesa.
	 * 
	 * 
	 */
	public void registrarConsumision(int numeroDeMesa,Producto producto){
		this.mesas.get(numeroDeMesa).setConsumisiones(producto, 1);
	}
	/**
	 * pre: Se le ingresa el numero de mesa, el producto y la cantidad de el producto a agregar.
	 * 
	 * 
	 */
	public void registrarConsumision(int numeroDeMesa,Producto producto,Integer cantidad){
		this.mesas.get(numeroDeMesa).setConsumisiones(producto, cantidad);
	}
	
	
	

	


	
	
}
