package com.example.dependencyinjection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.text.Typography.dagger

// STEP 2) DIApp을 `@HiltAndroidApp`로 어노테이션합니다.
@HiltAndroidApp
class DIApp : Application()

// dagger.hilt 에서, 의존성 주입을 하기 위해서는
// 애플리케이션 객체가 만들어져 있어야 합니다.