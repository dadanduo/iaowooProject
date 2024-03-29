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

#include "ArrayList.h"

typedef struct J4AC_java_util_ArrayList {
    jclass id;

    jmethodID constructor_ArrayList;
    jmethodID method_add;
} J4AC_java_util_ArrayList;
static J4AC_java_util_ArrayList class_J4AC_java_util_ArrayList;

jobject J4AC_java_util_ArrayList__ArrayList(JNIEnv *env)
{
    return (*env)->NewObject(env, class_J4AC_java_util_ArrayList.id, class_J4AC_java_util_ArrayList.constructor_ArrayList);
}

jobject J4AC_java_util_ArrayList__ArrayList__catchAll(JNIEnv *env)
{
    jobject ret_object = J4AC_java_util_ArrayList__ArrayList(env);
    if (J4A_ExceptionCheck__catchAll(env) || !ret_object) {
        return NULL;
    }

    return ret_object;
}

jobject J4AC_java_util_ArrayList__ArrayList__asGlobalRef__catchAll(JNIEnv *env)
{
    jobject ret_object   = NULL;
    jobject local_object = J4AC_java_util_ArrayList__ArrayList__catchAll(env);
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

jboolean J4AC_java_util_ArrayList__add(JNIEnv *env, jobject thiz, jobject object)
{
    return (*env)->CallBooleanMethod(env, thiz, class_J4AC_java_util_ArrayList.method_add, object);
}

jboolean J4AC_java_util_ArrayList__add__catchAll(JNIEnv *env, jobject thiz, jobject object)
{
    jboolean ret_value = J4AC_java_util_ArrayList__add(env, thiz, object);
    if (J4A_ExceptionCheck__catchAll(env)) {
        return false;
    }

    return ret_value;
}

int J4A_loadClass__J4AC_java_util_ArrayList(JNIEnv *env)
{
    int         ret                   = -1;
    const char *J4A_UNUSED(name)      = NULL;
    const char *J4A_UNUSED(sign)      = NULL;
    jclass      J4A_UNUSED(class_id)  = NULL;
    int         J4A_UNUSED(api_level) = 0;

    if (class_J4AC_java_util_ArrayList.id != NULL)
        return 0;

    sign = "java/util/ArrayList";
    class_J4AC_java_util_ArrayList.id = J4A_FindClass__asGlobalRef__catchAll(env, sign);
    if (class_J4AC_java_util_ArrayList.id == NULL)
        goto fail;

    class_id = class_J4AC_java_util_ArrayList.id;
    name     = "<init>";
    sign     = "()V";
    class_J4AC_java_util_ArrayList.constructor_ArrayList = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_java_util_ArrayList.constructor_ArrayList == NULL)
        goto fail;

    class_id = class_J4AC_java_util_ArrayList.id;
    name     = "add";
    sign     = "(Ljava/lang/Object;)Z";
    class_J4AC_java_util_ArrayList.method_add = J4A_GetMethodID__catchAll(env, class_id, name, sign);
    if (class_J4AC_java_util_ArrayList.method_add == NULL)
        goto fail;

    J4A_ALOGD("J4ALoader: OK: '%s' loaded\n", "java.util.ArrayList");
    ret = 0;
fail:
    return ret;
}
