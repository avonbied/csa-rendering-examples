package id.avonbied.csrapp;

import java.util.concurrent.ThreadLocalRandom;

public class ExampleObject {
	private int id, value;
	private String name;

	public ExampleObject() { this(0); }
	public ExampleObject(final int id) {
		this.update(id);
	}
	public void update(final int id) {
		this.id = id;
		this.name = String.format("Example %d", id);
		this.value = ThreadLocalRandom.current().nextInt(0, 101);
	}

	public String toJsonString() {
		return String.format("{\"id\":%d,\"name\":\"%s\",\"value\":%d}", this.id, this.name, this.value);
	}
	public String toString() {
		return String.format("{%d, %s, %d}", this.id, this.name, this.value);
	}
}