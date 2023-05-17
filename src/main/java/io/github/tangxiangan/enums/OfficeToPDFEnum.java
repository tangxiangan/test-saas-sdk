package io.github.tangxiangan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author TANGXIANGAN 2022/7/14
 */
@Getter
@RequiredArgsConstructor
public enum OfficeToPDFEnum {

    DOC_TO_PDF("doc/pdf"),
    DOCX_TO_PDF("docx/pdf"),
    XLSX_TO_PDF("xlsx/pdf"),
    XLS_TO_PDF("xls/pdf"),
    PPT_TO_PDF("ppt/pdf"),
    PPTX_TO_PDF("pptx/pdf"),
    TXT_TO_PDF("txt/pdf"),
    PNG_TO_PDF("png/pdf"),
    HTML_TO_PDF("html/pdf"),
    CSV_TO_PDF("csv/pdf"),
    RTF_TO_PDF("rtf/pdf");

    private final String value;

    public static OfficeToPDFEnum getInstance(String value) {
        for (OfficeToPDFEnum pdfToOfficeEnum : values()) {
            if (pdfToOfficeEnum.value.equals(value)) {
                return pdfToOfficeEnum;
            }
        }
        return null;
    }


}
