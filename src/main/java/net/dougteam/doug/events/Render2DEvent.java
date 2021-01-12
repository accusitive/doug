package net.dougteam.doug.events;

import com.darkmagician6.eventapi.events.Event;

import net.minecraft.client.util.math.MatrixStack;

public class Render2DEvent implements Event {
    public MatrixStack matrices;
    public float tickDelta;

    public Render2DEvent(MatrixStack matrices, float tickDelta) {
        this.matrices = matrices;
        this.tickDelta = tickDelta;
    }
    
}
