import java.sql.*;
import java.time.LocalDate;

public class QualityMonitor {

    // 检查1：总记录数和有效记录数
    public static void checkDataIntegrity() {
        String sql = """
            SELECT
                COUNT(*) as total,
                COUNT(CASE WHEN response_time >= 0 THEN 1 END) as valid,
                COUNT(CASE WHEN response_time = -1 THEN 1 END) as timeout,
                COUNT(CASE WHEN user_id = '' THEN 1 END) as missing_user
            FROM clean_logs
            """;

        try (Connection conn = DriverManager.getConnection(DatabaseLoader.URL, DatabaseLoader.USER, DatabaseLoader.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int total = rs.getInt("total");
                int valid = rs.getInt("valid");
                int timeout = rs.getInt("timeout");
                int missing = rs.getInt("missing_user");

                double missingRate = ((double)(total - valid) / total) * 100;

                System.out.println("=== 数据质量报告 ===");
                System.out.println("总记录数: " + total);
                System.out.println("有效记录: " + valid);
                System.out.println("超时记录: " + timeout);
                System.out.println("缺失用户: " + missing);
                System.out.println("异常率: " + String.format("%.2f%%", missingRate));

                // 保存到质量监控表
                saveQualityReport(total, valid, missingRate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 检查2：按用户统计
    public static void checkUserDistribution() {
        String sql = """
            SELECT user_id, COUNT(*) as action_count, AVG(response_time) as avg_rt
            FROM clean_logs
            WHERE response_time >= 0
            GROUP BY user_id
            HAVING COUNT(*) > 1
            ORDER BY action_count DESC
            """;

        try (Connection conn = DriverManager.getConnection(DatabaseLoader.URL, DatabaseLoader.USER, DatabaseLoader.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== 活跃用户分布 ===");
            while (rs.next()) {
                System.out.printf("用户 %s: %d 次操作, 平均响应 %.0fms\n",
                        rs.getString("user_id"),
                        rs.getInt("action_count"),
                        rs.getDouble("avg_rt"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 保存质量报告到数据库
    public static void saveQualityReport(int total, int valid, double missingRate) {
        String sql = """
            INSERT INTO data_quality (check_date, total_records, valid_records, missing_rate)
            VALUES (?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                total_records = VALUES(total_records),
                valid_records = VALUES(valid_records),
                missing_rate = VALUES(missing_rate)
            """;

        try (Connection conn = DriverManager.getConnection(DatabaseLoader.URL, DatabaseLoader.USER, DatabaseLoader.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, total);
            pstmt.setInt(3, valid);
            pstmt.setDouble(4, missingRate);
            pstmt.executeUpdate();

            System.out.println("质量报告已保存到数据库");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        checkDataIntegrity();
        checkUserDistribution();
    }
}
