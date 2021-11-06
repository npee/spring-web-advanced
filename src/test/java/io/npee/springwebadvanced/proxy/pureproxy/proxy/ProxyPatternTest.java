package io.npee.springwebadvanced.proxy.pureproxy.proxy;

import io.npee.springwebadvanced.proxy.pureproxy.proxy.code.ProxyPatternClient;
import io.npee.springwebadvanced.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject subject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(subject);
        client.execute();
        client.execute();
        client.execute();
    }
}
