package com.xhsoft.framework.common.views.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;



/**
 * TODO
 *
 * @author Admin
 * @version 1.0.0
 * @since   2012-11-30
 */
public class PdfView extends AbstractPdfView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractPdfView#buildPdfDocument(java.util.Map, com.lowagie.text.Document, com.lowagie.text.pdf.PdfWriter, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildPdfDocument(
			Map<String, Object> model,
			Document doc,
			PdfWriter writer, 
			HttpServletRequest req,
			HttpServletResponse resp)
			throws Exception {
		// TODO Auto-generated method stub
		List words = (List) model.get("wordList");
        
        for (int i=0; i<words.size(); i++)
            doc.add( new Paragraph((String) words.get(i)));
	}
}
