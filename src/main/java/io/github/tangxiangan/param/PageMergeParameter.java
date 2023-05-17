package io.github.tangxiangan.param;

import lombok.Data;

import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageMergeParameter extends FileParameter {
    /**
     * 输入文件以及页码
     */
    private List<PageMergeInputFileParameter> inputFileDTOList;

}
