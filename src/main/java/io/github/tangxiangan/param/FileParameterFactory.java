package io.github.tangxiangan.param;

import io.github.tangxiangan.enums.PDFServerEnum;
import io.github.tangxiangan.enums.PDFToOfficeEnum;

import java.util.Objects;

/**
 * @author tangxiangan
 */
public class FileParameterFactory {
    public static FileParameter getFileParameterByType(PDFToOfficeEnum type) {
        if (Objects.requireNonNull(type) == PDFToOfficeEnum.PDF_TO_WORD) {
            return null;
            //TODO 定义其他类型的转档参数对象
        }
        throw new IllegalArgumentException("Unsupported type:" + type);
    }

    public static <T extends FileParameter> T getFileParameterByType(PDFServerEnum type) {
        Class<T> clazz;
        FileParameter fileParameter;
        switch (type) {
            case INSERT:
                clazz = (Class<T>) PageInsertParameter.class;
                fileParameter = new PageInsertParameter();
                break;
            case SPLIT:
                clazz = (Class<T>) PageSplitParameter.class;
                fileParameter = new PageSplitParameter();
                break;
            case MERGE:
                clazz = (Class<T>) PageMergeParameter.class;
                fileParameter = new PageMergeParameter();
                break;
            case COMPRESS:
                clazz = (Class<T>) PDFCompressParameter.class;
                fileParameter = new PDFCompressParameter();
                break;
            case DELETE:
                clazz = (Class<T>) PageDeleteParameter.class;
                fileParameter = new PageMergeParameter();
                break;
            case EXTRACT:
                clazz = (Class<T>) PageExtractParameter.class;
                fileParameter = new PageMergeParameter();
                break;
            case ROTATION:
                clazz = (Class<T>) PageRotationParameter.class;
                fileParameter = new PageRotationParameter();
                break;
            case ADD_WATERMARK:
                clazz = (Class<T>) AddWatermarkParameter.class;
                fileParameter = new AddWatermarkParameter();
                break;
            default:
                throw new IllegalArgumentException("Unsupported type:" + type);
        }
        return clazz.cast(fileParameter);
    }


}
