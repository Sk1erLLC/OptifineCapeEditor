package club.sk1er.optifinecapes.gui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class GuiScreenCapeOF extends GuiScreen {

    private GuiScreen parentScreen;

    public GuiScreenCapeOF(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 155, calculateHeight(), 155, 20, "Open Cape Editor"));
        buttonList.add(new GuiButton(1, width / 2 + 5, calculateHeight(), 155, 20, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                openOptifineWebsite();
                break;

            case 1:
                mc.displayGuiScreen(parentScreen);
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void openOptifineWebsite() {
        try {
            GameProfile gameProfile = Minecraft.getMinecraft().thePlayer.getGameProfile();
            String username = gameProfile.getName();
            String userId = gameProfile.getId().toString().replace("-", "");
            String accessToken = Minecraft.getMinecraft().getSession().getToken();
            Random random = new Random();
            Random random1 = new Random(System.identityHashCode(new Object()));
            BigInteger randomBigInt = new BigInteger(128, random);
            BigInteger randomBigInt1 = new BigInteger(128, random1);
            BigInteger serverBigInt = randomBigInt.xor(randomBigInt1);
            String serverId = serverBigInt.toString(16);
            System.out.println(serverId);
            Minecraft.getMinecraft().getSessionService().joinServer(gameProfile, accessToken, serverId);
            String urlStr = "https://optifine.net/capeChange?u=" + userId + "&n=" + username + "&s=" + serverId;
            Desktop.getDesktop().browse(new URL(urlStr).toURI());
        } catch (AuthenticationException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateHeight() {
        return 55 + 4 * 23;
    }
}
