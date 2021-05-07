<p align="center">
  <a href="https://github.com/Sharm-Zhao/BookMarket_JavaWeb"><img src="https://img.shields.io/badge/阅读-read-brightgreen.svg" alt="阅读"></a>
  <a href="https://blog.csdn.net/weixin_44262126/article/details/113413122"><img src="https://img.shields.io/badge/博客-read-critical.svg" alt="博客"></a>
</p>

### 1 项目背景（Background）

该项目来源于尚硅谷的 JavaWeb 课程，可惜源代码并没有完成指定的功能，且存在多处 Bug，简直防不甚防。本人愚钝，花了几个月才将指定需求完成，同时增加并完善了项目的相关功能。

更详细版本的项目介绍写在了 CSDN 上，有兴趣的同学可以参考参考。[传送门](https://blog.csdn.net/weixin_44262126/article/details/113413122)

* **主要需求**

该项目类似于简单的淘宝购物项目，如果从业务的需求上来看，可以将该书城项目三个模块。

| 模块                     | 需求                                                         |
| ------------------------ | ------------------------------------------------------------ |
| **用户的注册和登录模块** | 1）当用户在该书城网站上未注册时，只能进行浏览，无法进行购物；2）当用户在该网站上完成注册且登录成功后，可以执行网站的全部功能； |
| **图书的管理模块**       | 1）设置图书库存，即实现图书的增删改查；2）将库存中图书分页显示在主页上，供用户加入购物车购买； |
| **订单的管理模块**       | 当用户在购物车提交订单后，用户订单中会显示该用户的每笔订单的创建时间、订单内容。 |

**次要需求**

1. 图书、订单等的分页；
2. JavaScript 事件的注册；
3. 图书的检索查找；
4. 利用第三方 jar 包完成注册时验证码的验证。



### 2 视频演示（Demo video）

点击[传送门](https://www.bilibili.com/video/BV1YK4y1T7BF/)，查看视频演示。



### 3 安装（Install）

本项目使用的环境：JDK11、MySQL8.0、Tomcat8.5.63 等。



### 4 使用（Usage）

1 解压压缩包，然后直接用 IEDA 打开；

2 依次点击如图，然后点击确认；

![image-20210506210541349](https://raw.githubusercontent.com/Sharm-Zhao/Picture/main/BookMarket/bookmarket_1.png)

![image-20210506210629169](https://raw.githubusercontent.com/Sharm-Zhao/Picture/main/BookMarket/bookmarket_2.png)

3 直接点击 IDEA 的运行按钮，便可直接运行

![image-20210506210722342](https://raw.githubusercontent.com/Sharm-Zhao/Picture/main/BookMarket/bookmarket_3.png)





**注意事项：**由于上传的项目中并不包含 sql 语句，所以我把该项目需要的 sql 语句贴在下面。

1 创建用户表的 sql

```sql
create database book;

use book;
create table t_user(
    # 初始话定义不同变量
    # 其中 ID 为自增；用户名唯一且非空；密码非空；邮箱没有要求
                     `user_id` int primary key auto_increment,
                     `username` varchar(20) not null  unique,
                     `password` varchar(32) not null ,
                     `email` varchar(100)
);
insert into t_user(`username`,`password`,`email`) values('sharm','123456','sharm@126.com');
```

2 创建书城图书的 sql

```sql
use book;
#下面就是为了创建图书模块的数据库
create table book(
                       `id` int primary key auto_increment,
                       `name` varchar(100),
                       `price` decimal(11,2),
                       `author` varchar(100),
                       `sales` int,
                       `stock` int,
                       `img_path` varchar(200)
);

## 插入初始化测试数据
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '高等数学' , '鸣人' , 99.50 , 48 , 34 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '张宇十八讲' , '张宇' , 9.90 , 102 , 78 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '论文写作指导' , '邓华' , 49.00 , 64 , 36 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '操作系统原理' , '谭浩强' , 133.05 , 122 , 188 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '三体' , '刘烨' , 89.15 , 20 , 10 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '论文写作指导' , '刘慈欣' , 49.00 , 64 , 36 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '飘' , '雷丰阳' , 133.05 , 122 , 188 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '自动控制原理' , '王万良' , 89.15 , 20 , 10 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '第七日' , '余华' , 49.00 , 64 , 36 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '人生' , '路遥' , 133.05 , 122 , 188 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
values(null , '活着' , '余华' , 89.15 , 20 , 10 , 'static/img/default.jpg');
## 查看表内容
select id,name,author,price,sales,stock,img_path from book;

```

3 创建书城图书订单的 sql

```sql
use book;
create table t_order(
                        `order_id` varchar(50) primary key,
                        `create_time` datetime,
                        `price` decimal(11,2),
                        `status` int,
                        `user_id` int,
                        foreign key(`user_id`) references t_user(`user_id`)
);

create table t_order_item(
                             `id` int primary key auto_increment,
                             `name` varchar(100),
                             `count` int,
                             `price` decimal(11,2),
                             `total_price` decimal(11,2),
                             `order_id` varchar(50),
                             foreign key(`order_id`) references t_order(`order_id`)
);
```



### 5 联系（Contact）

虽然已经尽力将每个细节都写在 readme 、博客和代码注释中，但难免会有所疏忽，如若在该仓库中遇到问题，可以联系 share_me@126.com，欢迎一起交流。



