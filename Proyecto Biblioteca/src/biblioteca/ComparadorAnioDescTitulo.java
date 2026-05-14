
package biblioteca;

/**
 *
 * @author javi
 */
import java.util.Comparator;

public class ComparadorAnioDescTitulo implements Comparator<Libro> {

	@Override
	public int compare(Libro l1, Libro l2) {
		// Orden descendente por año
		int res = Integer.compare(l2.getAnio(), l1.getAnio());
		if (res == 0) {
			res = l1.getTitulo().compareTo(l2.getTitulo());
		}
		if (res == 0) {
			res = l1.getIsbn().compareTo(l2.getIsbn());
		}
		return res;
	}
}
