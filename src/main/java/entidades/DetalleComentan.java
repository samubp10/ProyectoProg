package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the detallecomentan database table.
 * 
 */

//@Entity, Indica que la clase es una entidad que se va a mapear con una tabla 
//Los campos de la clase se mapearán con columnas de la tabla
@Entity

//@NamedQuery, sirve para indicar consultas identificables por un nombre

@NamedQuery(name = "DetalleComentan.findAll", query = "SELECT d FROM DetalleComentan d")

public class DetalleComentan implements Serializable {
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	@EmbeddedId
	private DetalleComentanPK id;

	// @Lob sirve pata especificar que el atributo anotado representa
	// un tipo de objeto largo
	@Lob
	private String comentarios;

	// Relación bidireccional muchos a uno con contenidos
	// Muchos comentarios pueden ser del mismo contenido, pero un comentario
	// solo puede pertenecer a un contenido.
	// Este atributo representa el contenido en el que se encuentra este 
	// comentario
	// La tabla DetalleComentan es la propietaria de la relación al tener la clave
	// ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla contenidos
	@ManyToOne(fetch = FetchType.LAZY)

	// Columna join con el campo contenidoID
	@JoinColumn(name = "contenidoID")
	private Contenidos contenidos;

	// Relación bidireccional muchos a uno con usuarios
	// Muchos comentarios pueden ser del mismo usuario, pero un comentario
	// solo puede pertenecer a un usuario.
	// Este atributo representa el usuario en el que se encuentra este
	// comentario
	// La tabla DetalleComentan es la propietaria de la relación al tener la clave
	// ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla contenidos
	@ManyToOne(fetch = FetchType.LAZY)

	// Columna join con el campo usuarioID
	@JoinColumn(name = "usuarioID")
	private Usuario usuario;

	// CONSTRUCTOR
	public DetalleComentan() {
	}

	// GETTERS Y SETTERS
	public DetalleComentanPK getId() {
		return this.id;
	}

	public void setId(DetalleComentanPK id) {
		this.id = id;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Contenidos getContenido() {
		return this.contenidos;
	}

	public void setContenido(Contenidos contenidos) {
		this.contenidos = contenidos;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {

		// Para evitar llamadas concatenadas entre objetos relacionados voy
		// a usar el atributo nombre del cliente, no el toString completo.
		String nombre = (this.usuario != null) ? this.usuario.getNombre() : "";

		StringBuilder builder = new StringBuilder();
		builder.append("DetalleComentan [id=");
		builder.append(id);
		builder.append(", comentarios=");
		builder.append(comentarios);
		builder.append(", contenidos=");
		builder.append(contenidos);
		builder.append(", usuario=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}

}