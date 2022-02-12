#include <jni.h>
#include <string>
#include <vector>

class Contact {
public:

    std::string name;
    std::string number;

    Contact(const std::string &name, const std::string &number) {
        this->name = name;
        this->number = number;
    }

    std::string getContactString() const {
        std::string str =
                "{\n\"name\":\"" + this->name + "\",\n\"number\":\"" + this->number + "\"\n}";
        return str;
    }
};


std::vector<std::string> createNames();
std::string createNumber();
static std::vector<Contact> createContacts() noexcept;
std::string jstring2string(JNIEnv *env, jstring jStr);
std::string getJsonContactString(std::vector<Contact>);

static std::vector<Contact> contacts = createContacts();

extern "C" JNIEXPORT jstring JNICALL
Java_com_test_phonebook_MainActivity_contactListFromJNI(JNIEnv *env, jobject  /* this */) {
    return env->NewStringUTF(getJsonContactString(contacts).c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_test_phonebook_MainActivity_contactSortFromJNI(JNIEnv *env, jobject  /* this */, jstring findName) {
    std::vector<Contact> sortContacts;
    std::string name = jstring2string(env,findName);
    for (auto &contact:contacts) {
        if (contact.name.find(name)!= std::string::npos)
            sortContacts.emplace_back(contact);
    }
    return env->NewStringUTF(getJsonContactString(sortContacts).c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_test_phonebook_MainActivity_addContactsFromJNI(JNIEnv *env, jobject  /* this */,
                                                        jstring name, jstring number) {
    contacts.emplace_back(jstring2string(env, name), jstring2string(env, number));
}

std::vector<std::string> createNames() {

    std::vector<std::string> names = {"Антон",
                                      "Борис",
                                      "Виталий",
                                      "Григорий",
                                      "Дмитрий"};
    std::vector<std::string> surnames = {"Арапов",
                                         "Беляев",
                                         "Волов",
                                         "Гончаров",
                                         "Денисенков"};
    std::vector<std::string> fullName;

    for (auto &name : names) {
        for (auto &surname : surnames) {
            fullName.emplace_back(name + " " + surname);
        }
    }
    return fullName;
}

std::string createNumber() {
    std::string buf = "89";

    for (int j = 0; j < 9; ++j) {
        buf += std::to_string(rand() % (10));
    }

    return buf;
}

static std::vector<Contact> createContacts() noexcept {
    std::vector<Contact> contacts;
    for (auto &name : createNames()) {
        contacts.emplace_back(name, createNumber());
    }
    return contacts;
}

std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes,
                                                                       env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte *pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *) pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

std::string getJsonContactString(std::vector<Contact> contacts) {
    std::string fContactString = "{\"contacts\":[\n";
    for (auto &contact:contacts) {
        fContactString += contact.getContactString() + ",\n\n";
    }
    fContactString.substr(0, fContactString.size() - 3);
    fContactString += "\n]\n}";
    return fContactString;
}