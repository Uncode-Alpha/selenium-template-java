package factory.dataprovider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataProviderFactory {
    String range() default "";
    String fileName() default "";
    String sheetName() default "";
    TYPE_OF_REQUEST typeOfRequest();
}
