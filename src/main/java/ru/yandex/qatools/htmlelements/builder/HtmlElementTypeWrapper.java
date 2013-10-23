package ru.yandex.qatools.htmlelements.builder;


public class HtmlElementTypeWrapper extends HtmlElementType {
    private String getPackageString() {
        return "package " + new BlockProperties().getPackageName() + ";\n";
    }

    private String getImports() {
        return
            "import org.openqa.selenium.support.FindBy;\n" +
            "import ru.yandex.qatools.htmlelements.annotations.Block;\n" +
            "import ru.yandex.qatools.htmlelements.element.HtmlElement;\n";
    }

    private String getClassSource() {
        return
            "@Block(@FindBy(" + getFindBy().replace("=", "=\"") + "\"))\n" +
            "public class " + getName() + " extends HtmlElement {\n" +
            "}\n";
    }


    public String getSource() {
        return getPackageString() + "\n" + getImports() + "\n" + getClassSource();
    }
}
