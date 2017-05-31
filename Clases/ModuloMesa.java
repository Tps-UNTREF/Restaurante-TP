package Clases;

import java.util.ArrayList;

import Clases.Mesa.Estados;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaYaDisponibleExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;

public class ModuloMesa {
	private static ModuloMesa moduloMesa;
	private ArrayList<Mesa> mesas;
	private boolean seGeneraronMesas;
	
	private ModuloMesa(){
		this.mesas = new ArrayList<Mesa>();
		this.seGeneraronMesas = false;
	}
	
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
	
	public int getCantidadMesas(){
		return mesas.size();
	}
	
	public boolean getSeGeneraronMesas(){
		return this.seGeneraronMesas;
	}
	
	public Mesa getMesa(int numeroDeMesa){
		return this.mesas.get(numeroDeMesa);
	}

	
	public void ocuparMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Disponible){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Ocupada);
		}else{
			throw new MesaNoDisponibleExcepcion("La mesa tiene que estar disponible para poder ocuparla.");
		}
	}
	
	public void cerrarMesa(int numeroDeMesa) throws MesaNoDisponibleExcepcion {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Disponible){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Cerrada);
		}else{
			throw new MesaNoDisponibleExcepcion("La mesa tiene que estar disponible para poder cerrarla.");
		}
	}
	
	public void pasarMesaADisponible(int numeroDeMesa) throws MesaYaDisponibleExcepcion {
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Cerrada || this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada){
			this.mesas.get(numeroDeMesa).setEstado(Estados.Disponible);
		}else{
			throw new MesaYaDisponibleExcepcion("La mesa ya esta disponible");
		}
	}
	/**
	 * pre: Se le ingresa el numero de mesa, y un unico producto que este consumiendo la mesa.
	 * 
	 * 
	 */
	public void registrarConsumision(int numeroDeMesa,Integer codigoDeProducto){
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada){
			this.mesas.get(numeroDeMesa).setConsumisiones(codigoDeProducto, 1);
		}else{
			throw new Error("La mesa tiene que estar ocupada");
		}
	}
	/**
	 * pre: Se le ingresa el numero de mesa, el producto y la cantidad de el producto a agregar.
	 * 
	 * 
	 */
	public void registrarConsumision(int numeroDeMesa,Integer codigoDeProducto,Integer cantidad){
		if(this.mesas.get(numeroDeMesa).getEstado() == Estados.Ocupada){
			this.mesas.get(numeroDeMesa).setConsumisiones(codigoDeProducto, cantidad);
		}else{
			throw new Error("La mesa tiene que estar ocupada");
		}
	}
	
	
	

	


	
	
}
