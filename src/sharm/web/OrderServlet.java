package sharm.web;

import sharm.pojo.*;
import sharm.service.OrderService;
import sharm.service.impl.OrderServiceImpl;
import sharm.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        System.out.println("OrderServlet程序在[" +Thread.currentThread().getName() + "]中");

        Integer userId = loginUser.getUser_id();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);
//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    // order 的显示
    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Integer userId = WebUtils.parseInt(req.getParameter("userId"), 0);

        //2 调用OrderService.page(pageNo，pageSize)：Page对象
        Page<Order> page = orderService.page(userId, pageNo, pageSize);
        page.setUrl("orderServlet?action=page");

        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        User loginUser = (User) req.getSession().getAttribute("user");
        Integer userId = loginUser.getUser_id();

        //1 通过OrderService查询全部图书
        List<Order> orders = orderService.queryOrders(userId);
        // 当该用户的订单数量为 0 时，提示用户自己现在还没有订单，并且转到首页购物
        if(orders.size() == 0){
            req.getRequestDispatcher("/pages/order/emptyOrder.jsp").forward(req,resp);
            // 如果不加上 return 强制跳出该方法，代码仍然会执行下去，从而导致程序抛出异常。
            return;
        }

        //2 把全部订单保存到Request域中
        req.setAttribute("orders", orders);
        //3 分页
        String url = req.getContextPath() + String.format("/orderServlet?action=page&pageNo=%d&userId=%d", pageNo,
                userId);

        resp.sendRedirect(url);
    }



    // orderItem 的显示
    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        String orderId = req.getParameter("orderId");

        //2 调用OrderService.page(pageNo，pageSize)：Page对象
        Page<OrderItem> page2 = orderService.page2(orderId, pageNo, pageSize);
        page2.setUrl("orderServlet?action=page2");

        //3 保存Page对象到Request域中
        req.setAttribute("page2",page2);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req,resp);
    }

    // 该方法的功能是显示订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;


        //1 获取请求的参数：订单号
        String orderId = req.getParameter("orderId");
        // 2 调用bookService.showOrderDetail 查询订单
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        // 3 保存订单列表到Request域中
        req.setAttribute("orderItems", orderItems) ;
//
//        //4 请求转发到。pages/manager/book_edit.jsp页面
//        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req,resp);

        //4 分页
        String url = req.getContextPath() + String.format("/orderServlet?action=page2&pageNo=%d&orderId=%s", pageNo,
                orderId);
        resp.sendRedirect(url);
    }
}
