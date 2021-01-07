package net.fabricmc.example;

public class NumberValue extends Value{
    int value;
    public NumberValue(int value){
        this.value = value;
    }
    public int get() {
        return this.value;
    }
    public void set(int n) {
        this.value = n;
	}
}
