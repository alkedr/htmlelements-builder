package ru.yandex.qatools.htmlelements.builder;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;



@Resource.Classpath("block.properties")
class BlockProperties {
    @Property("block.package_name")
    private String packageName;


    public BlockProperties() {
        PropertyLoader.populate(this);
    }

    public String getPackageName() {
        return packageName;
    }
}
