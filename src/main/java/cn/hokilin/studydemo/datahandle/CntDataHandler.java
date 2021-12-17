package cn.hokilin.studydemo.datahandle;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author linhuankai
 * @date 2021/12/17 21:18
 */
@Slf4j
public class CntDataHandler {

    private static final Map<String, String> CNT_APP_MAP;
    private static final List<String> ENV_LIST;

    static {
        ENV_LIST = Arrays.asList("prod", "test");
        Map<String, String> aMap = new HashMap<>(12);
        aMap.put("love100-liveplay", "girgir-liveplay");
        aMap.put("love100-rcs", "girgir-rcs");
        aMap.put("love100_dynamic", "findyou_dynamic");
        aMap.put("love100_imdock", "spf_imdock");
        aMap.put("love100_mission", "girgir_mission");
        aMap.put("love100_notice", "girgir_notice");
        aMap.put("love100_privilege", "findyou_privilege");
        aMap.put("love100_relation", "findyou_relation");
        aMap.put("love100_revenue", "girgir_revenue");
        aMap.put("love100_spf_mission", "spf_mission");
        aMap.put("love100_user", "girgir_user");
        aMap.put("love100_vip", "girgir_vip");
        CNT_APP_MAP = Collections.unmodifiableMap(aMap);
    }

    private List<CntValueEntity> getCntValueFromExcel(String filePath) {
        List<CntValueEntity> dataList = new LinkedList<>();
        EasyExcel.read(filePath).head(CntValueEntity.class).sheet().registerReadListener(new AnalysisEventListener<CntValueEntity>() {

            @Override
            public void invoke(CntValueEntity cntValueEntity, AnalysisContext analysisContext) {
                dataList.add(cntValueEntity);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                log.info("excel read complete!");
            }
        }).doRead();
        return dataList;
    }

    private String getKey(String app, String env) {
        return app + "-" + env;
    }

    private void handleCntValue(String inputFilePath, String outputFilePath) {
        // 从excel取出数据
        List<CntValueEntity> cntValueEntityList = getCntValueFromExcel(inputFilePath);
        Map<String, List<CntValueEntity>> cntDataMap = cntValueEntityList.stream().collect(Collectors.groupingBy(e -> getKey(e.getApp(), e.getEnv())));

        // 目标数据
        List<CntValueEntity> cntResultList = new ArrayList<>();

        for (Map.Entry<String, String> entry : CNT_APP_MAP.entrySet()) {
            String app1 = entry.getKey();
            String app2 = entry.getValue();
            for (String env : ENV_LIST) {
                String key1 = getKey(app1, env);
                String key2 = getKey(app2, env);
                List<CntValueEntity> list1 = cntDataMap.get(key1);
                List<String> list1Key = new ArrayList<>();
                if (CollectionUtils.isEmpty(list1)) {
                    log.warn(key1 + "is empty!");
                } else {
                    list1Key = list1.stream().map(CntValueEntity::getKey).collect(Collectors.toList());
                }
                List<CntValueEntity> list2 = cntDataMap.get(key2);
                if (CollectionUtils.isEmpty(list2)) {
                    log.warn(list2 + "is empty!");
                    continue;
                }
                // 取出list2中，不存在list1 key的数据
                List<String> finalList1Key = list1Key;
                List<CntValueEntity> result = list2.stream().filter(e -> !finalList1Key.contains(e.getKey())).collect(Collectors.toList());
                cntResultList.addAll(result);
            }
        }

        List<CntValueEntity> sortedList = cntResultList.stream().sorted(Comparator.comparing(CntValueEntity::getApp).thenComparing(CntValueEntity::getEnv)).collect(Collectors.toList());
        // 导出到excel
        EasyExcel.write(outputFilePath, CntValueEntity.class).sheet("").doWrite(sortedList);
    }

    public static void main(String[] args) {
        new CntDataHandler().handleCntValue("E:/Desktop/work/findyou_honeylove_cnt.xlsx", "E:/Desktop/work/cnt_result.xlsx");
    }
}
