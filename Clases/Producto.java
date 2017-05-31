package Clases;

public abstract class Producto {
	
	private static int contadorId = 0;
	private int codigoDeProducto;
	private String descripcion;
	private double precioDeCosto;
	private double precioDeVenta;
	private Categorias categoria;
	
	public Producto(String descripcion, double precioDeCosto, double precioDeVenta, Categorias categoria) {
		contadorId++;
		this.codigoDeProducto = contadorId;
		this.descripcion = descripcion;
		this.precioDeCosto = precioDeCosto;
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
	
	public void setprecioDeVenta(double precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	
	public double getPrecioDeVenta() {
		return precioDeVenta;
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

