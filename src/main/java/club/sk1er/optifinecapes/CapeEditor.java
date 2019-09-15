package club.sk1er.optifinecapes;

import club.sk1er.optifinecapes.gui.GuiScreenCapeOF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "cape_editor", name = "Optifine Cape Editor", version = "1.0")
public class CapeEditor {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void guiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiOptions) {
            GuiButton optifineCape;

            event.buttonList.add(optifineCape = new GuiButton(62727568, 2, event.gui.height - 20, 100, 20, "Optifine Cape"));

            if (Minecraft.getMinecraft().theWorld == null) {
                optifineCape.visible = false;
            }
        }
    }

    @SubscribeEvent
    public void actionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.button.id == 62727568 && event.gui instanceof GuiOptions) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiScreenCapeOF(event.gui));
        }
    }
}
