package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controladores.ControladorContenido;
import controladores.ControladorDetalleComentan;
import controladores.ControladorDirectores;
import controladores.ControladorUsuario;
import entidades.Contenidos;
import entidades.DetalleComentan;
import entidades.DetalleComentanPK;
import entidades.Directores;
import entidades.Usuario;

public class ServicioComentarios {
	public static DetalleComentan modificarDatosComentarios() {

		String usuarioID = "";
		String contenidoID = "";

		ControladorContenido controladorContenidos = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		List<DetalleComentan> listaComentarios = new ArrayList<DetalleComentan>();

		Usuario usuario = new Usuario();

		ControladorDetalleComentan controladorComentarios = new ControladorDetalleComentan();

		DetalleComentan comentario = new DetalleComentan();

		usuarioID = JOptionPane.showInputDialog(null,
				"¿Qué comentario quiere modificar? Ingresando el código de usuario\n\n"
						+ controladorComentarios.buscarTodosLosComentarios());

		contenidoID = JOptionPane.showInputDialog(null,
				"¿Qué comentario quiere modificar? Ingresando el código de contenido\n\n"
						+ controladorComentarios.buscarPorUsuarioID(Integer.parseInt(usuarioID)));

		String cuerpoComentario = "";
		
		if (!usuarioID.equalsIgnoreCase("")) {
			listaComentarios = controladorComentarios.buscarPorUsuarioID(Integer.parseInt(usuarioID));
		}

		for (DetalleComentan c : listaComentarios) {

			if (c.getContenido().getContenidoID() == Integer.parseInt(contenidoID)) {
				comentario = c;
			}

		}

		cuerpoComentario = JOptionPane.showInputDialog("Ingrese el comentario");
		contenidoID = JOptionPane.showInputDialog("Ingrese el código del contenido");
		usuarioID = JOptionPane.showInputDialog("Ingrese el código del usuario");

		

		if (!cuerpoComentario.equalsIgnoreCase("")) {
			comentario.setComentarios(cuerpoComentario);
		}

		if (!usuarioID.equalsIgnoreCase("")) {
			comentario.setUsuario(controladorUsuario.buscarPorPK(Integer.parseInt(usuarioID)));
		}

		if (!contenidoID.equalsIgnoreCase("")) {
			comentario.setContenido(controladorContenidos.buscarPorPK(Integer.parseInt(contenidoID)));
		}

		return comentario;
	}

	public static DetalleComentan insertarDatosComentarios() {

		ControladorContenido controladorContenidos = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		Usuario usuario = new Usuario();

		ControladorDetalleComentan controladorComentarios = new ControladorDetalleComentan();

		DetalleComentan comentario = new DetalleComentan();
		
		DetalleComentanPK pkComentario = new DetalleComentanPK();

		String cuerpoComentario = "";
		String contenidoID = "";
		String usuarioID = "";

		cuerpoComentario = JOptionPane.showInputDialog("Ingrese el comentario");
		contenidoID = JOptionPane.showInputDialog("Ingrese el código del contenido");
		usuarioID = JOptionPane.showInputDialog("Ingrese el código del usuario");
		
		pkComentario.setUsuarioID(Integer.parseInt(usuarioID));

		if (!cuerpoComentario.equalsIgnoreCase("")) {
			comentario.setComentarios(cuerpoComentario);
		}

		if (!usuarioID.equalsIgnoreCase("")) {
			comentario.setUsuario(controladorUsuario.buscarPorPK(Integer.parseInt(usuarioID)));
			pkComentario.setUsuarioID(Integer.parseInt(usuarioID));
		}

		if (!contenidoID.equalsIgnoreCase("")) {
			comentario.setContenido(controladorContenidos.buscarPorPK(Integer.parseInt(contenidoID)));
			pkComentario.setContenidoID(Integer.parseInt(contenidoID));
		}
		
		comentario.setId(pkComentario);

		return comentario;
	}

	public static DetalleComentan borrarComentario(int usuarioID) {

		ControladorContenido controladorContenidos = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		List<DetalleComentan> listaComentarios = new ArrayList<DetalleComentan>();

		Usuario usuario = new Usuario();

		ControladorDetalleComentan controladorComentarios = new ControladorDetalleComentan();

		DetalleComentan comentario = new DetalleComentan();

		String contenidoID = "";

		contenidoID = JOptionPane.showInputDialog(null,
				"¿Qué comentario quiere modificar? Ingresando el código de contenido\n\n"
						+ controladorComentarios.buscarPorUsuarioID(usuarioID));
		
		if (usuarioID != 0 ) {
			listaComentarios = controladorComentarios.buscarPorUsuarioID(usuarioID);
		}

		for (DetalleComentan c : listaComentarios) {

			if (c.getContenido().getContenidoID() == Integer.parseInt(contenidoID)) {
				comentario = c;
			}

		}

		return comentario;

	}
}
