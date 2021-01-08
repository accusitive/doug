package net.fabricmc.example.value;

public class BoolValue extends Value{
    boolean value;
    public BoolValue(boolean value){
        this.value = value;
    }
    public boolean get() {
        return this.value;
    }
	public void set(boolean b) {
        this.value = b;
	}
}
