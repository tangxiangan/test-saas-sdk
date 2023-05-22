//  Copyright © 2014-2023 PDF Technologies, Inc. All Rights Reserved.
//
//  THIS SOURCE CODE AND ANY ACCOMPANYING DOCUMENTATION ARE PROTECTED BY INTERNATIONAL COPYRIGHT LAW
//  AND MAY NOT BE RESOLD OR REDISTRIBUTED. USAGE IS BOUND TO THE ComPDFKit LICENSE AGREEMENT.
//  UNAUTHORIZED REPRODUCTION OR DISTRIBUTION IS SUBJECT TO CIVIL AND CRIMINAL PENALTIES.
//  This notice may not be removed from this file.

package cn.kdan.compdfkit.constant;


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
