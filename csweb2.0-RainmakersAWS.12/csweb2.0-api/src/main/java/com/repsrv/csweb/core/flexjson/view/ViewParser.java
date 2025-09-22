package com.repsrv.csweb.core.flexjson.view;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parser class that transforms source view files into View objects.
 *
 */
public class ViewParser {

	private static final Logger logger = LoggerFactory.getLogger(ViewParser.class);

	private static final String COMMENT = "#";
	private static final String VIEW_PROPERTY = "view.";

	public View loadView(File file) {
		View view = null;
		Stream<String> lines = null;
		try {
			lines = Files.lines(Paths.get(file.getPath()));
			view = this.parseView(lines.toArray(String[]::new));
		} catch (Exception exception) {
			logger.error("Error while loading view {}", file.getName(), exception);
		} finally {
			if (lines != null) {
				try {
					lines.close();
				} catch (Exception e) {
					// Log or handle the exception
				}
			}
		}
		return view;
	}
	

	private void validateView(View view) {
		if (view.getId().isEmpty())
			throw new IllegalArgumentException("Cannot create a View no valid id");

		if (view.getMappings().isEmpty())
			throw new IllegalArgumentException("Cannot create a View with no mapping rule(s)");
	}

	@SuppressWarnings("unchecked")
	public List<View> loadFileViews(String directory) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(directory);

		List<View> views = new LinkedList<>();

		if (url != null) {
			String path = url.getPath();

			File dir = new File(path);

			if (dir.isDirectory()) {
				
				Collection<File> files = FileUtils.listFiles(dir, new RegexFileFilter("^(.*\\.view$)"),
						DirectoryFileFilter.DIRECTORY);

				views = files.stream().map(this::loadView).collect(Collectors.toList());
			}
		}
		return views;
	}

	public View parseView(String[] lines) throws ClassNotFoundException {
		View view = new View();

		for (String line : lines) {
			if (line.isEmpty() || line.startsWith(COMMENT))
				continue;

			if (line.startsWith(VIEW_PROPERTY)) {
				ViewProperty property = this.parseViewProperty(line);
				view.setProperty(property);
				continue;
			}

			view.addMappingRule(this.parseMappingRule(line));
		}

		this.validateView(view);

		return view;
	}

	private ViewProperty parseViewProperty(String line) {
		String[] parts = line.substring(line.indexOf(VIEW_PROPERTY) + VIEW_PROPERTY.length()).split("=");
		if (parts.length != 2)
			throw new IllegalArgumentException("Cannot have an empty view property");

		return new ViewProperty(parts[0], parts[1]);
	}

	private MappingRule parseMappingRule(String line) {
		MappingRule rule = new MappingRule();
		int i = line.lastIndexOf(".");

		rule.setPrefix(i > 0 ? line.substring(0, i) : "");
		rule.setAttribute(i > 0 ? line.substring(i + 1) : line);

		return rule;
	}

}