package br.com.jpe.prcskt.application.constants;

import br.com.jpe.prcskt.application.AppGraphicsHolder;
import processing.core.PFont;

public interface Constants {

    interface Colors {
        static int blue = AppGraphicsHolder.getGraphics().color(15, 102, 242);
        static int blueDark = AppGraphicsHolder.getGraphics().color(92, 191, 237);
        static int blueLight = AppGraphicsHolder.getGraphics().color(66, 135, 245);

        static int white = AppGraphicsHolder.getGraphics().color(255, 255, 255);
    }

    interface Fonts {
        static PFont georgia =  AppGraphicsHolder.getGraphics().createFont("Georgia", 18);
    }

}
