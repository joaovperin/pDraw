package br.com.jpe.prcskt.application;

import br.com.jpe.prcskt.infra.AppRenderListener;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import processing.core.PApplet;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class AppRectangleShape extends AppShape implements AppRenderListener {

    @Builder.Default
    final int height = 100;
    @Builder.Default
    final int width = 100;

    @Builder.Default
    private boolean isVisible = true;

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();

        g.stroke(strokeColor.toRgb());
        g.fill(backgroundColor.toRgb());
        g.rect(x, y, width, height);

        g.popStyle();
    }

}
