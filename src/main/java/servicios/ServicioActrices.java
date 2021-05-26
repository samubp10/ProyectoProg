package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorActriz;
import entidades.Actrices;

public class ServicioActrices {
	public static Actrices ModificarDatosActriz(int id) {

		ControladorActriz controladorActrices = new ControladorActriz();

		Actrices actriz = new Actrices();
		
		actriz = controladorActrices.buscarPorPK(id);

		String apellido = "";
		String nombre = "";
		String pelicula = "";
		String premios = "";

		apellido = JOptionPane.showInputDialog("Ingrese un nuevo apellido");
		nombre = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
		pelicula = JOptionPane.showInputDialog("Ingrese una nueva pelicula");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la actriz/actor");

		if (!apellido.equalsIgnoreCase("")) {
			actriz.setApellido(apellido);
		}

		if (!nombre.equalsIgnoreCase("")) {
			actriz.setNombre(nombre);
		}
		if (!pelicula.equalsIgnoreCase("")) {
			actriz.setPelicula(pelicula);
		}
		if (!premios.equalsIgnoreCase("")) {
			actriz.setPremios(premios);
		}

		return actriz;

	}
	
	public static Actrices InsertarDatosActriz() {

		ControladorActriz controladorActrices = new ControladorActriz();

		Actrices actriz = new Actrices();

		String apellido = "";
		String nombre = "";
		String pelicula = "";
		String premios = "";

		apellido = JOptionPane.showInputDialog("Ingrese un nuevo apellido");
		nombre = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
		pelicula = JOptionPane.showInputDialog("Ingrese una nueva pelicula");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la actriz/actor");

		if (!apellido.equalsIgnoreCase("")) {
			actriz.setApellido(apellido);
		}

		if (!nombre.equalsIgnoreCase("")) {
			actriz.setNombre(nombre);
		}
		if (!pelicula.equalsIgnoreCase("")) {
			actriz.setPelicula(pelicula);
		}
		if (!premios.equalsIgnoreCase("")) {
			actriz.setPremios(premios);
		}

		return actriz;

	}
	
}
