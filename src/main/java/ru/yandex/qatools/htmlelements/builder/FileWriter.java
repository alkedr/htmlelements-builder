package ru.yandex.qatools.htmlelements.builder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static ru.yandex.qatools.htmlelements.builder.HtmlElementTypeSerializer.serialize;


public class FileWriter {
    public static void write(File folder, HtmlElementType htmlElementType) throws IOException {
        folder.createNewFile();
        File folderWithPackage = new File(folder.getAbsolutePath() + "/" + new BlockProperties().getPackageName().replace(".", "/"));
        FileUtils.writeStringToFile(new File(folderWithPackage.getAbsolutePath() + "/" + htmlElementType.getName() + ".java"), serialize(htmlElementType), "UTF-8");

        for (HtmlElementType field : htmlElementType.getHtmlElement()) {
            write(folder, field);
        }
    }
}
