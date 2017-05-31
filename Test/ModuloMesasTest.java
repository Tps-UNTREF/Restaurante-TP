package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Clases.Mesa;
import Clases.Mesa.Estados;
import Clases.ModuloMesa;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaYaDisponibleExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;

public class ModuloMesasTest {

	private ModuloMesa moduloMesa = null;
	
	@Before
	public void configurarModuloMesa() throws MesasYaGeneradasExcepcion {
		moduloMesa = ModuloMesa.getModuloMesa();
		if (moduloMesa.getCantidadMesas() == 0) {
			moduloMesa.generarMesas(10);			
		}
	}
	
	@Test
	public void a_Mesa_NumeroDeMesa() {
		Mesa mesa = new Mesa(1,Estados.Cerrada);
		assertEquals(1,mesa.getNumeroDeMesa());
	}
	
	@Test
	public void b_Mesa_Estado() {
		Mesa mesa = new Mesa(1,Estados.Cerrada);
		assertEquals(Estados.Cerrada,mesa.getEstado());
	}
	
	@Test
	public void c_ModuloMesa_CantidadDeMesas() {
		assertEquals(10,moduloMesa.getCantidadMesas());
	}
		
	@Test
	public void d_ModuloMesa_OcuparMesa() throws MesaNoDisponibleExcepcion { // la mesa 5 quedo ocupada
		moduloMesa.ocuparMesa(5);
		assertEquals(Estados.Ocupada, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test(expected = Error.class) // la mesa 6 quedo cerrada
	public void e_ModuloMesa_OcuparMesaEnEstadoCerrada() throws MesaNoDisponibleExcepcion {
		moduloMesa.cerrarMesa(5);
		moduloMesa.ocuparMesa(5);
	}
	
	@Test
	public void f_ModuloMesa_CerrarMesa() throws MesaNoDisponibleExcepcion { // la mesa 7 quedo cerrada
		moduloMesa.cerrarMesa(7);
		assertEquals(Estados.Cerrada, moduloMesa.getMesa(7).getEstado());
	}
	
	@Test(expected = Error.class) // la mesa 5 sigue ocupada
	public void g_ModuloMesa_CerrarMesaEnEstadoNoDisponible() throws MesaNoDisponibleExcepcion {
		moduloMesa.cerrarMesa(5);
	}
	
	@Test
	public void h_ModuloMesa_PasarADisponibleEstandoCerrada() throws MesaYaDisponibleExcepcion {
		moduloMesa.pasarMesaADisponible(7);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(7).getEstado());
	}
	
	@Test
	public void i_ModuloMesa_PasarADisponibleEstandoOcupada() throws MesaYaDisponibleExcepcion {
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}
	

	

	

	
	
	
	
	
	
	
	
	
	
	
	

}
