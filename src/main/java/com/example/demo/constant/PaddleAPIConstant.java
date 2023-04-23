package com.example.demo.constant;

/**
 * @author ComPDFKit-WPH 2023/1/16
 * <p>
 * PaypalAPIConstant
 */
public interface PaddleAPIConstant {

    String LIST_TRANSACTIONS = "/product/{id}/transactions";
    String CANCEL_SUBSCRIPTION = "/subscription/users_cancel";
    String UPDATE_SUBSCRIPTION = "/subscription/users/update";

    String VENDORID = "vendor_id";
    String VENDORAUTHCODE = "vendor_auth_code";
    /**
     * 订阅支付链接支付成功
     */
    String SUBSCRIPTION_PAYMENT_SUCCEEDED = "subscription_payment_succeeded";
    /**
     * 创建订阅
     */
    String SUBSCRIPTION_CREATED = "subscription_created";
//    /**
//     * 获取订阅信息
//     */
//    String GET_SUBSCRIPTIONS_INFO = "/v1/billing/subscriptions/";

}
