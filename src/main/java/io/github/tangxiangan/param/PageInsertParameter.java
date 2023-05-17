package io.github.tangxiangan.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class PageInsertParameter extends FileParameter {

    /**
     * 页码
     */
    private String targetPage;

    /**
     * 页面宽度（默认595）
     */
    private String width;

    /**
     * 页面高度（842）
     */
    private String height;

    /**
     * 插入页面数（默认1）
     */
    private String number;

}
