package net.dougteam.doug.client.value;

public class IntValue extends Value {
    int value;
    int max;
    int min;

    public IntValue(int value, int min, int max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public int get() {
        return this.value;
    }

    public void set(int n) {
        this.value = n;
    }

    public int max() {
        return this.max;
    }

    public int min() {
        return this.min;
    }
}
