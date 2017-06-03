package Clases;

import Excepciones.PrecioDeVentaInvalidoException;

public abstract class Producto implements Comparable<Producto> {
	
	private static int contadorId = 0;
	private int codigoDeProducto;
	private String descripcion;
	private double precioDeCosto;
	private double precioDeVenta;
	private Categorias categoria;
	
	public Producto(String descripcion, double precioDeCosto, double precioDeVenta, Categorias categoria) throws PrecioDeVentaInvalidoException {
		contadorId++;
		this.codigoDeProducto = contadorId;
		this.descripcion = descripcion;
		this.precioDeCosto = precioDeCosto;
		this.setPrecioDeVenta(precioDeVenta);
		this.categoria = categoria;
	}
	
	public int getCodigoDeProducto() {
		return codigoDeProducto;
	}
	
	public Categorias getCategoria() {
		return categoria;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setPrecioDeCosto(double precioDeCosto) {
		this.precioDeCosto = precioDeCosto;
	}
	
	public double getPrecioDeCosto() {
		return precioDeCosto;
	}
	
	public void setPrecioDeVenta(double precioDeVenta) throws PrecioDeVentaInvalidoException {
		if(precioDeVenta >= this.getPrecioDeCosto()) {
			this.precioDeVenta = precioDeVenta;
		} else {
			throw new PrecioDeVentaInvalidoException();
		}
	}
	
	public double getPrecioDeVenta() {
		return precioDeVenta;
	}
	
	@Override
    public int compareTo(Producto other){
        return this.getCategoria().compareTo(other.getCategoria());
    }
	
	@Override
	public String toString() {
		return getCodigoDeProducto() + leftPad(getDescripcion(), 20, '.') + leftPad(getPrecioDeVenta(), 10, '.');
	}

	private String leftPad(Object original, int length, char padCharacter) {
		String paddedString = original.toString();
      	while (paddedString.length() < length) {
    	  paddedString = padCharacter + paddedString;
      	}
      	return paddedString;
	}
	
	public enum Categorias {
		BebidaConAlcohol,
		BebidaSinAlcohol,
		Pizzas,
		Hamburguesas,
		Minutas,
		Combos  
	}
}

