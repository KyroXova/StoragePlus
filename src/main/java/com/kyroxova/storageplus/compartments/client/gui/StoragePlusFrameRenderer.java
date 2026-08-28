package com.kyroxova.storageplus.compartments.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class StoragePlusFrameRenderer {

    public static void bindAtlas() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(StoragePlusAtlas.TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawRegion(int x, int y, Region region) {
        Gui.func_146110_a(x, y, (float) region.x, (float) region.y, region.width, region.height,
                (float) StoragePlusAtlas.ATLAS_WIDTH, (float) StoragePlusAtlas.ATLAS_HEIGHT);
    }

    public static void drawClippedRegion(int x, int y, Region region, int width, int height) {
        Gui.func_146110_a(x, y, (float) region.x, (float) region.y, width, height,
                (float) StoragePlusAtlas.ATLAS_WIDTH, (float) StoragePlusAtlas.ATLAS_HEIGHT);
    }

    public static void drawFrame(int x, int y, int width, int height) {
        bindAtlas();

        int cornerSize = StoragePlusAtlas.CORNER_SIZE;

        // 1. Draw 4 Fixed Corners (23x23)
        drawRegion(x, y, StoragePlusAtlas.CORNER_TOP_LEFT);
        drawRegion(x + width - cornerSize, y, StoragePlusAtlas.CORNER_TOP_RIGHT);
        drawRegion(x, y + height - cornerSize, StoragePlusAtlas.CORNER_BOTTOM_LEFT);
        drawRegion(x + width - cornerSize, y + height - cornerSize, StoragePlusAtlas.CORNER_BOTTOM_RIGHT);

        // 2. Draw Top Edge (Alternating A and B segments, 7x3)
        int topSpan = width - (cornerSize * 2);
        int topEdgeX = x + cornerSize;
        boolean toggleTop = true;
        while (topSpan > 0) {
            int drawW = Math.min(topSpan, StoragePlusAtlas.HORIZONTAL_EDGE_WIDTH);
            Region edge = toggleTop ? StoragePlusAtlas.EDGE_TOP_A : StoragePlusAtlas.EDGE_TOP_B;
            drawClippedRegion(topEdgeX, y, edge, drawW, StoragePlusAtlas.HORIZONTAL_EDGE_HEIGHT);
            topEdgeX += drawW;
            topSpan -= drawW;
            toggleTop = !toggleTop;
        }

        // 3. Draw Bottom Edge (Alternating A and B segments, 7x3)
        int bottomSpan = width - (cornerSize * 2);
        int bottomEdgeX = x + cornerSize;
        int bottomEdgeY = y + height - StoragePlusAtlas.HORIZONTAL_EDGE_HEIGHT;
        boolean toggleBottom = true;
        while (bottomSpan > 0) {
            int drawW = Math.min(bottomSpan, StoragePlusAtlas.HORIZONTAL_EDGE_WIDTH);
            Region edge = toggleBottom ? StoragePlusAtlas.EDGE_BOTTOM_A : StoragePlusAtlas.EDGE_BOTTOM_B;
            drawClippedRegion(bottomEdgeX, bottomEdgeY, edge, drawW, StoragePlusAtlas.HORIZONTAL_EDGE_HEIGHT);
            bottomEdgeX += drawW;
            bottomSpan -= drawW;
            toggleBottom = !toggleBottom;
        }

        // 4. Draw Left Edge (Alternating A and B segments, 3x7)
        int leftSpan = height - (cornerSize * 2);
        int leftEdgeY = y + cornerSize;
        boolean toggleLeft = true;
        while (leftSpan > 0) {
            int drawH = Math.min(leftSpan, StoragePlusAtlas.VERTICAL_EDGE_HEIGHT);
            Region edge = toggleLeft ? StoragePlusAtlas.EDGE_LEFT_A : StoragePlusAtlas.EDGE_LEFT_B;
            drawClippedRegion(x, leftEdgeY, edge, StoragePlusAtlas.VERTICAL_EDGE_WIDTH, drawH);
            leftEdgeY += drawH;
            leftSpan -= drawH;
            toggleLeft = !toggleLeft;
        }

        // 5. Draw Right Edge (Alternating A and B segments, 3x7)
        int rightSpan = height - (cornerSize * 2);
        int rightEdgeX = x + width - StoragePlusAtlas.VERTICAL_EDGE_WIDTH;
        int rightEdgeY = y + cornerSize;
        boolean toggleRight = true;
        while (rightSpan > 0) {
            int drawH = Math.min(rightSpan, StoragePlusAtlas.VERTICAL_EDGE_HEIGHT);
            Region edge = toggleRight ? StoragePlusAtlas.EDGE_RIGHT_A : StoragePlusAtlas.EDGE_RIGHT_B;
            drawClippedRegion(rightEdgeX, rightEdgeY, edge, StoragePlusAtlas.VERTICAL_EDGE_WIDTH, drawH);
            rightEdgeY += drawH;
            rightSpan -= drawH;
            toggleRight = !toggleRight;
        }
    }

    public static void drawCentralSlot(int x, int y) {
        bindAtlas();
        drawRegion(x, y, StoragePlusAtlas.SLOT_TOP_LEFT);
        drawRegion(x + 7, y, StoragePlusAtlas.SLOT_TOP_RIGHT);
        drawRegion(x, y + 7, StoragePlusAtlas.SLOT_BOTTOM_LEFT);
        drawRegion(x + 7, y + 7, StoragePlusAtlas.SLOT_BOTTOM_RIGHT);
    }
}
