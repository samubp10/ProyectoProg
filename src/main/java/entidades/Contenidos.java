package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the contenidos database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "contenidos")

//@NamedQuery, sirve para indicar consultas identificables por un nombre
@NamedQueries({ @NamedQuery(name = "Contenidos.findAll", query = "SELECT c FROM Contenidos c"),
		@NamedQuery(name = "Contenidos.findContenido", query = "SELECT c FROM Contenidos c WHERE c.nombre = :nombre"), })

//Nombre de la clase y la implementación 
public class Contenidos implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id, indica el atributo que va a mapear con la clave primaria de la tabla
	@Id

	// @GeneratedValue, indica que este valor se genera automáticamente cuando la
	// entidad
	// se guarde en la tabla. En este caso IDENTITY se basa en una columna con
	// incremento automático
	// y permite que la base de datos genere un nuevo valor con cada operación de
	// inserción
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// ATRIBUTOS
	private int contenidoID;

	private String nombre;

	private int numeroLikes;

	private boolean premium;

	// Relación bidireccional muchos a uno a directores
	// Muchos contenidos pueden ser del mismo director
	// Este atributo representa el director involucrado en este contenido
	// La tabla contenido es la propietaria de la relación al tener la clave ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla directores
	@ManyToOne(fetch = FetchType.LAZY)

	// Columna join con el campo directorID
	@JoinColumn(name = "directorID")

	// Objeto de tipo directores
	private Directores directores;

	// Relación bidireccional uno a muchos a documentales
	// Un contenido puede tener varios documentales pero un documental solo puede 
	//pertenecer a un solo contenido.
	// Este atributo representa la lista de documentales en los que
	// se encuentra este contenido
	// mappedBy indica el atributo asociado en la clase documental 
	@OneToMany(mappedBy = "contenidos")
	private List<Documental> documentalesContenidos;


	// Relación bidireccional uno a muchos a peliculas
	// Un contenido puede tener varias peliculas pero una película solo puede 
	//pertenecer a un solo contenido.
	// Este atributo representa la lista de peliculas en los que
	// se encuentra este contenido
	// mappedBy indica el atributo asociado en la clase pelicula 
	@OneToMany(mappedBy = "contenidos")
	private List<Pelicula> peliculasContenidos;

	// Relación bidireccional uno a muchos a series
	// Un contenido puede tener varias series pero una serie solo puede 
	//pertenecer a un solo contenido.
	// Este atributo representa la lista de series en los que
	// se encuentra este contenido
	// mappedBy indica el atributo asociado en la clase serie 
	@OneToMany(mappedBy = "contenidos")
	private List<Serie> seriesContenidos;

	// Asociación bidireccional de muchos a muchos con la tabla actrices
	@ManyToMany

	// El join de la tabla contenidos con actrices
	@JoinTable(name = "actuan", joinColumns = { @JoinColumn(name = "contenidoID") }, inverseJoinColumns = {
			@JoinColumn(name = "actrizID") })
	private List<Actrices> actrices;

	// Relación bidireccional uno a muchos a detallecomentan
	// Un contenido puede tener varios comentarios pero un comentario solo puede 
	//pertenecer a un solo contenido.
	// Este atributo representa la lista de comentarios en los que
	// se encuentra este contenido
	// mappedBy indica el atributo asociado en la clase comentario 
	@OneToMany(mappedBy = "contenidos")
	private List<DetalleComentan> detalleComentans;

	// CONSTRUCTOR
	public Contenidos() {
	}

	// GETTERS Y SETTERS
	public int getContenidoID() {
		return this.contenidoID;
	}

	public void setContenidoID(int contenidoID) {
		this.contenidoID = contenidoID;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroLikes() {
		return this.numeroLikes;
	}

	public void setNumeroLikes(int numeroLikes) {
		this.numeroLikes = numeroLikes;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public Directores getDirectore() {
		return this.directores;
	}

	public void setDirectore(Directores directores) {
		this.directores = directores;
	}

	public List<Documental> getDocumentalesContenidos() {
		return this.documentalesContenidos;
	}

	public void setDocumentalesContenidos(List<Documental> documentalesContenidos) {
		this.documentalesContenidos = documentalesContenidos;
	}

	// Método el cuál recibe un objeto de la clase Documental y devuelve un
	// objeto de tipo Documental
	// Lo que hace este método es añadir el objeto pasado como parámetro
	public Documental addDocumentalesContenido(Documental documentalesContenido) {
		getDocumentalesContenidos().add(documentalesContenido);
		documentalesContenido.setContenido(this);

		return documentalesContenido;
	}

	// Método el cuál recibe un objeto de la clase Documental y devuelve un
	// objeto de tipo Documental
	// Lo que hace este método es eliminar el objeto pasado como parámetro
	public Documental removeDocumentalesContenido(Documental documentalesContenido) {
		getDocumentalesContenidos().remove(documentalesContenido);
		documentalesContenido.setContenido(null);

		return documentalesContenido;
	}

	public List<Pelicula> getPeliculasContenidos() {
		return this.peliculasContenidos;
	}

	public void setPeliculasContenidos(List<Pelicula> peliculasContenidos) {
		this.peliculasContenidos = peliculasContenidos;
	}

	// Método el cuál recibe un objeto de la clase Pelicula y devuelve un
	// objeto de tipo Pelicula
	// Lo que hace este método es añadir el objeto pasado como parámetro
	public Pelicula addPeliculasContenido(Pelicula peliculasContenido) {
		getPeliculasContenidos().add(peliculasContenido);
		peliculasContenido.setContenido(this);

		return peliculasContenido;
	}

	// Método el cuál recibe un objeto de la clase Pelicula y devuelve un
	// objeto de tipo Pelicula
	// Lo que hace este método es eliminar el objeto pasado como parámetro
	public Pelicula removePeliculasContenido(Pelicula peliculasContenido) {
		getPeliculasContenidos().remove(peliculasContenido);
		peliculasContenido.setContenido(null);

		return peliculasContenido;
	}

	public List<Serie> getSeriesContenidos() {
		return this.seriesContenidos;
	}

	public void setSeriesContenidos(List<Serie> seriesContenidos) {
		this.seriesContenidos = seriesContenidos;
	}

	// Método el cuál recibe un objeto de la clase Sery y devuelve un
	// objeto de tipo Sery
	// Lo que hace este método es añadir el objeto pasado como parámetro
	public Serie addSeriesContenido(Serie seriesContenido) {
		getSeriesContenidos().add(seriesContenido);
		seriesContenido.setContenido(this);

		return seriesContenido;
	}

	// Método el cuál recibe un objeto de la clase Sery y devuelve un
	// objeto de tipo Sery
	// Lo que hace este método es eliminar el objeto pasado como parámetro
	public Serie removeSeriesContenido(Serie seriesContenido) {
		getSeriesContenidos().remove(seriesContenido);
		seriesContenido.setContenido(null);

		return seriesContenido;
	}

	public List<Actrices> getActrices() {
		return this.actrices;
	}

	public void setActrices(List<Actrices> actrices) {
		this.actrices = actrices;
	}

	public List<DetalleComentan> getDetallecomentans() {
		return this.detalleComentans;
	}

	public void setDetallecomentans(List<DetalleComentan> detalleComentans) {
		this.detalleComentans = detalleComentans;
	}

	// Método el cuál recibe un objeto de la clase DetalleComentan y devuelve un
	// objeto de tipo DetalleComentan
	// Lo que hace este método es añadir el objeto pasado como parámetro
	public DetalleComentan addDetallecomentan(DetalleComentan detalleComentan) {
		getDetallecomentans().add(detalleComentan);
		detalleComentan.setContenido(this);

		return detalleComentan;
	}

	// Método el cuál recibe un objeto de la clase DetalleComentan y devuelve un
	// objeto de tipo DetalleComentan
	// Lo que hace este método es eliminar el objeto pasado como parámetro
	public DetalleComentan removeDetallecomentan(DetalleComentan detalleComentan) {
		getDetallecomentans().remove(detalleComentan);
		detalleComentan.setContenido(null);

		return detalleComentan;
	}

	@Override
	public String toString() {

		// Para evitar llamadas concatenadas entre objetos relacionados voy
		// a usar el atributo nombre del cliente, no el toString completo.
		String nombres = (this.directores != null) ? this.directores.getNombre() : "";

		StringBuilder builder = new StringBuilder();
		builder.append("Contenidos [contenidoID=");
		builder.append(contenidoID);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", numeroLikes=");
		builder.append(numeroLikes);
		builder.append(", premium=");
		builder.append(premium);
		builder.append(", directores=");
		builder.append(nombres);
		builder.append(", documentalesContenidos=");
		builder.append(documentalesContenidos);
		builder.append(", peliculasContenidos=");
		builder.append(peliculasContenidos);
		builder.append(", seriesContenidos=");
		builder.append(seriesContenidos);
		builder.append(", actrices=");
		builder.append(actrices);
		builder.append(", detalleComentans=");
		builder.append(detalleComentans);
		builder.append("]");
		return builder.toString();
	}

}