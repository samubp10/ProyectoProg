package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the series database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "series")

//@NamedQuery, sirve para indicar consultas identificables por un nombre
@NamedQueries({
@NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s"),
@NamedQuery(name = "Serie.findSerie", query = "SELECT s FROM Serie s WHERE s.nombre = :nombre"),
 })



//Nombre de la clase y la implementación 
public class Serie implements Serializable {
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
	private int serieID;

	private int duracion;

	private int episodios;

	private String nombre;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	@Lob
	private String premios;

	private int temporadas;

	// Relación bidireccional muchos a uno con contenidos
	// Muchos documentales pueden ser del mismo contenido, pero una serie
	// solo puede pertenecer a un contenido.
	// Este atributo representa el contenido en el que se encuentra esta
	// serie
	// La tabla documental es la propietaria de la relación al tener la clave
	// ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	@ManyToOne(fetch = FetchType.LAZY)

	// La columna con la que hace Join
	@JoinColumn(name = "contenidoID")

	private Contenidos contenidos;

	// CONSTRUCTOR
	public Serie() {
	}

	// GETTERS Y SETTERS
	public int getSerieID() {
		return this.serieID;
	}

	public void setSerieID(int serieID) {
		this.serieID = serieID;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getEpisodios() {
		return this.episodios;
	}

	public void setEpisodios(int episodios) {
		this.episodios = episodios;
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

	public int getTemporadas() {
		return this.temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
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
		builder.append("serieID = ");
		builder.append(serieID);
		builder.append(" || duracion=");
		builder.append(duracion);
		builder.append(" || episodios=");
		builder.append(episodios);
		builder.append(" || nombre=");
		builder.append(nombre);
		builder.append(" || premios=");
		builder.append(premios);
		builder.append(" || temporadas=");
		builder.append(temporadas);
		builder.append(" || contenidos=");
		if(!nombres.equalsIgnoreCase("")) {
		builder.append(nombres);
		}else {
			builder.append("ninguno");
		}
		builder.append("\n");
		return builder.toString();
	}

}