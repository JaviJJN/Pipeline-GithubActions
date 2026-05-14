
package biblioteca;

/**
 *
 * @author javi
 */
import java.util.Comparator;

public class ComparadorAutorTitulo implements Comparator<Libro> {

	@Override
	public int compare(Libro l1, Libro l2) {
		int resultado = l1.getAutor().compareTo(l2.getAutor());
		if (resultado == 0) {
			resultado = l1.getTitulo().compareTo(l2.getTitulo());
		}
		if (resultado == 0) {
			resultado = l1.getIsbn().compareTo(l2.getIsbn());
		}
		return resultado;
	}
}
