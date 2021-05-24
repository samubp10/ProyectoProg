package aplicaciones;

import java.util.List;

import controladores.ControladorActriz;

import entidades.Actrices;

public class PruebaControladorActriz {
	public static void main(String[] args) {

		// Creo un objeto de tipo controlador de serie
		ControladorActriz ca = new ControladorActriz();

		// Meto todos los datos de la tabla de actrices en una lista de actrices
		List<Actrices> listaActrices = ca.buscarTodasLasActrices();

		// Se imprime la lista
		imprimirEntidades(ca);

		// Obtengo a una entidad buscándola por su nombre
		Actrices actriz = ca.buscarPorNombre("Leonardo");
		System.out.println(actriz);

		// Creación de una entidad
		Actrices a = new Actrices();
		// Introduzco los valores del objeto que después serán columnas de la base de
		// datos
		a.setApellido("Gutierraz");
		a.setNombre("Angelina");
		a.setPelicula("Just Cause");
		a.setPremios("Ninguno");

		ca.crearActriz(a); // Si está creada lanzará una excepción
		// Creo una serie con el objeto anterior y con el controlador
		// Se obtienen todas las instancias

		imprimirEntidades(ca);

		// Se modifica el apellido de la actriz 1
		Actrices actrizModificar = ca.buscarPorPK(1);
		if (actrizModificar != null) {
			actrizModificar.setApellido("García");
			ca.modificarActriz(actrizModificar);
		}
		Actrices actrizModificar2 = ca.buscarPorPK(2);
		if (actrizModificar2 != null) {
			actrizModificar2.setPremios("Ninguno");
			ca.modificarActriz(actrizModificar2);
		}
		Actrices actrizModificar3 = ca.buscarPorPK(3);
		if (actrizModificar3 != null) {
			actrizModificar3.setPelicula("The Last Dance");
			ca.modificarActriz(actrizModificar3);
		}
//
//		// Se obtienen todas las instancias
		imprimirEntidades(ca);
//
//		// Borrado del usuario llamado Jose
		Actrices actrizABorrar = ca.buscarPorNombre("jewel");
		ca.borrarActriz(actrizABorrar);
//
//		// Se obtienen todas las instancias
		imprimirEntidades(ca);
//
//		// Se obtienen todas las instancias cuyo nickname sea ElisaCas1
		listaActrices = ca.buscarPorApellido("Electra");
		System.out.println("Todas las actrices con el apellido Electra ------------ ");
		listaActrices.forEach(System.out::println);
	}

	// Método el cuál sirve para imprimir todas las entidades del controlador de
	// serie
	private static void imprimirEntidades(ControladorActriz ca) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Actrices a : ca.buscarTodasLasActrices()) {
			System.out.println(a);
		}
	}
}
