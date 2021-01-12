package net.dougteam.doug.client.clickgui.component;

import net.minecraft.client.util.math.MatrixStack;

public class Component {
    public int x;
    public int y;
    int width;
    int height;
    public void render(MatrixStack matrixStack, int mouseX, int mouseY){

    }
    public boolean xHovered(int mouseX){
        boolean xHovered = mouseX > this.x && mouseX < this.x + this.width;
        return xHovered;
    }
    public boolean yHovered(int mouseY){
        boolean yHovered = mouseY > this.y && mouseY < this.y + this.height;
        return yHovered;
    }
    public boolean hovered(int mouseX, int mouseY){
        return xHovered(mouseX) && yHovered(mouseY);
    }

    public Component(int x, int y,  int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int height() {
        return this.height;
    }

}
