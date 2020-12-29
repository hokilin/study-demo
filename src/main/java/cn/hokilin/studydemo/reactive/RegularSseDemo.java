package cn.hokilin.studydemo.reactive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linhuankai
 * @date 2020/12/30 0:05
 * 把数据从web服务端传输到客户端的一种做法。顾名思义：服务端发送给客户端的事件。
 */
@RestController
public class RegularSseDemo {
    private final Map<String, SseEmitter> sses = new ConcurrentHashMap<>();

    @Bean
    public IntegrationFlow inboundFlow(@Value("${input-dir:file://E:/study_dir}") File in) {
        System.out.println(in.getAbsolutePath());
        return IntegrationFlows.from(Files.inboundAdapter(in).autoCreateDirectory(true), poller -> poller.poller(spec -> spec.fixedRate(1000L)))
                .transform(File.class, File::getAbsolutePath).handle(String.class, (path, map) -> {
                    sses.forEach((key, sse) -> {
                                try {
                                    sse.send(path);
                                } catch (Exception ex) {
                                    throw new RuntimeException();
                                }
                            }

                    );
                    return null;
                })
                .channel(filesChannel())
                .get();
    }

    @Bean
    SubscribableChannel filesChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @GetMapping("/files/{name}")
    SseEmitter file(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L);
        sses.put(name, sseEmitter);
        return sseEmitter;
    }
}
