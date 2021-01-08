package net.fabricmc.example;

public class NumberValue extends Value {
    float value;
    float max;
    float min;

    public NumberValue(float value, float min, float max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public float get() {
        return this.value;
    }

    public void set(float n) {
        this.value = n;
    }

    public float max() {
        return this.max;
    }

    public float min() {
        return this.min;
    }
}
