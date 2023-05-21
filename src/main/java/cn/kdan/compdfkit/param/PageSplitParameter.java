package cn.kdan.compdfkit.param;

import lombok.Data;

import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageSplitParameter extends FileParameter {

    /**
     * Enter file page number
     */
    private List<String> pageOptions;

}
