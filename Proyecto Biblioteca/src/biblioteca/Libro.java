package biblioteca;

import java.util.Objects;

/**
 *
 * @author javi
 */
public class Libro {

	private final String isbn;
	private String titulo;
	private String autor;
	private int anio;

	public Libro(String isbn, String titulo, String autor, int anio) {
		if (isbn == null) {
			throw new IllegalArgumentException("isbn no puede ser null");
		}
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getAnio() {
		return anio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Libro libro = (Libro) o;
		return Objects.equals(isbn, libro.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public String toString() {
		return "Libro[isbn=" + isbn + ",titulo=" + titulo + ",autor=" + autor + ",anio=" + anio + "]";
	}
}
