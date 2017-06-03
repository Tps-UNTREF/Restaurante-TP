package Test;

import java.util.Date;

import org.junit.Test;

import Clases.Combo;
import Clases.ModuloCaja;
import Clases.ModuloMesa;
import Clases.ModuloPrecios;
import Clases.Producto.Categorias;
import Clases.ProductoUnico;
import Excepciones.DescuentoInvalidoException;
import Excepciones.MesaEstadoInvalidoExcepcion;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaNoOcupadaExepcion;
import Excepciones.MesasYaGeneradasExcepcion;
import Excepciones.PrecioDeVentaInvalidoException;
import Excepciones.ProductoNoEncontradoException;

public class ModuloCajaTest {

	@Test // falta limpiar todo 
	public void ModuloCaja_listarTicket() throws MesasYaGeneradasExcepcion, PrecioDeVentaInvalidoException, MesaNoOcupadaExepcion, MesaNoDisponibleExcepcion, MesaEstadoInvalidoExcepcion, DescuentoInvalidoException, ProductoNoEncontradoException {
		System.out.println("TEST LISTAR TICKET \n");
		
		ModuloCaja mc = ModuloCaja.getModuloCaja();
		ModuloPrecios mp = ModuloPrecios.getModuloPrecios();
		ModuloMesa mm = ModuloMesa.getModuloMesa();
		
		
		ProductoUnico p1 = new ProductoUnico("Coca Cola", 5, 25, Categorias.BebidaSinAlcohol);
		ProductoUnico p3 = new ProductoUnico("Milanga napolitanga con fritangas", 20, 70, Categorias.Minutas);
		ProductoUnico p8 = new ProductoUnico("Hamburguesa completa", 25, 60, Categorias.Hamburguesas);
		Combo c1 = new Combo("Combo hamburguesa y gaseosa", 10);
		
		
		
		ModuloMesa.generarMesas(4);
		mp.altaProducto(p1);
		mp.altaProducto(p3);
		mp.altaProducto(p8);
		mp.altaProducto(c1);
		
		mm.ocuparMesa(1);
		mm.getMesa(1).setConsumisiones(p1, 2);
		mm.getMesa(1).setConsumisiones(p3, 2);
		//prueba del metodo generarTicket
		
		mm.ocuparMesa(2);
		mm.getMesa(2).setConsumisiones(p8, 1);
		mm.getMesa(2).setConsumisiones(p3, 2);
		//prueba del metodo generarTicket
		
		mm.ocuparMesa(3);
		mm.getMesa(3).setConsumisiones(c1, 1);
		mm.getMesa(3).setConsumisiones(p3, 2);
		mm.getMesa(2).setConsumisiones(p8, 1);
		
		mc.generarTicket(mm.getMesa(1));
		mc.generarTicket(mm.getMesa(2));
		mc.generarTicket(mm.getMesa(3));
		
		mc.listarTickets(new Date(), new Date());
		
	}
	
}
