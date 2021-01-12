package net.fabricmc.example.value;

public class FloatValue extends Value {
    float value;
    float max;
    float min;

    public FloatValue(float value, float min, float max) {
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
