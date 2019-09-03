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

#ifndef J4A__tv_danmaku_ijk_media_player_misc_IMediaDataSource__H
#define J4A__tv_danmaku_ijk_media_player_misc_IMediaDataSource__H

#include "j4a/j4a_base.h"

jint J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__readAt(JNIEnv *env, jobject thiz, jlong position, jbyteArray buffer, jint offset, jint size);
jint J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__readAt__catchAll(JNIEnv *env, jobject thiz, jlong position, jbyteArray buffer, jint offset, jint size);
jlong J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__getSize(JNIEnv *env, jobject thiz);
jlong J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__getSize__catchAll(JNIEnv *env, jobject thiz);
void J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__close(JNIEnv *env, jobject thiz);
void J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__close__catchAll(JNIEnv *env, jobject thiz);
int J4A_loadClass__J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource(JNIEnv *env);

#define J4A_HAVE_SIMPLE__J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource

#define J4AC_IMediaDataSource__readAt J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__readAt
#define J4AC_IMediaDataSource__readAt__catchAll J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__readAt__catchAll
#define J4AC_IMediaDataSource__getSize J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__getSize
#define J4AC_IMediaDataSource__getSize__catchAll J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__getSize__catchAll
#define J4AC_IMediaDataSource__close J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__close
#define J4AC_IMediaDataSource__close__catchAll J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource__close__catchAll
#define J4A_loadClass__J4AC_IMediaDataSource J4A_loadClass__J4AC_tv_danmaku_ijk_media_player_misc_IMediaDataSource

#endif//J4A__tv_danmaku_ijk_media_player_misc_IMediaDataSource__H
