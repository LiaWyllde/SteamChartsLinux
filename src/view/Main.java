package view;

import controller.SteamController;

public class Main {
	
	public static void main(String[] args) {

		SteamController file = new SteamController();
		
		String path = "/tmp";
		String name = "SteamCharts.csv";
		String newName = "newFile.csv";
		
		try {
			file.readDirectory(path, name, "2019", "January", 100000.0);
			file.file(path, newName, "2019", "January");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
