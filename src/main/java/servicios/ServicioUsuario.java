package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorUsuario;
import entidades.Usuario;

public class ServicioUsuario {
	public static Usuario ModificarDatosUsuario(int id) {

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		Usuario usuario = new Usuario();

		
		String apellido = "";
		String contrasena = "";
		String nickname = "";
		String nombre = "";
		String meseSuscrito = "";
		
		usuario = controladorUsuario.buscarPorPK(id);

		apellido = JOptionPane.showInputDialog("Ingrese un nuevo apellido");
		contrasena = JOptionPane.showInputDialog("Ingrese una nueva contraseña");
		nickname = JOptionPane.showInputDialog("Ingrese un nuevo nickname");
		nombre = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
		meseSuscrito = JOptionPane.showInputDialog("Ingrese los meses sucrito que lleva");

		if (!apellido.equalsIgnoreCase("")) {
			usuario.setApellido(apellido);
		}

		if (!contrasena.equalsIgnoreCase("")) {
			usuario.setContrasena(contrasena);
		}
		if (!nickname.equalsIgnoreCase("")) {
			usuario.setNickname(nickname);
		}
		if (!nombre.equalsIgnoreCase("")) {
			usuario.setNombre(nombre);
		}
		if (!meseSuscrito.equalsIgnoreCase("")) {
			usuario.setMesesSuscrito(Integer.parseInt(meseSuscrito));
		}

		return usuario;
	}
	
	public static Usuario InsertarDatosUsuario() {

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		Usuario usuario = new Usuario();

		
		String apellido = "";
		String contrasena = "";
		String nickname = "";
		String nombre = "";
		String meseSuscrito = "";

		apellido = JOptionPane.showInputDialog("Ingrese un nuevo apellido");
		contrasena = JOptionPane.showInputDialog("Ingrese una nueva contraseña");
		nickname = JOptionPane.showInputDialog("Ingrese un nuevo nickname");
		nombre = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
		meseSuscrito = JOptionPane.showInputDialog("Ingrese los meses sucrito que lleva");

		if (!apellido.equalsIgnoreCase("")) {
			usuario.setApellido(apellido);
		}

		if (!contrasena.equalsIgnoreCase("")) {
			usuario.setContrasena(contrasena);
		}
		if (!nickname.equalsIgnoreCase("")) {
			usuario.setNickname(nickname);
		}
		if (!nombre.equalsIgnoreCase("")) {
			usuario.setNombre(nombre);
		}
		if (!meseSuscrito.equalsIgnoreCase("")) {
			usuario.setMesesSuscrito(Integer.parseInt(meseSuscrito));
		}

		return usuario;
	}
}
