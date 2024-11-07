package controller;

import java.io.*;

public class SteamController {

	public void readDirectory(String path, String name, String year, String month, Double average) throws IOException {
		
		existentDirectory(path);
		
		File file = new File(path, name);
		String[] array;

		if (!(file.exists())) {
			
			throw new IOException("File doesn't exist.");
			
		} else {
			
			FileInputStream input = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			
			line = buffer.readLine();
			
			while (!(line == null)) {
				array = line.split(",");
				Double filtered = Double.parseDouble(array[3]);

				if (line.contains(year) && line.contains(month) && filtered >= average) {
					array = line.split(",");
					System.out.println(array[0] + " -- " + array[3]);
				}
				line = buffer.readLine();
			}
			buffer.close();
			reader.close();
			input.close();
			
		}

	}

	public void existentDirectory(String path) throws IOException {

		File directory = new File(path);
		if (!(directory.exists())) {
			throw new IOException("Invalid directory.");
		}
	}

	public void file(String path, String fileName, String year, String month) throws IOException {
		
		existentDirectory(path);
		
		File fileSteam = new File("/tmp", "SteamCharts.csv");
		File file = new File(path, fileName);
		StringBuffer csv = new StringBuffer();
		String[] array;

		if (!(fileSteam.exists() && fileSteam.isFile())) {
			throw new IOException("Invalid file.");

		} else {
			
			FileInputStream input = new FileInputStream(fileSteam);
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(reader);
			
			String line = buffer.readLine();
			line = buffer.readLine();
			
			while (!(line == null)) {

				if (line.contains(year) && line.contains(month)) {
					array = line.split(",");
					csv.append(array[0] + " ; " + array[3] + "\n");
				}
				line = buffer.readLine();
			}
			
			buffer.close();
			reader.close();
			input.close();
			
		}

		FileWriter fileWriter = new FileWriter(file);
		PrintWriter show = new PrintWriter(fileWriter);
		
		show.write(csv.toString());
		show.flush();
		show.close();
		fileWriter.close();

		System.out.println("\n" + "Thw new file was created.");
	}

}
