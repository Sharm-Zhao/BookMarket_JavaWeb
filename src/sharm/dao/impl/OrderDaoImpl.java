package sharm.dao.impl;

import sharm.dao.OrderDao;
import sharm.pojo.Order;
import sharm.pojo.OrderItem;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        System.out.println(" OrderDaoImpl 程序在[" +Thread.currentThread().getName() + "]中");

        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        // 原来 sql 语句中的问号针对的不是 `` 中的变量，而是下面这个语句中的变量
        return update(sql,order.getOrder_id(),order.getCreate_time(),order.getPrice(),order.getStatus(),order.getUser_id());
    }

    @Override
    public List<Order> queryOrders(Integer userId) {
        // 无框架时如何配置数据库带有下划线字段对应Java实体类属性
        String sql = "select  `order_id`, `create_time` , `price` , `status` ,`user_id` from t_order where `user_id` " +
                "= ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public Integer queryForPageTotalCount(Integer userId) {
        String sql = "select count(*) from t_order where `user_id` = ?";
        Number count = (Number) queryForSingleValue(sql, userId);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageItems(Integer user_id, int begin, int pageSize) {
        String sql = "select `order_id`, `create_time` , `price` , `status`  from t_order where `user_id` =" +
                " ? limit ?,? ";
        return queryForList(Order.class, sql, user_id, begin, pageSize);
    }


    @Override
    public int changeOrderStatus(int orderId, int status) {
        String sql = "update t_order set `status`=? where `order_id`=? ";
        return update(sql, status, orderId);
    }

    @Override
    public OrderItem queryOrdersByOrderId(int orderId) {
        return null;
    }
}
