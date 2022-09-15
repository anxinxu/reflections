package com.anxinxu.reflections.test;

public class TestJavaClass {

    private static String abc = "abc";

    public static String tag = "TestJavaClass" + abc;

    private static void test() {
        System.out.println("anxintag test");
    }

    private static void test1(String msg) {
        System.out.println("anxintag test1 " + msg);
    }

    private String objAbc = "inject";

    public TestJavaClass() {
        this("no params constructor");
    }

    private TestJavaClass(String objAbc) {
        this.objAbc = objAbc;
    }

    public String getObjAbc() {
        return objAbc;
    }

    private void testObjMethod() {
        System.out.println("anxintag testObjMethod");
    }
}
