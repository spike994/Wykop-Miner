package pl.poznan.put.cs.wykop.connection;

@SuppressWarnings("serial")
public class WykopException extends ConnectionException {
	public WykopException() {
		super();
	}

	public WykopException(Throwable cause) {
		super(cause);
	}

	public WykopException(String msg) {
		super(msg);
	}

	public WykopException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
