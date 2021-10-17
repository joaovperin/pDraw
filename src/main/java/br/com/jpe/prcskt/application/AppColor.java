package br.com.jpe.prcskt.application;

import lombok.Getter;

@Getter
public final class AppColor {

    private static final double LIGHTER_COEFF = 1.1;
    private static final double DARKER_COEFF = 0.8;

    public static final AppColor white = new AppColor(255, 220, 220, 220);
    public static final AppColor black = new AppColor(255, 20, 20, 20);
    public static final AppColor red = new AppColor(255, 223, 54, 45);
    public static final AppColor green = new AppColor(255, 129, 182, 34);
    public static final AppColor blue = new AppColor(255, 4, 212, 240);

    public interface Picker {
        public interface P1 {
            public static final AppColor black = new AppColor(255, 0, 0, 0);
            public static final AppColor gray = new AppColor(255, 127, 127, 127);
            public static final AppColor magenta = new AppColor(255, 136, 0, 21);
            public static final AppColor red = new AppColor(255, 237, 28, 36);
            public static final AppColor orange = new AppColor(255, 255, 127, 39);
            public static final AppColor yellow = new AppColor(255, 255, 242, 0);
            public static final AppColor green = new AppColor(255, 34, 177, 76);
            public static final AppColor turquoise = new AppColor(255, 0, 162, 232);
            public static final AppColor indigo = new AppColor(255, 63, 72, 204);
            public static final AppColor purple = new AppColor(255, 163, 73, 164);
        }

        public interface P2 {
            public static final AppColor white = new AppColor(255, 255, 255, 255);
            public static final AppColor lightGray = new AppColor(255, 195, 195, 195);
            public static final AppColor lightRed = new AppColor(255, 245, 126, 133);
            public static final AppColor rose = new AppColor(255, 255, 174, 201);
            public static final AppColor gold = new AppColor(255, 255, 201, 14);
            public static final AppColor lightYellow = new AppColor(255, 239, 228, 176);
            public static final AppColor lime = new AppColor(255, 181, 230, 29);
            public static final AppColor lightTurquoise = new AppColor(255, 153, 217, 234);
            public static final AppColor blueGray = new AppColor(255, 112, 146, 190);
            public static final AppColor lavender = new AppColor(255, 200, 191, 231);
        }
    }

    final int alpha;
    final int r;
    final int g;
    final int b;

    private AppColor(int alpha, int r, int g, int b) {
        this.alpha = Math.min(alpha, 255);
        this.r = Math.min(r, 255);
        this.g = Math.min(g, 255);
        this.b = Math.min(b, 255);
    }

    public static AppColor hex(int hex) {
        int alpha = (hex & 0xFF000000) >> 24;
        if (alpha == 0)
            alpha = 255;
        return new AppColor(alpha, (hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
    }

    public AppColor darker() {
        return new AppColor(this.alpha, (int) (this.r * DARKER_COEFF), (int) (this.g * DARKER_COEFF),
                (int) (this.b * DARKER_COEFF));
    }

    public AppColor lighter() {
        return new AppColor(this.alpha, (int) (this.r * LIGHTER_COEFF), (int) (this.g * LIGHTER_COEFF),
                (int) (this.b * LIGHTER_COEFF));
    }

    public int toRgb() {
        int va = alpha, v1 = r, v2 = g, v3 = b;
        if (va > 255)
            va = 255;
        else if (va < 0)
            va = 0;
        if (v1 > 255)
            v1 = 255;
        else if (v1 < 0)
            v1 = 0;
        if (v2 > 255)
            v2 = 255;
        else if (v2 < 0)
            v2 = 0;
        if (v3 > 255)
            v3 = 255;
        else if (v3 < 0)
            v3 = 0;

        return (va << 24) | (v1 << 16) | (v2 << 8) | v3;
    }

    // white(0xFFFFFFFF), black(0xFF000000), red(0xFFFF0000), green(0xFF00FF00),
    // blue(0xFF0000FF);

    // return 0xff000000 | (v1 << 16) | (v2 << 8) | v3;

    // final int rgb;

    // public int red() {
    // return 0x00000000 | (this.rgb >> 16);
    // }

    // public int green() {
    // return 0x00000000 | (this.rgb >> 8);
    // }

    // public int blue() {
    // return 0x00000000 | (this.rgb);
    // }

}
