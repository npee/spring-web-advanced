package io.npee.springwebadvanced.app.v1;

import io.npee.springwebadvanced.trace.TraceStatus;
import io.npee.springwebadvanced.trace.custom.CustomTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final CustomTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderRepository.request()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }


    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
