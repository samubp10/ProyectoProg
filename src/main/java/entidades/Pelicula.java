package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the peliculas database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "peliculas")

//@NamedQuery, sirve para indicar consultas identificables por un nombre

@NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p")

//Nombre de la clase y la implementación 
public class Pelicula implements Serializable {
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
	private int peliculaID;

	private int duracion;

	private String nombre;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	private String premios;

	// Relación bidireccional muchos a uno con contenidos
	// Muchos documentales pueden ser del mismo contenido, pero una película
	// solo puede pertenecer a un contenido.
	// Este atributo representa el contenido en el que se encuentra esta
	// palicula
	// La tabla pelicula es la propietaria de la relación al tener la clave
	// ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla contenidos
	@ManyToOne(fetch = FetchType.LAZY)

	// Join con la comuna contenidoID
	@JoinColumn(name = "contenidoID")
	private Contenidos contenidos;

	// CONSTRUCTOR
	public Pelicula() {
	}

	// GETTERS Y SETTERS
	public int getPeliculaID() {
		return this.peliculaID;
	}

	public void setPeliculaID(int peliculaID) {
		this.peliculaID = peliculaID;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPremios() {
		return this.premios;
	}

	public void setPremios(String premios) {
		this.premios = premios;
	}

	public Contenidos getContenido() {
		return this.contenidos;
	}

	public void setContenido(Contenidos contenidos) {
		this.contenidos = contenidos;
	}

	@Override
	public String toString() {

		// Para evitar llamadas concatenadas entre objetos relacionados voy
		// a usar el atributo nombre del cliente, no el toString completo.
		String nombres = (this.contenidos != null) ? this.contenidos.getNombre() : "";

		StringBuilder builder = new StringBuilder();
		builder.append("peliculaID = ");
		builder.append(peliculaID);
		builder.append(" || duracion = ");
		builder.append(duracion);
		builder.append(" || nombre = ");
		builder.append(nombre);
		builder.append(" || premios = ");
		builder.append(premios);
		builder.append(" || contenidos = ");
		if(!nombres.equalsIgnoreCase("")) {
			builder.append(nombres);
			}else {
				builder.append("ninguno");
			}
		builder.append("\n");
		return builder.toString();
	}

}