package cn.kdan.compdfkit.param;

import lombok.Data;

import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageExtractParameter extends FileParameter {

    /**
     * Enter document page number
     */
    private List<String> pageOptions;

}
