package ru.yandex.qatools.htmlelements.builder;


import java.io.Serializable;

public class HtmlElementTypeSerializer extends HtmlElementType {
    private static String getPackageString(HtmlElementType htmlElementType) {
        return "package " + new BlockProperties().getPackageName() + ";\n";
    }

    private static String getImports(HtmlElementType htmlElementType) {
        return
            "import org.openqa.selenium.support.FindBy;\n" +
            "import ru.yandex.qatools.htmlelements.annotations.Block;\n" +
            "import ru.yandex.qatools.htmlelements.element.HtmlElement;\n";
    }

    private static String getClassSource(HtmlElementType htmlElementType) {
        return
            "@Block(@FindBy(" + htmlElementType.getFindBy().replace("=", "=\"") + "\"))\n" +
            "public class " + htmlElementType.getName() + " extends HtmlElement {\n" +
            getFieldsSource(htmlElementType) +
            "}\n";
    }

    private static String getFieldsSource(HtmlElementType htmlElementType) {
        String res = "";
        for (HtmlElementType field : htmlElementType.getHtmlElement()) {
            res = res.concat("    public " + field.getName() + " " + field.getName().substring(0, 1).toLowerCase() + field.getName().substring(1) + ";\n");
        }
        return res;
    }


    public static String serialize(HtmlElementType htmlElementType) {
        return getPackageString(htmlElementType) + "\n" + getImports(htmlElementType) + "\n" + getClassSource(htmlElementType);
    }
}
