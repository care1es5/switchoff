package com.z3r0s.switchoff;


import java.lang.reflect.Array;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Set;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


public class SwitchOff implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.gemini.android.app")) {
            return;
        }

        findAndHookMethod("com.gantix.JailMonkey.JailMonkeyModule", lpparam.classLoader, "isJailBroken", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                param.setResult(false);

            }
        });


        findAndHookMethod("com.gantix.JailMonkey.JailMonkeyModule", lpparam.classLoader, "isSuperuserPresent", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("[*]isSuperuserPresentBefore:"+param.getResult().toString());

                param.setResult(false);

//                XposedBridge.log("[*]isSuperuserPresentAfter:"+param.getResult().toString());

            }
        });

        findAndHookMethod("com.gantix.JailMonkey.JailMonkeyModule", lpparam.classLoader, "checkRootMethod1", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

//                XposedBridge.log("[*]checkRootMethod1Before:"+param.getResult().toString());

                param.setResult(false);

//                XposedBridge.log("[*]checkRootMethod1After:"+param.getResult().toString());

            }
        });

        findAndHookMethod("com.gantix.JailMonkey.JailMonkeyModule", lpparam.classLoader, "checkRootMethod2", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("[*]checkRootMethod2Before:"+param.getResult().toString());
                param.setResult(false);
//                XposedBridge.log("[*]checkRootMethod2After:"+param.getResult().toString());
            }
        });


        findAndHookMethod("com.gantix.JailMonkey.JailMonkeyModule", lpparam.classLoader, "canExecuteCommand", String.class,new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("[*]Command:"+param.args[0].toString());
//                XposedBridge.log("[*]canExecuteCommandBefore:"+param.getResult().toString());
                param.setResult(false);
//                XposedBridge.log("[*]canExecuteCommandAfter:"+param.getResult().toString());
            }
        });

        //cert
        findAndHookMethod("com.android.org.conscrypt.TrustManagerImpl", lpparam.classLoader, "checkTrustedRecursive",X509Certificate[].class,String.class,boolean.class,ArrayList.class,ArrayList.class,Set.class,new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                ArrayList<X509Certificate> array = new ArrayList<X509Certificate>();

                param.setResult(array);

            }
        });


    }
}
