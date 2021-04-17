package com.xxd.annotation.target;

import com.xxd.annotation.target.domain.AnnotationTypeAnnotation;
import com.xxd.annotation.target.domain.NoTargetAnnotation;
import com.xxd.annotation.target.domain.TypeAnnotation;

// 注解上可以使用3个
@NoTargetAnnotation
@TypeAnnotation
@AnnotationTypeAnnotation
public @interface TargetAnnotation {
}
