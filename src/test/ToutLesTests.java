package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestFacture.class, TestsMiseEnFormeFacture.class, TestTesterFormat.class })
public class ToutLesTests {

}
