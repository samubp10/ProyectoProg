package aplicaciones;

import java.util.List;

import controladores.ControladorUsuario;

import entidades.Usuario;

public class PruebaControladorUsuario {
	public static void main(String[] args) {
		ControladorUsuario cu = new ControladorUsuario();

		// Se obtienen todas las instancias
		List<Usuario> listaUsuarios = cu.buscarTodosUsuarios();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (Usuario u : listaUsuarios) {
			System.out.println(u);
		}

//		// Se obtiene una entidad
//		System.out.println("Buscar usuarios llamados Elisa ------------ ");
		Usuario aux = cu.buscarPorNombre("Elisa");
		System.out.println(aux);
//
//		// Creación de una entidad 
		Usuario u = new Usuario();
		u.setApellido("Gutierraz");
		u.setContrasena("09sfw8fsdf7sef");
		u.setMesesSuscrito(1);
		u.setNickname("Jozelito120");
		u.setNombre("Jose");
		cu.crearUsuario(u); // Si está creada lanzará una excepción
//
//		// Se obtienen todas las instancias
		listaUsuarios = cu.buscarTodosUsuarios();
		System.out.println(
				"--------------------------------------------------Todas las entidades después de crear una -------------------------------------------------- ");
		listaUsuarios.forEach(System.out::println);

		// Se modifica 3 usuarios
		Usuario usuarioModificar = cu.buscarPorPK(1);
		if (usuarioModificar != null) {
			usuarioModificar.setContrasena("awe");
			cu.modificarUsuario(usuarioModificar);
		}
		Usuario usuarioModificar2 = cu.buscarPorPK(2);
		if (usuarioModificar2 != null) {
			usuarioModificar2.setContrasena("dsf66");
			cu.modificarUsuario(usuarioModificar2);
		}
		Usuario usuarioModificar3 = cu.buscarPorPK(3);
		if (usuarioModificar3 != null) {
			usuarioModificar3.setContrasena("fghf09");
			cu.modificarUsuario(usuarioModificar3);
		}

		imprimirEntidades(cu);

		// Borrado del usuario llamado Jose
		Usuario usuarioABorrar = cu.buscarPorNombre("Jose");
		cu.borrarUsuario(usuarioABorrar);

		imprimirEntidades(cu);

		// Se obtienen todas las instancias cuyo nickname sea ElisaCas1
		listaUsuarios = cu.buscarPorNickName("ElisaCas1");
		System.out.println("Todas las entidades ElisaCas1 ------------ ");
		listaUsuarios.forEach(System.out::println);
	}

	// Método el cuál sirve para imprimir todas las entidades del controlador de
	// serie
	private static void imprimirEntidades(ControladorUsuario cu) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
		// Al obtener un cliente, se obtiene también la tarjeta asociada gracias
		// a la bidireccionalidad
		for (Usuario u : cu.buscarTodosUsuarios()) {
			System.out.println(u);
		}
	}

}
