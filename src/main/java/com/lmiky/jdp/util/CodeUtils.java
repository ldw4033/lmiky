package com.lmiky.jdp.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lmiky.jdp.logger.util.LoggerUtils;

/**
 * 条形码/二维码工具
 * @author lmiky
 * @date 2014年9月15日 上午11:08:56
 */
public class CodeUtils {

	/**
	 * 生成二维码
	 * @author lmiky
	 * @date 2014年9月15日 上午11:10:42
	 * @param contents
	 * @param outputImgPath
	 * @param outputImgWidth
	 * @param outputImgHeight
	 * @return
	 */
	public boolean qrEncode(String contents, String outputImgPath, int outputImgWidth, int outputImgHeight) {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 设置二维码纠错能力级别为H（最高）
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, outputImgWidth, outputImgHeight, hints);
			//写出文件
			MatrixToImageWriter.writeToPath(bitMatrix, "png", new File(outputImgPath).toPath());
			return true;
		} catch (Exception e) {
			LoggerUtils.logException(e);
			return false;
		}
	}

	public static void main(String[] args) {
		String imgPath = "d:/test/qrcode" + System.currentTimeMillis() + ".png";  
        String contents = "http://192.168.1.232:8080/opetv-queen/?testp=testv";  
        int width = 300, height = 300;  
        CodeUtils code = new CodeUtils();  
        System.out.println(code.qrEncode(contents, imgPath, width, height));  
	}

}
