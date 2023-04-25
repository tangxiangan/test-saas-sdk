package com.kdan.compdfkit.controller;


import com.kdan.compdfkit.client.ComPdfKitClient;
import com.kdan.compdfkit.pojo.comPdfKit.CreateTaskResult;
import com.kdan.compdfkit.pojo.comPdfKit.QueryTaskInfoResult;
import com.kdan.compdfkit.utils.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * @author tangxiangan
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    public QueryTaskInfoResult test(@RequestParam(value="file") MultipartFile file) {
        File convertFile = FileUtils.multipartFileToFile(file);
        ComPdfKitClient client = new ComPdfKitClient("public_key_6617213b0fafc63575dabbfd69809c5f","secret_key_663d28ff2657e76e648e9f4e1b0cef12");
        CreateTaskResult result = client.createTask("pdf/docx");
        client.uploadFile(convertFile,result.getTaskId(),"");
        client.executeTask(result.getTaskId());
        QueryTaskInfoResult taskInfo = client.queryTaskInfo(result.getTaskId());
        return taskInfo;
    }





}
