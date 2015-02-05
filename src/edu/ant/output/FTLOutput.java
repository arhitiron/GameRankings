package edu.ant.output;

import java.io.File;
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
import edu.ant.models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FTLOutput implements Output{

	@SuppressWarnings("deprecation")
	private Configuration conf = new Configuration();
	private Template template;
	Map<String, Object> data = new HashMap<String, Object>();
	
	public FTLOutput() {
		try {
			conf.setDirectoryForTemplateLoading(new File(Config.FTL_DIR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showUser(User user) {
		prepareTemplate("user.ftl");
		data.put("user", user);
		flush();
	}

	@Override
	public void showUser(User user, String[] params) {
		prepareTemplate("user.ftl");
		data.put("user", user);
		addParams(params);
		flush();
	}

	@Override
	public void showUsers() {
		prepareTemplate("users.ftl");
		Game.userManager.sortUsersByWin(); // omg
		data.put("users", Game.userManager.getUserList());
		flush();
	}

	@Override
	public void showUsers(String[] params) {
		prepareTemplate("users.ftl");
		Game.userManager.sortUsersByWin(); // omg
		data.put("users", Game.userManager.getUserList());
		addParams(params);
		flush();
	}

	@Override
	public void showHelp() {
		prepareTemplate("help.ftl");
		flush();
	}
	
	private void prepareTemplate(String tplName) {
		try {
			template = conf.getTemplate(tplName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addParams(String[] params) {
		Map<String, Boolean> additionalParameters = new HashMap<>();
		for (String param : params) {
			additionalParameters.put(param, true);
		}
		data.put("params", additionalParameters);
	}
	
	private void flush() {
		Writer out = new OutputStreamWriter(System.out);
		try {
			out.flush();
			template.process(data, out);
		} catch (TemplateException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
