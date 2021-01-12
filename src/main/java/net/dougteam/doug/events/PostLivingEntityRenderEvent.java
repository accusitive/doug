package net.dougteam.doug.events;

import com.darkmagician6.eventapi.events.Event;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class PostLivingEntityRenderEvent<T extends LivingEntity> implements Event {
    public T entity;
    public float f; // TODO find out what this is
    public float g; // TODO this aswell
    public MatrixStack matrices;
    public VertexConsumerProvider vertexConsumerProvider; // yeah idk
    public int i; // TODO and this :(

    public PostLivingEntityRenderEvent(T entity, float f, float g, MatrixStack matrices,
            VertexConsumerProvider vertexConsumerProvider, int i) {
        this.entity = entity;
        this.f = f;
        this.g = g;
        this.matrices = matrices;
        this.vertexConsumerProvider = vertexConsumerProvider;
        this.i = i;
    }

}
