package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the documentales database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@Table, sirve para indicar características del esquema de la tabla
//En este caso, su nombre
@Table(name = "documentales")

//@NamedQuery, sirve para indicar consultas identificables por un nombre

@NamedQuery(name = "Documental.findAll", query = "SELECT d FROM Documental d")


//Nombre de la clase y la implementación 
public class Documental implements Serializable {
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
	
	//ATRIBUTOS
	private int doumentalID;

	private int duracion;

	private String nombre;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	@Lob
	private String premios;

	// Relación bidireccional muchos a uno con contenidos
	// Muchos documentales pueden ser del mismo contenido, pero un documental
	// solo puede pertenecer a un contenido.
	// Este atributo representa el contenido en el que se encuentra este 
	// documental
	// La tabla documental es la propietaria de la relación al tener la clave
	// ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla contenidos
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contenidoID")
	private Contenidos contenidos;

	//CONTRUCTOR
	public Documental() {
	}

	//GETTERS Y SETTERS
	public int getDoumentalID() {
		return this.doumentalID;
	}

	public void setDoumentalID(int doumentalID) {
		this.doumentalID = doumentalID;
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
		builder.append("Documental [doumentalID=");
		builder.append(doumentalID);
		builder.append(", duracion=");
		builder.append(duracion);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", premios=");
		builder.append(premios);
		builder.append(", contenidos=");
		builder.append(nombres);
		builder.append("]");
		return builder.toString();
	}

	
	
}