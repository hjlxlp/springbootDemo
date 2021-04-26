//package com.example.demo.service.impl;
//
//import com.example.demo.component.CancelOrderSender;
//import com.example.demo.service.OmsPortalOrderService;
//import com.example.demo.vo.OrderParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * 前台订单管理Service
// * Created by macro on 2018/8/30.
// */
//@Service
//public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
//
//    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
//
//    @Autowired
//    private CancelOrderSender cancelOrderSender;
//
//    @Override
//    public String generateOrder(OrderParam orderParam) {
//        //todo 执行一系类下单操作，具体参考mall项目
//        LOGGER.info("process generateOrder");
//        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
//        sendDelayMessageCancelOrder(11L);
//        return "下单成功";
//    }
//
//    @Override
//    public void cancelOrder(Long orderId) {
//        //todo 执行一系类取消订单操作，具体参考mall项目
//        LOGGER.info("process cancelOrder orderId:{}", orderId);
//    }
//
//    private void sendDelayMessageCancelOrder(Long orderId) {
//        //获取订单超时时间，假设为60分钟(测试用的30秒)
//        long delayTimes = 30 * 1000;
//        //发送延迟消息
//        cancelOrderSender.sendMessage(orderId, delayTimes);
//    }
//
//}
