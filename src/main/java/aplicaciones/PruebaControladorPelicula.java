package aplicaciones;

import java.util.List;

import controladores.ControladorContenido;
import controladores.ControladorPelicula;
import entidades.Contenidos;
import entidades.Pelicula;

public class PruebaControladorPelicula {
	public static void main(String[] args) {
		ControladorPelicula cp = new ControladorPelicula();

		// Se obtienen todas las instancias
		List<Pelicula> listaPeliculas = cp.buscarTodasPeliculas();

		imprimirEntidades(cp);

		// Se obtiene una entidad
		System.out.println("Buscar usuarios llamados Elisa ------------ ");
		Pelicula Pelicula = cp.buscarPorPK(1);
		System.out.println(Pelicula);

		// Creación de una entidad

		// Creo un controlador de contenido, con el que voy a meter el resultado de una
		// query en un objeto de tipo Contenidos
		ControladorContenido cc = new ControladorContenido();
		Contenidos contenidos = cc.buscarPorPK(1);
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setContenido(contenidos);
		pelicula1.setDuracion(198);
		pelicula1.setNombre("El ultimo baile");
		pelicula1.setPremios("Ninguno");

		// Meto la película anterior en la bbdd
		cp.crearPelicula(pelicula1); // Si está creada lanzará una excepción

		imprimirEntidades(cp);

		// Se modifica la contraseña del Pelicula 1
		Pelicula peliculaModificar = cp.buscarPorPK(1);
		if (peliculaModificar != null) {
			peliculaModificar.setNombre("Lalaland");
			cp.modificarPelicula(peliculaModificar);
		}

		// Se obtienen todas las instancias
		listaPeliculas = cp.buscarTodasPeliculas();
		System.out.println("Todas las entidades después de modificar una ------------ ");
		listaPeliculas.forEach(System.out::println);
		//
		// Borrado del Pelicula llamado Jose
		Pelicula peliculaABorrar = cp.buscarPorPK(2);
		cp.borrarPelicula(peliculaABorrar);

		imprimirEntidades(cp);

	}

	// Método el cuál sirve para imprimir todas las entidades del controlador de
	// serie
	private static void imprimirEntidades(ControladorPelicula cp) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Pelicula d : cp.buscarTodasPeliculas()) {
			System.out.println(d);
		}
	}
}
