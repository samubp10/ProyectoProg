package appfinal;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.util.List;

import controladores.ControladorActriz;
import controladores.ControladorContenido;
import controladores.ControladorDetalleComentan;
import controladores.ControladorDirectores;
import controladores.ControladorDocumental;
import controladores.ControladorPelicula;
import controladores.ControladorSerie;
import controladores.ControladorUsuario;
import entidades.Usuario;
import entidades.Contenidos;
import entidades.Directores;
import entidades.Actrices;
import entidades.DetalleComentan;
import entidades.Documental;
import entidades.Pelicula;
import entidades.Serie;

public class Aplicacion {

	private static Actrices introducirDatosActriz(int id) {

		ControladorActriz controladorActrices = new ControladorActriz();

		Actrices actriz = new Actrices();

		if (id != 0) {
			actriz = controladorActrices.buscarPorPK(id);
		}
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

	private static Usuario introducirDatosUsuario(int id) {

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		Usuario usuario = new Usuario();

		if (id != 0) {
			usuario = controladorUsuario.buscarPorPK(id);
		}
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

	private static Pelicula introducirDatosPeliculas(int id) {

		ControladorPelicula controladorPeliculas = new ControladorPelicula();

		Pelicula pelicula = new Pelicula();

		if (id != 0) {
			pelicula = controladorPeliculas.buscarPorPK(id);
		}
		String duracion = "";
		String nombre = "";
		String premios = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion de la película");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la película");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la película");

		if (!duracion.equalsIgnoreCase("")) {
			pelicula.setDuracion(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			pelicula.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			pelicula.setNombre(nombre);
		}

		return pelicula;
	}

	private static Serie introducirDatosSerie(int id) {

		ControladorSerie controladorSerie = new ControladorSerie();

		Serie serie = new Serie();

		if (id != 0) {
			serie = controladorSerie.buscarPorPK(id);
		}
		String duracion = "";
		String nombre = "";
		String premios = "";
		String episodios = "";
		String temporadas = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion media de los capítulos de la serie");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene la película");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la serie");
		episodios = JOptionPane.showInputDialog("Ingrese cuantos episodios tiene la serie");
		temporadas = JOptionPane.showInputDialog("Ingrese cuantas temporadas tiene la serie");

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

		return serie;
	}

	private static Documental introducirDatosDocumentales(int id) {

		ControladorDocumental controladorDocumental = new ControladorDocumental();

		Documental documental = new Documental();

		if (id != 0) {
			documental = controladorDocumental.buscarPorPK(id);
		}
		String duracion = "";
		String nombre = "";
		String premios = "";

		duracion = JOptionPane.showInputDialog("Ingrese la duracion del documental");
		premios = JOptionPane.showInputDialog("Ingrese los premios que tiene el documental");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del documental");

		if (!duracion.equalsIgnoreCase("")) {
			documental.setDuracion(Integer.parseInt(duracion));
		}

		if (!premios.equalsIgnoreCase("")) {
			documental.setPremios(premios);
		}
		if (!nombre.equalsIgnoreCase("")) {
			documental.setNombre(nombre);
		}

		return documental;
	}

	private static Contenidos introducirDatosContenidos(int id) {

		ControladorContenido controladorContenidos = new ControladorContenido();

		Contenidos contenido = new Contenidos();

		if (id != 0) {
			contenido = controladorContenidos.buscarPorPK(id);
		}
		String premium = "";
		String nombre = "";
		String numeroLikes  ="";

		premium = JOptionPane.showInputDialog("Ingrese si el contenido es premium (true o false)");
		numeroLikes = JOptionPane.showInputDialog("Ingrese el número de likes que tiene el contenido");
		nombre = JOptionPane.showInputDialog("Ingrese el nombre del contenido");

		if (!premium.equalsIgnoreCase("")) {
			contenido.setPremium(Boolean.parseBoolean(premium));
		}

		if (!numeroLikes.equalsIgnoreCase("")) {
			contenido.setNumeroLikes(Integer.parseInt(numeroLikes));
		}
		if (!nombre.equalsIgnoreCase("")) {
			contenido.setNombre(nombre);
		}

		return contenido;
	}
	
	private static int opcionesCRUD() {

		int seleccion = 0;

		seleccion = JOptionPane.showOptionDialog(null, "¿Qué es lo que quiere hacer?", "BPFILMS",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
				new Object[] { "Modificar datos", "Añadir datos", "Borrar datos", "Buscar datos", "Salir" },
				"opcion 1");

		return seleccion;

	}

	public static void main(String[] args) {

		ControladorUsuario controladorUsuario = new ControladorUsuario();

		ControladorActriz controladorActriz = new ControladorActriz();
		ControladorSerie controladorSerie = new ControladorSerie();
		ControladorPelicula controladorPelicula = new ControladorPelicula();
		ControladorDocumental controladorDocumental = new ControladorDocumental();
		ControladorDirectores controladorDirector = new ControladorDirectores();
		ControladorDetalleComentan controladorDetalleComentan = new ControladorDetalleComentan();
		ControladorContenido controladorContenidos = new ControladorContenido();

		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		Usuario usuario = new Usuario();

		int seleccion = 0;

		JOptionPane.showMessageDialog(null, "Bienvenido a la base de datos de BPFILMS", "BPFILMS",
				JOptionPane.INFORMATION_MESSAGE);

		do {

			seleccion = JOptionPane.showOptionDialog(null, "¿A que tabla quiere entrar?", "BPFILMS",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																							// defecto.
					new Object[] { "Usuarios", "Series", "Peliculas", "Documentales", "Contenidos", "DetalleComentan",
							"Actrices", "Directores", "Salir" },
					"opcion 1");

			if (seleccion != -1) {
				System.out.println("seleccionada opcion " + (seleccion + 1));
			}

			switch (seleccion) {

			case 0:
				seleccion = opcionesCRUD();

				switch (seleccion) {

				case 0:

					try {
						seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
								"¿Qué usuario quiere modificar?\n\n" + controladorUsuario.buscarTodosUsuarios()));

						usuario = introducirDatosUsuario(seleccion);
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null, "No ha sido modificado ningún usuario a la base de datos");
					}
					if (usuario != null) {
						controladorUsuario.modificarUsuario(usuario);
					}

					else {
						JOptionPane.showMessageDialog(null, "Lo siento ese usuario no existe");
					}
					break;

				case 1:

					try {
						usuario = introducirDatosUsuario(0);
					} catch (NullPointerException NPE) {
						JOptionPane.showMessageDialog(null, "No ha sido añadido ningún usuario a la base de datos");
					}
					controladorUsuario.crearUsuario(usuario);

					break;

				case 2:

					seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
							"¿Qué usuario quiere borra?\n\n" + controladorUsuario.buscarTodosUsuarios()));

					controladorUsuario.borrarUsuario(controladorUsuario.buscarPorPK(seleccion));

					break;
				case 3:

					seleccion = JOptionPane.showOptionDialog(null, "¿A que tabla quiere entrar?", "BPFILMS",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Buscar por Nickname", "Mostrar todos los usuarios ", "Buscar por nombre",
									"Buscar por pk", "Salir" },
							"opcion 1");
					switch (seleccion) {

					case 0:

						listaUsuario = controladorUsuario.buscarPorNickName(
								JOptionPane.showInputDialog("Introduzca el nickname del usuario que quiere buscar"));
						if (listaUsuario.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ningún usuario que tenga ese nickname");
						}

						for (Usuario u : listaUsuario) {
							JOptionPane.showMessageDialog(null, u);
						}

						break;

					case 1:

						Integer.parseInt(JOptionPane.showInputDialog(null,
								"Usuarios\n\n" + controladorUsuario.buscarTodosUsuarios()));

						break;

					case 2:

						usuario = controladorUsuario.buscarPorNombre(
								JOptionPane.showInputDialog("Introduzca el nombre del usuario que quiere buscar"));

						if (usuario == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero este usuario no existe");

						} else {

							JOptionPane.showMessageDialog(null, usuario);
						}
						break;

					case 3:

						usuario = controladorUsuario.buscarPorNombre(JOptionPane
								.showInputDialog("Introduzca la clave primaria del usuario que quiere buscar"));

						if (usuario == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero este usuario no existe");

						} else {

							JOptionPane.showMessageDialog(null, usuario);
						}
						break;

					case 4:
						break;

					}
				}
				break;

			case 1:

				seleccion = opcionesCRUD();

				switch (seleccion) {

				case 0:

					try {
						seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
								"¿Qué usuario quiere modificar?\n\n" + controladorUsuario.buscarTodosUsuarios()));

						usuario = introducirDatosUsuario(seleccion);
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null, "No ha sido modificado ningún usuario a la base de datos");
					}
					if (usuario != null) {
						controladorUsuario.modificarUsuario(usuario);
					}

					else {
						JOptionPane.showMessageDialog(null, "Lo siento ese usuario no existe");
					}
					break;

				case 1:

					try {
						usuario = introducirDatosUsuario(0);
					} catch (NullPointerException NPE) {
						JOptionPane.showMessageDialog(null, "No ha sido añadido ningún usuario a la base de datos");
					}
					controladorUsuario.crearUsuario(usuario);

					break;

				case 2:

					seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
							"¿Qué usuario quiere borra?\n\n" + controladorUsuario.buscarTodosUsuarios()));

					controladorUsuario.borrarUsuario(controladorUsuario.buscarPorPK(seleccion));

					break;
				case 3:

					seleccion = JOptionPane.showOptionDialog(null, "¿A que tabla quiere entrar?", "BPFILMS",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Buscar por Nickname", "Mostrar todos los usuarios ", "Buscar por nombre",
									"Buscar por pk", "Salir" },
							"opcion 1");
					switch (seleccion) {

					case 0:

						listaUsuario = controladorUsuario.buscarPorNickName(
								JOptionPane.showInputDialog("Introduzca el nickname del usuario que quiere buscar"));
						if (listaUsuario.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ningún usuario que tenga ese nickname");
						}

						for (Usuario u : listaUsuario) {
							JOptionPane.showMessageDialog(null, u);
						}

						break;

					case 1:

						Integer.parseInt(JOptionPane.showInputDialog(null,
								"Usuarios\n\n" + controladorUsuario.buscarTodosUsuarios()));

						break;

					case 2:

						usuario = controladorUsuario.buscarPorNombre(
								JOptionPane.showInputDialog("Introduzca el nombre del usuario que quiere buscar"));

						if (usuario == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero este usuario no existe");

						} else {

							JOptionPane.showMessageDialog(null, usuario);
						}
						break;

					case 3:

						usuario = controladorUsuario.buscarPorNombre(JOptionPane
								.showInputDialog("Introduzca la clave primaria del usuario que quiere buscar"));

						if (usuario == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero este usuario no existe");

						} else {

							JOptionPane.showMessageDialog(null, usuario);
						}
						break;

					case 4:
						break;

					}
				}
				break;
			case 2:

				seleccion = opcionesCRUD();
				break;
			case 3:

				seleccion = opcionesCRUD();
				break;
			case 4:

				seleccion = opcionesCRUD();
				break;
			case 5:

				seleccion = opcionesCRUD();
				break;
			case 6:

				seleccion = opcionesCRUD();
				break;
			case 7:

				seleccion = opcionesCRUD();
				break;

			}
		} while (seleccion != 8);

	}

}
