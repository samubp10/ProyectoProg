package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorContenido;
import controladores.ControladorDirectores;
import entidades.Contenidos;
import entidades.Directores;

public class ServicioContenidos {
	public static Contenidos modificarDatosContenidos(int id) {

		ControladorContenido controladorContenidos = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		contenido = controladorContenidos.buscarPorPK(id);

		Directores director = new Directores();

		ControladorDirectores controladorDirector = new ControladorDirectores();

		String premium = "";
		String nombre = "";
		String numeroLikes = "";
		String directores = "";

		premium = JOptionPane.showInputDialog("Ingrese si el contenido es premium (true o false)");
		numeroLikes = JOptionPane.showInputDialog("Ingrese el número de likes que tiene el contenido");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del contenido");
		directores = JOptionPane.showInputDialog("Ingrese la clave primaria del director de este contenido");

		if (!premium.equalsIgnoreCase("")) {
			contenido.setPremium(Boolean.parseBoolean(premium));
		}

		if (!numeroLikes.equalsIgnoreCase("")) {
			contenido.setNumeroLikes(Integer.parseInt(numeroLikes));
		}
		if (!nombre.equalsIgnoreCase("")) {
			contenido.setNombre(nombre);
		}

		if (!directores.equalsIgnoreCase("")) {
			contenido.setDirectore(controladorDirector.buscarPorPK(Integer.parseInt(directores)));
			;
		}

		return contenido;
	}

	public static Contenidos insertarDatosContenidos() {

		ControladorContenido controladorContenidos = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		Directores director = new Directores();

		ControladorDirectores controladorDirector = new ControladorDirectores();

		String premium = "";
		String nombre = "";
		String numeroLikes = "";
		String directores = "";

		premium = JOptionPane.showInputDialog("Ingrese si el contenido es premium (true o false)");
		numeroLikes = JOptionPane.showInputDialog("Ingrese el número de likes que tiene el contenido");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del contenido");
		directores = JOptionPane.showInputDialog("Ingrese la clave primaria del director de este contenido");

		if (!premium.equalsIgnoreCase("")) {
			contenido.setPremium(Boolean.parseBoolean(premium));
		}

		if (!numeroLikes.equalsIgnoreCase("")) {
			contenido.setNumeroLikes(Integer.parseInt(numeroLikes));
		}
		if (!nombre.equalsIgnoreCase("")) {
			contenido.setNombre(nombre);
		}

		if (!directores.equalsIgnoreCase("")) {
			contenido.setDirectore(controladorDirector.buscarPorPK(Integer.parseInt(directores)));
			;
		}

		return contenido;

	}
}
