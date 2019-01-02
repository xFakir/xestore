package com.xcx.xestore.common.constant;

/**
 * @ClassName : OrderConst
 * @Description : 订单常量
 * @Author : xcx
 * @Date : 2018-10-09 14:52
 * @Version : 1.0
 */
public class OrderConst {
    //订单相关

    //订单未付款
    public static final Integer ORDER_NOT_PAID = 301;

    //订单已付款
    public static final Integer ORDER_PAID = 302;

    //订单未发货
    public static final Integer ORDER_NOT_DELIVERED = 303;

    //订单已发货
    public static final Integer ORDER_DELIVERED = 304;

    //订单交易成功
    public static final Integer ORDER_SUCCESS = 305;

    //订单交易关闭
    public static final Integer ORDER_CLOSED = 306;

    //订单未评价
    public static final Integer ORDER_NOT_EVALUATED = 307;

    //订单已评价
    public static final Integer ORDER_EVALUATED = 308;

    //货到付款
    public static final Integer CASH_ON_DELIVERY = 309;

    //打白条
    public static final Integer WHITE_STRIPES = 310;

    //余额支付
    public static final Integer PAY_BY_BALANCE = 311;

    //银行卡支付
    public static final Integer PAY_BY_BANK = 312;
}
