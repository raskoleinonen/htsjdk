package htsjdk.variant.vcf;

import htsjdk.HtsjdkTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by farjoun on 11/26/17.
 */
public class VCFFileReaderTest extends HtsjdkTest{

    private static final File TEST_DATA_DIR = new File("src/test/resources/htsjdk/variant/");

    @DataProvider(name="queryableData")
    public Iterator<Object[]> queryableData() throws IOException {
        List<Object[]> tests = new ArrayList<>();
        tests.add(new Object[]{new File(TEST_DATA_DIR, "NA12891.fp.vcf"), false});
        tests.add(new Object[]{new File(TEST_DATA_DIR, "NA12891.vcf"), false});
        tests.add(new Object[]{VCFUtils.createTemporaryIndexedVcfFromInput(new File(TEST_DATA_DIR, "NA12891.vcf"), "fingerprintcheckertest.tmp."), true});
        tests.add(new Object[]{VCFUtils.createTemporaryIndexedVcfFromInput(new File(TEST_DATA_DIR, "NA12891.vcf.gz"), "fingerprintcheckertest.tmp."), true});

        return tests.iterator();
    }

    @Test(dataProvider = "queryableData")
    public void testIsQueriable(final File vcf, final boolean expectedQueryable) throws Exception {
        Assert.assertEquals(new VCFFileReader(vcf,false ).isQueriable(), expectedQueryable);
    }
}