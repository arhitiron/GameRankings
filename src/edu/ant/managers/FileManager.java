package edu.ant.managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.ant.config.Config;

public class FileManager implements Manager {

	private FileReader namesFile;
	private FileReader matchesFile;
	private BufferedReader namesBuffReader;
	private BufferedReader matchesBuffReader;
	
	public void init() {
		System.out.println("Initializing FileManager...");
		getNamesFile();
		getMatchesFile();
		System.out.println("FileManager initialized ");
	}
	
	public FileReader getNamesFile() {
		try {
			if (namesFile == null) {
				namesFile = new FileReader(Config.USER_NAMES);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return namesFile;
	}
	
	public BufferedReader getNamesBufferedReader() {
		FileReader nameFileReader = getNamesFile();
		if (nameFileReader != null && namesBuffReader == null) {
			namesBuffReader = new BufferedReader(nameFileReader);	
		}
		return namesBuffReader;
	}
	
	public FileReader getMatchesFile() {
		try {
			if (matchesFile == null) {
				matchesFile = new FileReader(Config.USER_MATCHES);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return matchesFile;
	}
	
	public BufferedReader getMatchesBufferedReader() {
		FileReader matchesFileReader = getMatchesFile();
		if (matchesFileReader != null && matchesBuffReader == null) {
			matchesBuffReader = new BufferedReader(matchesFileReader);	
		}
		return matchesBuffReader;
	}
	
}
