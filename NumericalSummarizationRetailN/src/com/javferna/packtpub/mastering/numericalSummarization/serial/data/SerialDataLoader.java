package com.javferna.packtpub.mastering.numericalSummarization.serial.data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.javferna.packtpub.mastering.numericalSummarization.common.Record;

public class SerialDataLoader {

	public static List<Record> load(Path path) throws IOException {
		System.out.println("Loading data");
		
		List<String> lines = Files.readAllLines(path);

		List<Record> records = lines
				.stream()
				.skip(1)
				.map(l -> l.split(";"))
				.map(t -> new Record(t))
				.collect(Collectors.toList());

		return records;
	}
}
