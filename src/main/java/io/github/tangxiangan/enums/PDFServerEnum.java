package io.github.tangxiangan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * @author tangxiangan
 */

@Getter
@RequiredArgsConstructor
public enum PDFServerEnum {

    SPLIT("pdf/split"),
    MERGE("pdf/merge"),
    COMPRESS("pdf/compress"),
    DELETE("pdf/delete"),
    EXTRACT("pdf/extract"),
    ROTATION("pdf/rotation"),
    /**
     * 插入页面
     */
    INSERT("pdf/insert"),
    /**
     * 增加水印
     */
    ADD_WATERMARK("pdf/addWatermark"),
    /**
     * 删除水印
     */
    DEL_WATERMARK("pdf/delWatermark");

    private final String value;

    public static PDFServerEnum getInstance(String value) {
        for (PDFServerEnum pdfServerEnum : values()) {
            if (pdfServerEnum.value.equals(value)) {
                return pdfServerEnum;
            }
        }
        return null;
    }


}
