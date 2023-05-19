package io.github.tangxiangan.param;

import lombok.Data;

import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageMergeParameter extends FileParameter {
    /**
     * file page number
     */
    private List<String> pageOptions;

}
