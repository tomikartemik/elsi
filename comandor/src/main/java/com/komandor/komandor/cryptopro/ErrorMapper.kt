package com.komandor.komandor.cryptopro

import ru.CryptoPro.JCSP.CSPConfig

val cryptoProErrorMapper: HashMap<Int, String> = hashMapOf<Int, String>(
    CSPConfig.CSP_INIT_OK to "Ok",

    CSPConfig.CSP_INIT_CONTEXT to "Couldn't initialize context.",
    CSPConfig.CSP_INIT_CREATE_INFRASTRUCTURE to "Couldn't create CSP infrastructure.",
    CSPConfig.CSP_INIT_COPY_RESOURCES to "Couldn't copy CSP resources.",

    CSPConfig.CSP_INIT_CHANGE_WORK_DIR to "Couldn't change CSP working directory.",
    CSPConfig.CSP_INIT_INVALID_LICENSE to "Invalid CSP serial number.",

    CSPConfig.CSP_TRUST_STORE_FAILED to "Couldn't create trust store for CAdES API.",
    CSPConfig.CSP_STORE_LIBRARY_PATH to "Couldn't store native library path to config.",
    CSPConfig.CSP_INIT_INVALID_INTEGRITY to "Integrity control failure.",
)