package net.dougteam.doug.client.clickgui.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.darkmagician6.eventapi.EventTarget;

import org.lwjgl.glfw.GLFW;

import net.dougteam.doug.client.Client;
import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.utils.RenderUtils;
import net.dougteam.doug.client.value.Value;
import net.dougteam.doug.events.KeyEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleComponent extends Component {
    public Utility utility;
    private boolean open = false;
    private int mouseX, mouseY;
    private boolean listening = false;

    CategoryComponent parent;
    public ArrayList<OptionComponent> optionComponents;

    public ModuleComponent(int x, int y) {
        super(x, y, 140, 14);
        this.optionComponents = new ArrayList<>();
    }

    public ModuleComponent setModule(Utility utility) {
        this.utility = utility;
        Iterator<Map.Entry<String, Value>> entrySet = utility.getSettings().entrySet().iterator();
        while (entrySet.hasNext()) {
            Map.Entry<String, Value> entry = entrySet.next();

            OptionComponent oc = new OptionComponent(this.x, this.y);
            oc.setVal(entry.getKey(), entry.getValue());
            oc.parent = this;
            this.optionComponents.add(oc);
        }
        // for (Value<?> v : utility.getSettings()) {
        // OptionComponent oc = new OptionComponent(this.x, this.y);
        // oc.setVal(v);
        // this.optionComponents.add(oc);
        // }
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        MinecraftClient mc = MinecraftClient.getInstance();
        RenderUtils.drawBorderedRectHori(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height(), 1,
                hovered(mouseX, mouseY) ? Client.panelSelectedColor() : Client.panelColor(), -1);
        // DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y +
        // this.height(),
        // hovered(mouseX, mouseY) ? Client.panelSelectedColor() : Client.panelColor());
        mc.textRenderer.drawWithShadow(matrixStack, utility.getName(), this.x + 4, this.y + 4,
                utility.enabled() ? Client.mainColor() : -1);
                String binding = this.listening ? ".." : GLFW.glfwGetKeyName(utility.bind, 0);
        mc.textRenderer.drawWithShadow(matrixStack, binding, this.x + this.width - 40, this.y + 4, -1);
        if (this.optionComponents.size() != 0) {
            mc.inGameHud.getFontRenderer().drawWithShadow(matrixStack, this.open ? "←" : "→", this.x + this.width - 20,
                    this.y + 4, -1);
        }
        int y = 0;
        if (this.open) {
            for (OptionComponent oc : this.optionComponents) {
                oc.x = this.x + this.width;
                oc.y = this.y + y;
                oc.render(matrixStack, mouseX, mouseY);
                y += oc.height();
            }
        }
        super.render(matrixStack, mouseX, mouseY);
    }

    public void mousePress(int button, int action) {
        if (this.hovered(mouseX, mouseY)) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                if (action == GLFW.GLFW_PRESS) {
                    this.utility.toggle();
                }
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                if (action == GLFW.GLFW_PRESS) {
                    if (this.utility.settings.size() > 0) {
                        boolean before = this.open;
                        for (ModuleComponent mc : this.parent.moduleComponents) {
                            for (OptionComponent oc : mc.optionComponents) {
                                oc.parent.open = false;
                            }
                        }

                        this.open = !before;
                    }
                }
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_MIDDLE) {
                if (action == GLFW.GLFW_PRESS) {
                    this.listening = true;
                }
            }
        }
        for (OptionComponent oc : optionComponents) {
            oc.mousePress(button, action);
        }
    }

    @EventTarget
    public void keyPress(KeyEvent kpe) {
        int key = kpe.getKey();
        if(this.listening) {
            if(key != GLFW.GLFW_KEY_SPACE) {
                this.utility.bind = key;
            }
            this.listening = false;
        }
	}
}
