package com.nih.savi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

public class CSVParser {
	private boolean removeHeader = false;
	
	public List<String[]> read(String filePath) {
		TsvParserSettings settings = new TsvParserSettings();
		settings.getFormat().setLineSeparator("\n");
		settings.setColumnReorderingEnabled(false);
		
		// creates a TSV parser
		TsvParser parser = new TsvParser(settings);

		// parses all rows in one go.
		List<String[]> allRows = parser.parseAll(getReader(filePath));
		
		if (removeHeader)
			allRows.remove(0);
		return allRows;
	}
	
	
	public List<String[]> read(String filePath, String[] columns) {
		TsvParserSettings settings = new TsvParserSettings();
		settings.getFormat().setLineSeparator("\n");
		settings.selectFields(columns);
		settings.setColumnReorderingEnabled(false);
		
		// creates a TSV parser
		TsvParser parser = new TsvParser(settings);

		// parses all rows in one go.
		List<String[]> allRows = parser.parseAll(getReader(filePath));
		
		if (removeHeader)
			allRows.remove(0);
		return allRows;
	}
	
	public Reader getReader(String relativePath)  {
        //return new InputStreamReader(this.getClass().getResourceAsStream(relativePath), "UTF-8");
		try {
			return new FileReader(relativePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }


	public boolean isRemoveHeader() {
		return removeHeader;
	}


	public void setRemoveHeader(boolean removeHeader) {
		this.removeHeader = removeHeader;
	}
	
}
