package net.fabricmc.example.clickgui.component;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.Client;
import net.fabricmc.example.RenderUtils;
import net.fabricmc.example.value.ModeValue;
import net.fabricmc.example.value.FloatValue;
import net.fabricmc.example.value.IntValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class OptionComponent extends Component {
    public Value val;
    public String valname;
    private int mouseX, mouseY;
    boolean open;
    ArrayList<ModeComponent> modeComponents;

    ModuleComponent parent;

    public OptionComponent(int x, int y) {
        super(x, y, 140, 14);
        this.modeComponents = new ArrayList<>();
    }

    public OptionComponent setVal(String valname, Value val) {
        this.valname = valname;
        this.val = val;
        if (this.val instanceof ModeValue) {
            for (String s : ((ModeValue) this.val).tags()) {
                ModeComponent mc = new ModeComponent(this.x, this.y);
                mc.setMode((ModeValue) this.val);
                mc.setTag(s);
                this.modeComponents.add(mc);
            }
        }
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer fr = mc.inGameHud.getFontRenderer();
        RenderUtils.drawBorderedRectHori(matrixStack, this.x + 1, this.y, this.x + this.width,
                this.y + this.height(), 1, Client.panelColor(), -1);
        // DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y +
        // this.height(),
        // hovered(mouseX, mouseY) ? Client.panelSelectedColor() : Client.panelColor());
        if (this.val instanceof BoolValue) {
            BoolValue boolValue = (BoolValue) this.val;
            String s = String.format("%s: %s", this.valname, boolValue.get() ? "§a✔" : "§c✖");
            int w = fr.getWidth(s);
            DrawableHelper.fill(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height(), boolValue.get() ? 0xff009900 : 0xff990000);
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2), this.y + 3, -1);
        } else if (this.val instanceof FloatValue) {
            FloatValue numValue = (FloatValue) this.val;

            String s = String.format("%s: %.1f", this.valname, numValue.get());
            int w = fr.getWidth(s);
            float zidth = map(numValue.get(), numValue.min(), numValue.max(), 0, this.width);
            DrawableHelper.fill(matrixStack, this.x, this.y, this.x + (int) zidth, this.y + height, Client.mainColor());
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2), (this.y + 3), -1);

            if (hovered(mouseX, mouseY)) {
                if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == 1) {
                    float num = map(this.mouseX, this.x, this.x + this.width, numValue.min() - 0.1f, numValue.max() + 0.1f);
                    numValue.set(num);
                }
            }
        } else if (this.val instanceof IntValue) {
            IntValue intValue = (IntValue) this.val;

            String s = String.format("%s: %s", this.valname, intValue.get());
            int w = fr.getWidth(s);
            float zidth = map(intValue.get(), intValue.min(), intValue.max(), 0, this.width);
            DrawableHelper.fill(matrixStack, this.x, this.y, this.x + (int) zidth, this.y + height, Client.mainColor());
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2), (this.y + 3), -1);

            if (hovered(mouseX, mouseY)) {
                if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == 1) {
                    int num = (int) map(this.mouseX, this.x, this.x + this.width, intValue.min()-3, intValue.max()+3);
                    intValue.set(num);
                }
            }
        } else if (this.val instanceof ModeValue) {
            ModeValue mv = (ModeValue) this.val;
            String s = String.format("%s: %s", valname, mv.current());
            int w = fr.getWidth(s);
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2), (this.y + 3), -1);
        }
        int y = 0;
        if (this.open) {
            for (ModeComponent mc_ : this.modeComponents) {
                mc_.render(matrixStack, mouseX, mouseY);
                mc_.x = this.x + this.width;
                mc_.y = this.y + y;
                y += mc_.height();
            }
        }

        super.render(matrixStack, mouseX, mouseY);
    }

    private float map(float n, float start1, float stop1, float start2, float stop2) {
        return ((n - start1) / (stop1 - start1)) * (stop2 - start2) + start2;
    }

    public void mousePress(int button, int action) {
        if (this.hovered(mouseX, mouseY)) {
            if (action == GLFW.GLFW_PRESS) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                    if (this.val instanceof BoolValue) {
                        BoolValue boolValue = (BoolValue) this.val;
                        boolValue.set(!boolValue.get());
                    } else if (this.val instanceof ModeValue) {
                        ModeValue mv = (ModeValue) this.val;
                        mv.set((mv.currentTag() + 1) % (mv.tagsLen()));
                    }
                } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    this.open = !this.open;
                }
            }
        }
        for (ModeComponent mc : this.modeComponents) {
            mc.mousePress(button, action);
        }
    }

    @Override
    public int height() {
        return 14;
    }
}
