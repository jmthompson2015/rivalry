#! /bin/bash

USER_DIR=/Users/jmthompson
JFAB_DIR=${USER_DIR}/Dropbox/SoftwareDev/JavaProjects/jfab/trunk/core
BASE_DIR=${USER_DIR}/Dropbox/SoftwareDev/JavaProjects/rivalry/core
LIB_DIR=${USER_DIR}/.m2/repository

INPUT_FILE=src/main/resources/RivalryModel.xml
OUTPUT_DIR=${BASE_DIR}/target/generated-sources/jfab

CP=${BASE_DIR}/../classes
CP=${OUTPUT_DIR}:${CP}
CP=${JFAB_DIR}/../classes:${CP}
CP=${LIB_DIR}/commons-cli/commons-cli/1.2/commons-cli-1.2.jar:${CP}
CP=${LIB_DIR}/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar:${CP}
CP=${LIB_DIR}/commons-io/commons-io/2.0.1/commons-io-2.0.1.jar:${CP}
CP=${LIB_DIR}/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:${CP}
CP=${LIB_DIR}/org/slf4j/slf4j-api/1.6.1/slf4j-api-1.6.1.jar:${CP}
CP=${LIB_DIR}/org/javaruntype/javaruntype/1.2/javaruntype-1.2.jar:${CP}
CP=${LIB_DIR}/org/antlr/antlr-runtime/3.1.2/antlr-runtime-3.1.2.jar:${CP}

rm -rf ${OUTPUT_DIR}/*

java -cp ${CP} org.jfab.core.JFabberMain -f src/main/resources/RivalryModel.xml -d ${OUTPUT_DIR}

