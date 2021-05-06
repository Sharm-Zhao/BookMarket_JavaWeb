package sharm.test;

import sharm.pojo.Cart;
import sharm.pojo.CartItem;
import sharm.pojo.Order;
import sharm.pojo.OrderItem;
import sharm.service.OrderService;
import sharm.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    OrderService orderService= new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }

    @Test
    public void queryOrders() {
        for (Order orders : orderService.queryOrders(1)) {
            System.out.println(orders);
        }
    }

    @Test
    public void showOrderDetail(){
        for (OrderItem orderItem : orderService.showOrderDetail("16155333252741")) {
            System.out.println(orderItem);
        }
    }

}