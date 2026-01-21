import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DataCleaner {

    // 清洗规则1：过滤空字段
    public static boolean isValidLog(String log) {
        if (log == null || log.trim().isEmpty()) return false;
        String[] parts = log.split("\\|");
        if (parts.length != 5) return false;
        // 必须包含用户ID和操作类型
        return !parts[1].isEmpty() && !parts[2].isEmpty();
    }

    // 清洗规则2：提取关键指标
    public static String extractMetrics(String log) {
        String[] parts = log.split("\\|");
        String timestamp = parts[0];
        String userId = parts[1];
        String action = parts[2];
        String status = parts[3];
        String responseTime = parts[4].replace("ms", "");

        // 转换响应时间：超时标记为-1
        int rt;
        try {
            rt = Integer.parseInt(responseTime);
            if (rt > 10000) rt = -1; // 超时
        } catch (NumberFormatException e) {
            rt = -1; // 异常值
        }

        return String.format("%s|%s|%s|%s|%d", timestamp, userId, action, status, rt);
    }

    // 清洗规则3：去重（基于用户ID+操作）
    public static List<String> deduplicate(List<String> logs) {
        Set<String> uniqueKeys = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (String log : logs) {
            String[] parts = log.split("\\|");
            String key = parts[1] + "_" + parts[2]; // user_action
            if (uniqueKeys.add(key)) {
                result.add(log);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> rawLogs = logReader.readLogs("d:\\data\\raw_logs.txt");
        System.out.println("原始条数: " + rawLogs.size());

        // 步骤1：过滤无效
        List<String> validLogs = new ArrayList<>();
        for (String log : rawLogs) {
            if (isValidLog(log)) validLogs.add(log);
        }
        System.out.println("有效条数: " + validLogs.size());

        // 步骤2：提取指标
        List<String> metrics = new ArrayList<>();
        for (String log : validLogs) {
            metrics.add(extractMetrics(log));
        }

        // 步骤3：去重
        List<String> cleanLogs = deduplicate(metrics);
        System.out.println("去重后条数: " + cleanLogs.size());

        // 输出前3条
        System.out.println("\n清洗后示例:");
        for (int i = 0; i < Math.min(3, cleanLogs.size()); i++) {
            System.out.println(cleanLogs.get(i));
        }

        // 保存到文件
        saveToFile(cleanLogs, "d:\\data\\clean_logs.txt");
    }

    public static void saveToFile(List<String> logs, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (String log : logs) {
                pw.println(log);
            }
        } catch (IOException e) {
            System.err.println("保存失败: " + e.getMessage());
        }
    }
    public static void logProcessing(int raw, int valid, int finalCount) {
        // 格式化日志字符串
        String log = String.format("[%s] 原始:%d 有效:%d 去重:%d",
                java.time.LocalDateTime.now(), raw, valid, finalCount);

        // 追加写入到处理日志文件
        try (FileWriter fw = new FileWriter("data/processing.log", true)) {
            fw.write(log + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
