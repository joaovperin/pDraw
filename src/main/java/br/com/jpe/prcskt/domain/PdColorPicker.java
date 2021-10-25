package br.com.jpe.prcskt.domain;

import java.util.List;

import br.com.jpe.prcskt.application.AppColor;
import br.com.jpe.prcskt.infra.AppMouseListener;
import br.com.jpe.prcskt.infra.AppRenderListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.event.MouseEvent;

@Getter
@Builder
@AllArgsConstructor
public class PdColorPicker implements AppRenderListener, AppMouseListener {

    private static final int numberOfLines = 2;

    int x;
    int y;

    final int height;
    final int maxWidth;

    @Getter
    @Builder.Default
    AppColor selectColorTop = AppColor.Picker.P1.black;

    @Getter
    @Builder.Default
    AppColor selectColorBottom = AppColor.Picker.P2.white;

    final List<AppColor> colors = List.of(
            // 1st line
            AppColor.Picker.P1.black, AppColor.Picker.P1.gray, AppColor.Picker.P1.magenta, AppColor.Picker.P1.red,
            AppColor.Picker.P1.orange, AppColor.Picker.P1.yellow, AppColor.Picker.P1.green,
            AppColor.Picker.P1.turquoise, AppColor.Picker.P1.indigo, AppColor.Picker.P1.purple,
            // 2nd line
            AppColor.Picker.P2.white, AppColor.Picker.P2.lightGray, AppColor.Picker.P2.rose,
            AppColor.Picker.P2.lightRed, AppColor.Picker.P2.gold, AppColor.Picker.P2.lightYellow,
            AppColor.Picker.P2.lime, AppColor.Picker.P2.lightTurquoise, AppColor.Picker.P2.blueGray,
            AppColor.Picker.P2.lavender);

    @Override
    public void handleDraw(PApplet g) {
        g.pushStyle();
        g.stroke(g.color(0, 0, 0));

        g.pushStyle();
        g.fill(selectColorTop.toRgb());
        g.rect(x, y, height, height / 2);
        g.popStyle();

        g.pushStyle();
        g.fill(selectColorBottom.toRgb());
        g.rect(x, y + height / 2, height, height / 2);
        g.popStyle();

        final int iniX = x + height + 10;
        final int eachCSize = height / numberOfLines;

        int cX = iniX, cY = y;
        for (int i = 0; i < colors.size(); i++) {
            g.pushStyle();

            final var c = colors.get(i);
            g.fill(c.toRgb());
            g.rect(cX, cY, eachCSize, eachCSize);

            g.popStyle();

            // jump line if reached max size
            cX += eachCSize;
            if (cX >= iniX + maxWidth) {
                cX = iniX;
                cY += eachCSize;
            }
            // prevents overflow
            if (cY > height) {
                break;
            }
        }

        g.popStyle();
    }

    @Override
    public void handleMouseReleased(PApplet applet, MouseEvent event) {
        final var evtX = event.getX();
        final var evtY = event.getY();

        final int iniX = x + height + 10;
        final int endX = iniX + maxWidth;
        final int iniY = y;
        final int endY = iniY + height;

        if (evtX < iniX || evtY < iniY)
            return;
        if (evtY > endY || evtX > endX)
            return;

        final int eachCSize = height / numberOfLines;

        int idx = (evtX - iniX) / eachCSize;
        if ((evtY - iniY) > eachCSize) {
            idx += (colors.size() / numberOfLines);
        }

        final var selectedColor = colors.get(idx);
        if (event.getButton() == PConstants.LEFT) {
            selectColorTop = selectedColor;
        } else if (event.getButton() == PConstants.RIGHT) {
            selectColorBottom = selectedColor;
        }
    }

}
