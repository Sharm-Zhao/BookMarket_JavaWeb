package sharm.service.impl;

import sharm.dao.BookDao;
import sharm.dao.OrderDao;
import sharm.dao.OrderItemDao;
import sharm.dao.impl.BookDaoImpl;
import sharm.dao.impl.OrderDaoImpl;
import sharm.dao.impl.OrderItemDaoImpl;
import sharm.pojo.*;
import sharm.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        System.out.println(" OrderServiceImpl 程序在[" +Thread.currentThread().getName() + "]中");

        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);

        }
        // 清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryOrders(Integer userId) {
        return orderDao.queryOrders(userId);
    }

    @Override
    public Page<Order> page(Integer userId, int pageNo, int pageSize) {
        Page<Order> page = new Page<Order>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = orderDao.queryForPageTotalCount(userId);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Order> items = orderDao.queryForPageItems(userId, begin, pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }


    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return  orderItemDao.queryOrdersByOrderId(orderId);
    }

    @Override
    public Page<OrderItem> page2(String orderId, int pageNo, int pageSize) {
        Page<OrderItem> page2 = new Page<OrderItem>();

        // 设置每页显示的数量
        page2.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = orderItemDao.queryForPageTotalCount(orderId);
        // 设置总记录数
        page2.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page2.setPageTotal(pageTotal);

        // 设置当前页码
        page2.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page2.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<OrderItem> items = orderItemDao.queryForPageItems(orderId, begin, pageSize);
        // 设置当前页数据
        page2.setItems(items);

        return page2;
    }
}
