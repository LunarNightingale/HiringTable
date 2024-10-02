public class FullTableException extends Exception{
    /**
     * A constructor for the FullTableException.
     *
     * @param msg
     * A string that represents an error message we would like to output in the event of the exception.
     */
    public FullTableException(String msg){
        super(msg);
    }
}
