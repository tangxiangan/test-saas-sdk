package io.github.tangxiangan.param;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class PageRotationParameter extends FileParameter {

    /**
     * Enter file page number
     */
    private List<String> pagesList;

    /**
     * Rotation angle 0 90 180 270
     */
    private String rotation;

}
