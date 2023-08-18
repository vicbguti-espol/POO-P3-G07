
package espol.poo.excepciones;


public class TerminoExistenteException extends Exception {

    /**
     * Creates a new instance of <code>TerminoExistenteException</code> without
     * detail message.
     */
    public TerminoExistenteException() {
    }

    /**
     * Constructs an instance of <code>TerminoExistenteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TerminoExistenteException(String msg) {
        super(msg);
    }
}
