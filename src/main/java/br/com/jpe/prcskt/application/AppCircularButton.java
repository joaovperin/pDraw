package br.com.jpe.prcskt.application;

import java.util.Optional;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class AppCircularButton extends AppCircleShape {

    final String text;

    public final Optional<String> getText() {
        return Optional.ofNullable(text);
    }

}
