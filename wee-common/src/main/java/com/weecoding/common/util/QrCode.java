package com.weecoding.common.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.weecoding.common.constant.BaseConstants;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * 二维码生成
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  22:44
 */
@Slf4j
public class QrCode {

    /**
     * 创建二维码 png格式
     *
     * @param content 二维码的内容
     * @return 返回值可能为空
     */
    public static byte[] create(String content) {
        /**
         * 生成二维码
         */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //初始化二维码模型
            BitMatrix bitMatrix = initBitMatrix(content);
            MatrixToImageWriter.writeToStream(bitMatrix, BaseConstants.PNG, bos);
            return bos.toByteArray();
        } catch (Exception e) {
            log.error("【生成二维码】<==异常：", e);
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                log.error("【生成二维码】<==关闭流异常：", e);
            }
        }
        return null;
    }

    /**
     * 创建二维码到指定路径下
     *
     * @param filePath 路径
     * @param content  二维码内容
     * @return
     */
    public static void create(String filePath, String content) {
        /*生成二维码*/
        try {
            BitMatrix bitMatrix = initBitMatrix(content);
            File file = new File(filePath);
            //目录不存在的时候创建目录
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            MatrixToImageWriter.writeToPath(bitMatrix, BaseConstants.PNG, file.toPath());
        } catch (Exception e) {
            log.error("【生成二维码】<==异常：", e);
        }
    }

    /**
     * 创建二维码的BufferedImage对象
     *
     * @param content
     * @return 返回值可能为 空
     */
    public static BufferedImage createBufferedImage(String content) {
        /*生成二维码*/
        try {
            BitMatrix bitMatrix = initBitMatrix(content);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            log.error("【生成二维码】<==异常：", e);
            return null;
        }
    }

    /**
     * 获取二维码的内容
     *
     * @param filePath 二维码路径
     * @return
     */
    public static String getContent(String filePath) {
        MultiFormatReader formatReader = new MultiFormatReader();
        File file = new File(filePath);
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap hints = new HashMap(BaseConstants.MAP_INIT_SIZE) {{
                //指定字符编码为“utf-8”
                put(EncodeHintType.CHARACTER_SET, BaseConstants.UTF8);
            }};
            Result result = formatReader.decode(binaryBitmap, hints);
            return result.toString();
        } catch (Exception e) {
            log.error("【生成二维码】<==异常：", e);
            return null;
        }
    }

    /**
     * 初始化二维码模型
     *
     * @param content
     * @return
     * @throws WriterException
     */
    private static BitMatrix initBitMatrix(String content) throws WriterException {
        //设置图片宽高
        int width = 300, height = 300;
        /*定义二维码的参数*/
        HashMap hints = new HashMap(BaseConstants.MAP_INIT_SIZE) {{
            //指定字符编码为“utf-8”
            put(EncodeHintType.CHARACTER_SET, BaseConstants.UTF8);
            //指定二维码的纠错等级为中级
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            //设置图片的边距
            put(EncodeHintType.MARGIN, 2);
        }};
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }
}
