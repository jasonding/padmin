package com.hrin.admin.web;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.util.NullUtil;
import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by jason on 15-1-9.
 */
public class WebControlbHelper {

    public List<QueryBean> getQuryBeanByMap(Map<String,String> params) {
        if(NullUtil.isNullOrEmpty(params)) return Collections.emptyList();
        List<QueryBean> list = new ArrayList<QueryBean>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if(!entry.getKey().contains("_")) continue;
            QueryBean queryBean = new QueryBean(entry.getKey(),entry.getValue());
            list.add(queryBean);
        }
        return list;
    }


    /**
     * 接收File输出图片
     * @param file
     * @param writePath
     * @param width
     * @param height
     * @param format
     * @return
     */
    public boolean resizeImage(File file, String writePath, Integer width, Integer height, String format) {
        try {
            BufferedImage inputBufImage = ImageIO.read(file);
            inputBufImage.getType();
            //log.info("转前图片高度和宽度：" + inputBufImage.getHeight() + ":"+ inputBufImage.getWidth());
            ResampleOp resampleOp = new ResampleOp(width, height);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,null);
            ImageIO.write(rescaledTomato, format, getFile(writePath));
            //log.info("转后图片高度和宽度：" + rescaledTomato.getHeight() + ":"+ rescaledTomato.getWidth());
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public String getFileSuffix(String fileName){
        if(fileName == null || fileName.isEmpty()) throw new NullPointerException("文件名为空");
        return fileName.substring(fileName.lastIndexOf(".") + 1).trim().toLowerCase();
    }

    public String getNewFileName(String name,String fileSuffix){
        return name + "." + fileSuffix;
    }
    public String getNewFileName5050(String name,String fileSuffix){
        return name + "5050." + fileSuffix;
    }
    public String getNewFileName2525(String name,String fileSuffix){
        return name + "2525." + fileSuffix;
    }
    public String getNewFileName100100(String name,String fileSuffix){
        return name + "100100." + fileSuffix;
    }
    public String getNewFileName150150(String name,String fileSuffix){
        return name + "150150." + fileSuffix;
    }

    public File getFile(String path) {
        if(path == null || path.isEmpty()) return null;
        File file = new File(path);
        if(!file.getParentFile().exists()) {
            File filePath = new File(file.getParent());
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
        }
        return file;
    }
}
