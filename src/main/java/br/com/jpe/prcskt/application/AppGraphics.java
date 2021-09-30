package br.com.jpe.prcskt.application;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;

@RequiredArgsConstructor
public class AppGraphics {

    private final PApplet g;



    // public void draw(PApplet g) {
    //     g.pushStyle();
    //     int colorToPaint;

    //     if (isClicked) {
    //         colorToPaint = Constants.Colors.blue;
    //     } else if (isHovered) {
    //         colorToPaint = Constants.Colors.blueDark;
    //     } else {
    //         colorToPaint = Constants.Colors.blueLight;
    //     }

    //     g.stroke(g.color(0, 0, 0));
    //     g.fill(colorToPaint);
    //     g.rect(x, y, width, height);

    //     if (text != null) {
    //         PFont font = Constants.Fonts.georgia;
    //         g.textFont(font);
    //         g.textSize(20);

    //         g.stroke(Constants.Colors.white);
    //         g.fill(Constants.Colors.white);
    //         g.textAlign(PConstants.CENTER, PConstants.CENTER);

    //         final var tX = x + ((endX - x) / 2);
    //         final var tY = y + ((endY - y) / 2);

    //         g.text(text, tX, tY);
    //     }

    //     g.popStyle();
    // }

}
