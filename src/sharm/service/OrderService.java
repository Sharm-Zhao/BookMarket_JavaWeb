package sharm.service;

import sharm.pojo.Cart;
import sharm.pojo.Order;
import sharm.pojo.OrderItem;
import sharm.pojo.Page;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    /**
     * 返回订单的列表
     * @return
     */
    public List<Order> queryOrders(Integer userId);

    Page<Order> page(Integer userId, int pageNo, int pageSize);

    /**
     * 根据订单号查询该订单的详细信息，包括 name count 单价 总价
     * @param orderId
     * @return 某个订单号的订单列表
     */
    public List<OrderItem> showOrderDetail(String orderId);

    Page<OrderItem> page2(String orderId, int pageNo, int pageSize);




}
