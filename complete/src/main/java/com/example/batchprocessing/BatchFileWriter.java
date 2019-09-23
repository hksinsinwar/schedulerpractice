package com.example.batchprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BatchFileWriter<T> implements ItemWriter<T>, AutoCloseable {

	private File file;
	private PrintWriter fao;
	ObjectMapper mapper = new ObjectMapper();

	public BatchFileWriter(){}
	
	public BatchFileWriter(File file) {
		this.file = file;
		try {
			fao = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
		}
	}

	@Override
	public void write(List<? extends T> items) throws Exception {
		fao.write(mapper.writeValueAsString(items)); 
		fao.flush();
	}

	@Override
	public void close() throws IOException {
		fao.close();
	}

	
}
