package ru.yandex.qatools.htmlelements.examples;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Block(@FindBy(xpath="/some/xpath/1"))
public class SubBlockWithFields extends HtmlElement {
    public SubSubBlockWithoutFields subSubBlockWithoutFields;
}
