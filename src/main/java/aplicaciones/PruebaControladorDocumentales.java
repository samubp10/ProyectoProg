package aplicaciones;


import java.util.List;
import controladores.ControladorContenido;
import controladores.ControladorDocumental;

import entidades.Contenidos;
import entidades.Documental;

public class PruebaControladorDocumentales {
	public static void main(String[] args) {
	ControladorDocumental cd = new ControladorDocumental();

	// Se obtienen todas las instancias
	List<Documental> listaDocumentales = cd.buscarTodosDocumentales();

	imprimirEntidades(cd);

//	// Se obtiene una entidad
	System.out.println("Buscar usuarios llamados Elisa ------------ ");
	Documental Documental = cd.buscarPorPK(1);
	System.out.println(Documental);
//
//	// Creación de una entidad 

	ControladorContenido cc = new ControladorContenido();
	Contenidos contenidos = cc.buscarPorPK(1);
	Documental documental = new Documental();
	documental.setContenido(contenidos);
	documental.setDuracion(198);;
	documental.setNombre("El ultimo baile");;
	documental.setPremios("Ninguno");;
	cd.crearDocumental(documental); // Si está creada lanzará una excepción

	imprimirEntidades(cd);

	// Se modifica la contraseña del Documental 1
	Documental contenidoModificar = cd.buscarPorPK(1);
	if (contenidoModificar != null) {
		contenidoModificar.setNombre("Lalaland");;
		cd.modificarDocumental(contenidoModificar);
	}
//

	imprimirEntidades(cd);
	
//
//	// Borrado del Documental llamado Jose
	Documental contenidoABorrar = cd.buscarPorPK(2);
	cd.borrarDocumental(contenidoABorrar);
//
//	// Se obtienen todas las instancias
	listaDocumentales = cd.buscarTodosDocumentalesPorDuracion(196);
	System.out.println("Todas las entidades después de borrar una ------------ ");
	listaDocumentales.forEach(System.out::println);


}
	private static void imprimirEntidades(ControladorDocumental cd) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Documental d : cd.buscarTodosDocumentales()) {
			System.out.println(d);
		}
	}
}
