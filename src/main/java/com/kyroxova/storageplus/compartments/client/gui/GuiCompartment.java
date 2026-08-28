package com.kyroxova.storageplus.compartments.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import com.kyroxova.storageplus.init.ModBlocks;
import com.kyroxova.storageplus.network.MessageChangePage;
import com.kyroxova.storageplus.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GuiCompartment extends ContainerScreen<ContainerCompartment> {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("storageplus", "textures/gui/compartment.png");
    private final CompartmentType type;

    private PageButton buttonPrev;
    private PageButton buttonNext;

    public GuiCompartment(ContainerCompartment container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.type = container.getCompartmentType();
        this.imageWidth = 194;
        this.imageHeight = 218;
        this.inventoryLabelX = 17;
        this.inventoryLabelY = 113;
    }

    @Override
    protected void init() {
        super.init();

        int left = (this.width - this.imageWidth) / 2;
        int top = (this.height - this.imageHeight) / 2;

        this.buttonPrev = this.addButton(new PageButton(left + 124, top + 4, false, button -> {
            int currentPage = this.menu.getCurrentPage();
            if (currentPage > 0) {
                int newPage = currentPage - 1;
                this.menu.setCurrentPage(newPage);
                PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
                updateButtonStates();
            }
        }));

        this.buttonNext = this.addButton(new PageButton(left + 174, top + 4, true, button -> {
            int currentPage = this.menu.getCurrentPage();
            if (currentPage < this.menu.getTotalPages() - 1) {
                int newPage = currentPage + 1;
                this.menu.setCurrentPage(newPage);
                PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
                updateButtonStates();
            }
        }));

        updateButtonStates();
    }

    private void updateButtonStates() {
        if (buttonPrev != null && buttonNext != null) {
            buttonPrev.active = this.menu.getCurrentPage() > 0;
            buttonNext.active = this.menu.getCurrentPage() < this.menu.getTotalPages() - 1;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
        updateButtonStates();

        // 1. Container Title (Uppercase bold look)
        String titleStr = new TranslationTextComponent("block.storageplus." + type.getRegistryName()).getString().toUpperCase();
        this.font.draw(matrixStack, titleStr, 26, 7, 0x373737);

        // 2. Page Indicator inside Pill (x: 139, y: 7)
        String pageText = (this.menu.getCurrentPage() + 1) + " / " + this.menu.getTotalPages();
        int textWidth = this.font.width(pageText);
        int pillCenterX = 139 + (34 - textWidth) / 2;
        this.font.draw(matrixStack, pageText, pillCenterX, 7, 0xFFFFFF);

        // 3. Inventory Label
        this.font.draw(matrixStack, this.inventory.getDisplayName(), this.inventoryLabelX, this.inventoryLabelY, 0x373737);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI_TEXTURE);

        int left = (this.width - this.imageWidth) / 2;
        int top = (this.height - this.imageHeight) / 2;

        // Draw Main GUI Panel
        this.blit(matrixStack, left, top, 0, 0, this.imageWidth, this.imageHeight);

        // Draw Page Indicator Pill Background at (left + 138, top + 4)
        this.blit(matrixStack, left + 138, top + 4, 196, 0, 36, 14);

        // Draw Decorative StoragePlus Frame Atlas border
        StoragePlusFrameRenderer.drawFrame(matrixStack, left, top, this.imageWidth, this.imageHeight);

        // Draw Container Icon at (left + 6, top + 3)
        ItemStack stack = new ItemStack(ModBlocks.getCompartmentBlock(this.type).get());
        this.itemRenderer.renderAndDecorateItem(stack, left + 6, top + 3);
    }

    public static class PageButton extends Button {
        private final boolean isNext;

        public PageButton(int x, int y, boolean isNext, IPressable onPress) {
            super(x, y, 14, 14, StringTextComponent.EMPTY, onPress);
            this.isNext = isNext;
        }

        @Override
        public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bind(GUI_TEXTURE);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            int u = 196;
            if (!this.active) {
                u = 228;
            } else if (this.isHovered()) {
                u = 212;
            }
            int v = this.isNext ? 32 : 16;
            this.blit(matrixStack, this.x, this.y, u, v, this.width, this.height);
        }
    }
}
