package com.example.demo.pojo.comPdfKit;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class ComPdfKitResult<T> {
    private String code;
    private String msg;
    private T data;
}
