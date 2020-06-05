package com.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtil {

    /**
     * 生成的PDF文件的路径。
     */
    public static final String RESULT = "C:\\Users\\xiala\\Desktop\\test\\hello.pdf";

    /**
     * 创建一个PDF文件：hello.pdf
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) throws DocumentException, IOException {
        PdfUtil.createPdf(RESULT);
    }

    /**
     * 创建PDF文档.
     * 1.1 第一个iText示例：Hello World
     *
     * @param filename 新PDF文档的路径
     * @throws DocumentException
     * @throws IOException
     */
    public static void createPdf(String filename) throws DocumentException, IOException {
        // 第一步 创建文档实例
        Document document = new Document();
        // 第二步 获取PdfWriter实例
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // 第三步 打开文档
        document.open();
        // 第四步 添加段落内容
        document.add(new Paragraph("Hello World!"));
        // 第五部 操作完成后必须执行文档关闭操作。
        document.close();
    }

    /**
     * 1.2 文档构造函数示例
     */
    public static void createPdf() throws FileNotFoundException, DocumentException {
        // step 1
        // 自定义页面大小使用
        Rectangle pagesize = new Rectangle(216f, 720f);
        Document document = new Document(pagesize, 36f, 72f, 108f, 180f);
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(
                "Hello World! Hello People! " +
                        "Hello Sky! Hello Sun! Hello Moon! Hello Stars!"));
        // step 5
        document.close();
    }

    /**
     * 1.3 设置最大尺寸页面的pdf
     */
    public void createPdf3() throws DocumentException, IOException {
        // 第一步
        //最大页面尺寸
        Document document = new Document(new Rectangle(14400, 14400));
        // 第二步
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // 改变用户单位 使用此方法设置用户单位。UserUnit是定义默认用户空间单位的值。最小UserUnit为1（1个单位= 1/72英寸）。最大UserUnit为75,000。请注意，此用户单元仅适用于从PDF1.6开始！
        writer.setUserunit(75000f);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World"));
        // step 5
        document.close();

    }

    /**
     * 1.4 生成纸张尺寸大小的pdf文件
     */
    public void createPdf4() throws DocumentException, IOException {
        // 第一步
        //横向的格式通过 rotate() 方法
        Document document = new Document(PageSize.LETTER.rotate());
        // 第二步
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // 第三步
        document.open();
        // 第四步
        document.add(new Paragraph("Hello World"));
        // 第五步
        document.close();

    }

    /**
     * 1.5 横向显示pdf通过 rotate()
     */
    public void createPdf5() throws DocumentException, IOException {
        // 第一步
        //横向的格式通过 rotate() 方法
        Document document = new Document(PageSize.LETTER.rotate());
        // 第二步
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // 第三步
        document.open();
        // 第四步
        document.add(new Paragraph("Hello World"));
        // 第五步
        document.close();
    }

    /**
     * 1.6 横向显示pdf通过设置宽高
     */
    public void createPdf6() throws DocumentException, IOException {
        // 第一步
        //横向格式 通过 宽度 大于 高度
        Document document = new Document(new Rectangle(792, 612));
        // 第二步
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // 第三步
        document.open();
        // 第四步
        document.add(new Paragraph("Hello World"));
        // 第五步
        document.close();

    }

    /**
     * 1.7 设置页边距和装订格式
     */
    public void createPdf7() throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        document.setPageSize(PageSize.A5);
        document.setMargins(36, 72, 108, 180);
        document.setMarginMirroring(true);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(
                "The left margin of this odd page is 36pt (0.5 inch); " +
                        "the right margin 72pt (1 inch); " +
                        "the top margin 108pt (1.5 inch); " +
                        "the bottom margin 180pt (2.5 inch)."));
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        for (int i = 0; i < 20; i++) {
            paragraph.add("Hello World! Hello People! " +
                    "Hello Sky! Hello Sun! Hello Moon! Hello Stars!");
        }
        document.add(paragraph);
        document.add(new Paragraph(
                "The right margin of this even page is 36pt (0.5 inch); " +
                        "the left margin 72pt (1 inch)."));
        // step 5
        document.close();

    }

    public void createPdf71() throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // setting page size, margins and mirrered margins
        document.setPageSize(PageSize.A5);
        document.setMargins(36, 72, 108, 180);
        document.setMarginMirroringTopBottom(true);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(
                "The left margin of this odd page is 36pt (0.5 inch); " +
                        "the right margin 72pt (1 inch); " +
                        "the top margin 108pt (1.5 inch); " +
                        "the bottom margin 180pt (2.5 inch)."));
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        for (int i = 0; i < 20; i++) {
            paragraph.add("Hello World! Hello People! " +
                    "Hello Sky! Hello Sun! Hello Moon! Hello Stars!");
        }
        document.add(paragraph);
        document.add(new Paragraph("The top margin 180pt (2.5 inch)."));
        // step 5
        document.close();

    }

    /**
     * 1.8 内存中操作PDF文件
     */
    public void createPdf8() throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        // we'll create the file in memory
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();

        // let's write the file in memory to a file anyway
        FileOutputStream fos = new FileOutputStream(RESULT);
        fos.write(baos.toByteArray());
        fos.close();

    }

    /**
     * 1.9 设置pdf 的版本
     */
    public void createPdf9()
            throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        // Creating a PDF 1.7 document
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();
    }

    /**
     * 1.10 HelloWorldDirect ：设置指定位置添加pdf的内容 更为直接的方式
     */
    public void createPdf10()
            throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer =
                PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        PdfContentByte canvas = writer.getDirectContentUnder();
        writer.setCompressionLevel(0);
        canvas.saveState();                               // q
        canvas.beginText();                               // BT
        canvas.moveText(36, 788);                         // 36 788 Td
        canvas.setFontAndSize(BaseFont.createFont(), 12); // /F1 12 Tf
        canvas.showText("Hello World");                   // (Hello World)Tj
        canvas.endText();                                 // ET
        canvas.restoreState();                            // Q
        // step 5
        document.close();
    }

    /**
     * 1.11 设置指定位置添加pdf的内容 容易理解的方式
     */
    public void createPdf11() throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer
                = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        // we set the compression to 0 so that we can read the PDF syntax
        writer.setCompressionLevel(0);
        // writes something to the direct content using a convenience method
        Phrase hello = new Phrase("Hello World");
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
                hello, 36, 788, 0);
        // step 5
        document.close();

    }

    /**
     * 1.12 压缩多个pdf文件
     */
    public void createPdf12() throws DocumentException, IOException {
        // creating a zip file with different PDF documents
        ZipOutputStream zip =
                new ZipOutputStream(new FileOutputStream(RESULT));
        for (int i = 1; i <= 3; i++) {
            ZipEntry entry = new ZipEntry("hello_" + i + ".pdf");
            zip.putNextEntry(entry);

            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, zip);
            writer.setCloseStream(false);
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph("Hello " + i));
            // step 5
            document.close();

            zip.closeEntry();
        }
        zip.close();


    }
}
