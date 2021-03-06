package common.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class TwemojiCSSCreator {

	public static void main(String[] args) throws Exception {

		final SortedMap<String, String> twemojiToSVGFileNameMap = getTwemojiToSVGFileNameMap();

		final List<String> twemojiCSSLines = getTwemojiCSSLines(twemojiToSVGFileNameMap);
		final Path twemojiCSSs = Paths.get(".", "public", "lib", "twemoji-awesome", "twemojiawesome.css");
		Files.write(twemojiCSSs, twemojiCSSLines, StandardCharsets.UTF_8);
		System.out.println("Created :" + twemojiCSSs);
	}

	private static List<String> getTwemojiCSSLines(final SortedMap<String, String> twemojiToSVGFileNameMap) {

		final List<String> twemojiCSSLines = new ArrayList<>();
		twemojiCSSLines.add("");
		twemojiCSSLines.add(".ta {");
		twemojiCSSLines.add("	display: inline-flex;");
		twemojiCSSLines.add("	background-position: center;");
		twemojiCSSLines.add("	background-size: contain;");
		twemojiCSSLines.add("	background-repeat: no-repeat;");
		twemojiCSSLines.add("	width: 1.0em;");
		twemojiCSSLines.add("	height: 1.0em;");
		twemojiCSSLines.add("	vertical-align: -0.26em;");
		twemojiCSSLines.add("}");
		twemojiCSSLines.add("");

		twemojiCSSLines.add(".fa-lg>.ta {");
		twemojiCSSLines.add("	vertical-align: 0.14em;");
		twemojiCSSLines.add("}");
		twemojiCSSLines.add("");

		twemojiCSSLines.add(".ta.fa-lg {");
		twemojiCSSLines.add("	vertical-align: -0.03em;");
		twemojiCSSLines.add("}");
		twemojiCSSLines.add("");

		twemojiCSSLines.add(".ta:before{");
		twemojiCSSLines.add("	content: \"i\";");
		twemojiCSSLines.add("	color: transparent;");
		twemojiCSSLines.add("}");
		twemojiCSSLines.add("");

		twemojiCSSLines.addAll(twemojiToSVGFileNameMap.entrySet().stream().map(entry -> {

			final String cssClassName = entry.getKey();
			final String svgFileName = entry.getValue();
			if (cssClassName.startsWith("NOT MAPPED")) {

				System.out.println(cssClassName + " = " + svgFileName);
				return "/* " + svgFileName + " */";
			}

			final StringBuilder builder = new StringBuilder();
			builder.append(".ta-").append(cssClassName).append(" {").append("\n");
			builder.append("\t").append("background-image: url(../../lib/twemoji/2/svg/").append(svgFileName).append(");").append("\n");
			builder.append("}").append("\n");

			return builder.toString();
		}).collect(Collectors.toList()));

		return twemojiCSSLines;
	}

	private static Map<String, String> codePointToUnicodeCodePoint = new HashMap<String, String>() {
		{//
			// Mapped.

			// #
			put("23 20E3", "0023 20E3");
			// *
			put("2A 20E3", "002A 20E3");
			// 0〜9
			put("30 20E3", "0030 20E3");
			put("31 20E3", "0031 20E3");
			put("32 20E3", "0032 20E3");
			put("33 20E3", "0033 20E3");
			put("34 20E3", "0034 20E3");
			put("35 20E3", "0035 20E3");
			put("36 20E3", "0036 20E3");
			put("37 20E3", "0037 20E3");
			put("38 20E3", "0038 20E3");
			put("39 20E3", "0039 20E3");
			// (C)
			put("A9", "00A9");
			// (R)
			put("AE", "00AE");

			// Not Mapped.

			// 109
			put("E50A", "");
			// skier
			put("26F7 1F3FB", "");
			put("26F7 1F3FC", "");
			put("26F7 1F3FD", "");
			put("26F7 1F3FE", "");
			put("26F7 1F3FF", "");
			// A〜Z
			put("1F1E6", "");
			put("1F1E7", "");
			put("1F1E8", "");
			put("1F1E9", "");
			put("1F1EA", "");
			put("1F1EB", "");
			put("1F1EC", "");
			put("1F1ED", "");
			put("1F1EE", "");
			put("1F1EF", "");
			put("1F1F0", "");
			put("1F1F1", "");
			put("1F1F2", "");
			put("1F1F3", "");
			put("1F1F4", "");
			put("1F1F5", "");
			put("1F1F6", "");
			put("1F1F7", "");
			put("1F1F8", "");
			put("1F1F9", "");
			put("1F1FA", "");
			put("1F1FB", "");
			put("1F1FC", "");
			put("1F1FD", "");
			put("1F1FE", "");
			put("1F1FF", "");
			// suit levitating
			put("1F574 1F3FB 200D 2640 FE0F", "");
			put("1F574 1F3FB 200D 2642 FE0F", "");
			put("1F574 1F3FC 200D 2640 FE0F", "");
			put("1F574 1F3FC 200D 2642 FE0F", "");
			put("1F574 1F3FD 200D 2640 FE0F", "");
			put("1F574 1F3FD 200D 2642 FE0F", "");
			put("1F574 1F3FE 200D 2640 FE0F", "");
			put("1F574 1F3FE 200D 2642 FE0F", "");
			put("1F574 1F3FF 200D 2640 FE0F", "");
			put("1F574 1F3FF 200D 2642 FE0F", "");
			put("1F574 FE0F 200D 2640 FE0F", "");
			put("1F574 FE0F 200D 2642 FE0F", "");
			// tuxedo
			put("1F935 1F3FB 200D 2640 FE0F", "");
			put("1F935 1F3FB 200D 2642 FE0F", "");
			put("1F935 1F3FC 200D 2640 FE0F", "");
			put("1F935 1F3FC 200D 2642 FE0F", "");
			put("1F935 1F3FD 200D 2640 FE0F", "");
			put("1F935 1F3FD 200D 2642 FE0F", "");
			put("1F935 1F3FE 200D 2640 FE0F", "");
			put("1F935 1F3FE 200D 2642 FE0F", "");
			put("1F935 1F3FF 200D 2640 FE0F", "");
			put("1F935 1F3FF 200D 2642 FE0F", "");
			put("1F935 200D 2640 FE0F", "");
			put("1F935 200D 2642 FE0F", "");
		}
	};

	private static SortedMap<String, String> getTwemojiToSVGFileNameMap() throws IOException {

		final Path path = Paths.get(".", "target", "web", "public", "main", "lib", "twemoji", "2", "svg");
		if (!Files.exists(path)) {

			throw new RuntimeException(": " + path);
		}

		final Map<String, String> codePointToIconNameMap = getCodePointToIconNameMap();
		final List<String> svgFileNames = Files.walk(path)//
				.map(filePath -> filePath.getFileName().toString())//
				.filter(name -> name.endsWith(".svg"))//
				.sorted()//
				.collect(Collectors.toList());
		final SortedMap<String, String> twemojiToSVGFileNameMap = svgFileNames.stream()//
				.map(svgFileName -> {

					String codePoint = com.google.common.io.Files.getNameWithoutExtension(svgFileName).toUpperCase().replaceAll("-", " ");
					if (!codePointToIconNameMap.containsKey(codePoint)) {

						codePoint = codePointToUnicodeCodePoint.get(codePoint);
					}
					final String cssClassName;
					final String iconName = codePointToIconNameMap.get(codePoint);
					if (Objects.isNull(iconName)) {

						cssClassName = "NOT MAPPED[" + svgFileName + "]";
					} else {

						cssClassName = toCSSClassName(iconName);
					}

					final Entry<String, String> entry = new SimpleImmutableEntry<>(cssClassName, svgFileName);
					return entry;
				})//
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (u, v) -> v, TreeMap::new));

		return twemojiToSVGFileNameMap;
	}

	private static String toCSSClassName(final String iconName) {

		String cssClassName = iconName.toLowerCase();
		cssClassName = cssClassName.replace("keycap: #", "keycap: hash");// specially
		cssClassName = cssClassName.replace("keycap: *", "keycap: asterisk");// specially
		cssClassName = cssClassName.replaceAll("[!’().“”,]", "");
		cssClassName = cssClassName.replaceAll("\\s&\\s", "-");
		cssClassName = cssClassName.replaceAll(":\\s", "_");
		cssClassName = cssClassName.replaceAll("\\s", "-");
		cssClassName = Normalizer.normalize(cssClassName, Normalizer.Form.NFD);

		if (cssClassName.replaceAll("[^0-9a-z_-]", "[ACCENT]️").contains("[ACCENT]")) {

			// for print.
			System.out.println("Not CSS class name. :" + iconName);
		}
		cssClassName = cssClassName.replaceAll("[^0-9a-z_-]", "️");

		return cssClassName;
	}

	private static final Pattern PATTERN_EMOJI = Pattern.compile("(?<codePoint>.*?)\\s*;\\s(?<qualified>.*?)\\s*#\\s(?<icon>.*?)\\s(?<iconName>.*)");

	private static Map<String, String> getCodePointToIconNameMap() throws IOException {

		// https://unicode.org/Public/emoji/12.0/
		// https://unicode.org/Public/emoji/12.0/emoji-test.txt
		final Path path = Paths.get(".", "conf", "resources", "emoji", "emoji-test.txt");
		if (!Files.exists(path)) {

			throw new RuntimeException(": " + path);
		}

		final List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		final List<String> emojiLines = lines.stream().filter(line -> !line.isEmpty() && !line.startsWith("#")).collect(Collectors.toList());
		System.out.println(emojiLines.size());
		final Map<String, String> codePointToIconNameMap = emojiLines.stream().map(line -> {

			final Matcher matcher = PATTERN_EMOJI.matcher(line);
			if (!matcher.matches()) {

				throw new IllegalStateException(line);
			}

			final String codePoint = matcher.group("codePoint");
			final String qualified = matcher.group("qualified");
			final String icon = matcher.group("icon");
			final String iconName = matcher.group("iconName");
			System.out.println(codePoint + " / " + qualified + " / " + icon + " / " + iconName);

			final Entry<String, String> entry = new SimpleImmutableEntry<>(codePoint, iconName);
			return entry;
		}).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		System.out.println(codePointToIconNameMap.entrySet().size());

		return codePointToIconNameMap;
	}
}
