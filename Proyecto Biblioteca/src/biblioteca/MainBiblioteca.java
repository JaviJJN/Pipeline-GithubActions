
package biblioteca;

import java.util.List;

/**
 *
 * @author javi
 */
public class MainBiblioteca {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("======================================================");
		System.out.println(" Biblioteca (Set -> List -> Map -> Genéricos)");
		System.out.println(" Paquete: biblioteca");
		System.out.println("======================================================");

		Biblioteca b = new Biblioteca();

		// ------------------------------------------------------------
		// Datos de ejemplo (los mismos durante toda la demo)
		// ------------------------------------------------------------
		Libro l1 = new Libro("9788461330010", "El viaje íntimo de la locura", "Roberto Iniesta", 2009);
		Libro l2 = new Libro("9788439746959", "Peces", "Eva Baltasar", 2026);
		Libro l3 = new Libro("9788410352018", "Han cantado bingo", "Lana Corujo", 2025);

		Libro l1Dup = new Libro("9788461330010", "El viaje íntimo de la locura", "Roberto Iniesta", 2009);
		
		System.out.println();
		System.out.println("========== PARTE 1: Registro de libros (Set) ==========");
		System.out.println("Objetivo: evitar duplicados por ISBN usando Set + equals/hashCode.");
		System.out.println("Además: probar eliminarLibro(isbn) (debe usar Iterator.remove).");
		System.out.println();

		System.out.println("[P1] Mostrando libros con toString() (debe seguir el formato exacto del contrato):");
		System.out.println("     " + l1);
		System.out.println("     " + l2);
		System.out.println("     " + l3);
		System.out.println();

		System.out.println("[P1] registrarLibro(l1) -> esperado: true   | obtenido: " + b.registrarLibro(l1));
		System.out.println("[P1] registrarLibro(l2) -> esperado: true   | obtenido: " + b.registrarLibro(l2));
		System.out.println("[P1] registrarLibro(l3) -> esperado: true   | obtenido: " + b.registrarLibro(l3));

		System.out.println("[P1] totalLibros()       -> esperado: 3      | obtenido: " + b.totalLibros());
		System.out.println();

		System.out.println("[P1] Intentando registrar un duplicado lógico (mismo ISBN que l1):");
		System.out.println("[P1] registrarLibro(l1Dup) -> esperado: false | obtenido: " + b.registrarLibro(l1Dup));
		System.out.println("[P1] totalLibros()         -> esperado: 3     | obtenido: " + b.totalLibros());
		System.out.println();

		System.out.println("[P1] Probando eliminarLibro(isbn):");
		System.out.println("[P1] eliminarLibro(\"9788439746959\") -> esperado: true  | obtenido: " + b.eliminarLibro("9788439746959"));
		System.out.println("[P1] totalLibros()               -> esperado: 2     | obtenido: " + b.totalLibros());
		System.out.println("[P1] eliminarLibro(\"NO_EXISTE\")   -> esperado: false | obtenido: " + b.eliminarLibro("NO_EXISTE"));
		System.out.println();

		// ============================================================
		// PARTE 2 - List + Comparator (clases independientes)
		// ============================================================
		System.out.println();
		System.out.println("========== PARTE 2: Catálogo ordenado (List + Comparator) ==========");
		System.out.println("Objetivo: obtener una LISTA NUEVA ordenada sin modificar el Set interno.");
		System.out.println("Comparadores obligatorios: ComparadorAutorTitulo y ComparadorAnioDescTitulo.");
		System.out.println();

		System.out.println("[P2] Catálogo ordenado por AUTOR asc, TITULO asc, ISBN asc:");
		List<Libro> cat1 = b.catalogoOrdenado(new ComparadorAutorTitulo());
		imprimirLista(cat1);

		System.out.println();
		System.out.println("[P2] Catálogo ordenado por ANIO desc, TITULO asc, ISBN asc:");
		List<Libro> cat2 = b.catalogoOrdenado(new ComparadorAnioDescTitulo());
		imprimirLista(cat2);

		// ============================================================
		// PARTE 3 - Map (inventario) + operaciones agregadas
		// ============================================================
		System.out.println();
		System.out.println("========== PARTE 3: Inventario (Map<Libro,Integer>) ==========");
		System.out.println("Objetivo: gestionar ejemplares (agregar, prestar, devolver) y consultas agregadas.");
		System.out.println();

		System.out.println("[P3] Estado inicial de ejemplares (tras registrar libros, inventario debe existir con 0):");
		System.out.println("[P3] ejemplaresDisponibles(l1) -> esperado: 0 | obtenido: " + b.ejemplaresDisponibles("9788461330010"));
		System.out.println("[P3] ejemplaresDisponibles(l3) -> esperado: 0 | obtenido: " + b.ejemplaresDisponibles("9788410352018"));
		System.out.println("[P3] totalEjemplares()         -> esperado: 0 | obtenido: " + b.totalEjemplares());
		System.out.println();

		System.out.println("[P3] Agregando ejemplares:");
		System.out.println("[P3] agregarEjemplares(l1, 3)");
		b.agregarEjemplares(l1, 3);
		System.out.println("[P3] agregarEjemplares(l3, 2)");
		b.agregarEjemplares(l3, 2);

		System.out.println("[P3] ejemplaresDisponibles(l1) -> esperado: 3 | obtenido: " + b.ejemplaresDisponibles("9788461330010"));
		System.out.println("[P3] ejemplaresDisponibles(l3) -> esperado: 2 | obtenido: " + b.ejemplaresDisponibles("9780132350884"));
		System.out.println("[P3] totalEjemplares()         -> esperado: 5 | obtenido: " + b.totalEjemplares());
		System.out.println();

		System.out.println("[P3] Prestando libros:");
		System.out.println("[P3] prestarLibro(l1) -> esperado: true  | obtenido: " + b.prestarLibro("9788461330010"));
		System.out.println("[P3] prestarLibro(l1) -> esperado: true  | obtenido: " + b.prestarLibro("9788461330010"));
		System.out.println("[P3] prestarLibro(l1) -> esperado: true  | obtenido: " + b.prestarLibro("9788461330010"));
		System.out.println("[P3] prestarLibro(l1) -> esperado: false | obtenido: " + b.prestarLibro("9788461330010"));
		System.out.println("[P3] ejemplaresDisponibles(l1) -> esperado: 0 | obtenido: " + b.ejemplaresDisponibles("9788461330010"));
		System.out.println();

		System.out.println("[P3] Devolviendo libro:");
		System.out.println("[P3] devolverLibro(l1)");
		b.devolverLibro("9788461330010");
		System.out.println("[P3] ejemplaresDisponibles(l1) -> esperado: 1 | obtenido: " + b.ejemplaresDisponibles("9788461330010"));
		System.out.println();

		System.out.println("[P3] Consultas agregadas:");
		System.out.println("[P3] totalEjemplares() -> valor actual: " + b.totalEjemplares());
		Libro top = b.libroConMasEjemplares();
		System.out.println("[P3] libroConMasEjemplares() -> " + (top == null ? "null" : top.toString()));
		System.out.println("     (Si hay empate, debe ser el ISBN menor; el grader lo comprobará.)");

		// ============================================================
		// PARTE 4 - Genéricos (ColeccionesUtil.copiaOrdenada)
		// ============================================================
		System.out.println();
		System.out.println("========== PARTE 4: Genéricos (ColeccionesUtil) ==========");
		System.out.println("Objetivo: usar un método genérico para obtener copias ordenadas.");
		System.out.println("Se demuestra ordenando el catálogo con ColeccionesUtil directamente.");
		System.out.println();

		System.out.println("[P4] Copia ordenada de la lista cat2 (por AUTOR/TITULO/ISBN) usando ColeccionesUtil:");
		List<Libro> cat2OrdenadoPorAutor = ColeccionesUtil.copiaOrdenada(cat2, new ComparadorAutorTitulo());
		imprimirLista(cat2OrdenadoPorAutor);

		// ------------------------------------------------------------
		// Bonus: demostración de mensajes de error exactos
		// (para que el alumnado vea los mensajes y los respete)
		// ------------------------------------------------------------
		System.out.println();
		System.out.println("========== BONUS: Errores (mensajes exactos) ==========");
		System.out.println("Este bloque muestra excepciones esperadas y sus mensajes literales.");
		System.out.println("Si tus mensajes no coinciden EXACTAMENTE, el grader fallará.");
		System.out.println();

		probarExcepcion("registrarLibro(null) -> \"libro no puede ser null\"",
		() -> b.registrarLibro(null));

		probarExcepcion("eliminarLibro(null) -> \"isbn no puede ser null\"",
		() -> b.eliminarLibro(null));

		probarExcepcion("catalogoOrdenado(null) -> \"comparador no puede ser null\"",
		() -> b.catalogoOrdenado(null));

		probarExcepcion("agregarEjemplares(null, 1) -> \"libro no puede ser null\"",
		() -> b.agregarEjemplares(null, 1));

		probarExcepcion("agregarEjemplares(l1, 0) -> \"cantidad debe ser > 0\"",
		() -> b.agregarEjemplares(l1, 0));

		probarExcepcion("prestarLibro(null) -> \"isbn no puede ser null\"",
		() -> b.prestarLibro(null));

		probarExcepcion("devolverLibro(null) -> \"isbn no puede ser null\"",
		() -> b.devolverLibro(null));

		probarExcepcion("ejemplaresDisponibles(null) -> \"isbn no puede ser null\"",
		() -> b.ejemplaresDisponibles(null));

		probarExcepcion("ColeccionesUtil.copiaOrdenada(null, cmp) -> \"origen no puede ser null\"",
		() -> ColeccionesUtil.copiaOrdenada(null, new ComparadorAutorTitulo()));

		probarExcepcion("ColeccionesUtil.copiaOrdenada(origen, null) -> \"comparador no puede ser null\"",
		() -> ColeccionesUtil.copiaOrdenada(cat1, null));

		System.out.println();
		System.out.println("======================================================");
		System.out.println(" FIN DE LA DEMO");
		System.out.println("======================================================");
	}

	private static void imprimirLista(List<Libro> lista) {
		if (lista == null) {
			System.out.println("  (lista es null)  <-- NO debería ocurrir según el contrato");
			return;
		}
		if (lista.isEmpty()) {
			System.out.println("  (lista vacía)");
			return;
		}
		for (int i = 0; i < lista.size(); i++) {
			System.out.println("  [" + i + "] " + lista.get(i));
		}
	}

	@FunctionalInterface
	private interface ThrowingAction {

		void run();
	}

	private static void probarExcepcion(String titulo, ThrowingAction action) {
		System.out.println("[EXC] " + titulo);
		try {
			action.run();
			System.out.println("      ❌ No se lanzó excepción (ERROR, debería lanzarse).");
		} catch (IllegalArgumentException e) {
			System.out.println("      ✅ IllegalArgumentException: \"" + e.getMessage() + "\"");
		} catch (Exception e) {
			System.out.println("      ❌ Se lanzó otra excepción distinta: " + e.getClass().getName()
			+ " -> \"" + e.getMessage() + "\"");
		}
	}
}
