package io.npee.springwebadvanced.trace.strategy;

import io.npee.springwebadvanced.trace.strategy.code.strategy.*;
import io.npee.springwebadvanced.trace.template.code.AbstractTemplate;
import io.npee.springwebadvanced.trace.template.code.SubClassLogic1;
import io.npee.springwebadvanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 파라미터 전달 방식을 사용한 전략 패턴(실행 시 조립)
     */
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("로직 1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("로직 2 실행");
            }
        });
    }

    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("로직 1 실행"));
        context.execute(() -> log.info("로직 2 실행"));
    }
}
