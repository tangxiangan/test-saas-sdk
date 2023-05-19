package io.github.tangxiangan.param;

import lombok.Data;

import java.io.File;
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
