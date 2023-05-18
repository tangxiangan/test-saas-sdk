package io.github.tangxiangan.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class PageInsertParameter extends FileParameter {

    /**
     * page number
     */
    private String targetPage;

    /**
     * Page width (default 595)
     */
    private String width;

    /**
     * page height (842)
     */
    private String height;

    /**
     * Number of pages to insert (default 1)
     */
    private String number;

}
