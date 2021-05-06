package sharm.test;

import org.junit.Test;
import sharm.dao.OrderItemDao;
import sharm.dao.impl.OrderItemDaoImpl;
import sharm.pojo.OrderItem;
import sharm.pojo.Page;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {

        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        // 不能直接为该表增加数据了，因为该表的 orderId 与另一张表相关联，如果该表变，那另外一张就会有影响。
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567890"));

    }

    @Test
    public void queryOrdersByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        // Java 的String 类型找不到数据库的varchar类型。可以找到。
//        orderItemDao.queryOrdersByOrderId("16158785947351");
        // cao, 我傻了，原来一定要遍历输出
        for (OrderItem book : orderItemDao.queryOrdersByOrderId("16158960475011")) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItems() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        for (OrderItem orderItem : orderItemDao.queryForPageItems("16159466449511", 1, Page.PAGE_SIZE)) {
            System.out.println(orderItem);
        }
    }
}