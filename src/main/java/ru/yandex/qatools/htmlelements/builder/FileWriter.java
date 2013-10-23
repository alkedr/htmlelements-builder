package ru.yandex.qatools.htmlelements.builder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileWriter {
    public static void write(File file, HtmlElementType htmlElementType) throws IOException {
        FileUtils.writeStringToFile(file, htmlElementType.toString(), "UTF-8");
    }
}
