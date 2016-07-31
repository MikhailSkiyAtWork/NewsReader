package mikhailskiy.com.newsreader.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Mikhail on 01.08.16.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}