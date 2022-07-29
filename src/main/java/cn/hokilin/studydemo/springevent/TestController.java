package cn.hokilin.studydemo.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linhuankai
 * @date 2022/3/14 15:40
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping("pushEvent")
    public void pushEvent(Long uid) {
        EventTest eventTest = new EventTest();
        eventTest.setUid(uid);
        applicationEventPublisher.publishEvent(eventTest);
    }
}
