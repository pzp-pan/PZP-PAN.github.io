import java.sql.*;
import java.util.List;

public class DatabaseLoader {
    public static final String URL = "jdbc:mysql://localhost:3306/log_analysis?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    // 批量插入清洗后的日志
    public static void insertCleanLogs(List<String> logs) {
        String sql = "INSERT INTO clean_logs (log_time, user_id, action, status, response_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int batchSize = 0;
            for (String log : logs) {
                String[] parts = log.split("\\|");
                // 解析时间
                Timestamp timestamp = Timestamp.valueOf(parts[0].replace(" ", " "));
                // 设置参数
                pstmt.setTimestamp(1, timestamp);
                pstmt.setString(2, parts[1]);
                pstmt.setString(3, parts[2]);
                pstmt.setString(4, parts[3]);
                pstmt.setInt(5, Integer.parseInt(parts[4]));

                pstmt.addBatch();
                batchSize++;

                // 每100条提交一次
                if (batchSize % 100 == 0) {
                    pstmt.executeBatch();
                }
            }
            // 提交剩余
            pstmt.executeBatch();
            System.out.println("成功插入 " + logs.size() + " 条记录");

        } catch (SQLException e) {
            System.err.println("数据库错误: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 读取清洗后的数据
        List<String> cleanLogs = logReader.readLogs("d:\\data\\clean_logs.txt");
        // 入库
        insertCleanLogs(cleanLogs);
    }
}
