package cn.kdan.compdfkit.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class PDFCompressParameter extends FileParameter {

    /**
     * Compression ratio 0-100
     */
    String quality;

}