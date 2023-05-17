package io.github.tangxiangan.param;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageMergeInputFileParameter {

    /**
     * 输入文件
     */
    private File inputFile;

    /**
     * 文件页码
     */
    private List<String> filePages;
}
