package org.rivalry.core.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

/**
 * Provides tests for the <code>RivalryDataWriter</code> class.
 */
public class RivalryDataWriterTest
{
    /** Test data. */
    private final TestData _testData = new TestData();

    /**
     * Test the <code></code> method.
     */
    @Test
    public void test0()
    {
        final RivalryData rivalryData = _testData.createRivalryData();
        final RivalryDataWriter rivalryDataWriter = new RivalryDataWriter();
        final Writer writer = new StringWriter();
        rivalryDataWriter.write(rivalryData, writer);
        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n<java version=\"1.6.0_29\" class=\"java.beans.XMLDecoder\"> \n <object class=\"org.rivalry.core.model.RivalryData\"> \n  <void property=\"candidatesList\"> \n   <void method=\"add\"> \n    <object class=\"org.rivalry.core.model.DefaultCandidate\"> \n     <void property=\"name\"> \n      <string>1</string> \n     </void> \n     <void property=\"ratings\"> \n      <object class=\"java.util.LinkedHashMap\"> \n       <void method=\"put\"> \n        <object id=\"DefaultCriterion0\" class=\"org.rivalry.core.model.DefaultCriterion\"> \n         <void property=\"category\"> \n          <object id=\"DefaultCategory0\" class=\"org.rivalry.core.model.DefaultCategory\"> \n           <void property=\"name\"> \n            <string>A</string> \n           </void> \n          </object> \n         </void> \n         <void property=\"name\"> \n          <string>a</string> \n         </void> \n        </object> \n        <double>1.1</double> \n       </void> \n       <void method=\"put\"> \n        <object id=\"DefaultCriterion1\" class=\"org.rivalry.core.model.DefaultCriterion\"> \n         <void property=\"category\"> \n          <object id=\"DefaultCategory1\" class=\"org.rivalry.core.model.DefaultCategory\"> \n           <void property=\"name\"> \n            <string>B</string> \n           </void> \n          </object> \n         </void> \n         <void property=\"name\"> \n          <string>b</string> \n         </void> \n        </object> \n        <double>1.2</double> \n       </void> \n       <void method=\"put\"> \n        <object id=\"DefaultCriterion2\" class=\"org.rivalry.core.model.DefaultCriterion\"> \n         <void property=\"category\"> \n          <object id=\"DefaultCategory2\" class=\"org.rivalry.core.model.DefaultCategory\"> \n           <void property=\"name\"> \n            <string>C</string> \n           </void> \n          </object> \n         </void> \n         <void property=\"name\"> \n          <string>c</string> \n         </void> \n        </object> \n        <double>1.3</double> \n       </void> \n      </object> \n     </void> \n    </object> \n   </void> \n   <void method=\"add\"> \n    <object class=\"org.rivalry.core.model.DefaultCandidate\"> \n     <void property=\"name\"> \n      <string>2</string> \n     </void> \n     <void property=\"ratings\"> \n      <object class=\"java.util.LinkedHashMap\"> \n       <void method=\"put\"> \n        <object idref=\"DefaultCriterion0\"/> \n        <double>2.1</double> \n       </void> \n       <void method=\"put\"> \n        <object idref=\"DefaultCriterion1\"/> \n        <double>2.2</double> \n       </void> \n       <void method=\"put\"> \n        <object idref=\"DefaultCriterion2\"/> \n        <double>2.3</double> \n       </void> \n      </object> \n     </void> \n    </object> \n   </void> \n   <void method=\"add\"> \n    <object class=\"org.rivalry.core.model.DefaultCandidate\"> \n     <void property=\"name\"> \n      <string>3</string> \n     </void> \n     <void property=\"ratings\"> \n      <object class=\"java.util.LinkedHashMap\"> \n       <void method=\"put\"> \n        <object idref=\"DefaultCriterion0\"/> \n        <double>3.1</double> \n       </void> \n       <void method=\"put\"> \n        <object idref=\"DefaultCriterion1\"/> \n        <double>3.2</double> \n       </void> \n       <void method=\"put\"> \n        <object idref=\"DefaultCriterion2\"/> \n        <double>3.3</double> \n       </void> \n      </object> \n     </void> \n    </object> \n   </void> \n  </void> \n  <void property=\"categoriesList\"> \n   <void method=\"add\"> \n    <object idref=\"DefaultCategory0\"/> \n   </void> \n   <void method=\"add\"> \n    <object idref=\"DefaultCategory1\"/> \n   </void> \n   <void method=\"add\"> \n    <object idref=\"DefaultCategory2\"/> \n   </void> \n  </void> \n  <void property=\"criteriaList\"> \n   <void method=\"add\"> \n    <object idref=\"DefaultCriterion0\"/> \n   </void> \n   <void method=\"add\"> \n    <object idref=\"DefaultCriterion1\"/> \n   </void> \n   <void method=\"add\"> \n    <object idref=\"DefaultCriterion2\"/> \n   </void> \n  </void> \n </object> \n</java> \n";
        final String result = writer.toString();
        System.out.println("result =\n" + result);
        assertThat(result, is(expected));
    }
}