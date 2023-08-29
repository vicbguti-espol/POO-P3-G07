
package espol.poo.excepciones;

/**
 *
 * @author Omen
 */
public class NoOptionException extends Exception{
    /**
     * Excepción de término en existencia
     */
    public NoOptionException() {
    }

    /**
     * Excepción de término en existencia
     * @param msg 
     */
    public NoOptionException(String msg) {
        super(msg);
    }
    
}
