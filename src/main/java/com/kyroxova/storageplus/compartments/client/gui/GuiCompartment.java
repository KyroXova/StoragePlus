package com.kyroxova.storageplus.compartments.client.gui;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import com.kyroxova.storageplus.network.MessageChangePage;
import com.kyroxova.storageplus.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCompartment extends GuiContainer {

    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    private final ContainerCompartment container;
    private final TileEntityCompartment tileEntity;
    private final CompartmentType type;

    private GuiButton buttonPrev;
    private GuiButton buttonNext;

    public GuiCompartment(InventoryPlayer playerInventory, TileEntityCompartment tileEntity) {
        super(new ContainerCompartment(playerInventory, tileEntity));
        this.tileEntity = tileEntity;
        this.type = tileEntity.getType();
        this.container = (ContainerCompartment) this.inventorySlots;
        this.xSize = 204;
        this.ySize = 204;
    }

    @Override
    public void initGui() {
        super.initGui();

        int left = (this.width - this.xSize) / 2;
        int top = (this.height - this.ySize) / 2;

        this.buttonList.clear();
        this.buttonPrev = this.addButton(new GuiButton(0, left + 140, top + 5, 20, 12, "<"));
        this.buttonNext = this.addButton(new GuiButton(1, left + 165, top + 5, 20, 12, ">"));

        updateButtonStates();
    }

    private void updateButtonStates() {
        if (buttonPrev != null && buttonNext != null) {
            buttonPrev.enabled = container.getCurrentPage() > 0;
            buttonNext.enabled = container.getCurrentPage() < container.getTotalPages() - 1;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int currentPage = container.getCurrentPage();
        if (button.id == 0 && currentPage > 0) {
            int newPage = currentPage - 1;
            container.setCurrentPage(newPage);
            PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
        } else if (button.id == 1 && currentPage < container.getTotalPages() - 1) {
            int newPage = currentPage + 1;
            container.setCurrentPage(newPage);
            PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
        }
        updateButtonStates();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        updateButtonStates();

        String containerTitle = I18n.format("tile.storageplus." + type.getRegistryName() + ".name");
        this.fontRenderer.drawString(containerTitle, 12, 6, 0x404040);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 21, this.ySize - 94, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 114);
        this.drawTexturedModalRect(k, l + 114, 0, 126, this.xSize, 90);
    }
}
