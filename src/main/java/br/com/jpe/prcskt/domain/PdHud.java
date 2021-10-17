package br.com.jpe.prcskt.domain;

import br.com.jpe.prcskt.infra.AppRenderListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import processing.core.PApplet;

@Getter
@Builder
@AllArgsConstructor
public class PdHud implements AppRenderListener {

    final int height;
    final int width;

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();

        g.stroke(g.color(100, 200, 200));
        g.fill(g.color(100, 200, 200));

        g.rect(0, 0, width, height);

        g.popStyle();
    }

}
