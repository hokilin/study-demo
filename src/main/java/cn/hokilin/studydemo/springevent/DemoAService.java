package cn.hokilin.studydemo.springevent;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author linhuankai
 * @date 2022/3/14 15:43
 */
@Service
@Slf4j
public class DemoAService extends AbstractDemoService {

    @Override
    public Logger getLogger() {
        return LoggerFactory.getLogger(DemoAService.class);
    }

    @Override
    protected void doProcess(Long uid) {
        log.info("doProcess A, uid:{}", uid);
    }
}
