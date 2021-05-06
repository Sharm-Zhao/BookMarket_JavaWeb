package sharm.dao;

import sharm.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    Integer queryForPageTotalCount(String orderId);

    List<OrderItem> queryForPageItems(String orderId, int begin, int pageSize);

    /**
     *  查看订单详情，其 OrderItem 中的信息
     * @param orderId 订单 Id 号
     * @return
     */
    public List<OrderItem> queryOrdersByOrderId(String orderId);
}
