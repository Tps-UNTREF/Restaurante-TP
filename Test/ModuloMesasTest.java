package Test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Clases.Mesa;
import Clases.Mesa.Estados;
import Clases.ModuloMesa;
import Excepciones.MesaNoDisponibleExcepcion;
import Excepciones.MesaYaDisponibleExcepcion;
import Excepciones.MesasYaGeneradasExcepcion;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModuloMesasTest {

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
	public void c_ModuloMesa_CantidadDeMesas() throws MesasYaGeneradasExcepcion {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		assertEquals(10,moduloMesa.getCantidadMesas());
	}
		
	@Test
	public void d_ModuloMesa_OcuparMesa() throws MesaNoDisponibleExcepcion { // la mesa 5 quedo ocupada
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.ocuparMesa(5);
		assertEquals(Estados.Ocupada, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test(expected = MesaNoDisponibleExcepcion.class) // la mesa 6 quedo cerrada
	public void e_ModuloMesa_OcuparMesaEnEstadoCerrada() throws MesaNoDisponibleExcepcion {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.cerrarMesa(6);
		moduloMesa.ocuparMesa(6);
	}
	
	@Test
	public void f_ModuloMesa_CerrarMesa() throws MesaNoDisponibleExcepcion { // la mesa 7 quedo cerrada
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.cerrarMesa(7);
		assertEquals(Estados.Cerrada, moduloMesa.getMesa(7).getEstado());
	}
	
	@Test(expected = MesaNoDisponibleExcepcion.class) // la mesa 5 sigue ocupada
	public void g_ModuloMesa_CerrarMesaEnEstadoNoDisponible() throws MesaNoDisponibleExcepcion {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.cerrarMesa(5);
	}
	
	@Test // la mesa 7 paso a disponible
	public void h_ModuloMesa_PasarADisponibleEstandoCerrada() throws MesaYaDisponibleExcepcion {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.pasarMesaADisponible(7);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(7).getEstado());
	}
	
	@Test // la mesa 5 paso a disponible
	public void i_ModuloMesa_PasarADisponibleEstandoOcupada() throws MesaYaDisponibleExcepcion {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test // la mesa 5 paso a disponible
	public void j_ModuloMesa_PasarADisponibleEstandoOcupada() throws MesaYaDisponibleExcepcion {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}
	
	


}
