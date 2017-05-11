package concesionario2.estructura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import concesionario2.excepciones.CocheNoExisteException;
import concesionario2.excepciones.CocheYaExisteException;
import concesionario2.excepciones.MatriculaNoValidaException;

/**
 * Representa un concesionario de coches.
 * 
 * L�gicamente, no podr� a�adirse un coche inv�lido almac�n del concesinario)
 * 
 * Han de conocerse todas sus caracter�sticas Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Mar�aLourdes
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Almac�n de los coches del concesionario
	 */
	private ArrayList<Coche> almacen = new ArrayList<Coche>();
	/**
	 * Nombre del concesionario
	 */
	private final String nombre = "IES Gran Capit�n";
	/**
	 * Bandera que me indica si el concesionario ha sido modificado
	 */
	private boolean modificado = false;

	/**
	 * 
	 * @return
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * 
	 * @param modificado
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	 * A�ade un coche al concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a a�adir
	 * @param color
	 *            Color del coche a a�adir
	 * @param modelo
	 *            Modelo del coche a a�adir
	 * @throws Exception
	 *             Si no se ha podido a�adir el coche al concesionario, porque
	 *             ya hay otro con la misma matr�cula o porque faltan datos
	 */
	public void annadir(String matricula, Color color, Modelo modelo) throws Exception {
		Coche coche = new Coche(matricula, color, modelo);
		if (!almacen.contains(coche))
			almacen.add(coche);
		else
			throw new CocheYaExisteException("El coche ya existe en el concesionario. ");
	}

	/**
	 * Elimina un coche del concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a eliminar
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida en su formato
	 * @return true si se ha eliminado. false en otro caso
	 */
	public boolean eliminar(String matricula) throws MatriculaNoValidaException, CocheNoExisteException {
		return almacen.remove(new Coche(matricula));
	}

	/**
	 * Devuelve el n�mero de coches en el almac�n del concesionario
	 * 
	 * @return N�mero de coches en el almac�n del concesionario
	 */
	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almac�n indicado por la matr�cula
	 * 
	 * @param matricula
	 *            Matr�cula a buscar
	 * @return Coche contenido en el almac�n. null si no existe
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida
	 */
	public Coche get(String matricula) throws MatriculaNoValidaException, CocheNoExisteException {
		try {
			return almacen.get(almacen.indexOf(new Coche(matricula)));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CocheNoExisteException("El coche no est� en el concesionario.");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	/**
	 * Obtiene los coches por el color
	 * 
	 * @param color
	 * @return
	 */
	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

	/**
	 * 
	 * @return
	 */
	public String[][] toArray() {
		String[][] array = new String[almacen.size()][3];
		for (int i = 0; i < array.length; i++) {
			array[i][0] = almacen.get(i).getMatricula();
			array[i][1] = String.valueOf(almacen.get(i).getColor());
			array[i][2] = String.valueOf(almacen.get(i).getModelo());
		}
		return array;
	}

	/**
	 * Obtiene un coche por indice
	 * 
	 * @param indice
	 * @return
	 */
	public Coche get(int indice) {
		return almacen.get(indice);
	}

	/**
	 * Comprueba que la matricula sea valida
	 * 
	 * @param text
	 * @return
	 */
	public boolean checkMatricula(String text) {
		return Coche.esValida(text);
	}

	public ListIterator<Coche> listIterator() {
		return almacen.listIterator();
	}
	
	public ListIterator<Coche> listIterator(int index) {
		return almacen.listIterator(index);
	}

	public int indexOf(Coche coche) {
		// TODO Auto-generated method stub
		return almacen.indexOf(coche);
	}
	
	
}
