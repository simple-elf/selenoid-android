package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTests.class,
        GetStartedTest.class,
        MyListsTests.class,
        SearchTests.class
})

@RunWith(Suite.class)
public class TestSuite {

}
