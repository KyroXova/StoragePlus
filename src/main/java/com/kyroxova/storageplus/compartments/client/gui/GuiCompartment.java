package com.kyroxova.storageplus.compartments.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.kyroxova.storageplus.compartments.CompartmentType;
import com.kyroxova.storageplus.compartments.container.ContainerCompartment;
import com.kyroxova.storageplus.network.MessageChangePage;
import com.kyroxova.storageplus.network.PacketHandler;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GuiCompartment extends ContainerScreen<ContainerCompartment> {

    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    private final CompartmentType type;

    private Button buttonPrev;
    private Button buttonNext;

    public GuiCompartment(ContainerCompartment container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.type = container.getType();
        this.imageWidth = 204;
        this.imageHeight = 204;
        this.inventoryLabelY = this.imageHeight - 94;
        this.inventoryLabelX = 21;
    }

    @Override
    protected void init() {
        super.init();

        int left = (this.width - this.imageWidth) / 2;
        int top = (this.height - this.imageHeight) / 2;

        this.buttonPrev = this.addButton(new Button(left + 140, top + 5, 20, 12, new StringTextComponent("<"), button -> {
            int currentPage = this.menu.getCurrentPage();
            if (currentPage > 0) {
                int newPage = currentPage - 1;
                this.menu.setCurrentPage(newPage);
                PacketHandler.INSTANCE.sendToServer(new MessageChangePage(newPage));
                updateButtonStates();
            }
        }));

        this.buttonNext = this.addButton(new Button(left + 165, top + 5, 20, 12, new StringTextComponent(">"), button -> {
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

        ITextComponent containerTitle = new TranslationTextComponent("block.storageplus." + type.getRegistryName());
        this.font.draw(matrixStack, containerTitle, 12, 6, 0x404040);
        this.font.draw(matrixStack, this.inventory.getDisplayName(), this.inventoryLabelX, this.inventoryLabelY, 0x404040);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(CHEST_GUI_TEXTURE);

        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;

        this.blit(matrixStack, k, l, 0, 0, this.imageWidth, 114);
        this.blit(matrixStack, k, l + 114, 0, 126, this.imageWidth, 90);
    }
}
