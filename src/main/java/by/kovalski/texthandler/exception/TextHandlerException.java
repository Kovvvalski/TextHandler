package by.kovalski.texthandler.exception;

public class TextHandlerException extends Exception {
  public TextHandlerException() {
    super();
  }

  public TextHandlerException(String message) {
    super(message);
  }

  public TextHandlerException(String message, Throwable cause) {
    super(message, cause);
  }

  public TextHandlerException(Throwable cause) {
    super(cause);
  }
}
