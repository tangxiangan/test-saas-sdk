package cn.kdan.compdfkit.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class PDFToExcelParameter extends FileParameter {

    /**
     * extractContentOptions（1:OnlyText、2:OnlyTable、3:AllContent）
     */
    private String contentOptions;

    /**
     * createWorksheetOptions（1:ForEachTable、2:ForEachPage、3:ForTheDocument）
     */
    private String worksheetOptions;

}
