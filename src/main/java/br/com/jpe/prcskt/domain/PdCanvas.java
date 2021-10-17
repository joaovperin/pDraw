package br.com.jpe.prcskt.domain;

import java.util.Optional;

import br.com.jpe.prcskt.infra.AppMouseListener;
import br.com.jpe.prcskt.infra.AppRenderListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import processing.core.PApplet;
import processing.event.MouseEvent;

@Builder
@AllArgsConstructor
public class PdCanvas implements AppRenderListener, AppMouseListener {

    int x;
    int y;

    final int height;
    final int width;

    final Runnable onClick;

    @Builder.Default
    private boolean isClicked = false;

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();

        g.stroke(g.color(0, 0, 0));
        g.fill(g.color(220, 210, 200));

        g.rect(x, y, width, height);

        g.popStyle();
    }

    public final Optional<Runnable> getOnClick() {
        return Optional.ofNullable(onClick);
    }

    @Override
    public void handleMousePressed(PApplet applet, MouseEvent event) {
        isClicked = intersects(event);
        if (isClicked) {
            getOnClick().ifPresent(Runnable::run);
        }
    }

    @Override
    public void handleMouseReleased(PApplet applet, MouseEvent event) {
        isClicked = false;
    }

    private boolean intersects(MouseEvent event) {
        final int endX = x + width;
        final int endY = y + height;

        if (event.getX() >= x && event.getX() <= endX) {
            if (event.getY() >= y && event.getY() <= endY) {
                return true;
            }
        }
        return false;
    }

}
