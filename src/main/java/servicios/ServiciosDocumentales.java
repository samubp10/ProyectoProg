package servicios;

import javax.swing.JOptionPane;

import controladores.ControladorContenido;
import controladores.ControladorDocumental;
import entidades.Contenidos;
import entidades.Documental;

public class ServiciosDocumentales {
	public static Documental modificarDatosDocumentales(int id) {

		ControladorDocumental controladorDocumental = new ControladorDocumental();

		Documental documental = new Documental();
		
		documental = controladorDocumental.buscarPorPK(id);

		ControladorContenido controladorContenido = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		String duracion = "";
		String nombre = "";
		String premios = "";
		String contenidoID = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion del documental");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene el documental");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del documental");
		contenidoID = JOptionPane.showInputDialog("Ingrese el contenido con el que se relaciona este documental");

		if (!duracion.equalsIgnoreCase("")) {
			documental.setDuracion(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			documental.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			documental.setNombre(nombre);
		}
		
		if (!contenidoID.equalsIgnoreCase("")) {
			documental.setContenido(controladorContenido.buscarPorPK(Integer.parseInt(contenidoID)));
		}

		return documental;
	}

	public static Documental insertarDatosDocumentales() {

		Documental documental = new Documental();

		ControladorContenido controladorContenido = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		String duracion = "";
		String nombre = "";
		String premios = "";
		String contenidoID = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion del documental");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene el documental");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del documental");
		contenidoID = JOptionPane.showInputDialog("Ingrese el contenido con el que se relaciona este documental");

		if (!duracion.equalsIgnoreCase("")) {
			documental.setDuracion(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			documental.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			documental.setNombre(nombre);
		}
		
		if (!contenidoID.equalsIgnoreCase("")) {
			documental.setContenido(controladorContenido.buscarPorPK(Integer.parseInt(contenidoID)));
		}

		return documental;
	}

}
