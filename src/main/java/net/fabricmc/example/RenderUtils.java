package net.fabricmc.example;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class RenderUtils {
    public static void drawRect(MatrixStack matrices, int left, int top, int right, int bottom, int color) {
        DrawableHelper.fill(matrices, left, top, right, bottom, color);
    }
    public static void drawBorderedRect(MatrixStack matrices, int left, int top, int right, int bottom, int borderWidth, int color, int borderColor){
        //left border
        drawRect(matrices, left-borderWidth, top-borderWidth, left, bottom+borderWidth, borderColor);
        //top
        drawRect(matrices, left-borderWidth, top-borderWidth, right+borderWidth, top, borderColor);
        //right
        drawRect(matrices, right, top, right + borderWidth, bottom, borderColor);
        //bottom
        drawRect(matrices, left, bottom+borderWidth, right+borderWidth, bottom, borderColor);

        drawRect(matrices, left, top, right, bottom, color);
    }
    
    public static void drawBorderedRectHori(MatrixStack matrices, int left, int top, int right, int bottom, int borderWidth, int color, int borderColor){
        //left border
        drawRect(matrices, left-borderWidth, top, left, bottom, borderColor);
        //top
        // drawRect(matrices, left-borderWidth, top-borderWidth, right+borderWidth, top, borderColor);
        //right
        drawRect(matrices, right, top, right + borderWidth, bottom, borderColor);
        //bottom
        // drawRect(matrices, left, bottom+borderWidth, right+borderWidth, bottom, borderColor);

        drawRect(matrices, left, top, right, bottom, color);
    }
    public static void drawBorderedRectHoriTop(MatrixStack matrices, int left, int top, int right, int bottom, int borderWidth, int color, int borderColor){
        //left border
        drawRect(matrices, left-borderWidth, top-borderWidth, left, bottom+borderWidth, borderColor);
        //top
        drawRect(matrices, left-borderWidth, top-borderWidth, right+borderWidth, top, borderColor);
        //right
        drawRect(matrices, right, top, right + borderWidth, bottom, borderColor);
        //bottom
        // drawRect(matrices, left, bottom+borderWidth, right+borderWidth, bottom, borderColor);

        drawRect(matrices, left, top, right, bottom, color);
    }
    public static void drawBorderedLeft(MatrixStack matrices, int left, int top, int right, int bottom, int borderWidth, int color, int borderColor){
        //left border
        drawRect(matrices, left-borderWidth, top-borderWidth, left, bottom+borderWidth, borderColor);
        //top
        drawRect(matrices, left-borderWidth, top-borderWidth, right+borderWidth, top, borderColor);
        //right
        // drawRect(matrices, right, top, right + borderWidth, bottom, borderColor);
        //bottom
        // drawRect(matrices, left, bottom+borderWidth, right+borderWidth, bottom, borderColor);

        drawRect(matrices, left, top, right, bottom, color);
    }
}
