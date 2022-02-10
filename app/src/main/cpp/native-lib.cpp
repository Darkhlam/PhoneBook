#include <jni.h>
#include <string>

using namespace std;

extern "C" __attribute__((unused)) JNIEXPORT jstring JNICALL
Java_com_test_phonebook_MainActivity_contactsFromJNI(JNIEnv* env, jobject /* this */) {
    string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}