package io.github.tangxiangan.controller;


import io.github.tangxiangan.client.ComPdfKitClient;
import io.github.tangxiangan.enums.PDFServerEnum;
import io.github.tangxiangan.param.FileParameterFactory;
import io.github.tangxiangan.param.PageInsertParameter;
import io.github.tangxiangan.pojo.comPdfKit.CreateTaskResult;
import io.github.tangxiangan.pojo.comPdfKit.QueryTaskInfoResult;
import io.github.tangxiangan.utils.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        ComPdfKitClient client = new ComPdfKitClient("public_key_6617213b0fafc63575dabbfd69809c5f", "secret_key_663d28ff2657e76e648e9f4e1b0cef12");
        CreateTaskResult result = client.createTask(PDFServerEnum.INSERT.getValue());
        PageInsertParameter parameter = FileParameterFactory.getFileParameterByType(PDFServerEnum.INSERT);
        parameter.setHeight("300");
        parameter.setTargetPage("1");
        client.uploadFile(convertFile, result.getTaskId(), parameter);
        client.executeTask(result.getTaskId());
        return client.queryTaskInfo(result.getTaskId());
    }

}
