package cn.kdan.compdfkit.constant;

/**
 * @author tangxiangan
 */
public interface CommonConstant {

    String SUCCESS_CODE = "200";

    int SUCCESS = 200;

    String RESULT_SUCCESS = "success";

    // 字符串分割符
    String STRING_SIGN_PERIOD = ".";
    String STRING_SIGN_COLON = ":";
    String PARAMS_MISSING_ERROR = "缺少必要参数！";

    /**
     * 参数错误状态
     */
    int EXCEPTION_CODE_PARAMETERS_ERROR = 300;

    /**
     * 服务错误状态
     */
    int EXCEPTION_CODE_SERVER_ERROR = 500;
    /**
     * 运行错误状态
     */
    int EXCEPTION_CODE_RUNTIME_ERROR = 700;
}
