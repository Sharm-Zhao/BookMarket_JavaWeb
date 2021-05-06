package sharm.dao.impl;

import sharm.dao.OrderItemDao;
import sharm.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotal_price(),
                orderItem.getOrder_id());
    }

    @Override
    public Integer queryForPageTotalCount(String orderId) {
        String sql = "select count(*) from t_order_item where `order_id` = ?";
        Number count = (Number) queryForSingleValue(sql, orderId);
        return count.intValue();
    }

    @Override
    public List<OrderItem> queryOrdersByOrderId(String orderId) {
        // 假设 Dao 层的这句语句成功了
        String sql = "select  `id`, `name`, `count`,`price`,`total_price`,`order_id` from t_order_item where `order_id` = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }

    @Override
    public List<OrderItem> queryForPageItems(String orderId, int begin, int pageSize ) {
        String sql = "select `name`, `count` , `price` , `total_price`, `order_id` from t_order_item where `order_id` = ? limit ?,?";
        return queryForList(OrderItem.class, sql, orderId, begin, pageSize);
    }
}
