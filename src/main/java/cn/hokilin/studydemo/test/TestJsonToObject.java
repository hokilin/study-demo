package cn.hokilin.studydemo.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.Data;

import java.util.Map;

/**
 * @author linhuankai
 * @date 2022/3/31 15:34
 */
public class TestJsonToObject {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
//        StrikeUpCntConfig strikeUpCntConfig = new StrikeUpCntConfig();
//        System.out.println(JSON.toJSONString(strikeUpCntConfig));

        String data = "{\"levelToStrikeUpTimesMap\":{\"1\":{\"loginMinSeconds\":15,\"loginMaxSeconds\":15,\"intervalMinSeconds\":30,\"intervalMaxSeconds\":60,\"onceTimesFemaleNum\":1,\"onceLoginMaxTimes\":2,\"oneDayMaxTimes\":3},\"2\":{\"loginMinSeconds\":15,\"loginMaxSeconds\":15,\"intervalMinSeconds\":30,\"intervalMaxSeconds\":60,\"onceTimesFemaleNum\":1,\"onceLoginMaxTimes\":2,\"oneDayMaxTimes\":3},\"3\":{\"loginMinSeconds\":15,\"loginMaxSeconds\":15,\"intervalMinSeconds\":30,\"intervalMaxSeconds\":60,\"onceTimesFemaleNum\":1,\"onceLoginMaxTimes\":2,\"oneDayMaxTimes\":3},\"4\":{\"loginMinSeconds\":15,\"loginMaxSeconds\":15,\"intervalMinSeconds\":30,\"intervalMaxSeconds\":60,\"onceTimesFemaleNum\":1,\"onceLoginMaxTimes\":2,\"oneDayMaxTimes\":3}},\"addIntimacyNum\":0,\"helloMsg\":\"\",\"systemToast\":\"月老发现你俩很有缘，为你们牵线成功，赶快聊聊吧~\",\"filterIntimacy\":0,\"replyHasGiftToast\":\"月老发现你俩今天很有缘！30分钟内回消息即可获得收益。小技巧：发语音消息对方更容易回复哦~\",\"replyNoGiftToast\":\"月老发现你俩很有缘，为你们牵线成功，赶快聊聊吧~\"}";
        try {
            StrikeUpCntConfig config = mapper.readValue(data, StrikeUpCntConfig.class);
            System.out.println(config);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Data
    public static class StrikeUpCntConfig {

        /**
         * 男户等级->代打次数、时间配置
         */
        private Map<Integer, StrikeUpTimesConfig> levelToStrikeUpTimesMap = ImmutableMap.of(1, new StrikeUpTimesConfig());

        /**
         * 男代打添加的亲密值
         */
        private int addIntimacyNum = 2;
        /**
         * 男代打打招呼文案
         */
        private String helloMsg = "你好";
        /**
         * 打招呼系统提示文案
         */
        private String systemToast = "月老发现你俩很有缘，为你们牵线成功，赶快聊聊吧~";

        /**
         * 过滤亲密度
         */
        private int filterIntimacy = 0;

        /**
         * 透传给客户端的回复提醒系统文案-有搭讪礼物
         */
        private String replyHasGiftToast = "月老发现你俩今天很有缘！30分钟内回消息即可获得收益。小技巧：发语音消息对方更容易回复哦~";
        /**
         * 透传给客户端的回复提醒系统文案-无搭讪礼物
         */
        private String replyNoGiftToast = "月老发现你俩很有缘，为你们牵线成功，赶快聊聊吧~";
    }

    @Data
    public static class StrikeUpTimesConfig {
        /**
         * 登录后T0-T1秒后触发
         */
        private int loginMinSeconds = 10;
        private int loginMaxSeconds = 15;
        /**
         * 登录触发第一个后每隔T2-T3秒触发一次
         */
        private int intervalMinSeconds = 30;
        private int intervalMaxSeconds = 60;
        /**
         * 每次打招呼选取多少个女户
         */
        private int onceTimesFemaleNum = 1;
        /**
         * 每次登录最多触发A次
         */
        private int onceLoginMaxTimes = 3;
        /**
         * 每天最多触发B次
         */
        private int oneDayMaxTimes = 5;

        public int getLoginRandomSeconds() {
            return getRandom(loginMinSeconds, loginMaxSeconds);
        }

        public int getIntervalRandomSeconds() {
            return getRandom(intervalMinSeconds, intervalMaxSeconds);
        }

        private int getRandom(Integer start, Integer end) {
            return (int) (Math.random() * (end - start + 1) + start);
        }
    }
}
