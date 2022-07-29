package cn.hokilin.studydemo.utils;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Map;

/**
 * @author linhuankai
 * @date 2022/3/4 14:05
 */
public class IdGeneratorNew {

    private static final Logger log = LoggerFactory.getLogger(IdGeneratorNew.class);
    private static final Marker MARKER = MarkerFactory.getMarker("serious");
    private static long lastTs = 0L;
    private static long processId;
    private static int processIdBits = 10;
    private static long sequence = 0L;
    private static int sequenceBits = 12;

    public IdGeneratorNew() {
    }

    public static synchronized long nextId() {
        if (processId == 0L) {
            initProcessId();
        }

        long ts = timeGen();
        if (ts < lastTs) {
            throw new RuntimeException("时间戳顺序错误");
        } else {
            if (ts == lastTs) {
                log.info("ts==lastTs : {}", ts);
                sequence = sequence + 1L & (long) ((1 << sequenceBits) - 1);
                log.info("ts==lastTs : {}", sequence);
                if (sequence == 0L) {
                    ts = nextTs(lastTs);
                }
            } else {
                sequence = 0L;
            }

            lastTs = ts;
            return ts - 1483200000000L << processIdBits + sequenceBits | processId << sequenceBits | sequence;
        }
    }

    private static void initProcessId() {
        try {
            Map<Integer, Long> ipMap = ImmutableMap.of(0, 10298182L);
            if (ipMap == null || ipMap.isEmpty()) {
                processId = System.currentTimeMillis() % 1024L;
            } else {
                long ipLong = ipMap.values().stream().findFirst().orElse(0L);
                processId = ipLong % 1000L;
            }
            if (processId == 0L) {
                throw new RuntimeException("msg=id generator init fail, maybe not set spring.application.processid");
            }
        } catch (Exception var4) {
            processId = System.currentTimeMillis() % 1024L;
            log.error(MARKER, "[cmd=initProcessId, msg=initProcessId fail, use temp processid={}]", processId, var4);
        }

    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }

    protected static long nextTs(long lastTs) {
        long ts;
        for (ts = timeGen(); ts <= lastTs; ts = timeGen()) {
        }

        return ts;
    }
}
