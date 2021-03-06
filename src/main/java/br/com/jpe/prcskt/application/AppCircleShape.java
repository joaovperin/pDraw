package br.com.jpe.prcskt.application;

import br.com.jpe.prcskt.infra.AppRenderListener;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import processing.core.PApplet;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class AppCircleShape extends AppShape implements AppRenderListener {

    int radius;

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();

        g.stroke(strokeColor.toRgb());
        g.fill(backgroundColor.toRgb());

        g.ellipse(x, y, radius, radius);

        g.popStyle();
    }

    @Override
    public void grow() {
        this.radius += 5;
    }

    @Override
    public void shrink() {
        this.radius -= 5;
    }

}
