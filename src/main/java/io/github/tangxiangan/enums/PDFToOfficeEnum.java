package io.github.tangxiangan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author TANGXIANGAN 2022/7/14
 */
@Getter
@RequiredArgsConstructor
public enum PDFToOfficeEnum {

    PDF_TO_WORD("pdf/docx"),
    PDF_TO_EXCEL("pdf/xlsx"),
    PDF_TO_PPT("pdf/pptx"),
    PDF_TO_TXT("pdf/txt"),
    PDF_TO_PNG("pdf/png"),
    PDF_TO_JPG("pdf/jpg"),
    PDF_TO_HTML("pdf/html"),
    PDF_TO_RTF("pdf/rtf"),
    PDF_TO_CSV("pdf/csv");

    private final String value;

    public static PDFToOfficeEnum getInstance(String value) {
        for (PDFToOfficeEnum pdfToOfficeEnum : values()) {
            if (pdfToOfficeEnum.value.equals(value)) {
                return pdfToOfficeEnum;
            }
        }
        return null;
    }


}
