package sharm.test;

import org.junit.Test;
import sharm.dao.OrderDao;
import sharm.dao.impl.OrderDaoImpl;
import sharm.pojo.Order;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void queryOrders() {
        OrderDao order = new OrderDaoImpl();
        // Java 的String 类型找不到数据库的varchar类型。可以找到。
//        orderItemDao.queryOrdersByOrderId("16158785947351");
        // cao, 我傻了，原来一定要遍历输出
        for (Order book : order.queryOrders(1)) {
            System.out.println(book);
        }
    }
}