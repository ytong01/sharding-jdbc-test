//package cn.cloudwalk.javaconfig.sharding;
//
//import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
//import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
//import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Arrays;
//
//public final class MultiTableSelect {
//
//    public static void main(final String[] args) throws SQLException {
//        DataSource dataSource = getOrderShardingDataSource();
//        printMultiTableSelect(dataSource);
//    }
//
//    private static void executeUpdate(final DataSource dataSource, final String sql) throws SQLException {
//        try (
//                Connection conn = dataSource.getConnection();
//                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            preparedStatement.executeUpdate();
//        }
//    }
//    private static ShardingDataSource getOrderShardingDataSource() {
//        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
//        TableRule orderTableRule = TableRule.builder("t_order").actualTables(Arrays.asList("t_order_0", "t_order_1")).dataSourceRule(dataSourceRule).build();
//        TableRule orderItemTableRule = TableRule.builder("t_order_item").actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).dataSourceRule(dataSourceRule).build();
//        ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(Arrays.asList(orderTableRule,orderItemTableRule))
//                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
//                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).build();
//        Properties prop = new Properties();
//        prop.setProperty(ShardingPropertiesConstant.SQL_SHOW.getKey(), "true");
//        ShardingDataSource shardingDataSource = new ShardingDataSource(shardingRule , prop);
//        return shardingDataSource;
//    }
//
//    private static void printMultiTableSelect(final DataSource dataSource) throws SQLException {
//        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
//        try (
//                Connection conn = dataSource.getConnection();
//                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            preparedStatement.setInt(1, 10);
//            preparedStatement.setInt(2, 1001);
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    System.out.println(rs.getInt(1));
//                    System.out.println(rs.getInt(2));
//                    System.out.println(rs.getInt(3));
//                }
//            }
//        }
//    }
////    private static DataSource createDataSource(final String dataSourceName) {
//        BasicDataSource result = new BasicDataSource();
//        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        result.setUrl(String.format("jdbc:mysql://127.0.0.1:3306/%s", dataSourceName));
//        result.setUsername("root");
//        result.setPassword("123456");
//        return result;
//    }
//    private static Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>(2);
//        result.put("ds_jdbc_0", createDataSource("ds_jdbc_0"));
//        result.put("ds_jdbc_1", createDataSource("ds_jdbc_1"));
//        return result;
//    }
//
//    ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(Arrays.asList(orderTableRule,orderItemTableRule))
//            .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))))
//            .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
//            .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).build();
//}