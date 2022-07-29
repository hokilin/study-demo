package cn.hokilin.studydemo.springevent;

import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.UUID;

/**
 * @author linhuankai
 * @date 2022/3/14 15:42
 */
public abstract class AbstractDemoService {
    private final Logger log = getLogger();

    public abstract Logger getLogger();

    @EventListener
    @Async("commonSyncExecutor")
    public void eventTest(EventTest eventTest) {
        log.info(eventTest.toString() + UUID.randomUUID());
        doProcess(eventTest.getUid());
    }

    protected abstract void doProcess(Long uid);
}
