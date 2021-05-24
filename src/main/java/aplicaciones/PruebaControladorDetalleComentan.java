package aplicaciones;


import java.util.List;


import controladores.ControladorContenido;
import controladores.ControladorDetalleComentan;
import controladores.ControladorUsuario;

import entidades.Contenidos;
import entidades.DetalleComentan;
import entidades.Usuario;

public class PruebaControladorDetalleComentan {
	public static void main(String[] args) {
		ControladorDetalleComentan cdc = new ControladorDetalleComentan();

		// Se obtienen todas las instancias
		List<DetalleComentan> listaComentarios = cdc.buscarTodosLosComentarios();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (DetalleComentan c : listaComentarios) {
			System.out.println(c);
		}

//		// Se obtiene una entidad
		System.out.println("Buscar los comentarios hechos en el contenido 5  ------------ ");
		DetalleComentan comentario1 = cdc.buscarPorUsuarioID(2).get(0);
		System.out.println(comentario1);

		// Creo un objeto de tipo controlador de contenido
		ControladorUsuario cu = new ControladorUsuario();
		//Creo un objeto de tipo usuario t meto el resltado de la búsqueda por la pk
		Usuario usuario1 = new Usuario();
		usuario1 = cu.buscarPorPK(4);
		
		//Creo un objeto de tipo controlador del contenido
		ControladorContenido ccontenido = new ControladorContenido();
		
		//Creo un objet de tipo contenido y meto el resultado de buscar el usuario por la pk
		Contenidos contenido1 = new Contenidos();
		contenido1 = ccontenido.buscarPorPK(8);
		
		//Creo un objeto de tipo DetalleComentan y meto todos los valores y finalmente 
		//Meto el objeto en la base de datos
		DetalleComentan comentario = new DetalleComentan();
		comentario.setUsuario(usuario1);
		comentario.setContenido(contenido1);
		comentario.setComentarios("Buena película aunque mal final");
		cdc.crearComentario(comentario); // Si está creada lanzará una excepción

		imprimirEntidades(cdc);

		// Se modifica la contraseña del Contenidos 1
		DetalleComentan comentarioModificar = cdc.buscarPorUsuarioID(3).get(0);
		if (comentarioModificar != null) {
			comentarioModificar.setComentarios("Mala Película, con buen final");;
			cdc.modificarComentario(comentarioModificar);
		}

		imprimirEntidades(cdc);
		
//		// Borrado los comentarios del usuario 3
		DetalleComentan comentarioABorrar = cdc.buscarPorUsuarioID(2).get(0);
		cdc.borrarComentario(comentarioABorrar);
//
//		// Se obtienen todas las instancias
		imprimirEntidades(cdc);

	}
	
	// Método el cuál sirve para imprimir todas las entidades del controlador de
		// serie
		private static void imprimirEntidades(ControladorDetalleComentan cdc) {
			System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
			// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
			// a la bidireccionalidad
			for (DetalleComentan dc : cdc.buscarTodosLosComentarios()) {
				System.out.println(dc);
			}
		}

}
