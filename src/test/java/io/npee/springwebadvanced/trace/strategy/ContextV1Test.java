package io.npee.springwebadvanced.trace.strategy;

import io.npee.springwebadvanced.trace.strategy.code.strategy.ContextV1;
import io.npee.springwebadvanced.trace.strategy.code.strategy.StrategyLogic1;
import io.npee.springwebadvanced.trace.strategy.code.strategy.StrategyLogic2;
import io.npee.springwebadvanced.trace.template.code.AbstractTemplate;
import io.npee.springwebadvanced.trace.template.code.SubClassLogic1;
import io.npee.springwebadvanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 시작
        log.info("비즈니스 로직 1 실행");
        // 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 시작
        log.info("비즈니스 로직 2 실행");
        // 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime {}", resultTime);
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("로직 1 실행");
            }
        };
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("로직 2 실행");
            }
        };
        template2.execute();
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

}
