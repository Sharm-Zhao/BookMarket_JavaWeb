package sharm.dao;

import sharm.pojo.Order;
import sharm.pojo.OrderItem;

import java.util.List;

public interface OrderDao {

    public int saveOrder(Order order);

    Integer queryForPageTotalCount(Integer userId);

    List<Order> queryForPageItems(Integer user_id, int begin, int pageSize);

    /**
     *  查询某个用户的所有订单
     * @return 该用户的所有订单项
     */
    public List<Order> queryOrders(Integer user_id);

    /**
     *  查询订单状况：已发货或者未发货
     * @param orderId
     * @param status
     * @return
     */
    public int changeOrderStatus(int orderId, int status);

    /**
     *  查看订单详情，其 OrderItem 中的信息
     * @param orderId 订单 Id 号
     * @return
     */
    public OrderItem queryOrdersByOrderId(int orderId);

}
