package com.example.demo.constant;

/**
 * @author tangxiangan
 */
public interface ComPdfKitConstant {

    String API_V1_OAUTH_TOKEN = "v1/oauth/token";

    String API_V1_CREATE_TASK = "v1/task/{executeTypeUrl}";

    String API_V1_UPLOAD_FILE = "v1/file/upload";

    String API_V1_EXECUTE_TASK = "v1/execute/start";

    String API_V1_TASK_INFO = "v1/task/taskInfo";

    String COM_PDF_KIT_TOKEN = "ComPDFKit_AccessToken";

    String EXCEPTION_MSG_GET_TOKEN_FAIL = "获取ComPDFKit Token 失败";

    String EXCEPTION_MSG_CREATE_TASK_FAIL = "saas创建任务失败";

    String EXCEPTION_MSG_UPLOAD_FILE_FAIL = "saas上传文件失败";

    String EXCEPTION_MSG_EXECUTE_TASK_FAIL = "saas文件转档失败";

    String EXCEPTION_MSG_TASK_INFO_FAIL = "查询saas文件状态失败";
}
