package se.mejsla.kd.montyhall.domain;

public class Door {

	private final Prize concealment;
	private boolean isOpen;

	public Door(final Prize concealment) {
		this.isOpen = false;
		this.concealment = concealment;
	}

	public Prize getConcealment() {
		return concealment;
	}

	public void open() {
		isOpen = true;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void close() {
		isOpen = false;
	}

	public boolean isClosed() {
		return !isOpen;
	}
}
