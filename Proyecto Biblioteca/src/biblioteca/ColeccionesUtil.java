package biblioteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author javi
 */
public class ColeccionesUtil {

	public static <T> List<T> copiaOrdenada(Collection<T> origen, Comparator<T> comparador) {
		if (origen == null) {
			throw new IllegalArgumentException("origen no puede ser null");
		}
		if (comparador == null) {
			throw new IllegalArgumentException("comparador no puede ser null");
		}

		List<T> lista = new ArrayList<>(origen);
		lista.sort(comparador);
		return lista;
	}
}
