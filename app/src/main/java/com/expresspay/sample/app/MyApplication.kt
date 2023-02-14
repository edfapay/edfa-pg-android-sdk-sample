/*
 * Property of Expresspay (https://expresspay.sa).
 */

package com.expresspay.sample.app

import android.app.Application
import android.content.pm.ApplicationInfo
import android.webkit.WebView
import com.expresspay.sdk.core.ExpresspaySdk
import io.kimo.lib.faker.BuildConfig
import io.kimo.lib.faker.Faker


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Faker.with(this)
        ExpresspaySdk.init(
            this,
            "b5abdab4-5c46-11ed-a7be-8e03e789c25f",
            "cdb715a1b482b2af375785d70e8005cd",
            "https://api.expresspay.sa/post"
        )

        if(BuildConfig.DEBUG)
            if (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
                WebView.setWebContentsDebuggingEnabled(true)
            }
    }
}
