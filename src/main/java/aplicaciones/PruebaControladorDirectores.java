package aplicaciones;

import java.util.ArrayList;
import java.util.List;
import controladores.ControladorContenido;
import controladores.ControladorDirectores;
import entidades.Contenidos;
import entidades.Directores;

public class PruebaControladorDirectores {
	public static void main(String[] args) {
		ControladorDirectores cd = new ControladorDirectores();

		// Se obtienen todas las instancias
		List<Directores> listaDirectores = cd.buscarTodosDirectores();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (Directores d : listaDirectores) {
			System.out.println(d);
		}

		// Se obtiene una entidad
		System.out.println("Buscar usuarios llamados Elisa ------------ ");
		Directores director = cd.buscarPorNombre("Elisa");
		System.out.println(director);

		// Creo un objeto de tipo contenido en el que voy a meter el 
		//resultado de una query que voy a hacer gracias al 
		//controlador de tipo contenido 
		Directores d = new Directores();
		ControladorContenido cc = new ControladorContenido();
		Contenidos contenido1 = new Contenidos();
		List<Contenidos> contenidos = new ArrayList<Contenidos>();
		contenidos.add(contenido1);
		contenido1 = cc.buscarPorPK(1);
		d.setApellido("Piedra");
		d.setContenido("Lalaland");
		d.setNombre("Pepes");
		d.setPremios("Ninguno");

		// Meto el director en la BBDD
		cd.crearDirector(d); // Si está creada lanzará una excepción

		imprimirEntidades(cd);

		// Se modifican 3 directores
		Directores directorModificar = cd.buscarPorPK(1);
		if (directorModificar != null) {
			directorModificar.setApellido("García");
			cd.modificarDirector(directorModificar);
		}
		Directores directorModificar2 = cd.buscarPorPK(2);
		if (directorModificar2 != null) {
			directorModificar2.setNombre("Fernando");
			cd.modificarDirector(directorModificar2);
		}
		Directores directorModificar3 = cd.buscarPorPK(3);
		if (directorModificar3 != null) {
			directorModificar3.setPremios("Ninguno en realidad");
			cd.modificarDirector(directorModificar3);
		}

		imprimirEntidades(cd);

		// Borrado del Directores llamado Jose
		Directores directorABorrar = cd.buscarPorNombre("Pepes");
		cd.borrarDirector(directorABorrar);
//

		imprimirEntidades(cd);

		// Se obtienen todas las instancias cuyo nickname sea ElisaCas1
		listaDirectores = cd.buscarPorApellido("García");
		System.out.println("Todas las entidades García ------------ ");
		listaDirectores.forEach(System.out::println);
	}

	// Método el cuál sirve para imprimir todas las entidades del controlador de
	// serie
	private static void imprimirEntidades(ControladorDirectores cd) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Directores d : cd.buscarTodosDirectores()) {
			System.out.println(d);
		}
	}
}
