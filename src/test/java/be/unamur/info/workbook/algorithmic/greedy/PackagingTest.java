package be.unamur.info.workbook.algorithmic.greedy;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PackagingTest {

    private static final Logger LOG = LoggerFactory.getLogger(PackagingTest.class);

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            LOG.info(String.format("Starting test: %s()...",
                    description.getMethodName()));
        }
    ;

    };

    @Test
    public void testPackaging() {
        double[] weights = new double[]{9.0, 7.0, 3.0, 2.0, 1.0};
        double capacity = 10.0;
        Packaging packaging = new Packaging(weights, capacity);
        // No optimal solution is guaranteed by spec.: in this case, test is best effort.
        int packagesCount = packaging.packagesCount();
        assertThat("Wrong number of packages!", packagesCount, lessThanOrEqualTo(weights.length));
        for (int i = 0; i < weights.length; i++) {
            assertThat("Box " + i + " has an invalid package index (above number of packages)!", packaging.getPackageIndex(i), lessThanOrEqualTo(packagesCount));
            assertThat("Box " + i + " has an invalid package index (below 1)!", packaging.getPackageIndex(i), greaterThan(0));
        }
    }
    
    @Test
    public void testPackagingFitsExactly() {
        double[] weights = new double[]{10.0, 10.0, 10.0, 10.0, 10.0};
        double capacity = 10.0;
        Packaging packaging = new Packaging(weights, capacity);
        assertThat("Wrong number of packages!", packaging.packagesCount(), equalTo(weights.length));
        Set<Integer> indexes = Sets.newHashSet();
        for (int i = 0; i < weights.length; i++) {
            indexes.add(packaging.getPackageIndex(i));
            assertThat("Box " + i + " has an invalid package index (above number of packages)!", packaging.getPackageIndex(i), lessThanOrEqualTo(weights.length));
            assertThat("Box " + i + " has an invalid package index (below 1)!", packaging.getPackageIndex(i), greaterThan(0));
        }
        // Check that all the indexes are unique
        assertThat(indexes.size(), equalTo(weights.length));
    }

}
