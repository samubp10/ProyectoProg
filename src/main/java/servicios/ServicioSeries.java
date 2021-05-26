package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorContenido;
import controladores.ControladorSerie;
import entidades.Contenidos;
import entidades.Serie;

public class ServicioSeries {
	public static Serie modificarDatosSerie(int id) {

		ControladorSerie controladorSerie = new ControladorSerie();

		Serie serie = new Serie();

		serie = controladorSerie.buscarPorPK(id);
		
		ControladorContenido controladorContenido = new ControladorContenido();

		Contenidos contenido = new Contenidos();
		
		String duracion = "";
		String nombre = "";
		String premios = "";
		String episodios = "";
		String temporadas = "";
		String contenidoID = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion media de los capítulos de la serie");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la película");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la serie");
		episodios = JOptionPane.showInputDialog("Ingrese cuantos episodios tiene la serie");
		temporadas = JOptionPane.showInputDialog("Ingrese cuantas temporadas tiene la serie");
		contenidoID = JOptionPane.showInputDialog("Ingrese el contenido con el que se relaciona esta serie");

		if (!duracion.equalsIgnoreCase("")) {
			serie.setDuracion(Integer.parseInt(duracion));
		}

		if (!episodios.equalsIgnoreCase("")) {
			serie.setEpisodios(Integer.parseInt(duracion));
		}

		if (!temporadas.equalsIgnoreCase("")) {
			serie.setTemporadas(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			serie.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			serie.setNombre(nombre);
		}
		
		if (!contenidoID.equalsIgnoreCase("")) {
			serie.setContenido(controladorContenido.buscarPorPK(Integer.parseInt(contenidoID)));
		}


		return serie;
	}
	
	public static Serie insertarDatosSerie() {

		Serie serie = new Serie();


		ControladorContenido controladorContenido = new ControladorContenido();

		Contenidos contenido = new Contenidos();
		
		String duracion = "";
		String nombre = "";
		String premios = "";
		String episodios = "";
		String temporadas = "";
		String contenidoID = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion media de los capítulos de la serie");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la película");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la serie");
		episodios = JOptionPane.showInputDialog("Ingrese cuantos episodios tiene la serie");
		temporadas = JOptionPane.showInputDialog("Ingrese cuantas temporadas tiene la serie");
		contenidoID = JOptionPane.showInputDialog("Ingrese el contenido con el que se relaciona esta serie");

		if (!duracion.equalsIgnoreCase("")) {
			serie.setDuracion(Integer.parseInt(duracion));
		}

		if (!episodios.equalsIgnoreCase("")) {
			serie.setEpisodios(Integer.parseInt(duracion));
		}

		if (!temporadas.equalsIgnoreCase("")) {
			serie.setTemporadas(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			serie.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			serie.setNombre(nombre);
		}
		
		if (!contenidoID.equalsIgnoreCase("")) {
			serie.setContenido(controladorContenido.buscarPorPK(Integer.parseInt(contenidoID)));
		}


		return serie;
	}
}
