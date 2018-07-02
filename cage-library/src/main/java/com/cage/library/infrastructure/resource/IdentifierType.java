package com.cage.library.infrastructure.resource;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by luyunfeng on 16/12/1.
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({"id", "layout", "drawable", "string"})
public @interface IdentifierType {
}
