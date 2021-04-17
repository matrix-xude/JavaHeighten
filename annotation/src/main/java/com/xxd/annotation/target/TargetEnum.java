package com.xxd.annotation.target;

import com.xxd.annotation.target.domain.FieldAnnotation;
import com.xxd.annotation.target.domain.NoTargetAnnotation;
import com.xxd.annotation.target.domain.TypeAnnotation;
import com.xxd.annotation.target.domain.TypeUseAnnotation;

// enum上可使用3个
@NoTargetAnnotation
@TypeAnnotation
@TypeUseAnnotation
enum TargetEnum {

    // 与普通成员变量类似，3个
    @NoTargetAnnotation
    @FieldAnnotation
    @TypeUseAnnotation
    T1,
    T2,
    T3
}
