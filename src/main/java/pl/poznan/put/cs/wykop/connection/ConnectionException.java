package pl.poznan.put.cs.wykop.connection;

@SuppressWarnings("serial")
public class ConnectionException extends Exception {
	public ConnectionException() {
		super();
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}

	public ConnectionException(String msg) {
		super(msg);
	}

	public ConnectionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
