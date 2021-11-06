package io.npee.springwebadvanced.proxy.pureproxy.decorator;

import io.npee.springwebadvanced.proxy.pureproxy.decorator.code.Component;
import io.npee.springwebadvanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import io.npee.springwebadvanced.proxy.pureproxy.decorator.code.MessageDecorator;
import io.npee.springwebadvanced.proxy.pureproxy.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void decorator1() {
        Component component = new RealComponent();
        Component decorator = new MessageDecorator(component);
        DecoratorPatternClient client = new DecoratorPatternClient(decorator);
        client.execute();
    }
}
