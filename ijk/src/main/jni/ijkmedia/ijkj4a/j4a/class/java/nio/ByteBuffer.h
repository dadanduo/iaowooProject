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

#ifndef J4A__java_nio_ByteBuffer__H
#define J4A__java_nio_ByteBuffer__H

#include "j4a/j4a_base.h"

jobject J4AC_java_nio_ByteBuffer__allocate(JNIEnv *env, jint capacity);
jobject J4AC_java_nio_ByteBuffer__allocate__catchAll(JNIEnv *env, jint capacity);
jobject J4AC_java_nio_ByteBuffer__allocate__asGlobalRef__catchAll(JNIEnv *env, jint capacity);
jobject J4AC_java_nio_ByteBuffer__allocateDirect(JNIEnv *env, jint capacity);
jobject J4AC_java_nio_ByteBuffer__allocateDirect__catchAll(JNIEnv *env, jint capacity);
jobject J4AC_java_nio_ByteBuffer__allocateDirect__asGlobalRef__catchAll(JNIEnv *env, jint capacity);
jobject J4AC_java_nio_ByteBuffer__limit(JNIEnv *env, jobject thiz, jint newLimit);
jobject J4AC_java_nio_ByteBuffer__limit__catchAll(JNIEnv *env, jobject thiz, jint newLimit);
jobject J4AC_java_nio_ByteBuffer__limit__asGlobalRef__catchAll(JNIEnv *env, jobject thiz, jint newLimit);
int J4A_loadClass__J4AC_java_nio_ByteBuffer(JNIEnv *env);

#define J4A_HAVE_SIMPLE__J4AC_java_nio_ByteBuffer

#define J4AC_ByteBuffer__allocate J4AC_java_nio_ByteBuffer__allocate
#define J4AC_ByteBuffer__allocate__asGlobalRef__catchAll J4AC_java_nio_ByteBuffer__allocate__asGlobalRef__catchAll
#define J4AC_ByteBuffer__allocate__catchAll J4AC_java_nio_ByteBuffer__allocate__catchAll
#define J4AC_ByteBuffer__allocateDirect J4AC_java_nio_ByteBuffer__allocateDirect
#define J4AC_ByteBuffer__allocateDirect__asGlobalRef__catchAll J4AC_java_nio_ByteBuffer__allocateDirect__asGlobalRef__catchAll
#define J4AC_ByteBuffer__allocateDirect__catchAll J4AC_java_nio_ByteBuffer__allocateDirect__catchAll
#define J4AC_ByteBuffer__limit J4AC_java_nio_ByteBuffer__limit
#define J4AC_ByteBuffer__limit__asGlobalRef__catchAll J4AC_java_nio_ByteBuffer__limit__asGlobalRef__catchAll
#define J4AC_ByteBuffer__limit__catchAll J4AC_java_nio_ByteBuffer__limit__catchAll
#define J4A_loadClass__J4AC_ByteBuffer J4A_loadClass__J4AC_java_nio_ByteBuffer

#endif//J4A__java_nio_ByteBuffer__H
