package ru.yandex.qatools.htmlelements.examples;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Block(@FindBy(xpath="/some/xpath"))
public class BlockWithFields extends HtmlElement {
    public SubBlockWithFields subBlockWithFields;
    public SubBlockWithoutFields subBlockWithoutFields;
}
