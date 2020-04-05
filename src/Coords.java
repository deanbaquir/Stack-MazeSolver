
public class Coords {
	private int x;
	private int y;
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object o) {
		Coords c = (Coords) o;
		return (c.x == this.x && c.y == this.y);
	}
	
	public String toString() {
		return this.toString();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
