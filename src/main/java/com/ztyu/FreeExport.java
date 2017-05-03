package com.ztyu;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * Created by ztyu
 * on 2017/5/2.
 */
public class FreeExport {

    public static final String dest = "D:\\hello.pdf";

    public static void main(String[] args){

        int xposition = 150;
        int yposition = 800;
        int width = 480;
        int height = 11;

        Document document = new Document();
        PdfWriter writer ;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

            document.open();
            document.setMargins(68, 68, 36, 85);

            //创建模板样例，width, height分别为插入变量内容在页面显示的横向和纵向的长度。
            PdfTemplate template = writer.getDirectContent().createTemplate(width, height);
            BaseFont baseFontChinese = BaseFont.createFont("/TTF/TNR.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFontChinese, 10, Font.NORMAL);

            document.add(new Paragraph("content A", font));
            document.add(new Paragraph("content B", font));//创建插入文字的字体格式，并创建模板

            Image img = Image.getInstance(template);
            img.setAbsolutePosition(xposition, yposition); //模板样例在pdf中的绝对位置，xposition, yposition分别为新坐标和y坐标
            ColumnText.showTextAligned(template, Element.ALIGN_LEFT, new Paragraph("I am template.", font), 2, 2, 0);
            document.add(img);

        }catch (Exception e){
            e.printStackTrace();
        }
        document.close();
    }

}
