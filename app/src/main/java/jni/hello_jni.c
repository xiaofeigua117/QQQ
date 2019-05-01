//
// Created by 10188 on 2018/5/24.
//
#include "com_example_diy_HelloTest.h"
#include <jni.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/stat.h>
#include <sys/ioctl.h>
#include <unistd.h>
#include <stdlib.h>
JNIEXPORT jstring JNICALL Java_com_example_diy_HelloTest_getName
  (JNIEnv *env, jclass jobj){
  };


JNIEXPORT jint JNICALL Java_com_example_diy_HelloTest_getStart
 (JNIEnv *env, jclass jobj, jint a){

  int fd=0;
     fd = open("/dev/led",O_WRONLY);
     ioctl(fd,a);
     if(fd>0)
         close(fd);
 return a+1;
}

