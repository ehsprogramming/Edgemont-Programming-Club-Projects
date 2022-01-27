package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import main.data.FlashcardData;
import main.data.StudySet;

public class FileUtil {

	static File documents = FileSystemView.getFileSystemView().getDefaultDirectory();
	
	public static void save(CreatePane create) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(documents, create.title.getText() + ".flip")), "utf-8"));
			
			bw.append(create.title.getText());
			bw.append('\0');
			
			for(var card: create.cards) {
				bw.append(card.term.getText());
				bw.append('\0');
				bw.append(card.def.getText());
				bw.append('\0');
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static StudySet read(String name) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(documents, name + ".flip")), "utf-8"));
		
		String title = null;
		String term = null;
		String definition = null;
		
		StringBuffer sb = new StringBuffer();
		int c = 0;
		
		StudySet ss = new StudySet();
		
		while((c = br.read()) != -1) {
			char ch = (char) c;
			
			if(ch == '\0') {
				String text = sb.toString();
				
				if(title == null) {
					ss.title = text;
				}else if(term == null) {
					term = text;
				}else{
					definition = text;
					
					ss.data.add(new FlashcardData(term, definition));
					
					term = null;
					definition = null;
				}
				
				sb = new StringBuffer();
			}else
				sb.append(ch);
		}
		
		return ss;
	}
	
	public static File[] savedSets() {
		return documents.listFiles((File directory, String fileName) -> fileName.endsWith(".flip"));
	}
	
	public static void main(String[] args) throws IOException {
		
	}
	
}
