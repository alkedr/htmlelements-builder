package ru.yandex.qatools.htmlelements.builder;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

import static org.junit.Assert.assertEquals;


public class HtmlElementTypeWrapperTest {

    private static File getResourceFile(String name) {
        return new File(HtmlElementTypeWrapperTest.class.getResource(name).getPath());
    }


    @Test
    public void buildFromXmlTest() throws IOException, JAXBException {
        Reader reader = new BufferedReader(new FileReader(getResourceFile("/example.xml")));
        Unmarshaller unmarshaller = JAXBContext.newInstance(HtmlElementType.class.getPackage().getName()).createUnmarshaller();
        @SuppressWarnings("unchecked")
        HtmlElementType htmlElementType = ((JAXBElement<HtmlElementType>)unmarshaller.unmarshal(reader)).getValue();
        assertEquals(htmlElementType.getName(), "SimpleBlock");
        assertEquals(htmlElementType.getFindBy(), "xpath=/some/xpath");
    }

    @Test
    public void generateSimpleBlock() throws IOException {
        String expected = FileUtils.readFileToString(getResourceFile("/ru/yandex/qatools/htmlelements/examples/SimpleBlock.java"));
        HtmlElementTypeWrapper htmlElementTypeWrapper = new HtmlElementTypeWrapper();
        htmlElementTypeWrapper.setName("SimpleBlock");
        htmlElementTypeWrapper.setFindBy("xpath=/some/xpath");
        assertEquals(expected, htmlElementTypeWrapper.getSource());
    }
}