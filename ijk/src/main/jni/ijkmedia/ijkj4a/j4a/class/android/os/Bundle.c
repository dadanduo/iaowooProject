/*
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * https://github.com/Bilibili/jni4android
 * This file is automatically generated by jni4android, do not modify.
 */

#include "Bundle.h"

typedef struct J4AC_android_os_Bundle {
    jclass id;

    jmethodID constructor_Bundle;
    jmethodID method_getInt;
    jmethodID method_putInt;
    jmethodID method_getString;
    jmethodID method_putString;
    jmethodID method_putParcelableArrayList;
    jmethodID method_getLong;
    jmethodID method_putLong;
} J4AC_android_os_Bundle;
static J4AC_android_os_Bundle class_J4AC_android_os_Bundle;

jobject J4AC_android_os_Bundle__Bundle(JNIEnv *env)
{
    return (*env)->NewObject(env, class_J4AC_android_os_Bundle.id, class_J4AC_android_os_Bundle.constructor_Bundle);
}

jobject J4AC_android_os_Bundle__Bundle__catchAll(JNIEnv *env)
{
    jobject ret_object = J4AC_android_os_Bundle__Bundle(env);
    if (J4A_ExceptionCheck__catchAll(env) || !ret_object) {
        return NULL;
    }

    return ret_object;
}

jobject J4AC_android_os_Bundle__Bundle__asGlobalRef__catchAll(JNIEnv *env)
{
    jobject ret_object   = NULL;
    jobject local_object = J4AC_android_os_Bundle__Bundle__catchAll(env);
    if (J4A_ExceptionCheck__catchAll(env) || !local_object) {
        ret_object = NULL;
        goto fail;
    }

    ret_object = J4A_NewGlobalRef__catchAll(env, local_object);
    if (!ret_object) {
        ret_object = NULL;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &local_object);
    return ret_object;
}

jint J4AC_android_os_Bundle__getInt(JNIEnv *env, jobject thiz, jstring key, jint defaultValue)
{
    return (*env)->CallIntMethod(env, thiz, class_J4AC_android_os_Bundle.method_getInt, key, defaultValue);
}

jint J4AC_android_os_Bundle__getInt__catchAll(JNIEnv *env, jobject thiz, jstring key, jint defaultValue)
{
    jint ret_value = J4AC_android_os_Bundle__getInt(env, thiz, key, defaultValue);
    if (J4A_ExceptionCheck__catchAll(env)) {
        return 0;
    }

    return ret_value;
}

jint J4AC_android_os_Bundle__getInt__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__, jint defaultValue)
{
    jint ret_value = 0;
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;

    ret_value = J4AC_android_os_Bundle__getInt(env, thiz, key, defaultValue);
    if (J4A_ExceptionCheck__throwAny(env)) {
        ret_value = 0;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &key);
    return ret_value;
}

jint J4AC_android_os_Bundle__getInt__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__, jint defaultValue)
{
    jint ret_value = 0;
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;

    ret_value = J4AC_android_os_Bundle__getInt__catchAll(env, thiz, key, defaultValue);
    if (J4A_ExceptionCheck__catchAll(env)) {
        ret_value = 0;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &key);
    return ret_value;
}

void J4AC_android_os_Bundle__putInt(JNIEnv *env, jobject thiz, jstring key, jint value)
{
    (*env)->CallVoidMethod(env, thiz, class_J4AC_android_os_Bundle.method_putInt, key, value);
}

void J4AC_android_os_Bundle__putInt__catchAll(JNIEnv *env, jobject thiz, jstring key, jint value)
{
    J4AC_android_os_Bundle__putInt(env, thiz, key, value);
    J4A_ExceptionCheck__catchAll(env);
}

void J4AC_android_os_Bundle__putInt__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__, jint value)
{
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;

    J4AC_android_os_Bundle__putInt(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
}

void J4AC_android_os_Bundle__putInt__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__, jint value)
{
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;

    J4AC_android_os_Bundle__putInt__catchAll(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
}

jstring J4AC_android_os_Bundle__getString(JNIEnv *env, jobject thiz, jstring key)
{
    return (*env)->CallObjectMethod(env, thiz, class_J4AC_android_os_Bundle.method_getString, key);
}

jstring J4AC_android_os_Bundle__getString__catchAll(JNIEnv *env, jobject thiz, jstring key)
{
    jstring ret_object = J4AC_android_os_Bundle__getString(env, thiz, key);
    if (J4A_ExceptionCheck__catchAll(env) || !ret_object) {
        return NULL;
    }

    return ret_object;
}

jstring J4AC_android_os_Bundle__getString__asGlobalRef__catchAll(JNIEnv *env, jobject thiz, jstring key)
{
    jstring ret_object   = NULL;
    jstring local_object = J4AC_android_os_Bundle__getString__catchAll(env, thiz, key);
    if (J4A_ExceptionCheck__catchAll(env) || !local_object) {
        ret_object = NULL;
        goto fail;
    }

    ret_object = J4A_NewGlobalRef__catchAll(env, local_object);
    if (!ret_object) {
        ret_object = NULL;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &local_object);
    return ret_object;
}

const char *J4AC_android_os_Bundle__getString__asCBuffer(JNIEnv *env, jobject thiz, jstring key, char *out_buf, int out_len)
{
    const char *ret_value = NULL;
    const char *c_str     = NULL;
    jstring local_string = J4AC_android_os_Bundle__getString(env, thiz, key);
    if (J4A_ExceptionCheck__throwAny(env) || !local_string) {
        goto fail;
    }

    c_str = (*env)->GetStringUTFChars(env, local_string, NULL );
    if (J4A_ExceptionCheck__throwAny(env) || !c_str) {
        goto fail;
    }

    strlcpy(out_buf, c_str, out_len);
    ret_value = out_buf;

fail:
    J4A_ReleaseStringUTFChars__p(env, local_string, &c_str);
    J4A_DeleteLocalRef__p(env, &local_string);
    return ret_value;
}

const char *J4AC_android_os_Bundle__getString__asCBuffer__catchAll(JNIEnv *env, jobject thiz, jstring key, char *out_buf, int out_len)
{
    const char *ret_value = NULL;
    const char *c_str     = NULL;
    jstring local_string = J4AC_android_os_Bundle__getString__catchAll(env, thiz, key);
    if (J4A_ExceptionCheck__catchAll(env) || !local_string) {
        goto fail;
    }

    c_str = (*env)->GetStringUTFChars(env, local_string, NULL );
    if (J4A_ExceptionCheck__catchAll(env) || !c_str) {
        goto fail;
    }

    strlcpy(out_buf, c_str, out_len);
    ret_value = out_buf;

fail:
    J4A_ReleaseStringUTFChars__p(env, local_string, &c_str);
    J4A_DeleteLocalRef__p(env, &local_string);
    return ret_value;
}

jstring J4AC_android_os_Bundle__getString__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__)
{
    jstring ret_object = NULL;
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;

    ret_object = J4AC_android_os_Bundle__getString(env, thiz, key);
    if (J4A_ExceptionCheck__throwAny(env) || !ret_object) {
        ret_object = NULL;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &key);
    return ret_object;
}

jstring J4AC_android_os_Bundle__getString__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__)
{
    jstring ret_object = NULL;
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;

    ret_object = J4AC_android_os_Bundle__getString__catchAll(env, thiz, key);
    if (J4A_ExceptionCheck__catchAll(env) || !ret_object) {
        ret_object = NULL;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &key);
    return ret_object;
}

jstring J4AC_android_os_Bundle__getString__withCString__asGlobalRef__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__)
{
    jstring ret_object   = NULL;
    jstring local_object = J4AC_android_os_Bundle__getString__withCString__catchAll(env, thiz, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !local_object) {
        ret_object = NULL;
        goto fail;
    }

    ret_object = J4A_NewGlobalRef__catchAll(env, local_object);
    if (!ret_object) {
        ret_object = NULL;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &local_object);
    return ret_object;
}

const char *J4AC_android_os_Bundle__getString__withCString__asCBuffer(JNIEnv *env, jobject thiz, const char *key_cstr__, char *out_buf, int out_len)
{
    const char *ret_value = NULL;
    const char *c_str     = NULL;
    jstring local_string = J4AC_android_os_Bundle__getString__withCString(env, thiz, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !local_string) {
        goto fail;
    }

    c_str = (*env)->GetStringUTFChars(env, local_string, NULL );
    if (J4A_ExceptionCheck__throwAny(env) || !c_str) {
        goto fail;
    }

    strlcpy(out_buf, c_str, out_len);
    ret_value = out_buf;

fail:
    J4A_ReleaseStringUTFChars__p(env, local_string, &c_str);
    J4A_DeleteLocalRef__p(env, &local_string);
    return ret_value;
}

const char *J4AC_android_os_Bundle__getString__withCString__asCBuffer__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__, char *out_buf, int out_len)
{
    const char *ret_value = NULL;
    const char *c_str     = NULL;
    jstring local_string = J4AC_android_os_Bundle__getString__withCString__catchAll(env, thiz, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !local_string) {
        goto fail;
    }

    c_str = (*env)->GetStringUTFChars(env, local_string, NULL );
    if (J4A_ExceptionCheck__catchAll(env) || !c_str) {
        goto fail;
    }

    strlcpy(out_buf, c_str, out_len);
    ret_value = out_buf;

fail:
    J4A_ReleaseStringUTFChars__p(env, local_string, &c_str);
    J4A_DeleteLocalRef__p(env, &local_string);
    return ret_value;
}

void J4AC_android_os_Bundle__putString(JNIEnv *env, jobject thiz, jstring key, jstring value)
{
    (*env)->CallVoidMethod(env, thiz, class_J4AC_android_os_Bundle.method_putString, key, value);
}

void J4AC_android_os_Bundle__putString__catchAll(JNIEnv *env, jobject thiz, jstring key, jstring value)
{
    J4AC_android_os_Bundle__putString(env, thiz, key, value);
    J4A_ExceptionCheck__catchAll(env);
}

void J4AC_android_os_Bundle__putString__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__, const char *value_cstr__)
{
    jstring key = NULL;
    jstring value = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;
    value = (*env)->NewStringUTF(env, value_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !value)
        goto fail;

    J4AC_android_os_Bundle__putString(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
    J4A_DeleteLocalRef__p(env, &value);
}

void J4AC_android_os_Bundle__putString__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__, const char *value_cstr__)
{
    jstring key = NULL;
    jstring value = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;
    value = (*env)->NewStringUTF(env, value_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !value)
        goto fail;

    J4AC_android_os_Bundle__putString__catchAll(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
    J4A_DeleteLocalRef__p(env, &value);
}

void J4AC_android_os_Bundle__putParcelableArrayList(JNIEnv *env, jobject thiz, jstring key, jobject value)
{
    (*env)->CallVoidMethod(env, thiz, class_J4AC_android_os_Bundle.method_putParcelableArrayList, key, value);
}

void J4AC_android_os_Bundle__putParcelableArrayList__catchAll(JNIEnv *env, jobject thiz, jstring key, jobject value)
{
    J4AC_android_os_Bundle__putParcelableArrayList(env, thiz, key, value);
    J4A_ExceptionCheck__catchAll(env);
}

void J4AC_android_os_Bundle__putParcelableArrayList__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__, jobject value)
{
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;

    J4AC_android_os_Bundle__putParcelableArrayList(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
}

void J4AC_android_os_Bundle__putParcelableArrayList__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__, jobject value)
{
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;

    J4AC_android_os_Bundle__putParcelableArrayList__catchAll(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
}

jlong J4AC_android_os_Bundle__getLong(JNIEnv *env, jobject thiz, jstring key)
{
    return (*env)->CallLongMethod(env, thiz, class_J4AC_android_os_Bundle.method_getLong, key);
}

jlong J4AC_android_os_Bundle__getLong__catchAll(JNIEnv *env, jobject thiz, jstring key)
{
    jlong ret_value = J4AC_android_os_Bundle__getLong(env, thiz, key);
    if (J4A_ExceptionCheck__catchAll(env)) {
        return 0;
    }

    return ret_value;
}

jlong J4AC_android_os_Bundle__getLong__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__)
{
    jlong ret_value = 0;
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;

    ret_value = J4AC_android_os_Bundle__getLong(env, thiz, key);
    if (J4A_ExceptionCheck__throwAny(env)) {
        ret_value = 0;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &key);
    return ret_value;
}

jlong J4AC_android_os_Bundle__getLong__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__)
{
    jlong ret_value = 0;
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;

    ret_value = J4AC_android_os_Bundle__getLong__catchAll(env, thiz, key);
    if (J4A_ExceptionCheck__catchAll(env)) {
        ret_value = 0;
        goto fail;
    }

fail:
    J4A_DeleteLocalRef__p(env, &key);
    return ret_value;
}

void J4AC_android_os_Bundle__putLong(JNIEnv *env, jobject thiz, jstring key, jlong value)
{
    (*env)->CallVoidMethod(env, thiz, class_J4AC_android_os_Bundle.method_putLong, key, value);
}

void J4AC_android_os_Bundle__putLong__catchAll(JNIEnv *env, jobject thiz, jstring key, jlong value)
{
    J4AC_android_os_Bundle__putLong(env, thiz, key, value);
    J4A_ExceptionCheck__catchAll(env);
}

void J4AC_android_os_Bundle__putLong__withCString(JNIEnv *env, jobject thiz, const char *key_cstr__, jlong value)
{
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__throwAny(env) || !key)
        goto fail;

    J4AC_android_os_Bundle__putLong(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
}

void J4AC_android_os_Bundle__putLong__withCString__catchAll(JNIEnv *env, jobject thiz, const char *key_cstr__, jlong value)
{
    jstring key = NULL;

    key = (*env)->NewStringUTF(env, key_cstr__);
    if (J4A_ExceptionCheck__catchAll(env) || !key)
        goto fail;

    J4AC_android_os_Bundle__putLong__catchAll(env, thiz, key, value);

fail:
    J4A_DeleteLocalRef__p(env, &key);
}

int J4A_loadClass__J4AC_android_os_Bundle(JNIEnv *env)
{
    int         ret                   = -1;
    const char *J4A_UNUSED(name)      = NULL;
    const char *J4A_UNUSED(sign)      = NULL;
    jclass      J4A_UNUSED(class_id)  = NULL;
    int         J4A_UNUSED(api_level) = 0;

    if (class_J4AC_android_os_Bundle.id != NULL)
        return 0;

    sign = "android/os/Bundle";
    class_J4AC_android_os_Bundle.id = J4A_FindClass__asGlobalRef__catchAll(env, sign);
    if (class_J4AC_android_os_Bundle.id == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "<init>";
    sign     = "()V";
    class_J4AC_android_os_Bundle.constructor_Bundle = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.constructor_Bundle == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "getInt";
    sign     = "(Ljava/lang/String;I)I";
    class_J4AC_android_os_Bundle.method_getInt = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_getInt == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "putInt";
    sign     = "(Ljava/lang/String;I)V";
    class_J4AC_android_os_Bundle.method_putInt = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_putInt == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "getString";
    sign     = "(Ljava/lang/String;)Ljava/lang/String;";
    class_J4AC_android_os_Bundle.method_getString = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_getString == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "putString";
    sign     = "(Ljava/lang/String;Ljava/lang/String;)V";
    class_J4AC_android_os_Bundle.method_putString = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_putString == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "putParcelableArrayList";
    sign     = "(Ljava/lang/String;Ljava/util/ArrayList;)V";
    class_J4AC_android_os_Bundle.method_putParcelableArrayList = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_putParcelableArrayList == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "getLong";
    sign     = "(Ljava/lang/String;)J";
    class_J4AC_android_os_Bundle.method_getLong = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_getLong == NULL)
        goto fail;

    class_id = class_J4AC_android_os_Bundle.id;
    name     = "putLong";
    sign     = "(Ljava/lang/String;J)V";
    class_J4AC_android_os_Bundle.method_putLong = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_android_os_Bundle.method_putLong == NULL)
        goto fail;

    J4A_ALOGD("J4ALoader: OK: '%s' loaded\n", "android.os.Bundle");
    ret = 0;
fail:
    return ret;
}
