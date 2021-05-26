package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorContenido;
import controladores.ControladorPelicula;
import entidades.Contenidos;
import entidades.Pelicula;

public class ServicioPeliculas {
	public static Pelicula modificarDatosPeliculas(int id) {

		ControladorPelicula controladorPeliculas = new ControladorPelicula();

		Pelicula pelicula = new Pelicula();

		pelicula = controladorPeliculas.buscarPorPK(id);

		ControladorContenido controladorContenido = new ControladorContenido();
		
		Contenidos contenido = new Contenidos();
		
		String duracion = "";
		String nombre = "";
		String premios = "";
		String contenidoID = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion de la película");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la película");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la película");
		contenidoID = JOptionPane.showInputDialog("Ingrese el contenido con el que se relaciona enta pelicula");

		if (!duracion.equalsIgnoreCase("")) {
			pelicula.setDuracion(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			pelicula.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			pelicula.setNombre(nombre);
		}

		if (!contenidoID.equalsIgnoreCase("")) {
			pelicula.setContenido(controladorContenido.buscarPorPK(Integer.parseInt(contenidoID)));
		}

		return pelicula;
	}

	public static Pelicula insertarDatosPeliculas() {

		ControladorPelicula controladorPeliculas = new ControladorPelicula();

		Pelicula pelicula = new Pelicula();
ControladorContenido controladorContenido = new ControladorContenido();
		
		Contenidos contenido = new Contenidos();
		
		String duracion = "";
		String nombre = "";
		String premios = "";
		String contenidoID = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion de la película");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la película");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la película");
		contenidoID = JOptionPane.showInputDialog("Ingrese el contenido con el que se relaciona enta pelicula");

		if (!duracion.equalsIgnoreCase("")) {
			pelicula.setDuracion(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			pelicula.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			pelicula.setNombre(nombre);
		}

		if (!contenidoID.equalsIgnoreCase("")) {
			pelicula.setContenido(controladorContenido.buscarPorPK(Integer.parseInt(contenidoID)));
		}

		return pelicula;
	}

}
