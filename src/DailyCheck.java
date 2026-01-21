import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * 每日检查脚本
 * 功能：一键执行完整的数据处理流程
 */
public class DailyCheck {

    /**
     * 主方法 - 执行完整流程
     */
    public static void main(String[] args) {
        System.out.println("=== " + LocalDate.now() + " 数据质量检查开始 ===");

        // 步骤1：读取原始日志
        List<String> rawLogs = logReader.readLogs("d:\\data\\raw_logs.txt");
        int rawCount = rawLogs.size();

        // 步骤2：清洗数据
        List<String> cleanLogs = new ArrayList<>();
        for (String log : rawLogs) {
            if (DataCleaner.isValidLog(log)) {
                cleanLogs.add(DataCleaner.extractMetrics(log));
            }
        }
        int validCount = cleanLogs.size();

        // 步骤3：去重
        cleanLogs = DataCleaner.deduplicate(cleanLogs);
        int finalCount = cleanLogs.size();

        // 记录处理日志
        DataCleaner.logProcessing(rawCount, validCount, finalCount);

        // 步骤4：数据入库
        DatabaseLoader.insertCleanLogs(cleanLogs);

        // 步骤5：质量检查
        QualityMonitor.checkDataIntegrity();

        System.out.println("=== 检查完成 ===");
    }
}
