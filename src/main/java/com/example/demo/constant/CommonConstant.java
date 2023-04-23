package com.example.demo.constant;

public interface CommonConstant {

    // 字符串分割符
    String STRING_SIGN_COMMA = ",";
    String STRING_SIGN_PERIOD = ".";
    String STRING_SIGN_COLON = ":";
    String STRING_SIGN_STAR = "*";
    String STRING_UNDERLINE = "_";
    String STRING_VIRGULE = "/";
    String STRIKE_THROUGH = "-";
    String STRING_VERTICAL = "|";
    String STRING_PERCENT = "%";
    String STRING_WAVE = "~";
    String STRING_ENTER = "\n";
    String STRING_EMPTY = "";
    String STRING_THROUGH_AND_SIGN_STAR = "-*";
    String STRING_AT = "@";
    String EQUALS_STR = "=";

    String STRING_IMAGE = "image";

    String STRING_CODE = "code";

    String ENCODING_UTF8 = "utf-8";

    String CODE_OK = "ok";

    String SUCCESS_CODE = "200";

    String AUTH_TOKEN = "x-auth-token";

    String STRING_PNG = "png";
    String STRING_NO_CACHE = "No-cache";
    int SUCCESS = 200;
    String RESULT_SUCCESS = "success";
    String APP_CODE = "PDF Reader";
    String CODE_SUCCESS = "SUCCESS";
    String PAGE_INFO = "pageInfo";

    String PARAMS_MISSING_ERROR = "缺少必要参数！";

    String DUPLICATED_REQUEST_ERROR = "重复请求！";
    String DUPLICATED_REQUEST_HEADER_TIMESTAMP = "timeStamp";
    String DUPLICATED_REQUEST_HEADER_RANDOM = "random";

    String DEFAULT_ENCRYPT_KEY = "1234567890123456";
    String DEFAULT_ENCRYPT_IV_KEY = "6543********4321";
    /**
     * 参数错误状态
     */
    int EXCEPTION_CODE_PARAMETERS_ERROR = 300;
    String EXCEPTION_MSG_PARAMETERS_ERROR = "没有获取到必须的参数或参数格式错误";
    String EXCEPTION_MSG_PARAMETERS_AT_LEAST_ONE = "请至少设置一个有效的参数";

    int ERROR = 400; // 400 失败

    /**
     * 服务错误状态
     */
    int EXCEPTION_CODE_SERVER_ERROR = 500;
    String EXCEPTION_MSG_SERVER_ERROR = "服务错误";

    /**
     * 数据库错误状态
     */
    int EXCEPTION_CODE_SERVER_DATA_ACCESS_ERROR = 600;
    String EXCEPTION_MSG_SERVER_DATA_ACCESS_ERROR = "数据库服务错误";

    /**
     * 运行错误状态
     */
    int EXCEPTION_CODE_RUNTIME_ERROR = 700;
    String EXCEPTION_MSG_RUNTIME_ERROR = "运行时错误";

    /**
     * 用户登录错误状态
     */
    int EXCEPTION_CODE_LOGIN_USER_NOT_FOUND = 800;
    String EXCEPTION_MSG_LOGIN_USER_NOT_FOUND = "未找到登录用户信息";

    int EXCEPTION_CODE_CURRENT_USER_NO_PERMISSION = 801;
    String EXCEPTION_MSG_CURRENT_USER_NO_PERMISSION = "当前用户不具备操作权限";

    int EXCEPTION_CODE_DATA_KEY_DECRYPT = 902;
    String EXCEPTION_MSG_DATA_KEY_DECRYPT = "数据解密失败";

    int EXCEPTION_CODE_DUPLICATED_REQUEST = 903;
    String EXCEPTION_MSG_DUPLICATED_REQUEST = "请不要重复请求";

    /**
     * 字节编码
     */
    String ENCODE_UTF8 = "utf-8";


    String TEXT_HTML_UTF8 = "text/html; charset=utf-8";

    /**
     * 分页默认第一页
     */
    int PAGENUM = 1;

    /**
     * 分页默认每页显示10条
     */
    int PAGESIZE = 10;

    /**
     * 分页默认排序规则
     */
    String ORDERBYCLAUSE = "create_time desc";

    String verifyMessage = "您正在%s，验证码为%s，十五分钟后失效。请您按照提示填写验证码，切勿将验证码泄露于他人。";

    String resetPasswordEmailTemplate = "<p>尊敬的用户您好：</p>\n" +
            "<p>请点击下方的链接以便对您的17PDF Reader(Staging)账号密码进行重置</p>\n" +
            "<table class=\"btn-primary\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "  <tr>\n" +
            "    <td>\n" +
            "    <a href=\"%s\"> <button>重置密码</button></a>\n" +
            "    </td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "<p>如果无法点击，请将链接复制到浏览器地址栏，访问该地址进行重置</p>\n" +
            "<p>系统邮件，请勿回复。</p>";

    String registerEmailTemplate = "<p><b>尊敬的PDF Reader用户，</b></p>\n" +
            "\n" +
            "<p>您正在注册长沙凯钿软件PDF Reader 平台账号，</p>\n" +
            "\n" +
            "<p>验证码是：<b>\"%s\"</b>，请复制该验证码并完成电子邮件验证过程，</p>\n" +
            "\n" +
            "<p>出于安全考虑，该验证码<b>3分钟内</b>有效，且只能使用一次，请及时输入。</p>\n" +
            "\n" +
            "<p>PS：如果没有验证码，您的账户将无法访问；如果您没有提交该请求，请忽略本条消息</p>\n" +
            "\n" +
            "<p>非常感谢您对PDF Reader的支持，祝您一切顺利</p>\n" +
            "<p>长沙凯钿软件</p>";

    /**
     * 名称校验不通过信息
     */
    String NAME_INVALID_MESSAGE="名称必须以下划线、数字、字母开头，不能包含特殊字符";

    /**
     * 文件起始行
     */
    Integer SOURCE_LINE_FROM = 1;

    String PASSWORD_STRENTH = "1";
    /**
     * 文件结束行
     */
    Integer SOURCE_LINE_TO = 500;

    String ZERO_STRING = "0";

    /** 手机号正则验证 */
    String mobileRegex = "^(1[0-9]{10})$";
    /** 邮箱正则验证 */
    String emailRegex = "^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";

}
