package com.xxd.annotation.target;

import com.xxd.annotation.target.domain.NoTargetAnnotation;
import com.xxd.annotation.target.domain.TypeAnnotation;
import com.xxd.annotation.target.domain.TypeParameterAnnotation;
import com.xxd.annotation.target.domain.TypeUseAnnotation;

@NoTargetAnnotation
@TypeAnnotation
@TypeUseAnnotation
public interface TargetInterface<@TypeParameterAnnotation @TypeUseAnnotation T> {
}
