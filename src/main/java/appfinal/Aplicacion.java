package appfinal;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.persistence.NonUniqueResultException;
import javax.persistence.RollbackException;
import javax.swing.JComboBox;
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
import servicios.ServicioActrices;
import servicios.ServicioComentarios;
import servicios.ServicioContenidos;
import servicios.ServicioDirectores;
import servicios.ServicioPeliculas;
import servicios.ServicioSeries;
import servicios.ServicioUsuario;
import servicios.ServiciosDocumentales;
import entidades.Contenidos;
import entidades.Directores;
import entidades.Actrices;
import entidades.DetalleComentan;
import entidades.Documental;
import entidades.Pelicula;
import entidades.Serie;

public class Aplicacion {

	private static int opcionesCRUD() {

		int seleccion = 0;

		seleccion = JOptionPane.showOptionDialog(null, "¿Qué es lo que quiere hacer?", "BPFILMS",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
				new Object[] { "Modificar datos", "Añadir datos", "Borrar datos", "Buscar datos", "Salir" },
				"opcion 1");

		return seleccion;

	}

	public static void main(String[] args) {

		// Todos los controladores instanciados
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		ControladorActriz controladorActriz = new ControladorActriz();
		ControladorSerie controladorSerie = new ControladorSerie();
		ControladorPelicula controladorPelicula = new ControladorPelicula();
		ControladorDocumental controladorDocumental = new ControladorDocumental();
		ControladorDirectores controladorDirector = new ControladorDirectores();
		ControladorDetalleComentan controladorDetalleComentan = new ControladorDetalleComentan();
		ControladorContenido controladorContenidos = new ControladorContenido();

		// Listas de entidades
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		List<Serie> listaSeries = new ArrayList<Serie>();
		List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
		List<Documental> listaDocumentales = new ArrayList<Documental>();
		List<Directores> listaDirectores = new ArrayList<Directores>();
		List<Contenidos> listaContenidos = new ArrayList<Contenidos>();
		List<DetalleComentan> listaComentarios = new ArrayList<DetalleComentan>();
		List<Actrices> listaActrices = new ArrayList<Actrices>();

		// Entidades instanciadas
		Usuario usuario = new Usuario();
		DetalleComentan comentario = new DetalleComentan();
		Directores director = new Directores();
		Actrices actriz = new Actrices();
		Serie serie = new Serie();
		Pelicula pelicula = new Pelicula();
		Documental documental = new Documental();
		Contenidos contenido = new Contenidos();

		int seleccion = 0;

		JOptionPane.showMessageDialog(null, "Bienvenido a la base de datos de BPFILMS", "BPFILMS",
				JOptionPane.INFORMATION_MESSAGE);

		do {
			// Inicio de la aplicación
			seleccion = JOptionPane.showOptionDialog(null, "¿A que tabla quiere entrar?", "BPFILMS",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																							// defecto.
					new Object[] { "Usuarios", "Series", "Peliculas", "Documentales", "Contenidos", "DetalleComentan",
							"Actrices", "Directores", "Salir" },
					"opcion 1");

			switch (seleccion) {

			case 0:
				do {
					seleccion = opcionesCRUD();

					switch (seleccion) {
					// OPCIONES CRUD DE USUARIOS
					case 0:

						try {
							seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
									"¿Qué usuario quiere modificar?\n\n" + controladorUsuario.buscarTodosUsuarios()));

							usuario = ServicioUsuario.ModificarDatosUsuario(seleccion);
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido modificado ningún usuario a la base de datos");
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
							usuario = ServicioUsuario.InsertarDatosUsuario();
						} catch (Exception NPE) {
							JOptionPane.showMessageDialog(null, "No ha sido añadido ningún usuario a la base de datos");
						}
						try {
						controladorUsuario.crearUsuario(usuario);
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "No ha sido añadido ningún usuario a la base de datos");
						}
						break;

					case 2:

						seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
								"¿Qué usuario quiere borra?\n\n" + controladorUsuario.buscarTodosUsuarios()));
						try {
						controladorUsuario.borrarUsuario(controladorUsuario.buscarPorPK(seleccion));
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "No ha sido borrado ningún usuario");
						}
						break;
					case 3:

						seleccion = JOptionPane.showOptionDialog(null, "¿A que tabla quiere entrar?", "BPFILMS",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono
																										// por
																										// defecto.
								new Object[] { "Buscar por Nickname", "Mostrar todos los usuarios ",
										"Buscar por nombre", "Buscar por pk", "Salir" },
								"opcion 1");
						switch (seleccion) {

						case 0:

							listaUsuario = controladorUsuario.buscarPorNickName(JOptionPane
									.showInputDialog("Introduzca el nickname del usuario que quiere buscar"));
							if (listaUsuario.isEmpty()) {
								JOptionPane.showMessageDialog(null, "No hay ningún usuario que tenga ese nickname");
							}

							for (Usuario u : listaUsuario) {
								JOptionPane.showMessageDialog(null, u);
							}

							break;

						case 1:

							JOptionPane.showMessageDialog(null,
									"Usuarios\n\n" + controladorUsuario.buscarTodosUsuarios(), "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

							break;

						case 2:

							usuario = controladorUsuario.buscarPorNombre(
									JOptionPane.showInputDialog("Introduzca el nombre del usuario que quiere buscar"));

							if (usuario == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este usuario no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, usuario);
							}
							break;

						case 3:

							usuario = controladorUsuario.buscarPorNombre(JOptionPane
									.showInputDialog("Introduzca la clave primaria del usuario que quiere buscar"));

							if (usuario == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este usuario no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, usuario, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 4:
							break;

						}
					}
				} while (seleccion != 4);
				break;

			// OPCIONES DE SERIES
			case 1:

				seleccion = opcionesCRUD();

				switch (seleccion) {

				case 0:

					try {
						seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
								"¿Qué serie quiere modificar?\n\n" + controladorSerie.buscarTodasSeries()));

						serie = ServicioSeries.modificarDatosSerie(seleccion);
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null, "No ha sido modificado ningúna serie a la base de datos", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
					}
					if (serie != null) {
						controladorSerie.modificarSerie(serie);
					}

					else {
						JOptionPane.showMessageDialog(null, "Lo siento esa serie no existe", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;

				case 1:

					try {
						serie = ServicioSeries.insertarDatosSerie();
					} catch (NullPointerException NPE) {
						JOptionPane.showMessageDialog(null, "No ha sido añadido ninguna serie a la base de datos", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null,
								"Ha habido un error al insertar los datos y no ha sido añadido ninguna serie a la base de datos", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					controladorSerie.crearSerie(serie);

					break;

				case 2:

					seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
							"¿Qué serie quiere borra?\n\n" + controladorSerie.buscarTodasSeries()));

					controladorSerie.borrarSerie(controladorSerie.buscarPorPK(seleccion));

					break;
				case 3:

					seleccion = JOptionPane.showOptionDialog(null, "¿Qué serie quiere ver?", "BPFILMS",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Buscar por duracion", "Mostrar todas las series ", "Buscar por nombre",
									"Buscar por pk", "Salir" },
							"opcion 1");
					switch (seleccion) {

					case 0:

						listaSeries = controladorSerie.buscarPorDuracion(Integer.parseInt(
								JOptionPane.showInputDialog("Introduzca la duración de la serie que quiere buscar")));
						if (listaSeries.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ninguna serie que tenga ese nickname", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}

						for (Serie s : listaSeries) {
							JOptionPane.showMessageDialog(null, s, "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}

						break;

					case 1:

						JOptionPane.showMessageDialog(null, "Series\n\n" + controladorSerie.buscarTodasSeries(), "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);

						break;

					case 2:

						try {
							serie = controladorSerie.buscarPorNombre(
									JOptionPane.showInputDialog("Introduzca el nombre de la serie que quiere buscar"));
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna serie", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						if (serie == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero esta serie no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

						} else {

							JOptionPane.showMessageDialog(null, serie, "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}
						break;

					case 3:

						serie = controladorSerie.buscarPorPK(Integer.parseInt(JOptionPane
								.showInputDialog("Introduzca la clave primaria de la serie que quiere buscar")));

						if (serie == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero esta serie no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

						} else {

							JOptionPane.showMessageDialog(null, serie, "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}
						break;

					case 4:
						break;

					}
				}
				break;

			// OPCIONES DE PELICULAS
			case 2:

				seleccion = opcionesCRUD();

				switch (seleccion) {

				case 0:

					try {
						seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
								"¿Qué pelicula quiere modificar?\n\n" + controladorPelicula.buscarTodasPeliculas()));

						pelicula = ServicioPeliculas.modificarDatosPeliculas(seleccion);
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null,
								"No ha sido modificado ningúna pelicula a la base de datos", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
					}
					if (pelicula != null) {
						controladorPelicula.modificarPelicula(pelicula);
					}

					else {
						JOptionPane.showMessageDialog(null, "Lo siento esa pelicula no existe", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;

				case 1:

					try {
						pelicula = ServicioPeliculas.insertarDatosPeliculas();
					} catch (NullPointerException NPE) {
						JOptionPane.showMessageDialog(null, "No ha sido añadido ninguna pelicula a la base de datos", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					} catch (NumberFormatException NFE) {
						JOptionPane.showMessageDialog(null,
								"Ha habido un error al insertar los datos y no ha sido añadido ninguna pelicula a la base de datos", "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					controladorPelicula.crearPelicula(pelicula);

					break;

				case 2:

					seleccion = Integer.parseInt(JOptionPane.showInputDialog(null,
							"¿Qué pelicula quiere borra?\n\n" + controladorPelicula.buscarTodasPeliculas()));

					controladorPelicula.borrarPelicula(controladorPelicula.buscarPorPK(seleccion));

					break;
				case 3:

					seleccion = JOptionPane.showOptionDialog(null, "¿Qué serie quiere ver?", "BPFILMS",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por
																									// defecto.
							new Object[] { "Buscar por duracion", "Mostrar todas las peliculas ", "Buscar por nombre",
									"Buscar por pk", "Salir" },
							"opcion 1");
					switch (seleccion) {

					case 0:

						listaPeliculas = controladorPelicula.buscarPorDuracion(Integer.parseInt(JOptionPane
								.showInputDialog("Introduzca la duración de la pelicula que quiere buscar")));
						if (listaPeliculas.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ninguna pelicula que tenga ese nickname", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}

						for (Pelicula p : listaPeliculas) {
							JOptionPane.showMessageDialog(null, p, "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}

						break;

					case 1:

						JOptionPane.showMessageDialog(null,
								"Peliculas\n\n" + controladorPelicula.buscarTodasPeliculas(), "BPFILMS",
								JOptionPane.INFORMATION_MESSAGE);

						break;

					case 2:

						try {
							pelicula = controladorPelicula.buscarPorNombre(JOptionPane
									.showInputDialog("Introduzca el nombre de la pelicula que quiere buscar"));
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna pelicula", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						if (pelicula == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero esta pelicula no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

						} else {

							JOptionPane.showMessageDialog(null, pelicula, "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}
						break;

					case 3:

						pelicula = controladorPelicula.buscarPorPK(Integer.parseInt(JOptionPane
								.showInputDialog("Introduzca la clave primaria de la pelicula que quiere buscar")));

						if (pelicula == null) {

							JOptionPane.showMessageDialog(null, "Lo sentimos pero esta pelicula no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

						} else {

							JOptionPane.showMessageDialog(null, pelicula, "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
						}
						break;

					case 4:
						break;

					}
				}
				break;

			// OPCIONES DE DOCUMENTALES
			case 3:
				do {
					seleccion = opcionesCRUD();
					switch (seleccion) {

					case 0:

						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué documental quiere modificar?\n\n"
											+ controladorDocumental.buscarTodosDocumentales()));

							documental = ServiciosDocumentales.modificarDatosDocumentales(seleccion);
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido modificado ningúna documental a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						if (documental != null) {
							controladorDocumental.modificarDocumental(documental);
						}

						else {
							JOptionPane.showMessageDialog(null, "Lo siento ese documental no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;

					case 1:

						try {
							documental = ServiciosDocumentales.insertarDatosDocumentales();
						} catch (NullPointerException NPE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido añadido ningun documental a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"Ha habido un error al insertar los datos y no ha sido añadido ningun documental a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						controladorDocumental.crearDocumental(documental);

						break;

					case 2:
						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué documental quiere borrar?\n\n"
											+ controladorDocumental.buscarTodosDocumentales()));

							controladorDocumental.borrarDocumental(controladorDocumental.buscarPorPK(seleccion));

						} catch (IllegalArgumentException IAE) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado el documental a borrar", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;
					case 3:

						seleccion = JOptionPane.showOptionDialog(null, "¿Qué serie quiere ver?", "BPFILMS",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono
																										// por
																										// defecto.
								new Object[] { "Buscar por duracion", "Mostrar todos los documentales",
										"Buscar por nombre", "Buscar por pk", "Salir" },
								"opcion 1");
						switch (seleccion) {

						case 0:

							listaDocumentales = controladorDocumental
									.buscarTodosDocumentalesPorDuracion(Integer.parseInt(JOptionPane.showInputDialog(
											"Introduzca la duración del documental que quiere buscar")));
							if (listaPeliculas.isEmpty()) {
								JOptionPane.showMessageDialog(null, "No hay ningun documental que tenga esa duración", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}

							for (Documental d : listaDocumentales) {
								JOptionPane.showMessageDialog(null, d, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}

							break;

						case 1:

							JOptionPane.showMessageDialog(null,
									"Peliculas\n\n" + controladorDocumental.buscarTodosDocumentales(), "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

							break;

						case 2:

							try {
								documental = controladorDocumental.buscarPorNombre(JOptionPane
										.showInputDialog("Introduzca el nombre del documental que quiere buscar"));
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(null, "No se ha encontrado ningun documental", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							if (documental == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este documental no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							} else {

								JOptionPane.showMessageDialog(null, documental, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 3:

							documental = controladorDocumental.buscarPorPK(Integer.parseInt(JOptionPane
									.showInputDialog("Introduzca la clave primaria del documental que quiere buscar")));

							if (documental == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero ese documental no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, documental, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 4:
							break;
						}
					}
				} while (seleccion != 4);
				break;
			// Contenidos
			case 4:
				do {
					seleccion = opcionesCRUD();

					switch (seleccion) {

					case 0:

						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué contenido quiere modificar?\n\n"
											+ controladorContenidos.buscarTodosLosContenidos()));

							contenido = ServicioContenidos.modificarDatosContenidos(seleccion);
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido modificado ningún contenido a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						if (contenido != null) {
							controladorContenidos.modificarContenido(contenido);

						}

						else {
							JOptionPane.showMessageDialog(null, "Lo siento ese contenido no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;

					case 1:

						try {
							contenido = ServicioContenidos.insertarDatosContenidos();
						} catch (NullPointerException NPE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido añadido ningun contenido a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"Ha habido un error al insertar los datos y no ha sido añadido ningun contenido a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						controladorContenidos.crearContenido(contenido);

						break;

					case 2:
						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué contenido quiere borrar?\n\n"
											+ controladorContenidos.buscarTodosLosContenidos()));
							try {
								controladorContenidos.borrarContenido(controladorContenidos.buscarPorPK(seleccion));
							} catch (RollbackException RBE) {
								JOptionPane.showMessageDialog(null,
										"No se ha podido borrar este contenido dado que contiene un clave foránea de otra tabla", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (IllegalArgumentException IAE) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado el contenido a borrar", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;
					case 3:

						seleccion = JOptionPane.showOptionDialog(null, "¿Qué contenido quiere ver?", "BPFILMS",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono
																										// por
																										// defecto.
								new Object[] { "Mostrar todos los contenidos ", "Buscar por nombre", "Buscar por pk",
										"Salir" },
								"opcion 1");
						switch (seleccion) {

						case 0:

							JOptionPane.showMessageDialog(null,
									"Peliculas\n\n" + controladorContenidos.buscarTodosLosContenidos(), "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

							break;

						case 1:

							try {
								contenido = controladorContenidos.buscarPorNombre(JOptionPane
										.showInputDialog("Introduzca el nombre del contenido que quiere buscar"));
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(null, "No se ha encontrado ningun contenido", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							if (contenido == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este contenido no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							} else {

								JOptionPane.showMessageDialog(null, contenido, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 2:

							contenido = controladorContenidos.buscarPorPK(Integer.parseInt(JOptionPane
									.showInputDialog("Introduzca la clave primaria del contenido que quiere buscar")));

							if (contenido == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este contenido no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, contenido, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 3:
							break;
						}
					}
				} while (seleccion != 4);
				break;
				
				
			// OPCIONES DE DETALLECOMENTAN
			case 5:

				do {
					seleccion = opcionesCRUD();

					switch (seleccion) {

					case 0:

						try {
					

							comentario = ServicioComentarios.modificarDatosComentarios();
						} catch (NullPointerException NPE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido modificado ningún comentario a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}catch (NumberFormatException NFE) {
							
							
						}
						
						
						if (comentario != null) {
							controladorDetalleComentan.modificarComentario(comentario);

						}

						else {
							JOptionPane.showMessageDialog(null, "Lo siento ese comentario no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;

					case 1:
						
						try {
							comentario = ServicioComentarios.insertarDatosComentarios();
						} catch (NullPointerException NPE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido añadido ningun comentario a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"Ha habido un error al insertar los datos y no ha sido añadido ningun comentario a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						controladorDetalleComentan.crearComentario(comentario);

						break;
						

					case 2:
						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué comentario quiere borrar? escribiendo el código del usuario\n\n"
											+ controladorDetalleComentan.buscarTodosLosComentarios()));

							comentario = ServicioComentarios.borrarComentario(seleccion);
							
							try {
								controladorDetalleComentan.borrarComentario(comentario);
								
							} catch (RollbackException RBE) {
								JOptionPane.showMessageDialog(null,
										"No se ha podido borrar este comentario dado que contiene un clave foránea de otra tabla", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (IllegalArgumentException IAE) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado el comentario a borrar", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;
					case 3:

						seleccion = JOptionPane.showOptionDialog(null, "¿Qué conmentarios quiere ver?", "BPFILMS",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono
																										// por
																										// defecto.
								new Object[] { "Mostrar todos los comentarios ", "Buscar por usuarioID", "Buscar por contenidoID",
										"Salir" },
								"opcion 1");
						switch (seleccion) {

						case 0:

							JOptionPane.showMessageDialog(null,
									"Comentarios\n\n" + controladorDetalleComentan.buscarTodosLosComentarios(), "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

							break;

						case 1:

							try {
								listaComentarios = controladorDetalleComentan.buscarPorUsuarioID(Integer.parseInt(JOptionPane
										.showInputDialog("Introduzca el usuarioID del comentario que quiere buscar")));
								
								
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(null, "No se ha encontrado ningun comentario", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							if (listaComentarios == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este comentario no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							} else {

								JOptionPane.showMessageDialog(null, listaComentarios, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 2:

							listaComentarios  = controladorDetalleComentan.buscarPorContenidoID(Integer.parseInt(JOptionPane
									.showInputDialog("Introduzca la clave primaria del comentario del que quiere ver sus comentarios")));

							if (listaComentarios == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este comentario no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, listaComentarios, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 3:
							break;
						}
					}
				} while (seleccion != 4);
				break;

			// OPCIONES DE ACTRICES
			case 6:

				do {
					seleccion = opcionesCRUD();

					switch (seleccion) {

					case 0:

						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué actriz quiere modificar?\n\n"
											+ controladorActriz.buscarTodasLasActrices()));

							actriz = ServicioActrices.ModificarDatosActriz(seleccion);
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido modificado ninguna actriz a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						if (actriz != null) {
							controladorActriz.modificarActriz(actriz);

						}

						else {
							JOptionPane.showMessageDialog(null, "Lo siento esa actriz no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;

					case 1:

						try {
							actriz = ServicioActrices.InsertarDatosActriz();
						} catch (NullPointerException NPE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido añadido ninguna actriz a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"Ha habido un error al insertar los datos y no ha sido añadido ningun actor o actriz a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						controladorActriz.crearActriz(actriz);

						break;

					case 2:
						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué actriz quiere borrar?\n\n"
											+ controladorActriz.buscarTodasLasActrices()));
							try {
								controladorActriz.borrarActriz(controladorActriz.buscarPorPK(seleccion));
							} catch (RollbackException RBE) {
								JOptionPane.showMessageDialog(null,
										"No se ha podido borrar este actor o actriz dado que contiene un clave foránea de otra tabla", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (IllegalArgumentException IAE) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado la actriz a borrar", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;
					case 3:

						seleccion = JOptionPane.showOptionDialog(null, "¿Qué actriz o actor quiere ver?", "BPFILMS",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono
																										// por
																										// defecto.
								new Object[] { "Mostrar todos las actrices o actores", "Buscar por nombre", "Buscar por pk",
										"Salir" },
								"opcion 1");
						switch (seleccion) {

						case 0:

							JOptionPane.showMessageDialog(null,
									"Peliculas\n\n" + controladorActriz.buscarTodasLasActrices(), "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

							break;

						case 1:

							try {
								actriz = controladorActriz.buscarPorNombre(JOptionPane
										.showInputDialog("Introduzca el nombre del actor o actriz que quiere buscar"));
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna actriz o actor", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}catch (NonUniqueResultException NURE) {
								
								JOptionPane.showMessageDialog(null, "Se ha encontrado más de una actriz o actor, pruebe a buscar por otro atributo", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							if (actriz == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este/a actriz o actor no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							} else {

								JOptionPane.showMessageDialog(null, actriz, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 2:

							actriz = controladorActriz.buscarPorPK(Integer.parseInt(JOptionPane
									.showInputDialog("Introduzca la clave primaria de la actriz o actor que quiere buscar")));

							if (actriz == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este actor o actriz no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, actriz, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 3:
							break;
						}
					}
				} while (seleccion != 4);
				break;

			// OPCIONES DE DIRECTORES
			case 7:

				do {
					seleccion = opcionesCRUD();

					switch (seleccion) {

					case 0:

						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué director quiere modificar?\n\n"
											+ controladorDirector.buscarTodosDirectores()));

							director = ServicioDirectores.modificarDatosDirectores(seleccion);
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido modificado ninguna director en la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						if (director != null) {
							controladorDirector.modificarDirector(director);

						}

						else {
							JOptionPane.showMessageDialog(null, "Lo siento ese director no existe", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;

					case 1:

						try {
							director = ServicioDirectores.insertarDatosDirectores();
						} catch (NullPointerException NPE) {
							JOptionPane.showMessageDialog(null,
									"No ha sido añadido ningun director a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						} catch (NumberFormatException NFE) {
							JOptionPane.showMessageDialog(null,
									"Ha habido un error al insertar los datos y no ha sido añadido ningun director a la base de datos", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						controladorDirector.crearDirector(director);

						break;

					case 2:
						try {
							seleccion = Integer
									.parseInt(JOptionPane.showInputDialog(null, "¿Qué director quiere borrar?\n\n"
											+ controladorDirector.buscarTodosDirectores()));
							try {
								controladorDirector.borrarDirector(controladorDirector.buscarPorPK(seleccion));
							} catch (RollbackException RBE) {
								JOptionPane.showMessageDialog(null,
										"No se ha podido borrar este director dado que contiene un clave foránea de otra tabla", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (IllegalArgumentException IAE) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado el director a borrar", "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						break;
					case 3:

						seleccion = JOptionPane.showOptionDialog(null, "¿Qué director quiere ver?", "BPFILMS",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono
																										// por
																										// defecto.
								new Object[] { "Mostrar todos los directores", "Buscar por nombre", "Buscar por pk",
										"Salir" },
								"opcion 1");
						switch (seleccion) {

						case 0:

							JOptionPane.showMessageDialog(null,
									"Peliculas\n\n" + controladorDirector.buscarTodosDirectores(), "BPFILMS",
									JOptionPane.INFORMATION_MESSAGE);

							break;

						case 1:

							try {
								director = controladorDirector.buscarPorNombre(JOptionPane
										.showInputDialog("Introduzca el nombre del director que quiere buscar"));
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(null, "No se ha encontrado ningun director", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}catch (NonUniqueResultException NURE) {
								
								JOptionPane.showMessageDialog(null, "Se ha encontrado más de un director, pruebe a buscar por otro atributo", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							if (director == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este/a director o actor no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							} else {

								JOptionPane.showMessageDialog(null, director, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 2:
							try {
							director = controladorDirector.buscarPorPK(Integer.parseInt(JOptionPane
									.showInputDialog("Introduzca la clave primaria del director que quiere buscar")));
							}catch (Exception e) {
								JOptionPane.showInputDialog("No se ha encontrado ningún director");
							}
							if (director == null) {

								JOptionPane.showMessageDialog(null, "Lo sentimos pero este director no existe", "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);

							} else {

								JOptionPane.showMessageDialog(null, director, "BPFILMS",
										JOptionPane.INFORMATION_MESSAGE);
							}
							break;

						case 3:
							break;
						}
					}
				} while (seleccion != 4);
				break;

			}
		} while (seleccion != 8);

	}

}
