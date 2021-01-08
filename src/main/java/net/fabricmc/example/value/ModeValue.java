package net.fabricmc.example.value;

import java.util.ArrayList;

public class ModeValue extends Value {
    ArrayList<String> tags;
    int current;
    public ModeValue(String... dags) {
        this.tags = new ArrayList<String>();
        for(String tag : dags) {
            this.tags.add(tag);
        }
    }

    public int tagsLen(){
        return this.tags.size();
    }
    public String current() {
        return this.tags.get(this.current);
    }
    public int currentTag() {
        return this.current;
    }
    public ArrayList<String> tags() {
        return this.tags;
    }

	public void set(int tag) {
        this.current = tag;
	}

}
