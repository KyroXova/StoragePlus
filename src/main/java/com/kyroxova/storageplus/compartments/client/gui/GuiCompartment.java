package com.kyroxova.storageplus.compartments.client.gui;

import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import com.kyroxova.storageplus.compartments.tile.TileEntityCompartment;
import com.kyroxova.storageplus.network.MessageChangePage;
import com.kyroxova.storageplus.network.PacketHandler;
import com.kyroxova.storageplus.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiCompartment extends GuiContainer {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/compartment.png");
    private final TileEntityCompartment tileEntity;
    private final CompartmentType type;
    private final ContainerCompartment container;

    private GuiPageButton buttonPrev;
    private GuiPageButton buttonNext;

    public GuiCompartment(InventoryPlayer playerInventory, TileEntityCompartment tileEntity) {
        super(new ContainerCompartment(playerInventory, tileEntity));
        this.container = (ContainerCompartment) this.inventorySlots;
        this.tileEntity = tileEntity;
        this.type = tileEntity.getType();
        this.xSize = 194;
        this.ySize = 218;
    }

    @Override
    public void initGui() {
        super.initGui();

        int left = (this.width - this.xSize) / 2;
        int top = (this.height - this.ySize) / 2;

        this.buttonList.clear();
        this.buttonPrev = this.addButton(new GuiPageButton(0, left + 124, top + 4, false));
        this.buttonNext = this.addButton(new GuiPageButton(1, left + 174, top + 4, true));

        updateButtonStates();
    }

    private void updateButtonStates() {
        if (buttonPrev != null && buttonNext != null) {
            buttonPrev.enabled = container.getCurrentPage() > 0;
            buttonNext.enabled = container.getCurrentPage() < container.getTotalPages() - 1;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        int currentPage = container.getCurrentPage();
        if (button.id == 0 && currentPage > 0) {
            int newPage = currentPage - 1;
            container.setCurrentPage(newPage);
            PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
            updateButtonStates();
        } else if (button.id == 1 && currentPage < container.getTotalPages() - 1) {
            int newPage = currentPage + 1;
            container.setCurrentPage(newPage);
            PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
            updateButtonStates();
        }
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

        // 1. Container Title (Uppercase bold look)
        String containerTitle = I18n.format("tile.storageplus.compartment_" + type.getName() + ".name").toUpperCase();
        this.fontRenderer.drawString(containerTitle, 26, 7, 0x373737);

        // 2. Page Indicator inside Pill (x: 139, y: 7)
        String pageText = (container.getCurrentPage() + 1) + " / " + container.getTotalPages();
        int textWidth = this.fontRenderer.getStringWidth(pageText);
        int pillCenterX = 139 + (34 - textWidth) / 2;
        this.fontRenderer.drawString(pageText, pillCenterX, 7, 0xFFFFFF);

        // 3. Inventory Label
        this.fontRenderer.drawString(I18n.format("container.inventory"), 17, 113, 0x373737);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);

        int left = (this.width - this.xSize) / 2;
        int top = (this.height - this.ySize) / 2;

        // Draw Main GUI Panel
        this.drawTexturedModalRect(left, top, 0, 0, this.xSize, this.ySize);

        // Draw Page Indicator Pill Background at (left + 138, top + 4)
        this.drawTexturedModalRect(left + 138, top + 4, 196, 0, 36, 14);

        // Draw Decorative StoragePlus Frame Atlas border
        StoragePlusFrameRenderer.drawFrame(left, top, this.xSize, this.ySize);

        // Draw Container Icon at (left + 6, top + 3)
        if (this.tileEntity.getBlockType() != null) {
            ItemStack stack = new ItemStack(this.tileEntity.getBlockType(), 1, this.tileEntity.getBlockMetadata());
            if (!stack.isEmpty()) {
                RenderHelper.enableGUIStandardItemLighting();
                this.itemRender.renderItemAndEffectIntoGUI(stack, left + 6, top + 3);
                RenderHelper.disableStandardItemLighting();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static class GuiPageButton extends GuiButton {
        private final boolean isNext;

        public GuiPageButton(int id, int x, int y, boolean isNext) {
            super(id, x, y, 14, 14, "");
            this.isNext = isNext;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                mc.getTextureManager().bindTexture(GUI_TEXTURE);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                int u = 196;
                if (!this.enabled) {
                    u = 228;
                } else if (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
                    u = 212;
                }
                int v = this.isNext ? 32 : 16;
                this.drawTexturedModalRect(this.x, this.y, u, v, this.width, this.height);
            }
        }
    }
}
