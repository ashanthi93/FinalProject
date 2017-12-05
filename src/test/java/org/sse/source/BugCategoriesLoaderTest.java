package org.sse.source;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BugCategoriesLoaderTest {
    @Test
    public void testGetVersionName() throws Exception {

        System.out.println(BugCategoriesLoader.getVersionName());

    }

}