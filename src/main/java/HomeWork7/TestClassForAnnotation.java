package HomeWork7;

public class TestClassForAnnotation {


    @AfterSuit
    public void first(){
        System.out.println("This is first method");
    }

    @Test
    public void second(){
        System.out.println("This is second method");
    }

    @Test(priority = 7)
    public void third(){
        System.out.println("This is third method");
    }

    @Test(priority = 2)
    public void fourth(){
        System.out.println("This is fourth method");
    }

    @Test(priority = 9)
    public void fifth(){
        System.out.println("This is fifth method");
    }

    @Test(priority = 2)
    public void sixth(){
        System.out.println("This is sixth method");
    }

    @BeforeSuit
    public void seventh(){
        System.out.println("This is seventh method");
    }
}
