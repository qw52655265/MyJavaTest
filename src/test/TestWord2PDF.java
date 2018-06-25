package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
//import com.bi.wms.kory.status.ConvertStatus;
//import com.bi.wms.kory.util.AsposeLicenseUtil;

/**
 * @author Administrator
 * @version $Id$
 * @since
 * @see
 */
public class TestWord2PDF {

	public static void main(String[] args) {
		// doc2pdf("D:\\upload\\word\\1-1广州中桥高速公路两阶段初步设计第一部分第二册全一册.docx",
		// "D:\\upload\\word\\aa.pdf");
		// doc2pdf("D:\\upload\\word\\规格说明书.docx", "D:\\upload\\word\\bb.pdf");
		 excel2Pdf("D:\\upload\\word\\123.xlsx", "D:\\upload\\word\\cc.pdf");
	}

	public static boolean getLicense() {
		boolean result = false;
		try {
			// InputStream is =
			// Test.class.getClassLoader().getResourceAsStream("license.xml"); //
			// license.xml应放在..\WebRoot\WEB-INF\classes路径下
			InputStream is = new FileInputStream(new File("E:\\EclipseWorkSpace\\MyJavaTest\\src\\test\\license.xml"));
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void doc2pdf(String inPath, String outPath) {
		if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
			return;
		}
		try {
			long old = System.currentTimeMillis();
			File file = new File(outPath); // 新建一个空白pdf文档
			FileOutputStream os = new FileOutputStream(file);
			Document doc = new Document(inPath); // Address是将要被转化的word文档
			doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
											// EPUB, XPS, SWF 相互转换
			long now = System.currentTimeMillis();
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");

	public static void excel2Pdf(String filePath, String savePath) {
		if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
			return;
		}

		try {
			// String time=sdf.format(new Date());
			// String excelpath="C://111.xls";
			// String pdfpath="C://Users//"+time+".pdf";
			// if (AsposeLicenseUtil.setCellsLicense()) {
			// long start = System.currentTimeMillis();
			InputStream inputStream = new FileInputStream(new File(filePath));
			OutputStream outputStream = new FileOutputStream(new File(savePath));

			Workbook workbook = new Workbook(inputStream);

			// workbook.Worksheets[0].VerticalPageBreaks

			Worksheet ws = workbook.getWorksheets().get(0);
			// ws.setPageBreakPreview(false);
			System.out.println("getHorizontalPageBreaks:" + ws.getHorizontalPageBreaks().getCount());
			System.out.println("vertical:" + ws.getVerticalPageBreaks().getCount());
			PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
			pdfSaveOptions.setOnePagePerSheet(true);

			ws.getHorizontalPageBreaks().clear();
			ws.getVerticalPageBreaks().clear();
			// workbook.save(outputStream, SaveFormat.PDF);
			workbook.save(outputStream, pdfSaveOptions);
			outputStream.flush();
			outputStream.close();

			// TODO 当excel宽度太大时，在PDF中会拆断并分页。此处如何等比缩放。

			// 将不同的sheet单独保存为pdf
			// Get the count of the worksheets in the workbook
//			int sheetCount = workbook.getWorksheets().getCount();
//
//			// Make all sheets invisible except first worksheet
//			for (int i = 1; i < workbook.getWorksheets().getCount(); i++) {
//				workbook.getWorksheets().get(i).setVisible(false);
//			}
//			workbook.save(outputStream, SaveFormat.PDF);

			// Take Pdfs of each sheet
//		for (int j = 0; j < workbook.getWorksheets().getCount(); j++) {
//			Worksheet ws = workbook.getWorksheets().get(j);
//			workbook.save("D:/Kory" + ws.getName() + ".pdf");
//
//			if (j < workbook.getWorksheets().getCount() - 1) {
//				workbook.getWorksheets().get(j + 1).setVisible(true);
//				workbook.getWorksheets().get(j).setVisible(false);
//			}
//		}

			long end = System.currentTimeMillis();
			System.out.println("excel to pdf success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}