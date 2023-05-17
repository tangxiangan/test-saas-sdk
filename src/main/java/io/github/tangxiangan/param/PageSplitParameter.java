package io.github.tangxiangan.param;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageSplitParameter extends FileParameter {

    /**
     * 输入文件页码
     */
    private List<String> pagesList;
    /**
     * 输入文件
     */
    private File inputFile;

}
