package io.github.tangxiangan.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class PDFCompressParameter extends FileParameter {

    /**
     * 压缩比例 0-100
     */
    String quality;

}
