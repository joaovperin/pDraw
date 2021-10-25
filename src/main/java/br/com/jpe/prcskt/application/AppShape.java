package br.com.jpe.prcskt.application;

import java.util.Optional;

import br.com.jpe.prcskt.infra.AppPositionedObject;
import br.com.jpe.prcskt.infra.OnMouseCallback;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
public abstract class AppShape implements AppPositionedObject {

    int x;
    int y;

    @Builder.Default
    final AppColor backgroundColor = AppColor.white;
    @Builder.Default
    final AppColor strokeColor = AppColor.black;
    @Builder.Default
    final AppColor textcolor = AppColor.black;

    final OnMouseCallback onClick;

    public final Optional<OnMouseCallback> getOnClick() {
        return Optional.ofNullable(onClick);
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
