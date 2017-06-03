package Test;

//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Clases.Combo;
import Clases.ModuloPrecios;
import Clases.Producto;
import Clases.Producto.Categorias;
import Clases.ProductoUnico;
import Excepciones.CantidadDeProductosInvalidoException;
import Excepciones.DescuentoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;

public class ModuloPreciosTest {

ModuloPrecios moduloPrecios = ModuloPrecios.getModuloPrecios();
	
	@Before
	public void inicializacion() throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, CantidadDeProductosInvalidoException {
		for (Producto p : moduloPrecios.listarMenu()) {
			moduloPrecios.bajaProducto(p.getCodigoDeProducto());
		}
		ProductoUnico heineken = new ProductoUnico("Heineken", 30, 65, Categorias.BebidaConAlcohol);
		ProductoUnico mozzarella = new ProductoUnico("Mozzarella", 20, 55, Categorias.Pizzas);
		Combo muzaHeineken = new Combo("Mozzarella + Heineken", 10);
		muzaHeineken.agregarProducto(heineken, 1);
		muzaHeineken.agregarProducto(mozzarella, 1);
		moduloPrecios.altaProducto(heineken);
		moduloPrecios.altaProducto(mozzarella);
		moduloPrecios.altaProducto(muzaHeineken);
		
	}
	
	@Test
	public void Mesa_NumeroDeMesa() {
		moduloPrecios.listarMenu();
	}

}
