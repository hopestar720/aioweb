/**
 * Copyright (c) 2012, xhsoft and/or its affiliates. All rights reserved.
 * Use is subject to license terms.
 */
package com.xhsoft.framework.common.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * TODO
 * @author lijw
 * @since 2012-11-10
 */
public class PDFHandle {
	
	public static void createPDF(String fileName){
		Document doc = new Document(PageSize.A4.rotate());
		OutputStream os = null;
		try {
			os = new FileOutputStream(fileName);
			PdfWriter.getInstance(doc, os );
			doc.open();
			doc.add(new Paragraph("Hello World ! "));
			doc.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setPdfInfo(Document doc){
		/*
		doc.addTitle(title);
		doc.addSubject(subject);
		doc.addKeywords(keyworks);
		doc.addProducer(producer);
		doc.addAuthor(author);
		doc.addCreator(app);
		doc.addCreationDate(new Date());
		*/
		
	}

}
