package aplicaciones;

import java.util.List;

import controladores.ControladorContenido;
import controladores.ControladorSerie;
import entidades.Contenidos;
import entidades.Serie;

public class PruebaControladorSerie {

	public static void main(String[] args) {

		// Creo un objeto de tipo controlador de serie
		ControladorSerie cs = new ControladorSerie();

		// Se obtienen todas las instancias de la tabla de Series
		List<Serie> listaSeries = cs.buscarTodasSeries();

		// Se imprime la lista
		imprimirEntidades(cs);

		// Se obtiene una entidad
		System.out.println("Buscar la serie un el pk 1 ------------ ");
		Serie serie = cs.buscarPorPK(1);
		System.out.println(serie);

		// Creo un objeto de tipo controlador de contenido
		ControladorContenido cc = new ControladorContenido();
		// Creo un objeto de tipo contenido y gracias al controlador busco el contenido
		// que tiene como pk 1 y lo meto en el objeto de tipo contenido
		Contenidos contenidos = cc.buscarPorPK(1);
		// Creo un objeto de tipo serie y lo instancio
		Serie serie1 = new Serie();
		// Introduzco los valores del objeto que después serán columnas de la base de
		// datos
		serie1.setContenido(contenidos);
		serie1.setDuracion(198);
		serie1.setEpisodios(15);
		serie1.setTemporadas(20);
		serie1.setNombre("El ultimo baile");
		serie1.setPremios("Ninguno");

		// Creo una serie con el objeto anterior y con el controlador
		cs.crearSerie(serie1); // Si está creada lanzará una excepción

		// Se obtienen todas las instancias
		imprimirEntidades(cs);

		// Se modifica el nombre de la serie con el pk 1
		Serie serieModificar = cs.buscarPorPK(1);
		if (serieModificar != null) {
			serieModificar.setNombre("Lalaland");
			;
			cs.modificarSerie(serieModificar);
		}

		// Se obtienen todas las instancias
		imprimirEntidades(cs);;

		// Borrado del Pelicula llamado Jose
		Serie serieABorrar = cs.buscarPorPK(2);
		cs.borrarSerie(serieABorrar);

		// Se obtienen todas las instancias
		imprimirEntidades(cs);

	}

	//Método el cuál sirve para imprimir todas las entidades del controlador de serie
	private static void imprimirEntidades(ControladorSerie cs) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Serie s : cs.buscarTodasSeries()) {
			System.out.println(s);
		}
	}
}
