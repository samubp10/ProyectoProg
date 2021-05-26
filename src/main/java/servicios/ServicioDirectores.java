package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorDirectores;
import entidades.Directores;

public class ServicioDirectores {
	public static Directores modificarDatosDirectores(int id) {

		ControladorDirectores controladorDirectores = new ControladorDirectores();

		Directores director = new Directores();

		String apellidos = "";
		String nombre = "";
		String contenido = "";
		String premios = "";

		director = controladorDirectores.buscarPorPK(id);
		
		apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del director");
		premios = JOptionPane.showInputDialog("Ingrese los premios que ha ganado el director");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del director");
		contenido = JOptionPane.showInputDialog("Ingrese el código de contenido que este director a dirigido");

		if (!apellidos.equalsIgnoreCase("")) {
			director.setApellido(apellidos);
		}

		if (!contenido.equalsIgnoreCase("")) {
			director.setPremios(premios);
		}

		if (!premios.equalsIgnoreCase("")) {
			director.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			director.setNombre(nombre);
		}

		return director;
	}

	public static Directores insertarDatosDirectores() {

		ControladorDirectores controladorDirectores = new ControladorDirectores();

		Directores director = new Directores();

		String apellidos = "";
		String nombre = "";
		String contenido = "";
		String premios = "";

		apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del director");
		premios = JOptionPane.showInputDialog("Ingrese los premios que ha ganado el director");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del director");
		contenido = JOptionPane.showInputDialog("Ingrese el código de contenido que este director a dirigido");

		if (!apellidos.equalsIgnoreCase("")) {
			director.setApellido(apellidos);
		}

		if (!contenido.equalsIgnoreCase("")) {
			director.setPremios(premios);
		}

		if (!premios.equalsIgnoreCase("")) {
			director.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			director.setNombre(nombre);
		}

		return director;
	}
}
