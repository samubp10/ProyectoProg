package aplicaciones;

import java.util.ArrayList;
import java.util.List;

import controladores.ControladorActriz;
import controladores.ControladorContenido;
import controladores.ControladorDirectores;
import entidades.Actrices;
import entidades.Contenidos;
import entidades.Directores;

public class PruebaControladorContenido {

	public static void main(String[] args) {
		ControladorContenido cc = new ControladorContenido();

		// Se obtienen todas las instancias
		List<Contenidos> listaContenidos = cc.buscarTodosLosContenidos();

		imprimirEntidades(cc);

//		// Se obtiene una entidad
		System.out.println("Buscar usuarios llamados Elisa ------------ ");
		Contenidos contenidos = cc.buscarPorPK(1);
		System.out.println(contenidos);
//
//		// Creación de una entidad 
		ControladorActriz ca = new ControladorActriz();
		List<Actrices> actrices = new ArrayList<Actrices>();
		Actrices actriz = new Actrices();
		actriz = ca.buscarPorPK(1);
		actrices.add(actriz);

		// Creo un objeto de tipo contenido
		Contenidos c = new Contenidos();
		// Creo un objeto de tipo controlador de cirectores
		ControladorDirectores cd = new ControladorDirectores();
		// Creo un objeto de tipo director
		Directores director1 = new Directores();
		// Meto dentro del objeto director el resultado de la búsqueda por pk
		director1 = cd.buscarPorPK(1);
		c.setActrices(actrices);
		c.setDirectore(director1);
		c.setNombre("Jozelito120");
		c.setNumeroLikes(510);
		c.setPremium(true);
		cc.crearContenido(c); // Si está creada lanzará una excepción

		imprimirEntidades(cc);

		// Se modifica el nombre del Contenidos 1
		Contenidos contenidoModificar = cc.buscarPorPK(1);
		if (contenidoModificar != null) {
			contenidoModificar.setNombre("Lalaland");
			cc.modificarContenido(contenidoModificar);
		}

		imprimirEntidades(cc);

		// Borrado del Contenidos llamado Jose
		Contenidos contenidoABorrar = cc.buscarPorPK(6);
		cc.borrarContenido(contenidoABorrar);

		imprimirEntidades(cc);

	}

	// Método el cuál sirve para imprimir todas las entidades del controlador de
	// serie
	private static void imprimirEntidades(ControladorContenido cc) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Contenidos c : cc.buscarTodosLosContenidos()) {
			System.out.println(c);
		}
	}

}
