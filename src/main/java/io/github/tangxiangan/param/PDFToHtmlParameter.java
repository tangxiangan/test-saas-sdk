package io.github.tangxiangan.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class PDFToHtmlParameter extends FileParameter {

    /**
     * pageOptions 1:SinglePage、2:SinglePageNavigationByBookmarks、3:MultiplePages、4:MultiplePagesSplitByBookmarks
     */
    private String pageOptions;

}
