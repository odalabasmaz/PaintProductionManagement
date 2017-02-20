package tr.com.ppm.desktop.view;


import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Orhun Dalabasmaz
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface FXMLView {
	String value() default "";
}
