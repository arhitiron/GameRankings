package edu.ant.managers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ant.config.Config;
import edu.ant.exceptions.UserNotFoundException;
import edu.ant.main.Game;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OutputFTLManager {

	public void printToFTL () {
		@SuppressWarnings("deprecation")
		Configuration conf = new Configuration();

		try {
			Template template = conf.getTemplate(Config.USER_OUTPUT_FTL);	
			
			Map<String, Object> data = new HashMap<String, Object>();
			try {
				data.put("user", Game.userManager.getUserById(10));
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}

			List<String> language = new ArrayList<String>();
			language.add("Java");
			language.add("C++");


			data.put("languages", language);

			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	

	
	
}
