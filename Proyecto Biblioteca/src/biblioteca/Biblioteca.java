
package biblioteca;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author javi
 */
public class Biblioteca {

	private Set<Libro> libros;
	private Map<Libro, Integer> inventario;

	public Biblioteca() {
		this.libros = new HashSet<>();
		this.inventario = new HashMap<>();
	}

	public boolean registrarLibro(Libro libro) {
		if (libro == null) {
			throw new IllegalArgumentException("libro no puede ser null");
		}
		if (libros.contains(libro)) {
			return false;
		}
		libros.add(libro);
		inventario.put(libro, 0);
		return true;
	}

	public boolean eliminarLibro(String isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("isbn no puede ser null");
		}
		Iterator<Libro> it = libros.iterator();
		while (it.hasNext()) {
			Libro l = it.next();
			if (l.getIsbn().equals(isbn)) {
				it.remove();
				inventario.remove(l);
				return true;
			}
		}
		return false;
	}

	public int totalLibros() {
		return libros.size();
	}

	public List<Libro> catalogoOrdenado(Comparator<Libro> comparador) {
		if (comparador == null) {
			throw new IllegalArgumentException("comparador no puede ser null");
		}
		return ColeccionesUtil.copiaOrdenada(this.libros, comparador);
	}

	public void agregarEjemplares(Libro libro, int cantidad) {
		if (libro == null) {
			throw new IllegalArgumentException("libro no puede ser null");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("cantidad debe ser > 0");
		}

		if (!libros.contains(libro)) {
			registrarLibro(libro);
		}

		int actual = inventario.get(libro);
		inventario.put(libro, actual + cantidad);
	}

	public boolean prestarLibro(String isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("isbn no puede ser null");
		}
		for (Libro l : libros) {
			if (l.getIsbn().equals(isbn)) {
				int cantidad = inventario.get(l);
				if (cantidad > 0) {
					inventario.put(l, cantidad - 1);
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public void devolverLibro(String isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("isbn no puede ser null");
		}
		for (Libro l : libros) {
			if (l.getIsbn().equals(isbn)) {
				inventario.put(l, inventario.get(l) + 1);
				return;
			}
		}
	}

	public int ejemplaresDisponibles(String isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("isbn no puede ser null");
		}
		for (Libro l : libros) {
			if (l.getIsbn().equals(isbn)) {
				return inventario.get(l);
			}
		}
		return 0;
	}

	public int totalEjemplares() {
		int total = 0;
		for (Integer cant : inventario.values()) {
			total += cant;
		}
		return total;
	}

	public Libro libroConMasEjemplares() {
		if (libros.isEmpty()) {
			return null;
		}

		Libro maxLibro = null;
		int maxCant = -1;

		for (Map.Entry<Libro, Integer> entry : inventario.entrySet()) {
			Libro actualLibro = entry.getKey();
			int actualCant = entry.getValue();

			if (actualCant > maxCant) {
				maxCant = actualCant;
				maxLibro = actualLibro;
			} else if (actualCant == maxCant) {
				if (actualLibro.getIsbn().compareTo(maxLibro.getIsbn()) < 0) {
					maxLibro = actualLibro;
				}
			}
		}
		return maxLibro;
	}
}
