package io.npee.springwebadvanced.app.config.postprocessor.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String[] packageNames;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String[] packageNames, Advisor advisor) {
        this.packageNames = packageNames;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 지정된 패키지에 포함된 클래스가 아니면 기본 빈 반환

        for (String packageName : packageNames) {
            if (packageName.equals(bean.getClass().getPackageName())) {
                log.info("bean {}", bean);
                log.info("beanName {}", beanName);

                // 프록시 등록
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvisor(advisor);
                Object proxy = proxyFactory.getProxy();
                log.info("target {}", bean.getClass());
                log.info("proxy {}", proxy.getClass());
                return proxy;
            }
        }

        return bean;

    }
}
