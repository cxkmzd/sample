package sample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author cxkmzd
 *
 */
public class DbConnection {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // ODO 自動生成されたメソッド・スタブ
        Connection conn = null;
        try {
            //JDBCドライバー読み込み
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            //下記の何れかでデータベース接続（ユーザー名：DBUSER パスワード：0000 ネットサービス名：orclpdb プラガブルDB名：orclpdbの場合）
            //OCI接続の場合
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.27:1521:xe","SYSTEM","oraclecxkmzd");
            
            //事前にデータベースに作成したTESTテーブルに対してSELECT文を準備
            String sql = "SELECT ID, NAME, AGE FROM Sample";
            
            //DBにSELECT文を送信する為にPreparedStatementインスタンスに格納
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //SELECT文の結果をResultSetインスタンスに格納
            ResultSet rs = ps.executeQuery();
            
            //レコードが存在する間はループ処理でSELECT文で取得したカラムを取り出し、コンソールに出力
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String age = rs.getString("AGE");
                System.out.println("No." + id + " 名前:" + name + " 年齢:" + age);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //データベースが接続されている場合
            if (conn != null) {
                try {
                    //接続を切断
                    conn.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}