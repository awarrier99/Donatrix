package edu.gatech.donatrix;

import org.junit.Test;

import static edu.gatech.donatrix.controllers.DataValidation.isPasswordStrong;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PasswordValidationTest {

    @Test
    public void isPasswordStrong_test_length() {
        assertTrue("test_short", !isPasswordStrong("Aa0")); //Too short
        assertTrue("test_long", isPasswordStrong("Aa0abcassjhjkj")); //long
        assertTrue("test_bc-1", !isPasswordStrong("Aa0aksj")); //Boundary -1
        assertTrue("test_bc", isPasswordStrong("Aa01ks@s")); //Boundary
        assertTrue("test_bc+1", isPasswordStrong("Aa01ks^43")); //Bounday +1
    }

    @Test
    public void isPasswordStrong_test_containsUpper() {
        assertTrue("test_0",!isPasswordStrong("daaadfbab1234jfjbq")); //no Cap
        assertTrue("test_begin", isPasswordStrong("Aaaadfbab90kjfjbq")); //1 Cap begin
        assertTrue("test_middle", isPasswordStrong("aaadfbaG2lkj00jbq")); //1 Cap middle
        assertTrue("test_end", isPasswordStrong("caaa12skdnafjbB")); //1 Cap end
        assertTrue("test_multi", isPasswordStrong("Aaaa16CablkjFOjbq")); //1+ Caps
    }

    @Test
    public void isPasswordStrong_test_containsLower() {
        assertTrue("test_0", !isPasswordStrong("123AFJF76^%")); //0 lower
        assertTrue("test_begin", isPasswordStrong("a123FJ54F76^%")); //1 lower begin
        assertTrue("test_middle", isPasswordStrong(")!3AFJo76ASDF")); //1 lower middle
        assertTrue("test_end", isPasswordStrong("12AKW$#^HH6^%k")); //1 lower end
        assertTrue("test_multi", isPasswordStrong("aSFKN0N#(@fd!")); //1+ lowers
    }

    @Test
    public void isPasswordStrong_test_containsNum() {
        assertTrue("test_0", !isPasswordStrong("askdjAKKFDF!")); //0 num
        assertTrue("test_begin", isPasswordStrong("0asdkGJDL()")); //1 num begin
        assertTrue("test_middle", isPasswordStrong("asdkajd5%#GHD")); //1 num middle
        assertTrue("test_end", isPasswordStrong("AKSKskfn62@#%9")); //1 num end
        assertTrue("test_multi", isPasswordStrong("12aksjFKFNAD%^#sada12")); //1+ nums
    }

}