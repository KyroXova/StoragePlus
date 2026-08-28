package com.kyroxova.storageplus.compartments.client.gui;

import net.minecraft.util.ResourceLocation;

/**
 * StoragePlus 64×64 UI Texture Atlas
 *
 * Coordinate system:
 * - Origin (0, 0) is the top-left of the texture.
 * - Coordinates are pixel coordinates.
 * - Region(x, y, width, height) uses width/height as dimensions,
 *   NOT inclusive end coordinates.
 *
 * Atlas layout:
 *
 *     23 + 1 + 7 + 2 + 7 + 1 + 23 = 64
 *     23 + 1 + 7 + 2 + 7 + 1 + 23 = 64
 *
 * The atlas contains:
 *
 *   4 × 23×23 corners
 *   4 × 7×3 horizontal edge pieces
 *   4 × 3×7 vertical edge pieces
 *   4 × 7×7 slot pieces
 *
 * Empty 1px / 2px areas between regions are atlas padding/separators
 * and are NEVER rendered as part of a UI component.
 */
public final class StoragePlusAtlas {

    public static final ResourceLocation TEXTURE = new ResourceLocation("storageplus", "textures/gui/frame_atlas.png");

    private StoragePlusAtlas() {
        // Utility class — do not instantiate.
    }


    // ========================================================================
    // FRAME CORNERS
    // ========================================================================

    /**
     * TOP-LEFT FRAME CORNER
     *
     * Size: 23×23
     *
     * Purpose:
     * Fixed top-left decorative corner of the StoragePlus GUI frame.
     *
     * This piece defines the upper-left curvature, outer border,
     * and purple accent of the frame.
     *
     * Rendering:
     * - Draw at the exact GUI top-left position.
     * - NEVER stretch or scale.
     * - Always remains 23×23 screen pixels.
     */
    public static final Region CORNER_TOP_LEFT =
            new Region(0, 0, 23, 23);


    /**
     * TOP-RIGHT FRAME CORNER
     *
     * Size: 23×23
     *
     * Purpose:
     * Fixed top-right decorative corner of the StoragePlus GUI frame.
     *
     * Rendering:
     * - Draw at GUI right edge.
     * - NEVER stretch or scale.
     * - Always remains 23×23 screen pixels.
     */
    public static final Region CORNER_TOP_RIGHT =
            new Region(41, 0, 23, 23);


    /**
     * BOTTOM-LEFT FRAME CORNER
     *
     * Size: 23×23
     *
     * Purpose:
     * Fixed bottom-left decorative corner of the StoragePlus GUI frame.
     *
     * Rendering:
     * - Draw at GUI bottom-left position.
     * - NEVER stretch or scale.
     * - Always remains 23×23 screen pixels.
     */
    public static final Region CORNER_BOTTOM_LEFT =
            new Region(0, 41, 23, 23);


    /**
     * BOTTOM-RIGHT FRAME CORNER
     *
     * Size: 23×23
     *
     * Purpose:
     * Fixed bottom-right decorative corner of the StoragePlus GUI frame.
     *
     * Rendering:
     * - Draw at GUI bottom-right position.
     * - NEVER stretch or scale.
     * - Always remains 23×23 screen pixels.
     */
    public static final Region CORNER_BOTTOM_RIGHT =
            new Region(41, 41, 23, 23);



    // ========================================================================
    // TOP FRAME EDGE
    // ========================================================================

    /**
     * TOP EDGE — SEGMENT A
     *
     * Size: 7×3
     *
     * Atlas position: (24, 1)
     *
     * Purpose:
     * First repeating decorative segment used to extend the top frame
     * between the 23×23 top-left and top-right corners.
     *
     * This is NOT a corner and should NOT be stretched.
     *
     * Rendering:
     * - Tile/repeat horizontally.
     * - Alternate with EDGE_TOP_B when constructing the top rail.
     * - Preserve the original 7×3 pixel size.
     *
     * Atlas padding:
     * There is a 1px empty row above this texture in the atlas.
     */
    public static final Region EDGE_TOP_A =
            new Region(24, 1, 7, 3);


    /**
     * TOP EDGE — SEGMENT B
     *
     * Size: 7×3
     *
     * Atlas position: (33, 1)
     *
     * Purpose:
     * Second decorative segment used together with EDGE_TOP_A
     * to construct the repeating top frame rail.
     *
     * Rendering:
     * - Tile/repeat horizontally.
     * - Normally alternate with EDGE_TOP_A.
     * - Preserve the original 7×3 pixel size.
     *
     * Atlas padding:
     * There is a 2px empty separator between EDGE_TOP_A and this piece.
     */
    public static final Region EDGE_TOP_B =
            new Region(33, 1, 7, 3);



    // ========================================================================
    // BOTTOM FRAME EDGE
    // ========================================================================

    /**
     * BOTTOM EDGE — SEGMENT A
     *
     * Size: 7×3
     *
     * Atlas position: (24, 60)
     *
     * Purpose:
     * First repeating decorative segment used to extend the bottom frame
     * between the bottom-left and bottom-right corners.
     *
     * Rendering:
     * - Tile/repeat horizontally.
     * - Alternate with EDGE_BOTTOM_B.
     * - Preserve the original 7×3 pixel size.
     */
    public static final Region EDGE_BOTTOM_A =
            new Region(24, 60, 7, 3);


    /**
     * BOTTOM EDGE — SEGMENT B
     *
     * Size: 7×3
     *
     * Atlas position: (33, 60)
     *
     * Purpose:
     * Second decorative segment used together with EDGE_BOTTOM_A
     * to construct the repeating bottom frame rail.
     *
     * Rendering:
     * - Tile/repeat horizontally.
     * - Alternate with EDGE_BOTTOM_A.
     * - Preserve the original 7×3 pixel size.
     */
    public static final Region EDGE_BOTTOM_B =
            new Region(33, 60, 7, 3);



    // ========================================================================
    // LEFT FRAME EDGE
    // ========================================================================

    /**
     * LEFT EDGE — SEGMENT A
     *
     * Size: 3×7
     *
     * Atlas position: (1, 24)
     *
     * Purpose:
     * First repeating vertical decorative segment used to extend
     * the left frame between the top-left and bottom-left corners.
     *
     * Rendering:
     * - Tile/repeat vertically.
     * - Alternate with EDGE_LEFT_B.
     * - Preserve the original 3×7 pixel size.
     *
     * Atlas padding:
     * There is a 1px empty column to the left of this texture.
     */
    public static final Region EDGE_LEFT_A =
            new Region(1, 24, 3, 7);


    /**
     * LEFT EDGE — SEGMENT B
     *
     * Size: 3×7
     *
     * Atlas position: (1, 33)
     *
     * Purpose:
     * Second repeating vertical decorative segment used together
     * with EDGE_LEFT_A to construct the left frame rail.
     *
     * Rendering:
     * - Tile/repeat vertically.
     * - Alternate with EDGE_LEFT_A.
     * - Preserve the original 3×7 pixel size.
     *
     * Atlas padding:
     * There is a 2px empty separator between EDGE_LEFT_A and this piece.
     */
    public static final Region EDGE_LEFT_B =
            new Region(1, 33, 3, 7);



    // ========================================================================
    // RIGHT FRAME EDGE
    // ========================================================================

    /**
     * RIGHT EDGE — SEGMENT A
     *
     * Size: 3×7
     *
     * Atlas position: (60, 24)
     *
     * Purpose:
     * First repeating vertical decorative segment used to extend
     * the right frame between the top-right and bottom-right corners.
     *
     * Rendering:
     * - Tile/repeat vertically.
     * - Alternate with EDGE_RIGHT_B.
     * - Preserve the original 3×7 pixel size.
     *
     * Atlas padding:
     * There is a 1px empty column to the right of this texture.
     */
    public static final Region EDGE_RIGHT_A =
            new Region(60, 24, 3, 7);


    /**
     * RIGHT EDGE — SEGMENT B
     *
     * Size: 3×7
     *
     * Atlas position: (60, 33)
     *
     * Purpose:
     * Second repeating vertical decorative segment used together
     * with EDGE_RIGHT_A to construct the right frame rail.
     *
     * Rendering:
     * - Tile/repeat vertically.
     * - Alternate with EDGE_RIGHT_A.
     * - Preserve the original 3×7 pixel size.
     *
     * Atlas padding:
     * There is a 2px empty separator between EDGE_RIGHT_A and this piece.
     */
    public static final Region EDGE_RIGHT_B =
            new Region(60, 33, 3, 7);



    // ========================================================================
    // SLOT / CENTRAL 4-PART STRUCTURE
    // ========================================================================

    /**
     * SLOT — TOP-LEFT QUADRANT
     *
     * Size: 7×7
     *
     * Atlas position: (24, 24)
     *
     * Purpose:
     * Top-left section of the four-part central StoragePlus slot motif.
     *
     * This is intended to be drawn as part of the 2×2 central slot
     * arrangement rather than as part of the outer frame.
     */
    public static final Region SLOT_TOP_LEFT =
            new Region(24, 24, 7, 7);


    /**
     * SLOT — TOP-RIGHT QUADRANT
     *
     * Size: 7×7
     *
     * Atlas position: (33, 24)
     *
     * Purpose:
     * Top-right section of the four-part central StoragePlus slot motif.
     *
     * The 2px atlas separator between the left and right slot pieces
     * is intentionally NOT included.
     */
    public static final Region SLOT_TOP_RIGHT =
            new Region(33, 24, 7, 7);


    /**
     * SLOT — BOTTOM-LEFT QUADRANT
     *
     * Size: 7×7
     *
     * Atlas position: (24, 33)
     *
     * Purpose:
     * Bottom-left section of the four-part central StoragePlus slot motif.
     */
    public static final Region SLOT_BOTTOM_LEFT =
            new Region(24, 33, 7, 7);


    /**
     * SLOT — BOTTOM-RIGHT QUADRANT
     *
     * Size: 7×7
     *
     * Atlas position: (33, 33)
     *
     * Purpose:
     * Bottom-right section of the four-part central StoragePlus slot motif.
     */
    public static final Region SLOT_BOTTOM_RIGHT =
            new Region(33, 33, 7, 7);



    // ========================================================================
    // GEOMETRY CONSTANTS
    // ========================================================================

    /**
     * Complete atlas dimensions.
     */
    public static final int ATLAS_WIDTH  = 64;
    public static final int ATLAS_HEIGHT = 64;


    /**
     * Fixed size of every outer frame corner.
     *
     * Corners must never be stretched.
     */
    public static final int CORNER_SIZE = 23;


    /**
     * Width of a horizontal edge segment.
     */
    public static final int HORIZONTAL_EDGE_WIDTH = 7;


    /**
     * Height of a horizontal edge segment.
     */
    public static final int HORIZONTAL_EDGE_HEIGHT = 3;


    /**
     * Width of a vertical edge segment.
     */
    public static final int VERTICAL_EDGE_WIDTH = 3;


    /**
     * Height of a vertical edge segment.
     */
    public static final int VERTICAL_EDGE_HEIGHT = 7;


    /**
     * Empty separator between the two 7px edge/slot sections.
     *
     * This is atlas spacing and is NOT rendered.
     */
    public static final int ATLAS_MIDDLE_GAP = 2;


    /**
     * Empty separator between a 23px corner and a 7px edge section.
     *
     * This is atlas spacing and is NOT rendered.
     */
    public static final int ATLAS_EDGE_GAP = 1;


    /**
     * Empty 1px row/column used as padding around the edge pieces
     * inside the texture atlas.
     *
     * This padding is NOT rendered.
     */
    public static final int ATLAS_EDGE_PADDING = 1;
}
