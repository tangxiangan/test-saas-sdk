package io.github.tangxiangan.param;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class AddWatermarkParameter extends FileParameter {

    /**
     * 注释类型
     */
    private String type;

    /**
     * 缩放
     */
    private String scale;

    /**
     * 透明度0~1
     */
    private String opacity;

    /**
     * 旋转弧度，正数为逆时针旋转
     */
    private String rotation;

    /**
     * 页码，页码从开始，例如：1,2,4,6
     */
    private String targetPages;

    /**
     * 垂直对齐方式：top、center、bottom
     */
    private String vertalign;

    /**
     * 水平对齐方式：left、center、right
     */
    private String horizalign;

    /**
     * 水平偏移量
     */
    private String xoffset;

    /**
     * 垂直偏移量
     */
    private String yoffset;

    /**
     * 图片路径
     */
    private String imagepath;

    /**
     * 文本
     */
    private String content;

    /**
     * 文本颜色，例如：#FFFFFF
     */
    private String textColor;

    /**
     * 是否在最前面
     */
    private String front;

    /**
     * 是否铺满整个页面
     */
    private String fullScreen;

    /**
     * 水平方向间距
     */
    private String horizontalSpace;

    /**
     * 垂直方向间距
     */
    private String verticalSpace;

    /**
     * 扩展信息，base64编码
     */
    private String extension;

}
