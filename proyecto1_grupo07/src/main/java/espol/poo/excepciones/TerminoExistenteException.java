package espol.poo.excepciones;

/**
 *
 * @author Omen
 */
public class TerminoExistenteException extends Exception {

    /**
     * Excepción de término en existencia
     */
    public TerminoExistenteException() {
    }

    /**
     * Excepción de término en existencia
     * @param msg 
     */
    public TerminoExistenteException(String msg) {
        super(msg);
    }
}
