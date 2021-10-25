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
    int height = 100;
    @Builder.Default
    int width = 100;

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();

        g.stroke(strokeColor.toRgb());
        g.fill(backgroundColor.toRgb());
        g.rect(x, y, width, height);

        g.popStyle();
    }

    @Override
    public void grow() {
        this.height += 5;
        this.width += 5;
    }

    @Override
    public void shrink() {
        this.height -= 5;
        this.width -= 5;
    }

}
