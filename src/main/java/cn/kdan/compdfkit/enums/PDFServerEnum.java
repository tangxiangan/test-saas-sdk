package cn.kdan.compdfkit.enums;

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
    INSERT("pdf/insert"),
    ADD_WATERMARK("pdf/addWatermark"),
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
