package io.github.darkkronicle.kronhud.gui.hud;

import io.github.darkkronicle.kronhud.config.*;
import io.github.darkkronicle.kronhud.gui.component.DynamicallyPositionable;
import io.github.darkkronicle.kronhud.gui.entry.TextHudEntry;
import io.github.darkkronicle.kronhud.gui.layout.AnchorPoint;
import io.github.darkkronicle.kronhud.util.ColorUtil;
import io.github.darkkronicle.kronhud.util.DrawPosition;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class TownHud extends TextHudEntry implements DynamicallyPositionable {

    public static final Identifier ID = new Identifier("kronhud", "townhud");

    private final KronColor firstColor = new KronColor("firsttextcolor", ID.getPath(), ColorUtil.SELECTOR_BLUE);
    private final KronColor secondColor = new KronColor("secondtextcolor", ID.getPath(), ColorUtil.WHITE);
    private final KronInteger decimalPlaces = new KronInteger("decimalplaces", ID.getPath(), 0, 0, 15);
    private final KronBoolean minimal = new KronBoolean("minimal", ID.getPath(), false);

    private final KronOptionList<AnchorPoint> anchor = DefaultOptions.getAnchorPoint(AnchorPoint.MIDDLE_MIDDLE);

    public TownHud() {
            super(79, 31, true);
    }

    @Override
    public void renderComponent(MatrixStack matrices, float delta) {
       TextRenderer textRenderer = client.textRenderer;




    }

    @Override
    public void renderPlaceholderComponent(MatrixStack matrices, float delta) {
       TextRenderer textRenderer = client.textRenderer;

       textRenderer.drawWithShadow(matrices, "Town", 0, 0, ColorUtil.WHITE.color());
    }

    @Override
    public List<KronConfig<?>> getConfigurationOptions() {
        List<KronConfig<?>> options = super.getConfigurationOptions();
        options.add(firstColor);
        options.add(secondColor);
        options.add(decimalPlaces);
        options.add(minimal);
        return options;
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public AnchorPoint getAnchor() {
        return anchor.getValue();
    }

}
