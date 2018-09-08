package exercises;

public class Car {
	
	private int doors;
	private String color;
	private String brand;
	private String owner;
	
	
	public static class Builder{
		private int doors;
		private String color;
		private String brand;
		private String owner;
		
		
		
		public Builder doors(int doors) {
			this.doors = doors;
					return this;
		}
		public Builder color(String color) {
			this.color = color;
			return this;
		}
		public Builder brand(String brand) {
			this.brand = brand;
			return this;
		}
		public Builder owner(String owner) {
			this.owner = owner;
			return this;
		}
		
		public Car build() {
			return new Car(this);
		}
		
		
	}
	
	private Car(Builder b) {
		this.doors = b.doors;
		this.color = b.color;
		this.brand = b.brand;
		this.owner = b.owner;
	}

	@Override
	public String toString() {
		return "Car [doors=" + doors + ", color=" + color + ", brand=" + brand + ", owner=" + owner + "]";
	}
	
	
	

}
